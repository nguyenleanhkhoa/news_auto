package model;

import java.util.List;

public class ContentFormat {
    List<String> category;
    List<String> time;
    List<String> content;

    public ContentFormat() {
    }

    public ContentFormat(List<String> category, List<String> time, List<String> content) {
        this.category = category;
        this.time = time;
        this.content = content;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
