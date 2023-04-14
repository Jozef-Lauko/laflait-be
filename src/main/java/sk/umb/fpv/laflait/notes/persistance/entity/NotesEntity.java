package sk.umb.fpv.laflait.notes.persistance.entity;

import jakarta.persistence.*;
import sk.umb.fpv.laflait.section.persistance.entity.SectionEntity;
import sk.umb.fpv.laflait.subsection.persistance.entity.SubsectionEntity;

@Entity(name = "Poznamky")
@Table(name = "poznamky")
public class NotesEntity {

    @Id
    @Column(name = "id_poznamky")
    private Long id;

    @Column(name = "text_p")
    private String text;

    @Column(name = "image")
    private String images;

    @Column(name = "link")
    private String links;

    @Column(name = "code")
    private String code;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
