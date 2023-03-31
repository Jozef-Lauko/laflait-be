package sk.umb.fpv.laflait.theses.controller;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import sk.umb.fpv.laflait.theses.service.ThesesDetailDTO;
import sk.umb.fpv.laflait.theses.service.ThesesRequestDTO;
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
        System.out.println("get all theses.");

        return thesesService.getAllTheses();
    }

    @GetMapping("/api/tezy/{thesisId}")
    public ThesesDetailDTO getThesis(@PathVariable Long thesisId) {
        System.out.println("get thesis by ID called.");

        return thesesService.getThesisByID(thesisId);
    }

    @PutMapping("/api/tezy/{thesisId}")
    public void updateThesis(@PathVariable Long thesisId, @Valid @RequestBody ThesesRequestDTO thesesRequestDTO){
        System.out.println("*** UPDATE THESIS BY ID ***");
        thesesService.updateThesis(thesisId, thesesRequestDTO);
    }
}
