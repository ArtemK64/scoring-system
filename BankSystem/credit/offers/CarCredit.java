package offers;

import lombok.Data;
import scoring.CreditScoring;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public CarCredit() {}

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

    private void readCarCreditOffers(List<CarCredit> carCreditList) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Prince of your car: ");
        int priceOfCar = scanner.nextInt();

        System.out.print("Model of car: ");
        String modelOfCar = scanner.next();

        System.out.print("Manufacturer: ");
        String manufacturerOfCar = scanner.next();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("carCreditOffers.txt"), StandardCharsets.UTF_8)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isBlank()) {
                    Matcher matcher = Pattern.compile("[\\w.]+").matcher(line);
                    List<String> listOfCarCreditOffers = new ArrayList<>();
                    while (matcher.find()) {
                        listOfCarCreditOffers.add(matcher.group());
                    }
                    carCreditList.add(new CarCredit(
                            listOfCarCreditOffers.get(0),
                            Double.parseDouble(listOfCarCreditOffers.get(1)),
                            Integer.parseInt(listOfCarCreditOffers.get(2)),
                            Boolean.parseBoolean(listOfCarCreditOffers.get(3)),
                            Integer.parseInt(listOfCarCreditOffers.get(4)),
                            priceOfCar,
                            modelOfCar,
                            manufacturerOfCar
                    ));
                }
            }
        }
    }

    public final void generateCarCredit(List<CarCredit> carCreditList) {

    }
}