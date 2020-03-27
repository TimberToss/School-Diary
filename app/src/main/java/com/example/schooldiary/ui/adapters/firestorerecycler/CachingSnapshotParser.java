package com.example.schooldiary.ui.adapters.firestorerecycler;


import androidx.annotation.NonNull;

import com.example.schooldiary.common.BaseCachingSnapshotParser;
import com.example.schooldiary.common.BaseSnapshotParser;
import com.google.firebase.firestore.DocumentSnapshot;

/**
 * Implementation of {@link BaseCachingSnapshotParser} for {@link DocumentSnapshot}.
 */
public class CachingSnapshotParser<T> extends BaseCachingSnapshotParser<DocumentSnapshot, T>
        implements SnapshotParser<T> {

    public CachingSnapshotParser(@NonNull BaseSnapshotParser<DocumentSnapshot, T> parser) {
        super(parser);
    }

    @NonNull
    @Override
    public String getId(@NonNull DocumentSnapshot snapshot) {
        return snapshot.getId();
    }
}
