package sk.umb.fpv.laflait.subsection.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "Podkapitoly")
public class SubsectionEntity {

    @Id
    @Column(name = "id_podkapitola")
    private Long id;

    @Column(name = "nazov_pk")
    private String title;

    @Column(name = "text_pk")
    private String text;

    @Column(name = "id_kapitola")
    private Long id_kapitola;

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

    public Long getId_kapitola() {
        return id_kapitola;
    }

    public void setId_kapitola(Long id_kapitola) {
        this.id_kapitola = id_kapitola;
    }
}
