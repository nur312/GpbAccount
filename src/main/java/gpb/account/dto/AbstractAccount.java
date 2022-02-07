package gpb.account.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
public abstract class AbstractAccount {
    private BigInteger accountNumber;
    private Long clientId;
    private Long accountId;
    private BigDecimal balance;
    private List<Operation> history;
    private Long historyId;
    private Boolean isFrozen;
}
