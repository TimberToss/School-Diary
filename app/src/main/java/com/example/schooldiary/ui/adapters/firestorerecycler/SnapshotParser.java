package com.example.schooldiary.ui.adapters.firestorerecycler;

import com.example.schooldiary.common.BaseSnapshotParser;
import com.google.firebase.firestore.DocumentSnapshot;

/**
 * Base interface for a {@link BaseSnapshotParser} for {@link DocumentSnapshot}.
 */
public interface SnapshotParser<T> extends BaseSnapshotParser<DocumentSnapshot, T> {
}
