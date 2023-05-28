package sk.umb.fpv.laflait.subsection.service;

import jakarta.persistence.Column;
import sk.umb.fpv.laflait.notes.service.NotesDetailDTO;
import sk.umb.fpv.laflait.section.service.SectionDetailDTO;

public class SubsectionDetailDTO {

    private Long id;

    private String title;

    private String text;

    private Long notesid;
    private String notestext;
    private String notescode;
    private byte[] notesimageData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
