package microservice.common;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;


public class MsConstants {

    public static final String env = System.getProperty("env", "localenv");
    private static final String propertiesFile = env + ".properties";
    private static final Config config = ConfigFactory.load(propertiesFile).withFallback(ConfigFactory.load("general.properties"));

    //private static final Config config = ConfigFactory.load("general.properties");

    public static final long commonSelenideTimeout = MsConstants.config.getLong("common_selenide_timeout_ms");
    public static final long doNotHurryTooMuchDelayMs = MsConstants.config.getLong("do_not_hurry_too_much_delay_ms");

    public static boolean selenideClickElementByJavascript = config.getBoolean("selenide_click_element_by_javascript");

    //SELENIUM GRID
    //public static final String seleniumGridWinHubAddress = config.getString("seleniumGridWinHubAddress");

    //FOR COMMON METHODs waitForElementClick and waitForButtonClickAngular
    public static int elementClickTimeoutMs = config.getInt("element_click_timeout_ms");
    public static int elementClickRetryIntervalMs = config.getInt("element_click_retryinterval_ms");
    public static int elementClickXpathWaitTimeoutMs = config.getInt("element_click_xpath_wait_timeout_ms");
    public static int elementClickAngularWaitTimeoutMs = config.getInt("element_click_angular_wait_timeout_ms");
    public static boolean doRefreshOnFailure = config.getBoolean("do_refresh_on_failure");


    //Microservice constants
    public static String microserviceHost = config.getString("microservice_host");
    public static String catalogServiceUrl = config.getString("catalog_service_url");
    public static String catalogURI = config.getString("catalog_uri");
    public static String customerServiceUrl = config.getString("customer_service_url");
    public static String customerURI = config.getString("customer_uri");
}
