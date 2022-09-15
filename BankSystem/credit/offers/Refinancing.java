package offers;

import java.util.*;

public class Refinancing {
    public static void generateRefinancingCredit(List<Credit> creditList, String scoringPotential) {
        switch (scoringPotential) {
            case "Average", "Good", "Great" -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("What amount of debt do you have?");
                int amountOfDebt = scanner.nextInt();

                System.out.println("What interest rate do you have of your credit?");
                double interestRateOfDebt = scanner.nextDouble();

                for (Credit credit: creditList) {
                    if (amountOfDebt / 1.2 < credit.getAmountOfMoney() && amountOfDebt > credit.getAmountOfMoney() && credit.getInterestRate() < interestRateOfDebt) {
                        System.out.println(credit);
                    }
                }
            }
        }
    }
}