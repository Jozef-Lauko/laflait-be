package sk.umb.fpv.laflait.subsection.persistance.entity;

import jakarta.persistence.*;
import sk.umb.fpv.laflait.notes.persistance.entity.NotesEntity;
import sk.umb.fpv.laflait.section.persistance.entity.SectionEntity;

@Entity(name = "Podkapitoly")
@Table(name = "podkapitoly")
public class SubsectionEntity {

    @Id
    @Column(name = "id_podkapitola")
    private Long id;

    @Column(name = "nazov_pk")
    private String title;

    @Column(name = "text_pk")
    private String text;

    @Column(name = "id_kapitola")
    private Long sectionID;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_poznamky")
    private NotesEntity notesEntity;

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

    public Long getSectionID() {
        return sectionID;
    }

    public void setSectionID(Long thesesID) {
        this.sectionID = thesesID;
    }

    public NotesEntity getNotesEntity() {
        return notesEntity;
    }

    public void setNotesEntity(NotesEntity notesEntity) {
        this.notesEntity = notesEntity;
    }
}
