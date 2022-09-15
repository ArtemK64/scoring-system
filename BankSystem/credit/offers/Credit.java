package offers;

import offers.exceptions.YouAreNotSuitableException;
import scoring.CreditScoring;
import lombok.Data;
import scoring.exeptions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

@Data
public class Credit {
    private String nameOfBank;
    private double interestRate;
    private int amountOfMoney;
    private boolean canPayBefore;
    private int periodInMonth;

    Credit(String nameOfBank, double interestRate, int amountOfMoney, boolean canPayBefore, int periodInMonth) {
        this.nameOfBank = nameOfBank;
        this.interestRate = interestRate;
        this.amountOfMoney = amountOfMoney;
        this.canPayBefore = canPayBefore;
        this.periodInMonth = periodInMonth;

        CreditScoring.checkNegativeValue(interestRate, amountOfMoney, periodInMonth);
    }

    public static void readFileOffers(List<Credit> creditList, String fileName)
            throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isBlank()) {
                    Matcher matcher = Pattern.compile("[\\w.]+").matcher(line);
                    List<String> listOfCarCreditOffers = new ArrayList<>();
                    while (matcher.find()) {
                        listOfCarCreditOffers.add(matcher.group());
                    }
                    creditList.add(new Credit(
                            listOfCarCreditOffers.get(0),
                            Double.parseDouble(listOfCarCreditOffers.get(1)),
                            Integer.parseInt(listOfCarCreditOffers.get(2)),
                            Boolean.parseBoolean(listOfCarCreditOffers.get(3)),
                            Integer.parseInt(listOfCarCreditOffers.get(4))
                    ));
                }
            }
        }
    }

    private List<Credit> sortFields(List<Credit> creditList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How do you want to sort your fields?" +
                "\nA. By name of bank" +
                "\nB. By interest rate" +
                "\nC. By amount of money" +
                "\nD. By period of month" +
                "\nE. Do not sort"
        );

        String inputValue = scanner.next();

        switch (inputValue.toUpperCase()) {
            case "A" -> creditList.sort(Comparator.comparing(Credit::getNameOfBank));
            case "B" -> creditList.sort(Comparator.comparing(Credit::getInterestRate));
            case "C" -> creditList.sort(Comparator.comparing(Credit::getAmountOfMoney));
            case "D" -> creditList.sort(Comparator.comparing(Credit::getPeriodInMonth));
            case "E" -> {
                return creditList;
            }
            default -> throw new IllegalArgumentException("You wrote incorrect value");
        }
        return creditList;
    }

    private void showCanPayBeforeOffers(List<Credit> creditList) {
        for (Credit credit: creditList) {
            if (credit.isCanPayBefore()) {
                System.out.print(credit);
            }
        }
    }

    public static <T> void printList(List<T> list) {
        for (T t: list) {
            System.out.println(t);
        }
    }

    public static void generateCredit(List<Credit> creditList)
            throws IOException, YouAreTooYoungException, IncorrectStringValueException, WrongEducationInputValueException, YouAreNotSuitableException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type of credit?" +
                "A. Car credit" +
                "B. Consumer credit" +
                "C. Mortgage" +
                "D. Refinancing");
        String typeOfCredit = scanner.next();

        CreditScoring creditScoring = new CreditScoring();
        CreditScoring.creditScoringFileReader(creditScoring);

        System.out.println("Hello, " + creditScoring.getFirstName() + ". Credit potential: " + creditScoring.creditPotential(creditScoring));

        switch (typeOfCredit) {
            case "A" -> {
                readFileOffers(creditList, "carCreditOffers.txt");
                CarCredit.generateCarCreditOffers(creditList, creditScoring.creditPotential(creditScoring));
            }
        }
    }
}