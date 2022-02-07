package gpb.account.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Operation {
    private AbstractAccount sender;
    private AbstractAccount receiver;
    private BigDecimal amount;
    private String date;
}
