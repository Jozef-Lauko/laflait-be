package sk.umb.fpv.laflait.grades.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.umb.fpv.laflait.grades.service.GradesDetailDTO;
import sk.umb.fpv.laflait.grades.service.GradesService;

import java.util.List;

@RestController
public class GradesController {

    private GradesService gradesService;

    public GradesController(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    @GetMapping("/api/znamky")
    public List<GradesDetailDTO> getAllGrades(){
        System.out.println("*** GET ALL GRADES ***");

        return gradesService.getAllGrades();
    }
}
