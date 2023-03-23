package sk.umb.fpv.laflait.theses.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.umb.fpv.laflait.theses.service.ThesesDetailDTO;
import sk.umb.fpv.laflait.theses.service.ThesesService;

import java.util.List;

@RestController
@RequestMapping("/api/tezy")
public class ThesesController {
    private ThesesService thesesService;

    @GetMapping
    public List<ThesesDetailDTO> getTheses() {
        System.out.println("get theses function called.");

        return thesesService.getAllTheses();
    }
}
