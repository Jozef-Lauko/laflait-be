package sk.umb.fpv.laflait.notes.service;

public class NotesDetailDTO {

    private Long id;

    private String[] paragraphs;

    private String[] images;

    private String[] links;

    private String[] code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getParagraphs() {
        return paragraphs;
    }

    public void setText(String text) {
        this.paragraphs = text.split("###");
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images.split("###");
    }

    public String[] getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links.split("###");
    }

    public String[] getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code.split("###");
    }
}
