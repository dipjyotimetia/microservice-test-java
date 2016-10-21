//package testsuites;
//
//
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import static com.codeborne.selenide.Selenide.$;
//import static com.codeborne.selenide.Selenide.$$;
//import static microservice.helper.SeleniumHelper.printMethodName;
//import static microservice.testlibraries.MsAcceptanceTestLib.*;
//
//
//public class MsCatalogIntegrationTests extends MsAbstractSuite {
//
//    private  static final String service = "http://localhost:9002";
//    private static final String uri = "catalog";
//
//    @BeforeClass
//    public static void setup() throws Exception {
//
////        MsMainPage msMainPage = Selenide.open("http://localhost:8080", MsMainPage.class);
////
////        msMainPage.navigateToCatalogPage();
//
//        //SelenideElement element = $$(By.xpath("")).find(Condition.visible);
//
//    }
//
//    @Test
//    public static void getCatalogItem() throws Exception {
//        printMethodName();
//
//        /*Given*/ catalogItemExistsAtTheDatabase();
//        /*When*/ iGetTheCatalogItemFromRest(service, uri, "2");
//        /*Then*/ catalogItemNameShouldBe ("iPod touch");
//          /*And*/ catalogItemPriceShouldBe("21.0");
//
//
//    }
//}
//
//
//
//
