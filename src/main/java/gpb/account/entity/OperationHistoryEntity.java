package gpb.account.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "operation_history", schema = "public", catalog = "GpbAccountDatabase")
public class  OperationHistoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "history_id")
    private int historyId;
    @Basic
    @Column(name = "amount")
    private Double amount;
    @Basic
    @Column(name = "operation_date")
    private Timestamp operationDate;
    @Basic
    @Column(name = "operation_type")
    private String operationType;
    @Basic
    @Column(name = "account_no")
    private Integer accountNo;

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Timestamp operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Integer getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Integer accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationHistoryEntity that = (OperationHistoryEntity) o;
        return historyId == that.historyId && Objects.equals(amount, that.amount) && Objects.equals(operationDate, that.operationDate) && Objects.equals(operationType, that.operationType) && Objects.equals(accountNo, that.accountNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyId, amount, operationDate, operationType, accountNo);
    }
}
