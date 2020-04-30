package com.example.schooldiary.base.viewmodel

import androidx.lifecycle.ViewModel
import com.example.schooldiary.data.Repository
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query

open class BaseViewModel : ViewModel() {
    val repository = Repository.getInstance()

    inline fun <reified T> getFirestoreRecyclerOptions(
            collectionName: String
    ): FirestoreRecyclerOptions<T> {
        return repository.getFirestoreRecyclerOptions(collectionName)
    }

    inline fun <reified T> getFirestoreRecyclerOptionsWithOrder(
            collectionName: String,
            order: String,
            queryDirection: Query.Direction = Query.Direction.ASCENDING
    ): FirestoreRecyclerOptions<T> {

        return repository.getFirestoreRecyclerOptionsWithOrder(
                collectionName,
                order,
                queryDirection)
    }
}