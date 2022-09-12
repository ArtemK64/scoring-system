import credit.scoring.CreditScoring;
import credit.scoring.exeptions.*;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args)
            throws YouAreTooYoungException, IOException, WrongEducationInputValue, IncorrectNameInformation {
        CreditScoring creditScoring = new CreditScoring();
        List<CreditScoring> creditScoringList = new ArrayList<>();
        creditScoring.creditScoringFileReader(creditScoringList);

        for (CreditScoring cs: creditScoringList) {
            System.out.println(cs.getFirstName() + ", your credit potential: " + creditScoring.creditPotential(cs));
        }
    }
}