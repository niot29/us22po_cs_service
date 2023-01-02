package se.us22po.us22po_cs_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @GetMapping(value = "info")
    public String info(){
        logger.info("info():  Application status");
        return "The application is up ..";
    }
}
