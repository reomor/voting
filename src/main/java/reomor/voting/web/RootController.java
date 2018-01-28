package reomor.voting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public String root() {
        log.info("start root page");
        return "index";
    }

    @GetMapping("/menus")
    public String meals() {
        log.info("meals page");
        return "menus";
    }
}
