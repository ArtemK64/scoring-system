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

    public static void readFileOffers(List<Credit> creditList, String fileName) throws IOException {
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

    private static List<Credit> sortFields(List<Credit> creditList) throws IncorrectStringValueException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("""

                How do you want to sort your fields?
                A. By name of bank
                B. By interest rate
                C. By amount of money
                D. By period of month
                E. Do not sort"""
        );
        System.out.print("\nInput value: ");
        String inputValue = scanner.next();

        switch (inputValue.toUpperCase()) {
            case "A" -> creditList.sort(Comparator.comparing(Credit::getNameOfBank));
            case "B" -> creditList.sort(Comparator.comparing(Credit::getInterestRate));
            case "C" -> creditList.sort(Comparator.comparing(Credit::getAmountOfMoney));
            case "D" -> creditList.sort(Comparator.comparing(Credit::getPeriodInMonth));
            case "E" -> {
                return creditList;
            }
            default -> throw new IncorrectStringValueException("You wrote incorrect value");
        }
        return creditList;
    }

    private static List<Credit> showCanPayBeforeOffers(List<Credit> creditList, String inputValue) throws IncorrectStringValueException {
        switch (inputValue.toUpperCase()) {
            case "YES" -> {
                List<Credit> resultCreditList = new ArrayList<>();
                for (Credit credit: creditList) {
                    if (credit.isCanPayBefore()) {
                        resultCreditList.add(credit);
                    }
                }
                return resultCreditList;
            }
            case "NO" -> {
                return creditList;
            }
            default -> throw new IncorrectStringValueException("You wrote incorrect value");
        }
    }

    public static <T> void printList(List<T> list) {
        for (T t: list) {
            System.out.println(t);
        }
    }

    public static void generateCredit()
            throws IOException, YouAreTooYoungException, IncorrectStringValueException, YouAreNotSuitableException {
        CreditScoring creditScoring = new CreditScoring();
        creditScoring = CreditScoring.creditScoringFileReader(creditScoring);

        System.out.println("\nHello, " + creditScoring.getFirstName() + ". Credit potential: " + creditScoring.creditPotential(creditScoring));

        List<Credit> creditList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Type of credit?
                A. Car credit
                B. Consumer credit
                C. Mortgage
                D. Refinancing""");
        System.out.print("\nInput value: ");
        String typeOfCredit = scanner.next();

        String creditScoringPotential = creditScoring.creditPotential(creditScoring);

        switch (typeOfCredit.toUpperCase()) {
            case "A" -> {
                readFileOffers(creditList, "carCreditOffers.txt");
                creditList = CarCredit.generateCarCreditOffers(creditList, creditScoringPotential);
            }
            case "B" -> {
                readFileOffers(creditList, "consumerCreditOffers.txt");
                ConsumerCredit.generateConsumerCreditOffers(creditList, creditScoringPotential);
            }
            case "C" -> {
                readFileOffers(creditList, "mortgageOffers.txt");
                Mortgage.generateMortgageOffers(creditList, creditScoringPotential);
            }
            case "D" -> {
                readFileOffers(creditList, "refinancingOffers.txt");
                creditList = Refinancing.generateRefinancingCredit(creditList, creditScoringPotential);
            }
            default -> throw new IncorrectStringValueException("You wrote incorrect value");
        }
        System.out.println("Should we show offers which you can pay before period will be end?");
        System.out.print("Input value: ");
        String inputValue = scanner.next();

        creditList = showCanPayBeforeOffers(creditList, inputValue);

        if (creditList.isEmpty()) {
            System.out.println("\nUnfortunately, there are no offers for you :(");
        } else {
            printList(sortFields(creditList));
        }
    }
}