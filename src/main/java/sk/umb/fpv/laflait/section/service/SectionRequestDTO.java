package sk.umb.fpv.laflait.section.service;

public class SectionRequestDTO {

    private String title;
    private String text;
    private Long thesisID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getThesisID() {
        return thesisID;
    }

    public void setThesisID(Long thesisID) {
        this.thesisID = thesisID;
    }
}
