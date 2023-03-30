package sk.umb.fpv.laflait.section.service;

import sk.umb.fpv.laflait.theses.service.ThesesDetailDTO;

public class SectionDetailDTO {

    private Long id;

    private String title;

    private String text;

    private ThesesDetailDTO thesesDetailDTO;

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

    public ThesesDetailDTO getThesesDetailDTO() {
        return thesesDetailDTO;
    }

    public void setThesesDetailDTO(ThesesDetailDTO thesesDetailDTO) {
        this.thesesDetailDTO = thesesDetailDTO;
    }
}
