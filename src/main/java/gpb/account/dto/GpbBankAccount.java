package gpb.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GpbBankAccount {
    private BigDecimal depositedFunds;
    private BigDecimal creditedFunds;
}
