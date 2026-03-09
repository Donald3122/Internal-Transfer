package task.internaltransfers.config.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.internaltransfers.config.entity.LogServiceEntity;

import java.util.List;
@Repository
public interface LogServiceRepository extends JpaRepository<LogServiceEntity,Long> {
    List<LogServiceEntity> findByRequisite(String requisite);
}
