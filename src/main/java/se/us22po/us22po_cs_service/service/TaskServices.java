package se.us22po.us22po_cs_service.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.us22po.us22po_cs_service.entity.CustomerTaskEntity;
import se.us22po.us22po_cs_service.repository.TaskRepositoryInterface;

import java.sql.Timestamp;


import java.util.Date;
import java.util.List;

@Service
public class TaskServices {
    private final Logger logger = LoggerFactory.getLogger(TaskServices.class);

    @Autowired
    TaskRepositoryInterface taskRepo;

    /**
     * List all task record from repository
     * @return List
     */
    public List<CustomerTaskEntity> getAllTask(){
        logger.info("getAllTask()");
        return taskRepo.findAll();
    }

    /**
     *
     * @param customerId - Get all customer task by ID
     * @return  List
     */
    public List<CustomerTaskEntity> getAllTaskByCustomerId(Long customerId){
        logger.info("getAllTaskByCustomerId(): ");
        return taskRepo.findByCustomerIdOrderByCreateDateTimeDesc(customerId);
    }
    /**
     *
     * @param cutomerId - List all Customer task record from repository by customer Id
     * @return List
     */
    public List<CustomerTaskEntity> getAllCustomerTask(Long cutomerId){
        logger.info("getAllCustomerTask()");
        return taskRepo.findByCustomerIdOrderByCreateDateTimeDesc(cutomerId);
    }

    /**
     * Create a new customer task
     * @param ctask
     * @return String
     */
    @Transactional
    private String createTask(CustomerTaskEntity ctask){
        logger.info("createTask():");

        try{

            Date date = new Date();
            Timestamp createTime = new Timestamp(date.getTime());

            ctask.setCreateDateTime(createTime);
            ctask.setUpdateDateTime(createTime);
            taskRepo.save(ctask);
            return "The task is saved";
        }catch (Exception e){
            logger.warn("The task can't be save, something is wrong");
            throw e;
        }

    }

    /**
     * Update Task, keep create date.
     * @param utask - CustomerID has to bee same as database storage customer id
     * @return String
     */
    @Transactional
    public String updateTask(CustomerTaskEntity utask){
        logger.info("updateTask():");
        try{
            CustomerTaskEntity oldTask = taskRepo.findById(utask.getId()).get();
            Date date = new Date();
            Timestamp createTime = new Timestamp(date.getTime());

            if (utask.getCustomerId() != oldTask.getCustomerId()){
                logger.warn("Cant update this task, this customer id is not the same");
                return "Cant update this task, this customer id is not the same";
            }
            utask.setCreateDateTime(oldTask.getCreateDateTime());
            utask.setUpdateDateTime(createTime);
            taskRepo.save(utask);
            return "The task is Updated";


        }catch (Exception e){
            logger.warn("The task can't be Update, something is wrong");
            throw e;
        }
    }


    /**
     * Manage Task if it needs create or update.
     * If id is 'null' then create a new task and else just update previous task with task is
     * @param mtask
     * @return String
     */
    public void manageTask(CustomerTaskEntity mtask){
        logger.info("manageTask()");
        String returnMsg = null;

        if(mtask.getId() == null){
            returnMsg = createTask(mtask);
        }
        returnMsg = updateTask(mtask);

        //return returnMsg;
    }

    @Transactional
    public String deleteCutomerTaskById(Long customerId){
        logger.info("deleteCutomerTask()");
        taskRepo.deleteAllByCustomerId(customerId);
        return "customer Task deleted";
    }
}
