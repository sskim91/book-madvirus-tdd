package chap02;

/**
 * @author sskim
 */
public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) {
            return PasswordStrength.INVALID;
        }
        int metCounts = getMetCriteriaCounts(s);

        if (metCounts <= 1) {
            return PasswordStrength.WEAK;
        }
        if (metCounts == 2) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(String s) {
        int metCounts = 0;
        if (s.length() >= 8) {
            metCounts++;
        }
        if (meetsContainsNumberCriteria(s)) {
            metCounts++;
        }
        if (meetsContainsUppercaseCriteria(s)) {
            metCounts++;
        }
        return metCounts;
    }

    private boolean meetsContainsNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainsUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}
