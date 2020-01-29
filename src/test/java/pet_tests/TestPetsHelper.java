package pet_tests;

import base.BaseTest;
import config.Config;
import infra.ParsingHelper;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;

import static infra.InfraHttpClient.*;
import static infra.ParsingHelper.*;


public class TestPetsHelper extends BaseTest {

    private static String URI_PETS_ByID = Config.baseConfig.api.GET_pet_byID;
    private static String URI_PETS_ByStatus = Config.baseConfig.api.GET_pets_byStatus;
    private static String URI_POST_NewPet = Config.baseConfig.api.POST_newPet;

    public static CloseableHttpResponse getPet_byId(int id, String api_key) throws IOException
    {
        return doGet_withAuth(URI_PETS_ByID+id, api_key);
    }

    public static CloseableHttpResponse getPets_byStatus(String key, String value, String apiKey) throws IOException, URISyntaxException
    {
        return doGet_withAuth_withQueryParams(URI_PETS_ByStatus,apiKey,key,value);
    }

    public static CloseableHttpResponse createNewPet(String api_key, String jsonString) throws IOException {
        System.out.println(jsonString);
        return doPost_withAuth(URI_POST_NewPet,api_key,jsonString);
    }

    // Verify successful new Pet creation by key -> id
    public static String getNewPetId(CloseableHttpResponse response) throws IOException {
        return getValue_byKey_fromJsonObject(getResponseBodyAsJsonObject(response),"id");
    }

    public static String getPetStatus (CloseableHttpResponse response) throws IOException {
        return getValue_byKey_fromJsonObject(getResponseBodyAsJsonObject(response),"status");
    }


}
