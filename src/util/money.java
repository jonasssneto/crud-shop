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

    public static int parse(String money) {
        if (money == null || money.trim().isEmpty()) {
            throw new IllegalArgumentException("Valor monetário vazio.");
        }

        money = money.trim().replace(",", ".");

        try {
            double value = Double.parseDouble(money);
            if (value < 0) {
                throw new IllegalArgumentException("O valor não pode ser negativo.");
            }

            return (int) Math.round(value * 100);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato inválido. Use por exemplo: 10,99 ou 10.99");
        }
    }
}
