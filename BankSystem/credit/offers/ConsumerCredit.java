package offers;

import offers.exceptions.YouAreNotSuitableException;

import java.util.*;

public class ConsumerCredit {
    public static void generateConsumerCreditOffers(List<Credit> consumerCreditList, String creditPotential)
            throws YouAreNotSuitableException {
        if (creditPotential.equals("You will not get a credit")) {
            throw new YouAreNotSuitableException("This type of credit is not suitable for you");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What type of product do you want to get?");
            String typeOfProduct = scanner.next();

            Credit.printList(consumerCreditList);
        }
    }
}
