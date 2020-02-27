package com.example.schooldiary.mainpage.ui;

import com.example.schooldiary.mainpage.common.BaseChangeEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

/**
 * Listener for changes to a {@link FirestoreArray}.
 */
public interface ChangeEventListener extends BaseChangeEventListener<DocumentSnapshot, FirebaseFirestoreException> {}
