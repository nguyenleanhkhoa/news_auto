package model;

import Entity.Category;

public class Menu {
    private Category category;
    private String href;

    public Menu() {
    }

    public Menu(Category category, String href) {
        this.category = category;
        this.href =href;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
