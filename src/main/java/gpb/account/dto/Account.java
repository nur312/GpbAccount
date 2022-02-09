package gpb.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long accountNumber;
    private Long clientId;
    private Long accountId;
    private BigDecimal balance;
    private List<Operation> history;
    private Long historyId;
    private Boolean isFrozen;

    private ClientType clientType;
    private AccountType accountType;
}
