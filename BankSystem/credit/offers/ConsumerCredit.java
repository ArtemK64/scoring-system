package offers;

import lombok.Data;

import java.util.Objects;

@Data
public class ConsumerCredit extends Credit {
    private String product;

    ConsumerCredit(String nameOfBank, double interestRate, int amountOfMoney, boolean canPayBefore, int periodInMonth, String product) {
        super(nameOfBank, interestRate, amountOfMoney, canPayBefore, periodInMonth);
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConsumerCredit that = (ConsumerCredit) o;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product);
    }
}
