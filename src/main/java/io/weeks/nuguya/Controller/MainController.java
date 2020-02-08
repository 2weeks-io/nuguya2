package io.weeks.nuguya.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/writings")
    public String main() {

        return "hello";
    }

}
