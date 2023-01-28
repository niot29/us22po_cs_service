package se.us22po.us22po_cs_service.controller;

import jakarta.persistence.PostUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String info(){
        logger.info("info():  Application status");
        return "The application (CustomerTask)is up ..";
    }

    @GetMapping
    public List<CustomerTaskEntity> getAlltask(){
        logger.info("getAlltask()");
        return taskServices.getAllTask();

    }
    @GetMapping("{customerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CustomerTaskEntity> getAllCustomerTask(@PathVariable ("customerId") String customerId){
        logger.info("getAllCustomerTask()");
        return taskServices.getAllTaskByCustomerId(customerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody CustomerTaskEntity task){
        logger.info("createTask(): {}", task);
        taskServices.manageTask(task);
        //return taskServices.manageTask(task);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateTask(@RequestBody CustomerTaskEntity task){
        logger.info("updateTask(): {}", task);
        if(task.getId() == null){
            logger.warn("updateTask(): Can't update the Task record: Need task ID");
            //return "Can't update the Task record: Need task ID";
        }else{
            taskServices.manageTask(task);
            logger.info("updateTask(): The record is updated");

            //return "Updated";
        }

    }

    @DeleteMapping("{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public String deletCutomerTask(@PathVariable ("customerId") String customerId){
        logger.info("deletCutomerTask()");
        return taskServices.deleteCutomerTaskById(customerId);
    }

}
