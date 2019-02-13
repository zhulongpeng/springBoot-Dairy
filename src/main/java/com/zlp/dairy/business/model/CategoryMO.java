package com.zlp.dairy.business.model;

import java.io.Serializable;

public class CategoryMO implements Serializable {

    private static final long serialVersionUID = 7134075937299713247L;

    private String language;

    private String categoryName;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
