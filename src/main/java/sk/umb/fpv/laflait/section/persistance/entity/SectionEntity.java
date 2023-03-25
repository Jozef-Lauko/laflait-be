package sk.umb.fpv.laflait.section.persistance.entity;

import jakarta.persistence.*;
import sk.umb.fpv.laflait.theses.persistance.entity.ThesesEntity;

import java.util.Set;

@Entity(name = "Kapitoly")
public class SectionEntity {

    @Id
    @Column(name = "id_kapitola")
    private Long id;

    @Column(name = "nazov_k")
    private String title;

    @Column(name = "text_k")
    private String text;

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

}
