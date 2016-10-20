package testsuites;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static microservice.helper.SeleniumHelper.printMethodName;


public class MsAcceptanceTests extends MsAbstractSuite {

    @BeforeClass
    public void preconditions() throws InterruptedException {
        printMethodName();

    }

    @Test
    public static void GoToCatalogPage() throws Exception {
        printMethodName();

        //openBrowserAndNavigateToCatalogPage();

    }
}




