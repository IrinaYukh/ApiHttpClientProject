package pet_tests;

import config.Config;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static infra.InfraHttpClient.*;
import static infra.ParsingHelper.getValue_byKey_fromJsonObject;

public class TestPets extends TestPetsHelper {

    // Create test variables for request
    public static final String api_key = Config.baseConfig.api.api_key;
    public static final String id = TestDataCreator.getPet_ID();
    public static final String name = TestDataCreator.getPet_name("Browne");

    @Test(priority = 1)
    public void testPost_newPet() throws IOException {

        // Creating request body for POST request
        JSONObject category = new JSONObject().put("name","dog");

        String jsonPet = new JSONObject()
                .put("id",id)
                .put("name", name)
                .put("status","available")
                .put("category",category)
                .toString();

    /*     This part of code can write like this

            1. Creating JSONObject with name pet
                JSONObject pet = new JSONObject()
                .put("id",id)
                .put("name", name)
                .put("status","available");

             2. Add to pet another JSONObject category

                    pet.put("category",category);

             3. Convert JSONObject pet to string for request execution

                    String jsonPet = pet.toString();

     */

        // Send request with authorization
        CloseableHttpResponse response = createNewPet(api_key,jsonPet);
        Assert.assertEquals(getStatusCodeFromResponse(response),200);
        Assert.assertEquals(getNewPetId(response),id);
    }

    @Test (priority = 2)
    public void testGetPet_byId() throws IOException
    {

        CloseableHttpResponse response = getPet_byId(Integer.parseInt(id), api_key);
        int response_statusCode = getStatusCodeFromResponse(response);
        Assert.assertEquals(response_statusCode,200);

        JSONObject jsonObject = getResponseBodyAsJsonObject(response);
        String nameInfra = getValue_byKey_fromJsonObject(jsonObject,"name");
        String responseId = getValue_byKey_fromJsonObject(jsonObject,"id");

        System.out.println("Name Infra ---->  " + nameInfra);
        System.out.println("ID Infra ---->   " + responseId);

        Assert.assertEquals(nameInfra,name);
        Assert.assertEquals(responseId, id);

    }

    @Test (priority = 3)
    public void testGetPets_byStatus() throws IOException, URISyntaxException
    {
        // Getting id of the Pets with status Available.

        CloseableHttpResponse response = getPets_byStatus("status","available", api_key);
        int response_statusCode = getStatusCodeFromResponse(response);
        Assert.assertEquals(response_statusCode,200);

        JSONArray pets = getResponseBodyAsJsonArray(response);
        int count = 0;
        for (int i=0; i<pets.length(); i++)
        {
            JSONObject pet = pets.getJSONObject(i);
            int id = pet.getInt("id");
            String status = pet.getString("status");
            count++;
            System.out.println("Id " + id + " has status  " + status);
        }
        System.out.println("Count is : " + count + ", length of json array is: " + pets.length());

    }

    @Test(priority = 4) // Update an existing pet info in the store - changed status from available --> sold
    public void testPut_updateExistingPet() throws IOException {

        // Creating request body for POST request
        JSONObject category = new JSONObject().put("name","dog");

        String jsonPet = new JSONObject()
                .put("id",id)
                .put("name", name)
                .put("status","sold")
                .put("category",category)
                .toString();

        // Send request with authorization
        CloseableHttpResponse response = updatePet(api_key,jsonPet);
        Assert.assertEquals(getStatusCodeFromResponse(response),200);
        Assert.assertEquals(getPetStatus(response),"sold");
    }

    @Test (priority = 5)
    public void testDeletePet_byId() throws IOException {
        CloseableHttpResponse response = deletePet_byId(id,api_key);
        int response_statusCode = getStatusCodeFromResponse(response);
        Assert.assertEquals(response_statusCode, 200);

        CloseableHttpResponse verifyResponse = getPet_byId(Integer.parseInt(id), api_key);
        int verify_statusCode = getStatusCodeFromResponse(verifyResponse);
        System.out.println("Verify status code is ---> " + verify_statusCode + ". The item deleted successfully");
        Assert.assertEquals(verify_statusCode,404);

    }

}
