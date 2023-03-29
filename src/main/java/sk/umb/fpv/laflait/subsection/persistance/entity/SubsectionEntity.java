package sk.umb.fpv.laflait.subsection.persistance.entity;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kapitola")
    private SectionEntity section;

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

    public SectionEntity getSection() {
        return section;
    }

    public void setSection(SectionEntity section) {
        this.section = section;
    }
}
