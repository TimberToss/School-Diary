package com.example.schooldiary.mainpage.ui;

import com.example.schooldiary.mainpage.common.BaseCachingSnapshotParser;
import com.example.schooldiary.mainpage.common.BaseObservableSnapshotArray;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

import androidx.annotation.NonNull;

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
