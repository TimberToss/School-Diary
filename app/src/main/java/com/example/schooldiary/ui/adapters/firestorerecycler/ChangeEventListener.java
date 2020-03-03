package com.example.schooldiary.ui.adapters.firestorerecycler;

import com.example.schooldiary.common.BaseChangeEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

/**
 * Listener for changes to a {@link FirestoreArray}.
 */
public interface ChangeEventListener extends BaseChangeEventListener<DocumentSnapshot, FirebaseFirestoreException> {}
