package sk.umb.fpv.laflait.theses.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sk.umb.fpv.laflait.theses.service.ThesesDetailDTO;
import sk.umb.fpv.laflait.theses.service.ThesesService;

import java.util.List;

@RestController
public class ThesesController {
    private ThesesService thesesService;

    public ThesesController(ThesesService thesesService) {
        this.thesesService = thesesService;
    }

    @GetMapping("/api/tezy")
    public List<ThesesDetailDTO> getTheses() {
        System.out.println("*** GET ALL THESES ***");

        return thesesService.getAllTheses();
    }

    @GetMapping("/api/teza/{thesisId}")
    public ThesesDetailDTO getThesis(@PathVariable Long thesisId) {
        System.out.println("*** GET THESIS BY ID ***");

        return thesesService.getThesisByID(thesisId);
    }
}
