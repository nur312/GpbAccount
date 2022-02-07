package gpb.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    private CurrentAccount sender;
    private CurrentAccount receiver;
    private BigDecimal amount;
    private String date;
}
