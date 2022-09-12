package credit.scoring;

import credit.scoring.exeptions.*;
import lombok.Data;

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
            throws YouAreTooYoungException, WrongEducationInputValue, IncorrectNameInformation {
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

        valueIsNull(education, firstName, middleName, lastName);
        checkName(lastName, firstName, middleName);
        checkEducation(education);
        checkAge(age);
        checkWorkExperience(workExperience);
    }

    public CreditScoring() {}

    public final void checkName(String... name) throws IncorrectNameInformation {
        for (String item: name) {
            if (!Pattern.compile("[A-Z][a-z]+").matcher(item).matches()) {
                throw new IncorrectNameInformation("You wrote incorrect name: " + item);
            }
        }
    }

    public final void checkEducation(Education education) throws WrongEducationInputValue {
        if (!education.equals(Education.SCHOOL) && !education.equals(Education.UNIVERSITY) && !education.equals(Education.COLLEGE)) {
            throw new WrongEducationInputValue("You wrote incorrect input education");
        }
    }

    @SafeVarargs
    public final <T> void valueIsNull(T... items) {
        if (Arrays.stream(items).anyMatch(Objects::isNull)) {
            throw new NullPointerException("We can not analyze a null values");
        }
    }

    public final void checkWorkExperience(int workExperience) {
        if (workExperience < 0) {
            throw new IllegalArgumentException("You work experience can not be below zero");
        }
    }

    public final void checkAge(int age) throws YouAreTooYoungException {
        if (age < 0) {
            throw new IllegalArgumentException("Your age can not be below zero");
        }
        if (age < 18) {
            throw new YouAreTooYoungException("You are too young to get a credit");
        }
    }

    public final void creditScoringFileReader(List<CreditScoring> creditScoringList)
            throws IOException, YouAreTooYoungException, WrongEducationInputValue, IncorrectNameInformation {
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