package assessment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public abstract class APIBase {

    public APIBase() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    // Method for POST requests
    protected Response postRequest(String endpoint, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);
    }

    // Method for GET requests
    protected Response getRequest(String endpoint) {
        return given()
                .accept(ContentType.JSON)
                .when()
                .get(endpoint);
    }

    // Method for DELETE requests
    protected Response deleteRequest(String endpoint) {
        return given()
                .accept(ContentType.JSON)
                .when()
                .delete(endpoint);
    }
    
}

