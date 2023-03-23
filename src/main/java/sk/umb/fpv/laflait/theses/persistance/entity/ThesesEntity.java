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
    private String thesis;

    @Column(name = "opis_t")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThesis() {
        return thesis;
    }

    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
