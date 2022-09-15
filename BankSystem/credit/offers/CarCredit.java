package offers;

import offers.exceptions.YouAreNotSuitableException;

import java.util.*;

public class CarCredit {
    public static void generateCarCreditOffers(List<Credit> creditList, String creditPotential)
            throws YouAreNotSuitableException {
        switch (creditPotential) {
            case "Average", "Good", "Great" -> {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Price of your car: ");
                int priceOfCar = scanner.nextInt();

                System.out.print("Model of car: ");
                String modelOfCar = scanner.next();

                System.out.print("Was produced (country): ");
                String manufacturerOfCar = scanner.next();

                for (Credit credit: creditList) {
                    if (priceOfCar / 1.2 < credit.getAmountOfMoney() && priceOfCar > credit.getAmountOfMoney()) {
                        System.out.println(credit);
                    }
                }
            }
            default -> throw new YouAreNotSuitableException("This type of credit is not suitable for you");
        }
    }
}