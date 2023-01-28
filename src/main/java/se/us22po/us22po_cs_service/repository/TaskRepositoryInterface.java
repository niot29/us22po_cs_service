package se.us22po.us22po_cs_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.us22po.us22po_cs_service.entity.CustomerTaskEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepositoryInterface extends JpaRepository<CustomerTaskEntity, Long> {

    List<CustomerTaskEntity> findByCustomerIdOrderByCreateDateTimeDesc(String customerId);

    CustomerTaskEntity findByIdAndCustomerId(Long id, String customerId);

    void deleteByCustomerId(String customerId);
}
