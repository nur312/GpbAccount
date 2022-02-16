package gpb.account.services;


import gpb.account.entity.BankAccountEntity;
import gpb.account.repo.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private final BankAccountRepo bankAccountRepo;

    @Autowired
    public BankAccountService(BankAccountRepo bankAccountRepo) {
        this.bankAccountRepo = bankAccountRepo;
    }

    public void deposit(double amount, @Value("${bank-account-no}") String Id) {

//        ToDo спросить про банк аккаунт номер(пока заглушка = 0)
        BankAccountEntity bankAccount = bankAccountRepo.getById(Integer.parseInt(Id));
        double newDepositedFunds = bankAccount.getFunds() + amount;

        bankAccount.setFunds(newDepositedFunds);
        bankAccountRepo.save(bankAccount);
    }

    public void withdraw(double amount, @Value("${bank-account-no}") String Id) {
//      ToDo докурутить логику выдачи кредитов(оставить одну переменную в базе для аккаунта)
        BankAccountEntity bankAccount = bankAccountRepo.getById(Integer.parseInt(Id));

        double newCreditedFunds = bankAccount.getFunds() - amount;
        if (newCreditedFunds < 0) {

            throw new IllegalStateException("Noе enough money to withdraw");
        }
        bankAccount.setFunds(newCreditedFunds);
        bankAccountRepo.save(bankAccount);
    }

    public  double getActualBalance(@Value("${bank-account-no}") String Id) {

        BankAccountEntity bankAccount = bankAccountRepo.getById(Integer.parseInt(Id));

        return bankAccount.getFunds();
    }

}
