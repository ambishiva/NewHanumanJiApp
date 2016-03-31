package data;

/**
 * Created by DELL on 08-03-2016.
 */
public class MoreModel {

    private String id;
    private String links;
    private String linkImage;

    public MoreModel(String id, String links, String linkImage) {
        this.id = id;
        this.links = links;
        this.linkImage = linkImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
