package microservice.testlibraries;


import com.fasterxml.jackson.databind.JsonNode;
import microservice.msrest.MsCatalogRest;


import static microservice.helper.SeleniumHelper.printMethodName;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MsAcceptanceTestLib {

    //public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    private static JsonNode catalogItem;

//    public static final String service = "http://localhost:9002";
//    public static final String uri = "catalog";
//
//    public static void main(String [ ] args) {
//        /*Given*/ catalogItemExistsAtTheDatabase();
//        /*When*/ iGetTheCatalogItemFromRest("2");
//        /*Then*/ catalogItemNameShouldBe ("iPod touch");
//        /*And*/ catalogItemPriceShouldBe("21.0");
//    }

    public static void catalogItemExistsAtTheDatabase() {
        printMethodName();

    }

    public static void iGetTheCatalogItemFromRest(String service, String uri, String id) {
        printMethodName();

        catalogItem = MsCatalogRest.getSingleCatalogItemWithId(service, uri, id);

    }

    public static void catalogItemNameShouldBe(String catalogItemName) {
        printMethodName();

        assertThat((catalogItem.get("name").asText()), is(catalogItemName));

        System.out.println("Catalog item name was OK: "+catalogItemName);

    }

    public static void catalogItemPriceShouldBe(String catalogItemPrice) {
        printMethodName();

        assertThat((catalogItem.get("price").asText()), is(catalogItemPrice));

        System.out.println("Catalog item price was OK: "+catalogItemPrice);
    }

}
