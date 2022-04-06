package gpb.account.services;

import gpb.account.entity.BankAccountEntity;
import gpb.account.exception.InvalidJsonException;
import gpb.account.repo.BankAccountRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

class BankAccountServiceTest {


    @Test
    void depositCorrect() {
        BankAccountRepo repo = Mockito.mock(BankAccountRepo.class);

        BankAccountEntity entity = new BankAccountEntity();
        entity.setFunds(5000.0);
        entity.setBankAccountNo(555);

        Mockito.when(repo.getById(anyInt())).thenReturn(entity);

        BankAccountService service = new BankAccountService(repo, 123);

        service.deposit(1000.0);

        double funds = entity.getFunds();

        assertEquals(6000.0, funds);
    }

    @Test
    void depositException() {
        BankAccountRepo repo = Mockito.mock(BankAccountRepo.class);

        BankAccountEntity entity = new BankAccountEntity();
        entity.setFunds(5000.0);
        entity.setBankAccountNo(555);

        Mockito.when(repo.getById(anyInt())).thenReturn(entity);

        BankAccountService service = new BankAccountService(repo, 123);

        assertThrows(InvalidJsonException.class, () -> service.deposit(-1.0));
    }
}