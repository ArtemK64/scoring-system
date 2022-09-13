package offers;

import lombok.Data;
import scoring.CreditScoring;
import scoring.exeptions.IncorrectStringValue;

import java.util.Objects;

@Data
public class Mortgage extends Credit {
    private String city;
    private String street;
    private String houseNumber;
    private String apartmentNumber;

    Mortgage(String nameOfBank, double interestRate, int amountOfMoney, boolean canPayBefore, int periodInMonth, String city, String street, String houseNumber, String apartmentNumber)
            throws IncorrectStringValue {
        super(nameOfBank, interestRate, amountOfMoney, canPayBefore, periodInMonth);
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;

        CreditScoring.checkStringValue(city, street);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mortgage mortgage = (Mortgage) o;
        return Objects.equals(city, mortgage.city) && Objects.equals(street, mortgage.street) && Objects.equals(houseNumber, mortgage.houseNumber) && Objects.equals(apartmentNumber, mortgage.apartmentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), city, street, houseNumber, apartmentNumber);
    }
}
