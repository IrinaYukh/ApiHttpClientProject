package infra;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;


public class InfraHttpClient
{
    private static CloseableHttpClient httpClient;
    private static HttpGet httpGet;
    private static HttpPost httpPost;
    private static HttpPut httpPut;
    private static HttpDelete httpDelete;
    private static HttpResponse httpResponse;


    // ---- Open api session by creating default httpClient object---
    public static void startHttpClient(){
        httpClient = HttpClients.createDefault();
    }

    // ---- Close httpClient session ----------
    public static void stopHttpClient() throws IOException {
        httpClient.close();
    }

    /*
        This is two methods execute to creation simple Http GET request without authentication.
     */
    public static HttpResponse do_Get (String uri) throws IOException {
        httpGet = new HttpGet(uri);
        httpResponse = httpClient.execute(httpGet);
        return httpResponse;
    }

    public static CloseableHttpResponse doGet (String uri) throws IOException {

        return httpClient.execute(new HttpGet(uri));
    }

    // Execute GET request with Authentication without Body
    public static CloseableHttpResponse doGet_withAuth(String uri, String api_key) throws IOException {
        httpGet = new HttpGet(uri);
        httpGet.setHeader("api_key",api_key);
        return httpClient.execute(httpGet);
    }

    // Execute GET request with Authentication and with query Params
    public static CloseableHttpResponse doGet_withAuth_withQueryParams(String uri, String api_key, String paramKey, String paramValue) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(uri);
        builder.setParameter(paramKey, paramValue);
        httpGet = new HttpGet(builder.build());
        httpGet.setHeader("api_key",api_key);
        return httpClient.execute(httpGet);
    }

    // Execute POST request with Authentication
    public static CloseableHttpResponse doPost_withAuth(String uri, String api_key, String jsonString) throws IOException {
      httpPost = new HttpPost(uri);
      httpPost.setEntity(new StringEntity(jsonString));
      httpPost.setHeader("Accept","application/json");
      httpPost.setHeader("Content-type","application/json");
      httpPost.setHeader("api_key",api_key);
      return httpClient.execute(httpPost);
    }

    // Execute PUT request
    public static CloseableHttpResponse doPut_withAuth (String uri, String api_key, String jsonString) throws IOException {
        httpPut = new HttpPut(uri);
        httpPut.setEntity(new StringEntity(jsonString));
        httpPut.setHeader("Accept","application/json");
        httpPut.setHeader("Content-type","application/json");
        httpPut.setHeader("api_key",api_key);
        return httpClient.execute(httpPut);
    }

    // Execute DELETE request
    public static CloseableHttpResponse doDeletePet_byId (String uri, String api_key) throws IOException {
        httpDelete = new HttpDelete(uri);
        httpDelete.setHeader("api_key",api_key);
        return httpClient.execute(httpDelete);
    }



    // -------- Methods for parsing the Json response object--------------

    public static int getStatusCodeFromResponse(CloseableHttpResponse response) {
        return response.getStatusLine().getStatusCode();
    }

    public static String getResponseBodyAsString(CloseableHttpResponse response) throws IOException {
        return EntityUtils.toString(response.getEntity());
    }

    public static JSONArray getResponseBodyAsJsonArray(CloseableHttpResponse response) throws IOException {
        String bodyString=getResponseBodyAsString(response);
        return new JSONArray(bodyString);
    }

    public static JSONObject getResponseBodyAsJsonObject(CloseableHttpResponse response) throws IOException {
        String bodyString = getResponseBodyAsString(response);
        System.out.println(bodyString);
        return new JSONObject(bodyString);
    }



}
