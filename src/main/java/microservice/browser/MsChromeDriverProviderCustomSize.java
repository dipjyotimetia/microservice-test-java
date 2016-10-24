package microservice.browser;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MsChromeDriverProviderCustomSize extends BrowserBase {
    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        // Set ACCEPT_SSL_CERTS  variable to true
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);

        return setCustomWindowSize(new ChromeDriver(desiredCapabilities),1000,800);
    }
}
