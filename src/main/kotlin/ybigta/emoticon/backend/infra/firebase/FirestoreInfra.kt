package ybigta.emoticon.backend.infra.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ybigta.emoticon.backend.domain.survey.entity.Survey
import ybigta.emoticon.backend.infra.aws.SecretsManagerInfra

@Service
class FirestoreInfra(
    secretsManagerInfra: SecretsManagerInfra,
) {
    private val db: Firestore = run {
        val serviceAccount = secretsManagerInfra
            .getString("ybigta-emoticon/firebase-admin-sdk-key")
            .byteInputStream()
        val credentials = GoogleCredentials.fromStream(serviceAccount)
        val options = FirebaseOptions
            .builder()
            .setCredentials(credentials)
            .build()
        FirebaseApp.initializeApp(options)

        FirestoreClient.getFirestore()
    }

    fun getSurveyById(id: String): Survey {
        val query = db.collection("surveys").document(id).get()
        val map = query.get().data ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "설문조사가 존재하지 않습니다.")

        return Survey.fromMap(map)
    }
}
