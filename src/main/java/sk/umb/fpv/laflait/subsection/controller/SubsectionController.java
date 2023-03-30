package sk.umb.fpv.laflait.subsection.controller;

import org.springframework.web.bind.annotation.*;
import sk.umb.fpv.laflait.subsection.service.SubsectionDetailDTO;
import sk.umb.fpv.laflait.subsection.service.SubsectionRequestDTO;
import sk.umb.fpv.laflait.subsection.service.SubsectionService;

import java.util.List;

@RestController
public class SubsectionController {

    private SubsectionService subsectionService;

    public SubsectionController(SubsectionService subsectionService) {
        this.subsectionService = subsectionService;
    }

    @GetMapping("/api/podkapitoly")
    public List<SubsectionDetailDTO> getSubsections() {
        System.out.println("*** GET ALL SUBSECTIONS ***");

        return subsectionService.getAllSubsections();
    }

    @GetMapping("/api/podkapitoly/{subsectionId}")
    public SubsectionDetailDTO getSubsection(@PathVariable Long subsectionId) {
        System.out.println("*** GET SUBSECTION BY ID ***");

        return subsectionService.getSubsectionByID(subsectionId);
    }

    @PutMapping("/api/podkapitoly/{subsectionId}")
    public void updateSubsection(@PathVariable Long subsectionId, @RequestBody SubsectionRequestDTO subsectionRequestDTO) {
        System.out.println("*** UPDATE SUBSECTION BY ID ***");
        subsectionService.updateSubsectionByID(subsectionId, subsectionRequestDTO);
    }
}
