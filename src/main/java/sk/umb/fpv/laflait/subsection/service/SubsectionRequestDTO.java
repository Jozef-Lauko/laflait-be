package sk.umb.fpv.laflait.subsection.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubsectionRequestDTO {
    @NotBlank(message = "Title must not be empty.")
    private String title;
    @NotBlank(message = "Text must not be empty.")
    private String text;
    @NotNull
    private Long sectionID;
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

    public Long getSectionID() {
        return sectionID;
    }

    public void setSectionID(Long sectionID) {
        this.sectionID = sectionID;
    }

    public Long getNotesID() {
        return notesID;
    }

    public void setNotesID(Long notesID) {
        this.notesID = notesID;
    }
}
