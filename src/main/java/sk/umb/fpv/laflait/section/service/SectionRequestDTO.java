package sk.umb.fpv.laflait.section.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sk.umb.fpv.laflait.notes.service.NotesRequestDTO;

public class SectionRequestDTO {

    @NotBlank(message = "Title must not be empty.")
    private String title;
    @NotBlank(message = "Text must not be empty.")
    private String text;
    private Long notesid;
    private String notestext;
    private String notescode;
    private byte[] notesimageData;

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

    public Long getNotesid() {
        return notesid;
    }

    public void setNotesid(Long notesid) {
        this.notesid = notesid;
    }

    public String getNotestext() {
        return notestext;
    }

    public void setNotestext(String notestext) {
        this.notestext = notestext;
    }

    public String getNotescode() {
        return notescode;
    }

    public void setNotescode(String notescode) {
        this.notescode = notescode;
    }

    public byte[] getNotesimageData() {
        return notesimageData;
    }

    public void setNotesimageData(byte[] notesimageData) {
        this.notesimageData = notesimageData;
    }
}
