package client;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class RestClient
{
    //1. GET Method
    public void get(String url) throws IOException {
        // Create simple Http Client Object
      CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url); // http GET request

        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); // getting the response by using the httpGet url

        //a. Status Code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code ---->" + statusCode);

        //b. Json String
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8"); // getting response, encoding and put to variable String

        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response Json API ---->" + responseJson);

        //c. All headers
        Header[] headersArray = closeableHttpResponse.getAllHeaders();

        HashMap<String,String> allHeaders = new HashMap<String,String>();
        for(Header header : headersArray)
        {
            allHeaders.put(header.getName(),header.getValue());
        }
        System.out.println("Headers Array-->" + allHeaders);





    }

}
