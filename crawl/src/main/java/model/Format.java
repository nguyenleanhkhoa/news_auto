package model;

import java.util.List;


public class Format {
    private List<String> nodeRoot;
    private List<String> nodeTitle;
    private List<String> nodeImage;
    private List<String> nodeDescription;

    public Format() {
    }

    public Format(List<String> nodeRoot, List<String> nodeTitle, List<String> nodeImage, List<String> nodeDescription) {
        this.nodeRoot = nodeRoot;
        this.nodeTitle = nodeTitle;
        this.nodeImage = nodeImage;
        this.nodeDescription = nodeDescription;
    }

    public List<String> getNodeRoot() {
        return nodeRoot;
    }

    public void setNodeRoot(List<String> nodeRoot) {
        this.nodeRoot = nodeRoot;
    }

    public List<String> getNodeTitle() {
        return nodeTitle;
    }

    public void setNodeTitle(List<String> nodeTitle) {
        this.nodeTitle = nodeTitle;
    }

    public List<String> getNodeImage() {
        return nodeImage;
    }

    public void setNodeImage(List<String> nodeImage) {
        this.nodeImage = nodeImage;
    }

    public List<String> getNodeDescription() {
        return nodeDescription;
    }

    public void setNodeDescription(List<String> nodeDescription) {
        this.nodeDescription = nodeDescription;
    }
}
