package sk.umb.fpv.laflait.subsection.service;

import jakarta.persistence.Column;
import sk.umb.fpv.laflait.section.service.SectionDetailDTO;

public class SubsectionDetailDTO {

    private Long id;

    private String title;

    private String[] paragraphs;

    private SectionDetailDTO sectionDetailDTO;

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

    public String[] getParagraphs() {
        return paragraphs;
    }

    public void setText(String text) {
        this.paragraphs = text.split("###");
    }

    public SectionDetailDTO getSectionDetailDTO() {
        return sectionDetailDTO;
    }

    public void setSectionDetailDTO(SectionDetailDTO sectionDetailDTO) {
        this.sectionDetailDTO = sectionDetailDTO;
    }
}