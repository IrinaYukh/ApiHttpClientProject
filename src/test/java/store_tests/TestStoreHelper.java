package store_tests;

import base.BaseTest;
import config.Config;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

import static infra.InfraHttpClient.doGet_withAuth;

public class TestStoreHelper extends BaseTest {

    private static String URI_GET_storeOrder_ById = Config.baseConfig.store.GET_storeOrder_byId;

    public static CloseableHttpResponse getStoreOrder_byId(int id, String api_key) throws IOException
    {
        return doGet_withAuth(URI_GET_storeOrder_ById+id, api_key);
    }
}
