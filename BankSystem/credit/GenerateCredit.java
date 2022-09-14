import scoring.CreditScoring;
import scoring.exeptions.*;
import java.io.IOException;
import java.util.*;

public class GenerateCredit {
    public static void main(String[] args) throws YouAreTooYoungException, WrongEducationInputValue, IncorrectStringValue, IOException {

        // show client information and credit potential
        CreditScoring creditScoring = new CreditScoring();
        List<CreditScoring> creditScoringList = new ArrayList<>();
        creditScoring.creditScoringFileReader(creditScoringList);

        for (CreditScoring cs: creditScoringList) {
            System.out.println("Hello, " + cs.getFirstName() + ". Credit potential: " + creditScoring.creditPotential(cs));
        }


    }
}