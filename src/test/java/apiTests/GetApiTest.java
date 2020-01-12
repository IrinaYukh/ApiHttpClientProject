package apiTests;

import base.TestBase;
import client.RestClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetApiTest extends TestBase
{
    TestBase testBase;
    String url;
    String apiUrl;
    String endpoint;
    RestClient restClient;

    @BeforeMethod
    public void setUp()  {
        testBase = new TestBase();
        url = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");
        endpoint = url + apiUrl;
    }

    @Test
    public void getApiTest() throws IOException {
        restClient = new RestClient();
        restClient.get(endpoint);

    }


}
