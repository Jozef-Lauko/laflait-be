package sk.umb.fpv.laflait.section.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import sk.umb.fpv.laflait.section.service.SectionDetailDTO;
import sk.umb.fpv.laflait.section.service.SectionRequestDTO;
import sk.umb.fpv.laflait.section.service.SectionService;

import java.util.List;

@RestController
public class SectionController {
    private SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/api/kapitoly/{thesisID}")
    public List<SectionDetailDTO> getSectionsByThesisID(@PathVariable Long thesisID) {
        System.out.println("*** GET ALL SECTIONS ***");

        return sectionService.getSectionsByThesisID(thesisID);
    }

    @GetMapping("/api/kapitoly/by/{sectionId}")
    public SectionDetailDTO getSection(@PathVariable Long sectionId) {
        System.out.println("*** GET SECTION BY ID ***");

        return sectionService.getSectionByID(sectionId);
    }

    @PutMapping("/api/kapitoly/{sectionId}")
    public void updateSection(@PathVariable Long sectionId, @Valid @RequestBody SectionRequestDTO sectionRequestDTO) {
        System.out.println("*** UPDATE SECTION BY ID ***");
        sectionService.updateSectionByID(sectionId, sectionRequestDTO);
    }
}
