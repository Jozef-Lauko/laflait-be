package sk.umb.fpv.laflait.section.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import sk.umb.fpv.laflait.notes.persistance.entity.NotesEntity;
import sk.umb.fpv.laflait.theses.persistance.entity.ThesesEntity;

import java.util.Set;

@Entity(name = "Kapitoly")
@Table(name = "kapitoly")
public class SectionEntity {

    @Id
    @Column(name = "id_kapitola")
    private Long id;

    @Column(name = "nazov_k")
    private String title;

    @Column(name = "text_k")
    private String text;

    @Column(name = "id_teza")
    private Long thesesID;

    @Column(name = "id_poznamky")
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

    public Long getThesesID() {
        return thesesID;
    }

    public void setThesesID(Long thesesID) {
        this.thesesID = thesesID;
    }

    public Long getNotesID() {
        return notesID;
    }

    public void setNotesID(Long notesID) {
        this.notesID = notesID;
    }
}
