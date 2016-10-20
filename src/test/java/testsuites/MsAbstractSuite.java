package testsuites;

import microservice.common.MsCommon;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static microservice.helper.SeleniumHelper.printMethodName;

public class MsAbstractSuite {

    @Parameters({ "browser" })
    @BeforeClass(alwaysRun = true)
    public void beforeClassSetUp(@Optional String browser) throws Exception {
        printMethodName();

        System.out.println("BROWSER: "+browser);
        if (browser != null) {
            com.codeborne.selenide.Configuration.browser = browser;
        }
        MsCommon.setUp();
    }

    @AfterClass(alwaysRun = true)
    public void afterClassTearDown() throws Exception {
        printMethodName();

        //Selenide.screenshot("afterClassTeardownScreenshot");

        //getWebDriver().quit();
    }


}
