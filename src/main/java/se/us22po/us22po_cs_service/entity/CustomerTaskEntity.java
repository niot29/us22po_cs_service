package se.us22po.us22po_cs_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name="CUSTOMER_SUPPORT_TASK")
public class CustomerTaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;

    @Column(length=1001)
    private String tasks;
    private String priority;
    private String status;

    @CreationTimestamp
    private Timestamp createDateTime;

    @UpdateTimestamp
    private Timestamp updateDateTime;

    public CustomerTaskEntity(Long id, Long customerId, String tasks, String priority, String status, Timestamp createDateTime, Timestamp updateDateTime) {
        this.id = id;
        this.customerId = customerId;
        this.tasks = tasks;
        this.priority = priority;
        this.status = status;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
    }

    public CustomerTaskEntity() {

    }
}
