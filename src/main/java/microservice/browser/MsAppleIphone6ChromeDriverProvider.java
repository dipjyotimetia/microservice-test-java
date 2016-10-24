package microservice.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MsAppleIphone6ChromeDriverProvider extends BrowserBase {

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        return createMobileChromeDriver("Apple iPhone 6", desiredCapabilities);
    }
}
