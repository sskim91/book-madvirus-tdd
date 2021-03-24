package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

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

        //상수를 변수로
        int addedMonths = payData.getPayAmount() / 10_000;

        if (payData.getFirstBillingDate() != null) {
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            if (payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()) {
                if (YearMonth.from(candidateExp).lengthOfMonth() < payData.getFirstBillingDate().getDayOfMonth()) {
                    return candidateExp.withDayOfMonth(YearMonth.from(candidateExp).lengthOfMonth());
                }
                return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
