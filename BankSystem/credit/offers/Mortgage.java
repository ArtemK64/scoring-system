package offers;

import offers.exceptions.YouAreNotSuitableException;

import java.util.*;

public class Mortgage {
    public static List<Credit> generateMortgageOffers(List<Credit> creditList, String creditPotential) throws YouAreNotSuitableException {
        switch (creditPotential) {
            case "Good", "Great" -> {
                return creditList;
            }
            default -> throw new YouAreNotSuitableException("This type of credit is not suitable for you");
        }
    }
}