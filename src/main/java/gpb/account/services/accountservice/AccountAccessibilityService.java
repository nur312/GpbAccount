package gpb.account.services.accountservice;

public interface AccountAccessibilityService {

    void freezeAccount(Integer account_no);

    void unfreezeAccount(Integer account_no);
}
