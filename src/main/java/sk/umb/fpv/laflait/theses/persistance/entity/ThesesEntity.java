package sk.umb.fpv.laflait.theses.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "Tezy")
public class ThesesEntity {

    @Id
    @Column(name = "id_teza")
    private Long id;

    @Column(name = "nazov_t")
    private String title;

    @Column(name = "opis_t")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
