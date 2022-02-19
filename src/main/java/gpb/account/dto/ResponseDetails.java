package gpb.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetails {
    private Integer accountNo;
    private String message;
    private Boolean isOk;
}
