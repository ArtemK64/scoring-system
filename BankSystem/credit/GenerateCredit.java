import offers.Credit;
import offers.exceptions.YouAreNotSuitableException;
import scoring.exeptions.*;

import java.io.IOException;

public class GenerateCredit {
    public static void main(String[] args)
            throws YouAreTooYoungException, IOException, IncorrectStringValueException, YouAreNotSuitableException {
        Credit.generateCredit();
    }
}