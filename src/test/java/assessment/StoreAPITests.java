package assessment;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

public class StoreAPITests {

    private StoreAPI storeAPI;

    @BeforeClass
    public void setup() {
        storeAPI = new StoreAPI();
    }

    @Test
    public void testCreateOrder() {
        Response response = storeAPI.createOrder(62, 123, 2, "2024-10-03T10:00:00.000Z", "placed", true);

        System.out.println("POST Response: " + response.asString());

        response.then()
                .statusCode(200)
                .body("id", equalTo(62))
                .body("petId", equalTo(123))
                .body("status", equalTo("placed"));
    }

    @Test(dependsOnMethods = "testCreateOrder")
    public void testGetOrderById() {
        Response response = storeAPI.getOrderById(62);
        
        System.out.println("GET Order Response: " + response.asString());

        response.then()
                .statusCode(200)
                .body("id", equalTo(62))
                .body("petId", equalTo(123))
                .body("status", equalTo("placed"));
    }

  
    @Test(dependsOnMethods = "testGetOrderById")
    public void testDeleteOrderById() {
        Response response = storeAPI.deleteOrderById(62);

        System.out.println("DELETE Response: " + response.asString());
        
        response.then().statusCode(200);
    }

    @Test(dependsOnMethods = "testDeleteOrderById")
    public void testGetDeletedOrder() {
        // Get the deleted order (expect 404)
        Response response = storeAPI.getOrderById(62);

        System.out.println("GET Deleted Order Response: " + response.asString());

        // Validate that the order is not found (404)
        response.then().statusCode(404);
    }
    
    @Test(dependsOnMethods = "testGetOrderById")
    public void testGetInventory() {
    
        Response response = storeAPI.getInventory();

        System.out.println("GET Inventory Response: " + response.asString());

        response.then().statusCode(200);
    }

}
