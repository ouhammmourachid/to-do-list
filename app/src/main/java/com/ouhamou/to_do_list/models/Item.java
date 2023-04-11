package com.ouhamou.to_do_list.models;

import android.content.ContentValues;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Item {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("category")
    @Expose
    private int category;
    @SerializedName("selected")
    @Expose
    private boolean selected;

    public Item() {
    }

    public Item(String text, int category) {
        this.text = text;
        this.category = category;
        this.selected = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        selected = selected;
    }


}
