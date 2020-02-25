package com.example.schooldiary.mainpage.recyclerView;

import android.graphics.drawable.Drawable;

public class ItemFragment {
    private int id;
    private Drawable iconFragment;
    private String titleFragment;

    public ItemFragment(int id, Drawable iconFragment, String titleFragment) {
        this.id = id;
        this.iconFragment = iconFragment;
        this.titleFragment = titleFragment;
    }

    public int getId() {
        return id;
    }

    public Drawable getIconFragment() {
        return iconFragment;
    }

    public String getTitleFragment() {
        return titleFragment;
    }
}
