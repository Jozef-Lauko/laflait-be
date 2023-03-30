package sk.umb.fpv.laflait.section.controller;

import org.springframework.web.bind.annotation.*;
import sk.umb.fpv.laflait.section.service.SectionDetailDTO;
import sk.umb.fpv.laflait.section.service.SectionRequestDTO;
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

    @GetMapping("/api/kapitoly/{sectionId}")
    public SectionDetailDTO getSection(@PathVariable Long sectionId) {
        System.out.println("*** GET SECTION BY ID ***");

        return sectionService.getSectionByID(sectionId);
    }

    @PutMapping("/api/kapitoly/{sectionId}")
    public void updateSection(@PathVariable Long sectionId, @RequestBody SectionRequestDTO sectionRequestDTO) {
        System.out.println("*** UPDATE SECTION BY ID ***");
        sectionService.updateSectionByID(sectionId, sectionRequestDTO);
    }
}
