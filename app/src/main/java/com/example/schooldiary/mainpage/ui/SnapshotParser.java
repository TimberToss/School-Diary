package com.example.schooldiary.mainpage.ui;

import com.example.schooldiary.mainpage.common.BaseSnapshotParser;
import com.google.firebase.firestore.DocumentSnapshot;

/**
 * Base interface for a {@link BaseSnapshotParser} for {@link DocumentSnapshot}.
 */
public interface SnapshotParser<T> extends BaseSnapshotParser<DocumentSnapshot, T> {}
