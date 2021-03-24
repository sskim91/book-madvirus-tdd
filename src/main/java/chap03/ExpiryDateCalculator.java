package chap03;

import java.time.LocalDate;

/**
 * @author sskim
 */
public class ExpiryDateCalculator {
    //이제 안씀
    @Deprecated
    public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
        return billingDate.plusMonths(1);
    }

    //리팩토링
    public LocalDate calculateExpiryDate(PayData payData) {
        return payData.getBillingDate().plusMonths(1);
    }
}
