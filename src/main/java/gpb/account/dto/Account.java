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

    private Integer accountNumber;
    private Integer clientId;
    private Double balance;
    private Boolean isFrozen;
    private ClientType clientType;
    private AccountType accountType;
}
//Todo
// private Long accountId;  есть мнение, что это дубликат и он не нужен!!!
//  private List<Operation> history;
//  private Long historyId;