package com.example.schooldiary.data

import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class Repository {
    inline fun <reified T> getFirestoreRecyclerOptions(
            collectionName: String
    ): FirestoreRecyclerOptions<T> {

        val firestore = FirebaseFirestore.getInstance()
        val query = firestore.collection(collectionName)
        return FirestoreRecyclerOptions.Builder<T>()
                .setQuery(query, T::class.java)
                .build()
    }

    inline fun <reified T> getFirestoreRecyclerOptionsWithOrder(
            collectionName: String,
            order: String,
            queryDirection: Query.Direction
    ): FirestoreRecyclerOptions<T> {

        val firestore = FirebaseFirestore.getInstance()
        val query = firestore.collection(collectionName)
                .orderBy(order, queryDirection)
        return FirestoreRecyclerOptions.Builder<T>()
                .setQuery(query, T::class.java)
                .build()
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: Repository().also { instance = it }
                }
    }
}