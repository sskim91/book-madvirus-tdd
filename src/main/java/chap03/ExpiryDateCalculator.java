package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * @author sskim
 */
public class ExpiryDateCalculator {

    @Deprecated
    public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
        return billingDate.plusMonths(1);
    }

    //리팩토링
    public LocalDate calculateExpiryDate(PayData payData) {
        //상수를 변수로
        int addedMonths = payData.getPayAmount() == 100_000 ? 12 : payData.getPayAmount() / 10_000;

        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    /**
     * 첫 납부일 존재 여부에 따라 계산로직이 달라지므로
     * 첫 납부일이 존재할때는 아래 메서드 사용하는것으로 리팩터링
     */
    public LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);  //후보 만료일 구함

        if (payData.getFirstBillingDate() != null) {
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            if (dayOfFirstBilling != candidateExp.getDayOfMonth()) {    //첫 납부일의 일자와 후보 만료일의 일자가 다르면
                final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
                if (dayLenOfCandiMon < dayOfFirstBilling) {
                    return candidateExp.withDayOfMonth(dayLenOfCandiMon);   //첫 납부일의 일자를 후보 만료일의 일자로 사용
                }
                return candidateExp.withDayOfMonth(dayOfFirstBilling);
            } else {
                return candidateExp;
            }
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
