package chap03;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author sskim
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PayData {

    private LocalDate billingDate;
    private LocalDate firstBillingDate;
    private int payAmount;
}
