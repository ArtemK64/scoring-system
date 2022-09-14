package offers;

import scoring.CreditScoring;
import lombok.Data;

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

    public Credit() {}

    private boolean checkTitleInInputFile(String line) {
        return Pattern.compile("[A-Za-z_]+").matcher(line).matches();
    }

    private void addValuesInCreditClass(List<Credit> offersList, String line) {
        Matcher matcher = Pattern.compile("[\\w-.]+").matcher(line);
        List<String> listOfFileLine = new ArrayList<>();
        while (matcher.find()) {
            listOfFileLine.add(matcher.group());
        }
        offersList.add(new Credit(
                listOfFileLine.get(0),
                Double.parseDouble(listOfFileLine.get(1)),
                Integer.parseInt(listOfFileLine.get(2)),
                Boolean.parseBoolean(listOfFileLine.get(3)),
                Integer.parseInt(listOfFileLine.get(4))
        ));
    }

    public final void offersFileReader(
            List<Credit> carCreditOffers, List<Credit> consumerCreditOffers, List<Credit> mortgagesOffers, List<Credit> refinancingOffers)
            throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("offersInformation.txt"), StandardCharsets.UTF_8)) {
            String line;

        switch (bufferedReader.readLine().toLowerCase()) {
            case "car_credit":
                while((line = bufferedReader.readLine()) != null) {
                    if (!line.isBlank()) {
                        if (checkTitleInInputFile(line)) {
                            break;
                        }
                        addValuesInCreditClass(carCreditOffers, line);
                    }
                }

            case "consumer_credits":
                while((line = bufferedReader.readLine()) != null) {
                    if (!line.isBlank()) {
                        if (checkTitleInInputFile(line)) {
                            break;
                        }
                        addValuesInCreditClass(consumerCreditOffers, line);
                    }
                }

            case "mortgage":
                while((line = bufferedReader.readLine()) != null) {
                    if (!line.isBlank()) {
                        if (checkTitleInInputFile(line)) {
                            break;
                        }
                        addValuesInCreditClass(mortgagesOffers, line);
                    }
                }

            case "refinancing":
                while((line = bufferedReader.readLine()) != null) {
                    if (!line.isBlank()) {
                        if (checkTitleInInputFile(line)) {
                            break;
                        }
                        addValuesInCreditClass(refinancingOffers, line);
                    }
                }
            }
        }
    }

    private void sortFields(String inputValue, List<Credit> creditList) {
        switch (inputValue.toUpperCase()) {
            case "A" -> creditList.sort(Comparator.comparing(Credit::getNameOfBank));
            case "B" -> creditList.sort(Comparator.comparing(Credit::getInterestRate));
            case "C" -> creditList.sort(Comparator.comparing(Credit::getAmountOfMoney));
            case "D" -> creditList.sort(Comparator.comparing(Credit::getPeriodInMonth));
            default -> throw new IllegalArgumentException("You wrote incorrect value");
        }
    }

    private void showOffers(String typeOfCredit) throws IOException {
        switch (typeOfCredit.toUpperCase()) {
            case "A" -> { // car credit
                try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("offersInformation.txt"), StandardCharsets.UTF_8)) {
                    String line;
                    if ((line = bufferedReader.readLine()) != null) {
                        if (!line.isBlank()) {
                            List<String> stringList =
                        }
                    }
                }
            }
        }
    }


}