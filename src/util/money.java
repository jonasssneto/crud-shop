package util;

public class money {
    public static String format(int cents) {
        if (cents < 0) {
            throw new IllegalArgumentException("Cents cannot be negative");
        }
        int dollars = cents / 100;
        int remainingCents = cents % 100;
        return String.format("%d.%02d", dollars, remainingCents);
    }
}
