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
    private int age;
    private boolean isMarried;
    private boolean hadCreditBefore;
    private int workExperience;
    private boolean haveCar;
    private Education education;

    private enum Education {
        UNIVERSITY, COLLEGE, SCHOOL
    }

    CreditScoring(int age, boolean isMarried, boolean hadCreditBefore, int workExperience, boolean haveCar, Education education)
            throws IncorrectInputIntValues, YouAreTooYoungException, WrongEducationInputValue {
        this.age = age;
        this.isMarried = isMarried;
        this.hadCreditBefore = hadCreditBefore;
        this.workExperience = workExperience;
        this.haveCar = haveCar;
        this.education = education;

        checkEducation(education); // wrong education input value
        valueIsNull(education); // if something is null
        checkIntValues(age, workExperience); // if age or workExperience below zero
        checkAge(age); // if age is below then 18
    }

    public CreditScoring() {}

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

    public final void checkIntValues(int... items) throws IncorrectInputIntValues {
        if (Arrays.stream(items).anyMatch(x -> x < 0)) {
            throw new IncorrectInputIntValues("You wrote incorrect values");
        }
    }

    public final void checkAge(int age) throws YouAreTooYoungException {
        if (age < 18) {
            throw new YouAreTooYoungException("You are too young to get a credit");
        }
    }

    public final void creditScoringFileReader(List<CreditScoring> creditScoringList)
            throws IOException, YouAreTooYoungException, IncorrectInputIntValues, WrongEducationInputValue {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("creditScoringClientInformation.txt"), StandardCharsets.UTF_8)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher =  Pattern.compile("\\w+").matcher(line);
                List<String> listOfFileLine = new ArrayList<>();
                while (matcher.find()) {
                    listOfFileLine.add(matcher.group());
                }
                creditScoringList.add(new CreditScoring(
                        Integer.parseInt(listOfFileLine.get(0)),
                        Boolean.parseBoolean(listOfFileLine.get(1)),
                        Boolean.parseBoolean(listOfFileLine.get(2)),
                        Integer.parseInt(listOfFileLine.get(3)),
                        Boolean.parseBoolean(listOfFileLine.get(4)),
                        Education.valueOf(listOfFileLine.get(5).toUpperCase())
                ));
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