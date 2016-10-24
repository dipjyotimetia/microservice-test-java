package microservice.testlibraries;

import com.fasterxml.jackson.databind.JsonNode;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import microservice.helper.SSHService;
import microservice.msrest.MsCatalogRest;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CatalogStepDefinitions {

    private static JsonNode catalogItem;

    @Given("^catalog item exists at the database$")
    public void catalogItemExistsAtTheDatabase() {

        //SSHService ssh = new SSHService("192.168.50.4", "vagrant", "vagrant",new File(""),"");
        SSHService ssh = new SSHService("192.168.50.4", "vagrant", "",new File("/Users/jariheikkila/.ssh/id_rsa"),"");

        try {
            System.out.println("RESPONSE: " + ssh.executeCommand("ifconfig","sshkey"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @When("i get the catalog item from rest (.*) (.*) (.*)")
    public void iGetTheCatalogItemFromRest(String service, String uri, String id) {

        catalogItem = MsCatalogRest.getSingleCatalogItemWithId(service, uri, id);
    }

    @Then("catalog item name should be (.*)")
    public void catalogItemNameShouldBe(String catalogItemName) {

        assertThat((catalogItem.get("name").asText()), is(catalogItemName));
        //assertThat((catalogItem.get("name").asText()), is("foo"));

        System.out.println("Catalog item name was OK: "+catalogItemName);

    }

    @And("catalog item price should be (.*)")
    public void catalogItemPriceShouldBe(String catalogItemPrice) {

        assertThat((catalogItem.get("price").asText()), is(catalogItemPrice));

        System.out.println("Catalog item price was OK: "+catalogItemPrice);

    }
}


