package offers;

import offers.exceptions.YouAreNotSuitableException;

import java.util.*;

public class Mortgage {
    public static void generateMortgageOffers(List<Credit> creditList, String creditPotential)
            throws YouAreNotSuitableException {
        switch(creditPotential) {
            case "Good", "Great" -> {
                Credit.printList(creditList);
            }

            default -> throw new YouAreNotSuitableException("This type of credit is not suitable for you");
        }
    }
}