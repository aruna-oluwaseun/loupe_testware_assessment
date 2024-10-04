package assessment;

import io.restassured.response.Response;
import org.json.JSONObject;

public class StoreAPI extends APIBase {

    public Response createOrder(int id, int petId, int quantity, String shipDate, String status, boolean complete) {
        JSONObject orderPayload = new JSONObject();
        orderPayload.put("id", id);
        orderPayload.put("petId", petId);
        orderPayload.put("quantity", quantity);
        orderPayload.put("shipDate", shipDate);
        orderPayload.put("status", status);
        orderPayload.put("complete", complete);

        return postRequest("/store/order", orderPayload.toString());
    }

    public Response getOrderById(int id) {
        return getRequest("/store/order/" + id);
    }

    public Response deleteOrderById(int id) {
        return deleteRequest("/store/order/" + id);
    }
    
    public Response getInventory() {  
        return getRequest("/store/inventory");
    }

}
