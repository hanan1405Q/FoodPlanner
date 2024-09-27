package com.example.foodplanner.model;

import com.google.gson.annotations.SerializedName;

public class Category {
        @SerializedName("idCategory")
        public String id;
        @SerializedName("strCategory")
        public String  name;
        @SerializedName("strCategoryThumb")
        public String imgSource;
        @SerializedName("strCategoryDescription")
        public String  description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
