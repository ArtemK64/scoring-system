package offers;

import offers.exceptions.YouAreNotSuitableException;

import java.util.*;

public class CarCredit {
    public static List<Credit> generateCarCreditOffers(List<Credit> creditList, String creditPotential) throws YouAreNotSuitableException {
        switch (creditPotential) {
            case "Average", "Good", "Great" -> {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Price of your car: ");
                int priceOfCar = scanner.nextInt();

                System.out.print("Model of car: ");
                String modelOfCar = scanner.next();

                System.out.print("Was produced (country): ");
                String manufacturerOfCar = scanner.next();

                List<Credit> resultCreditList = new ArrayList<>();
                for (Credit credit: creditList) {
                    if (priceOfCar / 1.2 < credit.getAmountOfMoney() && priceOfCar > credit.getAmountOfMoney()) {
                        resultCreditList.add(credit);
                    }
                }
                return resultCreditList;
            }
            default -> throw new YouAreNotSuitableException("This type of credit is not suitable for you");
        }
    }
}