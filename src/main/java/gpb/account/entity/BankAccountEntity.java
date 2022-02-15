package gpb.account.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bank_account", schema = "public", catalog = "postgres")
public class BankAccountEntity {

    @Id
    @Column(name = "bank_account_no")
    private int bankAccountNo;
    @Basic
    @Column(name = "funds")
    private double funds;

    public int getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(int bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountEntity that = (BankAccountEntity) o;
        return bankAccountNo == that.bankAccountNo && Double.compare(that.funds, funds) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankAccountNo, funds);
    }
}
