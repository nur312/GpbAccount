package gpb.account.services.accountservice;

import gpb.account.entity.AccountEntity;
import gpb.account.repo.AccountRepo;
import org.springframework.stereotype.Service;
//TODO Да, готов покритиковать)
//Воспрос по логике в сервисных классах. А в чем смысл деления логики работы с аккаунтами на 3 интерфейса?
// И по сервисному классу банка, в нем сначала дёргается запись из бд, а потом проверяете корректность присланных от клиента данных, и в случае плохих данных, кидаете исключение. Может стоит поменять последовательность.
//А также у вас избыточный запросы в бд, сначала в спомогательном классе, а потом снова уже в самом сервисном классе.
//Насколько знаю, методы findById может вернуть optional, а из optional можно сразу бросить исключение если значение null
@Service
public class AccountAccessibilityServiceImpl implements AccountAccessibilityService {

    private final AccountRepo accountRepo;

    public AccountAccessibilityServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public void freezeAccount(Integer account_no) {

        ExceptionStateChecker.throwExIdDoesNotExist(account_no, accountRepo);

        AccountEntity account = accountRepo.getById(account_no);

        account.setFrozen(true);

        accountRepo.save(account);

    }

    public void unfreezeAccount(Integer account_no) {

        ExceptionStateChecker.throwExIdDoesNotExist(account_no, accountRepo);
        AccountEntity account = accountRepo.getById(account_no);

        account.setFrozen(false);

        accountRepo.save(account);

    }
}
