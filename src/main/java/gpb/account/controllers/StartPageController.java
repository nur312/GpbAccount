package gpb.account.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
public class StartPageController {

    @RequestMapping(value = "/")
    public RedirectView redirect() throws IOException {
        return new RedirectView("/swagger-ui.html", true);
    }
}