package gpb.account.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account", schema = "public", catalog = "postgres")
public class AccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "account_no")
    private int accountNo;
    @Basic
    @Column(name = "client_id")
    private Integer clientId;

    // ToDo Уточнить как будем хранить деньги
    @Basic
    @Column(name = "actual_balance")
    private Double actualBalance;
    @Basic
    @Column(name = "account_type")
    private String accountType;
    @Basic
    @Column(name = "is_frozen")
    private Boolean isFrozen;
    @Basic
    @Column(name = "client_type")
    private String clientType;

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }


    public Double getActualBalance() {
        return actualBalance;
    }

    public void setActualBalance(Double actualBalance) {
        this.actualBalance = actualBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Boolean getFrozen() {
        return isFrozen;
    }

    public void setFrozen(Boolean frozen) {
        isFrozen = frozen;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return accountNo == that.accountNo && Objects.equals(clientId, that.clientId) && Objects.equals(actualBalance, that.actualBalance) && Objects.equals(accountType, that.accountType) && Objects.equals(isFrozen, that.isFrozen) && Objects.equals(clientType, that.clientType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo, clientId, actualBalance, accountType, isFrozen, clientType);
    }
}
