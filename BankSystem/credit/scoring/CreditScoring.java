package scoring;

import lombok.Data;
import scoring.exeptions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

@Data
public class CreditScoring {
    private String lastName;
    private String firstName;
    private String middleName;
    private int id;
    private int age;
    private boolean isMarried;
    private boolean hadCreditBefore;
    private int workExperience;
    private boolean haveCar;
    private Education education;

    private enum Education {
        UNIVERSITY, COLLEGE, SCHOOL
    }

    CreditScoring(String lastName, String firstName, String middleName, int id, int age, boolean isMarried, boolean hadCreditBefore, int workExperience, boolean haveCar, Education education)
            throws YouAreTooYoungException, WrongEducationInputValue, IncorrectStringValue {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.id = id;
        this.age = age;
        this.isMarried = isMarried;
        this.hadCreditBefore = hadCreditBefore;
        this.workExperience = workExperience;
        this.haveCar = haveCar;
        this.education = education;

        checkEducation(education);
        checkStringValue(lastName, firstName, middleName);
        checkNegativeValue(workExperience, age);
        checkAge(age);
    }

    public CreditScoring() {}

    public static void checkNegativeValue(double... items) {
        for (double item: items) {
            if (item < 0) {
                throw new IllegalArgumentException("Some values can not be below zero. Check your input information again");
            }
        }
    }

    public static void checkStringValue(String... items) throws IncorrectStringValue {
        for (String item: items) {
            if (!Pattern.compile("[a-z]+").matcher(item.toLowerCase()).matches()) {
                throw new IncorrectStringValue("You wrote incorrect information. You can not write another symbols except letters in some fields");
            }
        }
    }

    private void checkEducation(Education education) throws WrongEducationInputValue { // ?
        if (!education.equals(Education.SCHOOL) && !education.equals(Education.UNIVERSITY) && !education.equals(Education.COLLEGE)) {
            throw new WrongEducationInputValue("You wrote incorrect input education");
        }
    }

    private void checkAge(int age) throws YouAreTooYoungException {
        if (age < 18) {
            throw new YouAreTooYoungException("You are too young to get a credit");
        }
    }

    public final void creditScoringFileReader(List<CreditScoring> creditScoringList)
            throws IOException, YouAreTooYoungException, WrongEducationInputValue, IncorrectStringValue {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("creditScoringClientInformation.txt"), StandardCharsets.UTF_8)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isBlank()) {
                    Matcher matcher =  Pattern.compile("[\\w-]+").matcher(line);
                    List<String> listOfFileLine = new ArrayList<>();
                    while (matcher.find()) {
                        listOfFileLine.add(matcher.group());
                    }
                    creditScoringList.add(new CreditScoring(
                            listOfFileLine.get(0),
                            listOfFileLine.get(1),
                            listOfFileLine.get(2),
                            Integer.parseInt(listOfFileLine.get(3)),
                            Integer.parseInt(listOfFileLine.get(4)),
                            Boolean.parseBoolean(listOfFileLine.get(5)),
                            Boolean.parseBoolean(listOfFileLine.get(6)),
                            Integer.parseInt(listOfFileLine.get(7)),
                            Boolean.parseBoolean(listOfFileLine.get(8)),
                            Education.valueOf(listOfFileLine.get(9).toUpperCase())
                    ));
                }
            }
        }
    }

    private int educationScoring(CreditScoring creditScoring) {
        int result = 0;
        switch (creditScoring.getEducation()) {
            case SCHOOL -> result = 20;
            case COLLEGE -> result = 22;
            case UNIVERSITY -> result = 30;
        }
        return result;
    }

    private int ageScoring(CreditScoring creditScoring) {
        if (creditScoring.getAge() < 35) return 5;
        if (creditScoring.getAge() > 45) return  35;
        else return 30;
    }

    private int isMarriedScoring(CreditScoring creditScoring) {
        return creditScoring.isMarried() ? 30 : 10;
    }

    private int hadCreditBeforeScoring(CreditScoring creditScoring) {
        return creditScoring.isHadCreditBefore() ? 40 : 10;
    }

    private int workExperienceScoring(CreditScoring creditScoring) {
        if (creditScoring.getWorkExperience() < 1) return 15;
        if (creditScoring.getWorkExperience() < 3) return 18;
        if (creditScoring.getWorkExperience() < 6) return 19;
        else return 25;
    }

    private int haveCarScoring(CreditScoring creditScoring) {
        return creditScoring.isHaveCar() ? 50 : 10;
    }

    private int countScoring(CreditScoring creditScoring) {
        return educationScoring(creditScoring) +
                ageScoring(creditScoring) +
                isMarriedScoring(creditScoring) +
                hadCreditBeforeScoring(creditScoring) +
                workExperienceScoring(creditScoring) +
                haveCarScoring(creditScoring);
    }

    public final String creditPotential(CreditScoring creditScoring) {
        int creditScoringResult = countScoring(creditScoring);
        if (creditScoringResult < 75) return "Unfortunately, you will not get a credit";
        if (creditScoringResult < 90) return "Bad";
        if (creditScoringResult < 140) return "Below average";
        if (creditScoringResult < 180) return "Average";
        if (creditScoringResult < 200) return "Good";
        else return "Great";
    }
}