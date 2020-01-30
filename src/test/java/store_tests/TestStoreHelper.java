package store_tests;

import base.BaseTest;
import config.Config;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

import static infra.InfraHttpClient.*;
import static infra.ParsingHelper.getValue_byKey_fromJsonObject;

public class TestStoreHelper extends BaseTest {

    private static String URI_GET_storeOrder_ById = Config.baseConfig.store.GET_storeOrder_byId;
    private static String URI_POST_NewOrder = Config.baseConfig.store.POST_newOrder;
    private static String URI_DELETE_OrderById = Config.baseConfig.store.DELETE_order_byOrderId;

    public static CloseableHttpResponse getStoreOrder_byId(String id, String api_key) throws IOException
    {
        return doGet_withAuth(URI_GET_storeOrder_ById+id, api_key);
    }

    public static CloseableHttpResponse createNewOrder(String api_key, String jsonString) throws IOException {
//        System.out.println(jsonString);
        return doPost_withAuth(URI_POST_NewOrder, api_key, jsonString);
    }

    // Verify successful new Order creation by petId key -> id
    public static String getNewOrderPetId(CloseableHttpResponse response) throws IOException {
        return getValue_byKey_fromJsonObject(getResponseBodyAsJsonObject(response),"petId");
    }

    public static CloseableHttpResponse deleteOrder_byOrderId(String id, String api_key) throws IOException {
        return doDeletePet_byId(URI_DELETE_OrderById+id, api_key);
    }
}
