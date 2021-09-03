package model;

import java.util.ArrayList;
import java.util.List;

public class CategoryFormat {
    String categoryNode;
    List<String> categoryTitle;

    public CategoryFormat() {
    }

    public CategoryFormat(String categoryNode, List<String> categoryTitle) {
        this.categoryNode = categoryNode;
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryNode() {
        return categoryNode;
    }

    public void setCategoryNode(String categoryNode) {
        this.categoryNode = categoryNode;
    }

    public List<String> getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(List<String> categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
