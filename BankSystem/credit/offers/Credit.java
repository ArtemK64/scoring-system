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

    Credit() {}

    public final void offersFileReader(
            List<Credit> carCreditOffers, List<Credit> consumerCreditOffers, List<Credit> mortgagesOffers, List<Credit> refinancingOffers)
            throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("offersInformation.txt"), StandardCharsets.UTF_8)) {
            String line;

        switch (bufferedReader.readLine().toLowerCase()) {
            case "car_credit":
                while((line = bufferedReader.readLine()) != null) {
                    if (!line.isBlank()) {
                        if (Pattern.compile("[A-Za-z_]+").matcher(line).matches()) {
                            break;
                        }
                        Matcher matcher = Pattern.compile("[\\w-.]+").matcher(line);
                        List<String> listOfFileLine = new ArrayList<>();
                        while (matcher.find()) {
                            listOfFileLine.add(matcher.group());
                        }
                        carCreditOffers.add(new Credit(
                                listOfFileLine.get(0),
                                Double.parseDouble(listOfFileLine.get(1)),
                                Integer.parseInt(listOfFileLine.get(2)),
                                Boolean.parseBoolean(listOfFileLine.get(3)),
                                Integer.parseInt(listOfFileLine.get(4))
                        ));
                    }
                }

            case "consumer_credits":
                while((line = bufferedReader.readLine()) != null) {
                    if (!line.isBlank()) {
                        if (Pattern.compile("[A-Za-z_]+").matcher(line).matches()) {
                            break;
                        }
                        Matcher matcher = Pattern.compile("[\\w-.]+").matcher(line);
                        List<String> listOfFileLine = new ArrayList<>();
                        while (matcher.find()) {
                            listOfFileLine.add(matcher.group());
                        }
                        consumerCreditOffers.add(new Credit(
                                listOfFileLine.get(0),
                                Double.parseDouble(listOfFileLine.get(1)),
                                Integer.parseInt(listOfFileLine.get(2)),
                                Boolean.parseBoolean(listOfFileLine.get(3)),
                                Integer.parseInt(listOfFileLine.get(4))
                        ));
                    }
                }

            case "mortgage":
                while((line = bufferedReader.readLine()) != null) {
                    if (!line.isBlank()) {
                        if (Pattern.compile("[A-Za-z_]+").matcher(line).matches()) {
                            break;
                        }
                        Matcher matcher = Pattern.compile("[\\w-.]+").matcher(line);
                        List<String> listOfFileLine = new ArrayList<>();
                        while (matcher.find()) {
                            listOfFileLine.add(matcher.group());
                        }
                        mortgagesOffers.add(new Credit(
                                listOfFileLine.get(0),
                                Double.parseDouble(listOfFileLine.get(1)),
                                Integer.parseInt(listOfFileLine.get(2)),
                                Boolean.parseBoolean(listOfFileLine.get(3)),
                                Integer.parseInt(listOfFileLine.get(4))
                        ));
                    }
                }

            case "refinancing":
                while((line = bufferedReader.readLine()) != null) {
                    if (!line.isBlank()) {
                        if (Pattern.compile("[A-Za-z_]+").matcher(line).matches()) {
                            break;
                        }
                        Matcher matcher = Pattern.compile("[\\w-.]+").matcher(line);
                        List<String> listOfFileLine = new ArrayList<>();
                        while (matcher.find()) {
                            listOfFileLine.add(matcher.group());
                        }
                        refinancingOffers.add(new Credit(
                                listOfFileLine.get(0),
                                Double.parseDouble(listOfFileLine.get(1)),
                                Integer.parseInt(listOfFileLine.get(2)),
                                Boolean.parseBoolean(listOfFileLine.get(3)),
                                Integer.parseInt(listOfFileLine.get(4))
                        ));
                    }
                }
            }
        }
    }

    private void sortByNameOfBank(List<Credit> creditList) { // ?
        Comparator<Credit> compareByNameOfBank = Comparator.comparing(Credit::getNameOfBank);
        creditList = creditList.stream().sorted(compareByNameOfBank).toList();
    }
}