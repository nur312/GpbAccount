package gpb.account.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bank_account", schema = "public", catalog = "postgres")
@Getter
@Setter
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
