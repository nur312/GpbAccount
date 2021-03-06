package gpb.account.repo;

import gpb.account.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankAccountRepo extends JpaRepository<BankAccountEntity, Integer> {
}
