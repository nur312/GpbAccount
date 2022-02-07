package gpb.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentAccount {

    private BigInteger accountNumber;
    private Long clientId;
    private Long accountId;
    private BigDecimal balance;
    private List<Operation> history;
    private Long historyId;
    private Boolean isFrozen;

    // Прописать
    private String clientType = "physic";
    private String accountType = "current";
}
