package sk.umb.fpv.laflait.section.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SectionRequestDTO {

    @NotBlank(message = "Title must not be empty.")
    private String title;
    @NotBlank(message = "Text must not be empty.")
    private String text;
    @NotNull
    private Long thesisID;
    @NotNull
    private Long notesID;

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

    public Long getNotesID() {
        return notesID;
    }

    public void setNotesID(Long notesID) {
        this.notesID = notesID;
    }
}
