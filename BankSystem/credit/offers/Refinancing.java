package offers;

import lombok.Data;
import scoring.CreditScoring;

import java.util.Objects;

@Data
public class Refinancing extends Credit {
    private int amountOfDebt;
    private double interestRateOfDebt;

    Refinancing(String nameOfBank, double interestRate, int amountOfMoney, boolean canPayBefore, int periodInMonth, int amountOfDebt, double interestRateOfDebt) {
        super(nameOfBank, interestRate, amountOfMoney, canPayBefore, periodInMonth);
        this.amountOfDebt = amountOfDebt;
        this.interestRateOfDebt = interestRateOfDebt;

        CreditScoring.checkNegativeValue(amountOfDebt, interestRateOfDebt);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Refinancing that = (Refinancing) o;
        return amountOfDebt == that.amountOfDebt && Double.compare(that.interestRateOfDebt, interestRateOfDebt) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), amountOfDebt, interestRateOfDebt);
    }
}
