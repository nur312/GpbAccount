package gpb.account.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "account", schema = "public", catalog = "postgres")
public class AccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "account_no")
    private BigInteger accountNo;
    @Basic
    @Column(name = "client_id")
    private Long clientId;
    @Basic
    @Column(name = "actual_balance")
    private Double actualBalance;
    @Basic
    @Column(name = "account_type")
    private String accountType;
    @Basic
    @Column(name = "is_frozen")
    private Boolean isFrozen;

    public BigInteger getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(BigInteger accountNo) {
        this.accountNo = accountNo;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return Objects.equals(accountNo, that.accountNo) && Objects.equals(clientId, that.clientId) && Objects.equals(actualBalance, that.actualBalance) && Objects.equals(accountType, that.accountType) && Objects.equals(isFrozen, that.isFrozen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo, clientId, actualBalance, accountType, isFrozen);
    }
}
