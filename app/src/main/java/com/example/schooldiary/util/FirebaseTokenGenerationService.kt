package com.example.schooldiary.util

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessagingService

class FirebaseTokenGenerationService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {
        val email = FirebaseAuth.getInstance().currentUser?.email ?: return
        Log.d("Token from onNewToken", token)
        FirebaseFirestore.getInstance().collection("users")
                .document(email)
                .set(mapOf("fcmToken" to token))
    }
}