package microservice.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MsSamsungGalaxyNoteChromeDriverProvider extends BrowserBase {

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        return createMobileChromeDriver("Samsung Galaxy Note", desiredCapabilities);
    }
}
