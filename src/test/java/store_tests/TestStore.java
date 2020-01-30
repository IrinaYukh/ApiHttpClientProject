package store_tests;

import config.Config;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static infra.InfraHttpClient.getResponseBodyAsJsonObject;
import static infra.InfraHttpClient.getStatusCodeFromResponse;
import static infra.ParsingHelper.getValue_byKey_fromJsonObject;

public class TestStore extends TestStoreHelper {

    public static final String api_key = Config.baseConfig.api.api_key;

    @Test(priority = 1)
    public void testGet_StoreOrder_byId() throws IOException
    {

        CloseableHttpResponse response = getStoreOrder_byId(3, api_key);
        int response_statusCode = getStatusCodeFromResponse(response);
        Assert.assertEquals(response_statusCode,200);

        JSONObject jsonObject = getResponseBodyAsJsonObject(response);
        String petId = getValue_byKey_fromJsonObject(jsonObject,"petId");
        String responseId = getValue_byKey_fromJsonObject(jsonObject,"id");
        String quantity = getValue_byKey_fromJsonObject(jsonObject,"quantity");

        System.out.println("Pet id ---->  " + petId);
        System.out.println("Order id ---->   " + responseId);
        System.out.println("Quantity ---->   " + quantity);

        Assert.assertEquals(responseId, "3");

    }
}
