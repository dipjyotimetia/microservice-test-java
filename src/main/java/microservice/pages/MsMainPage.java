package microservice.pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import microservice.common.MsCommon;
import microservice.common.MsConstants;
import microservice.helper.SeleniumHelper;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static microservice.common.MsConstants.buttonPressTimeoutMs;
import static microservice.helper.SeleniumHelper.printMethodName;

public class MsMainPage {

    public MsCatalogPage navigateToCatalogPage() {
        printMethodName();

        int buttonPressTimeoutOld = MsConstants.buttonPressTimeoutMs;
        buttonPressTimeoutMs = 240000;
        MsConstants.doRefreshOnFailure = true;

        MsCommon.waitForElementClick("//div[contains(text(),'List / add / remove items')]/..//a[contains(text(),'Catalog')]",MsCatalogPage.addItemXpath);

        buttonPressTimeoutMs = buttonPressTimeoutOld;
        MsConstants.doRefreshOnFailure = false;
        SeleniumHelper.myDontHurryTooMuch();

        return page(MsCatalogPage.class);
    }

    public MsCustomerPage navigateToCustomerPage() {
        printMethodName();

        int buttonPressTimeoutOld = MsConstants.buttonPressTimeoutMs;
        buttonPressTimeoutMs = 240000;
        MsConstants.doRefreshOnFailure = true;

        MsCommon.waitForElementClick("//a[contains(text(),'Customer')]","//h1[contains(text(),'Customer : View all')]");

        buttonPressTimeoutMs = buttonPressTimeoutOld;
        MsConstants.doRefreshOnFailure = false;
        SeleniumHelper.myDontHurryTooMuch();

        return page(MsCustomerPage.class);
    }

    public MsOrderPage navigateToOrderPage() {
        printMethodName();

        int buttonPressTimeoutOld = MsConstants.buttonPressTimeoutMs;
        buttonPressTimeoutMs = 240000;
        MsConstants.doRefreshOnFailure = true;

        MsCommon.waitForElementClick("//a[contains(text(),'Order')]",MsOrderPage.addOrderXpath);

        buttonPressTimeoutMs = buttonPressTimeoutOld;
        MsConstants.doRefreshOnFailure = false;
        SeleniumHelper.myDontHurryTooMuch();

        return page(MsOrderPage.class);
    }
}
