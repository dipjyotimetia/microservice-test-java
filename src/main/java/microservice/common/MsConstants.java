package microservice.common;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;


public class MsConstants {

    public static final String env = System.getProperty("env", "localenv");
    private static final String propertiesFile = env + ".properties";
    private static final Config config = ConfigFactory.load(propertiesFile).withFallback(ConfigFactory.load("general.properties"));

    //private static final Config config = ConfigFactory.load("general.properties");

    public static final long commonSelenideTimeout = MsConstants.config.getLong("common_selenide_timeout_ms");

    public static boolean selenideClickElementByJavascript = config.getBoolean("selenide_click_element_by_javascript");

    //SELENIUM GRID
    //public static final String seleniumGridWinHubAddress = config.getString("seleniumGridWinHubAddress");

    //FOR COMMON METHODs waitForElementClick and waitForButtonClickAngular
    public static int buttonPressTimeoutMs = config.getInt("buttonpress_timeout_ms");
    public static int buttonPressRetryIntervalMs = config.getInt("buttonpress_retryinterval_ms");
    public static int buttonPressXpathWaitTimeoutMs = config.getInt("buttonpress_xpath_wait_timeout_ms");
    public static int buttonPressAngularWaitTimeoutMs = config.getInt("buttonpress_angular_wait_timeout_ms");
    public static boolean doRefreshOnFailure = config.getBoolean("do_refresh_on_failure");


    //Microservice constants
    public static String microserviceHost = config.getString("microservice_host");
    public static String catalogServiceUrl = config.getString("catalog_service_url");
    public static String catalogURI = config.getString("catalog_uri");
    public static String customerServiceUrl = config.getString("customer_service_url");
    public static String customerURI = config.getString("customer_uri");
}
