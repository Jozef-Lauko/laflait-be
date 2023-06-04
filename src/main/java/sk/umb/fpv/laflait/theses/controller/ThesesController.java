package sk.umb.fpv.laflait.theses.controller;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import sk.umb.fpv.laflait.theses.service.ThesesDetailDTO;
import sk.umb.fpv.laflait.theses.service.ThesesRequestDTO;
import sk.umb.fpv.laflait.theses.service.ThesesService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/api/tezy/{thesisId}")
    public ThesesDetailDTO getThesis(@PathVariable Long thesisId) {
        System.out.println("*** GET THESIS BY ID ***");

        return thesesService.getThesisByID(thesisId);
    }

    @PutMapping("/api/tezy/{thesisId}")
    public void updateThesis(@PathVariable Long thesisId, @Valid @RequestBody ThesesRequestDTO thesesRequestDTO){
        System.out.println("*** UPDATE THESIS BY ID ***");
        thesesService.updateThesis(thesisId, thesesRequestDTO);
    }

    @GetMapping("/api/tezy/size")
    public int getSizeOfTheses(){
        return thesesService.getSize();
    }
}
