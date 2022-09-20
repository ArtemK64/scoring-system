package offers;

import offers.exceptions.YouAreNotSuitableException;

import java.util.*;

public class Refinancing {
    public static List<Credit> generateRefinancingCredit(List<Credit> creditList, String scoringPotential) throws YouAreNotSuitableException {
        List<Credit> resultCreditList = new ArrayList<>();
        switch (scoringPotential) {
            case "Average", "Good", "Great" -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("What amount of debt do you have?");
                System.out.print("Input value: ");
                int amountOfDebt = scanner.nextInt();

                System.out.println("What interest rate do you have of your credit?");
                System.out.print("Input value: ");
                double interestRateOfDebt = scanner.nextDouble();

                for (Credit credit: creditList) {
                    if (amountOfDebt / 1.2 < credit.getAmountOfMoney() && amountOfDebt > credit.getAmountOfMoney() && credit.getInterestRate() < interestRateOfDebt) {
                        resultCreditList.add(credit);
                    }
                }
            }
            default -> throw new YouAreNotSuitableException("This type of credit is not suitable for you");
        }
        return resultCreditList;
    }
}