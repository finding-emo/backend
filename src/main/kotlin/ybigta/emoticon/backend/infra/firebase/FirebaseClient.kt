package ybigta.emoticon.backend.infra.firebase

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.QueryDocumentSnapshot
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Service
import ybigta.emoticon.backend.infra.aws.SecretsManagerInfra

@Service
class FirebaseClient(
    secretsManagerInfra: SecretsManagerInfra,
) {
    private val db: Firestore

    init {
        val serviceAccount = secretsManagerInfra
            .getString("ybigta-emoticon/firebase-admin-sdk-key")
            .byteInputStream()
        val credentials = GoogleCredentials.fromStream(serviceAccount)
        val options = FirebaseOptions
            .builder()
            .setCredentials(credentials)
            .build()

        FirebaseApp.initializeApp(options)

        db = FirestoreClient.getFirestore()
    }

    fun listSurveys(): MutableList<QueryDocumentSnapshot> {
        val query = db.collection("surveys").get()
        return query.get().documents
    }
}
