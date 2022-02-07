package gpb.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gpb.account.entity.AccountEntity;

@Repository
public interface AccountRepo extends JpaRepository<AccountEntity, Integer> {
    AccountEntity findByClientId (Long clientId);
}
