package gpb.account.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GpbBankAccount extends AbstractAccount {
    private BigDecimal depositedFunds;
    private BigDecimal creditedFunds;
}
