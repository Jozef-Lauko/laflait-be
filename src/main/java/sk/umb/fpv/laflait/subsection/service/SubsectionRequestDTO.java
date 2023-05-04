package sk.umb.fpv.laflait.subsection.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sk.umb.fpv.laflait.notes.service.NotesRequestDTO;

public class SubsectionRequestDTO {
    @NotBlank(message = "Title must not be empty.")
    private String title;
    @NotBlank(message = "Text must not be empty.")
    private String text;
    private NotesRequestDTO notes;
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

    public NotesRequestDTO getNotes() {
        return notes;
    }

    public void setNotes(NotesRequestDTO notes) {
        this.notes = notes;
    }
}
