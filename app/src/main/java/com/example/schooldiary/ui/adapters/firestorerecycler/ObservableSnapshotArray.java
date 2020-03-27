package com.example.schooldiary.ui.adapters.firestorerecycler;

import androidx.annotation.NonNull;

import com.example.schooldiary.common.BaseCachingSnapshotParser;
import com.example.schooldiary.common.BaseObservableSnapshotArray;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

/**
 * Subclass of {@link BaseObservableSnapshotArray} for Firestore data.
 */
public abstract class ObservableSnapshotArray<T>
        extends BaseObservableSnapshotArray<DocumentSnapshot, FirebaseFirestoreException, ChangeEventListener, T> {
    /**
     * @see BaseObservableSnapshotArray#BaseObservableSnapshotArray(BaseCachingSnapshotParser)
     */
    public ObservableSnapshotArray(@NonNull SnapshotParser<T> parser) {
        super(new CachingSnapshotParser<>(parser));
    }
}
