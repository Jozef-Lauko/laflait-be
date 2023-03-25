package sk.umb.fpv.laflait.section.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.umb.fpv.laflait.section.service.SectionDetailDTO;
import sk.umb.fpv.laflait.section.service.SectionService;

import java.util.List;

@RestController
public class SectionController {
    private SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/api/kapitola")
    public List<SectionDetailDTO> getSections() {
        System.out.println("get all sections.");

        return sectionService.getAllSections();
    }
}
