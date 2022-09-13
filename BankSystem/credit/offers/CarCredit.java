package offers;

import lombok.Data;
import scoring.CreditScoring;

import java.util.Objects;

@Data
public class CarCredit extends Credit {
    private int carPrice;
    private String carModel;
    private String manufacturer;

    CarCredit(String nameOfBank, double interestRate, int amountOfMoney, boolean canPayBefore, int periodInMonth, int carPrice, String carModel, String manufacturer) {
        super(nameOfBank, interestRate, amountOfMoney, canPayBefore, periodInMonth);
        this.carPrice = carPrice;
        this.carModel = carModel;
        this.manufacturer = manufacturer;

        CreditScoring.checkNegativeValue(carPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CarCredit credit = (CarCredit) o;
        return carPrice == credit.carPrice && Objects.equals(carModel, credit.carModel) && Objects.equals(manufacturer, credit.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), carPrice, carModel, manufacturer);
    }
}
