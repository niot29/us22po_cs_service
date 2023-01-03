package se.us22po.us22po_cs_service.controller;

import jakarta.persistence.PostUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.us22po.us22po_cs_service.entity.CustomerTaskEntity;
import se.us22po.us22po_cs_service.service.TaskServices;

import java.util.List;


@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskServices taskServices;

    @GetMapping(value = "info")
    public String info(){
        logger.info("info():  Application status");
        return "The application is up ..";
    }

    @GetMapping
    public List<CustomerTaskEntity> getAlltask(){
        logger.info("getAlltask()");
        return taskServices.getAllTask();

    }
    @GetMapping("{customerId}")
    public List<CustomerTaskEntity> getAllCustomerTask(@PathVariable ("customerId") Long customerId){
        logger.info("getAllCustomerTask()");
        return taskServices.getAllTaskByCustomerId(customerId);
    }

    @PostMapping
    public String createTask(@RequestBody CustomerTaskEntity task){
        logger.info("createTask(): {}", task);
        return taskServices.manageTask(task);
    }

    @PutMapping
    public String updateTask(@RequestBody CustomerTaskEntity task){
        logger.info("updateTask(): {}", task);
        if(task.getId() == null){
            logger.warn("updateTask(): Can't update the Task record: Need task ID");
            return "Can't update the Task record: Need task ID";
        }else{
            return taskServices.manageTask(task);
        }

    }

    @DeleteMapping("{customerId}")
    public String deletCutomerTask(@PathVariable ("customerId") Long customerId){
        logger.info("deletCutomerTask()");
        return taskServices.deleteCutomerTaskById(customerId);
    }

}
