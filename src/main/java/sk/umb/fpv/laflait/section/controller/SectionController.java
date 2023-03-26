package sk.umb.fpv.laflait.section.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sk.umb.fpv.laflait.section.service.SectionDetailDTO;
import sk.umb.fpv.laflait.section.service.SectionService;
import sk.umb.fpv.laflait.theses.service.ThesesDetailDTO;

import java.util.List;

@RestController
public class SectionController {
    private SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/api/kapitoly")
    public List<SectionDetailDTO> getSections() {
        System.out.println("*** GET ALL SECTIONS ***");

        return sectionService.getAllSections();
    }

    @GetMapping("/api/kapitola/{sectionId}")
    public SectionDetailDTO getSection(@PathVariable Long sectionId) {
        System.out.println("*** GET SECTION BY ID ***");

        return sectionService.getSectionByID(sectionId);
    }
}
