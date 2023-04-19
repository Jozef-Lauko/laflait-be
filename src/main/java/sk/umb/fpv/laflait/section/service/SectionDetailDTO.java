package sk.umb.fpv.laflait.section.service;

import sk.umb.fpv.laflait.notes.service.NotesDetailDTO;
import sk.umb.fpv.laflait.theses.service.ThesesDetailDTO;

public class SectionDetailDTO {

    private Long id;

    private String title;

    private String text;

    private Long thesesID;

    private Long notesID;

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

    public Long getNotesID() {
        return notesID;
    }

    public void setNotesID(Long notesID) {
        this.notesID = notesID;
    }

    public Long getThesesID() {
        return thesesID;
    }

    public void setThesesID(Long thesesID) {
        this.thesesID = thesesID;
    }
}
