package store_tests;

import config.Config;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import pet_tests.TestPets_DataCreator;

import java.io.IOException;

import static infra.InfraHttpClient.getResponseBodyAsJsonObject;
import static infra.InfraHttpClient.getStatusCodeFromResponse;
import static infra.ParsingHelper.getValue_byKey_fromJsonObject;

public class TestStore extends TestStoreHelper {

    public static final String api_key = Config.baseConfig.api.api_key;
    public static final String pet_id = TestPets_DataCreator.getPet_ID();
    public static final String orderId = "1";

    // Find purchase order by order id
    @Test(priority = 2)
    public void testGet_StoreOrder_byId() throws IOException
    {

        CloseableHttpResponse response = getStoreOrder_byId(orderId, api_key);
        int response_statusCode = getStatusCodeFromResponse(response);
        Assert.assertEquals(response_statusCode,200);

        JSONObject jsonObject = getResponseBodyAsJsonObject(response);
        String petId = getValue_byKey_fromJsonObject(jsonObject,"petId");
        String responseId = getValue_byKey_fromJsonObject(jsonObject,"id");
        String quantity = getValue_byKey_fromJsonObject(jsonObject,"quantity");

        System.out.println("Pet id ---->  " + petId);
        System.out.println("Order id ---->   " + responseId);
        System.out.println("Quantity ---->   " + quantity);

        Assert.assertEquals(responseId, orderId);

    }

    // Place an order for a pet
    @Test(priority = 1)
    public void testPost_Order() throws IOException {
        // Creating request body for POST request
        String jsonPet = new JSONObject()
                .put("id",orderId)
                .put("petId", pet_id)
                .put("quantity","1")
                .put("status","placed")
                .put("complete","true")
                .toString();

        // Send request with authorization
        CloseableHttpResponse response = createNewOrder(api_key,jsonPet);
        Assert.assertEquals(getStatusCodeFromResponse(response),200);
        System.out.println("Response status code is ---->  " + getStatusCodeFromResponse(response));
        Assert.assertEquals(getNewOrderPetId(response), pet_id);

    }

    // Delete purchase order by OrderId
    @Test (priority = 3)
    public void testDeleteOrder_byId() throws IOException {
        CloseableHttpResponse response = deleteOrder_byOrderId(orderId,api_key);
        int response_statusCode = getStatusCodeFromResponse(response);
        Assert.assertEquals(response_statusCode, 200);

        CloseableHttpResponse verifyResponse = getStoreOrder_byId(orderId, api_key);
        int verify_statusCode = getStatusCodeFromResponse(verifyResponse);
        System.out.println("Verify status code is ---> " + verify_statusCode + ". The item deleted successfully");
        Assert.assertEquals(verify_statusCode,404);

    }
}
