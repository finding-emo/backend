package ybigta.emoticon.backend.infra.openai

import com.theokanning.openai.completion.chat.ChatCompletionRequest
import com.theokanning.openai.completion.chat.ChatMessage
import com.theokanning.openai.completion.chat.ChatMessageRole
import com.theokanning.openai.service.OpenAiService
import org.springframework.stereotype.Service
import ybigta.emoticon.backend.infra.aws.SecretsManagerInfra


@Service
class OpenAiClient(
    secretsManagerInfra: SecretsManagerInfra,
) {
    private val service = OpenAiService(secretsManagerInfra.getString("ybigta-emoticon/open-ai-secret"))

    fun analyzeEmotion(text: String): String {
        val messages: MutableList<ChatMessage> = mutableListOf()

        val request = ChatMessage(
            ChatMessageRole.SYSTEM.value(),
            """
                Do not reply until the user has completed his or her sentence.
                There are multiple people talking in this chat room, with every user distinguished by different user names.
                Your role is to output the emotion that best identifies each user's message.
                Do not include other words in your output, only the emotion.
                There must be consistency in respective user's emotions.
                Make sure the emotions are in English.
                """.trimIndent(),
        )

        messages.add(request)

        messages.add(ChatMessage(ChatMessageRole.USER.value(), """User 1: 너를 체포하겠어!""""))
        messages.add(ChatMessage(ChatMessageRole.ASSISTANT.value(), "Anger, Threatening"))

        messages.add(ChatMessage(ChatMessageRole.USER.value(), """User 1: "내 마음을 훔친 죄로 너를 체포하겠다구!""""))
        messages.add(ChatMessage(ChatMessageRole.ASSISTANT.value(), "Playful Teasing"))

        messages.add(ChatMessage(ChatMessageRole.USER.value(), """User 1: "나 오늘""""))
        messages.add(ChatMessage(ChatMessageRole.ASSISTANT.value(), "Eagerness"))

        messages.add(ChatMessage(ChatMessageRole.USER.value(), """User 1: "꿍꼬또 귀신 꿍꼬또""""))
        messages.add(ChatMessage(ChatMessageRole.ASSISTANT.value(), "Playful Excitement"))

        messages.add(ChatMessage(ChatMessageRole.USER.value(), """User 1: "그래서 말인데""""))
        messages.add(ChatMessage(ChatMessageRole.ASSISTANT.value(), "Cautious Expectation"))

        messages.add(ChatMessage(ChatMessageRole.USER.value(), """User 1: "오늘 나랑 같이 자면 안될까~?""""))
        messages.add(ChatMessage(ChatMessageRole.ASSISTANT.value(), "Surprise"))

        messages.add(ChatMessage(ChatMessageRole.USER.value(), """User 2: "그러면 너무 좋겠당ㅎㅎ""""))
        messages.add(ChatMessage(ChatMessageRole.ASSISTANT.value(), "Happiness"))

        messages.add(ChatMessage(ChatMessageRole.USER.value(), """User 2: "오늘 저녁 먹으러 오니?""""))
        messages.add(ChatMessage(ChatMessageRole.ASSISTANT.value(), "Invitation, Inquisitive"))

        val userMessage = ChatMessage(ChatMessageRole.USER.value(), text)
        messages.add(userMessage)


        val chatCompletionRequest: ChatCompletionRequest = ChatCompletionRequest
            .builder()
            .model("gpt-3.5-turbo")
            .messages(messages)
            .temperature(1.5)
            .build()

        val responseMessage: ChatMessage = service.createChatCompletion(chatCompletionRequest).choices[0].message

        return responseMessage.content
    }
}
