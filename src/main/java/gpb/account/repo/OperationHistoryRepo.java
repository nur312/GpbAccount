package gpb.account.repo;

import gpb.account.entity.AccountEntity;
import gpb.account.entity.OperationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationHistoryRepo extends JpaRepository<OperationHistoryEntity, Integer> {

}
