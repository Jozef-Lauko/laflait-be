package sk.umb.fpv.laflait.theses.service;

import jakarta.validation.constraints.NotBlank;

public class ThesesRequestDTO {
    @NotBlank(message = "Title must not be empty.")
    private String title;
    @NotBlank(message = "Description must not be empty.")
    private String description;

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
