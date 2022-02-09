package gpb.account.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bank_account", schema = "public", catalog = "postgres")
public class BankAccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "bank_account_no")
    private int bankAccountNo;
    @Basic
    @Column(name = "credit_funds")
    private Double creditFunds;
    @Basic
    @Column(name = "debit_funds")
    private Double debitFunds;

    public int getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(int bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public Double getCreditFunds() {
        return creditFunds;
    }

    public void setCreditFunds(Double creditFunds) {
        this.creditFunds = creditFunds;
    }

    public Double getDebitFunds() {
        return debitFunds;
    }

    public void setDebitFunds(Double debitFunds) {
        this.debitFunds = debitFunds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountEntity that = (BankAccountEntity) o;
        return bankAccountNo == that.bankAccountNo && Objects.equals(creditFunds, that.creditFunds) && Objects.equals(debitFunds, that.debitFunds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankAccountNo, creditFunds, debitFunds);
    }
}
