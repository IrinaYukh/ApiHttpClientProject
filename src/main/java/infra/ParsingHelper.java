package infra;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParsingHelper
{
    // Get string value from Json Array by key
    public static List<String>getStringList_byKey_fromJsonArray(JSONArray jsonArray, String key)
    {
        List<String> result = new ArrayList<String>();
        for (Object json: jsonArray) {
            result.add(((JSONObject)json).optString(key));
        }
        return result;
    }

    // Get int value from Json Array by key
    public static  List<Integer>getIntegerList_byKey_fromJsonArray(JSONArray jsonArray, int key)
    {
        List<Integer> result = new ArrayList<Integer>();
        for (Object json: jsonArray)
        {
            result.add(((JSONArray)json).optInt(key));
        }
        return result;
    }

    public static String getValue_byKey_fromJsonObject(JSONObject jsonObject, String key)
    {
        String result = jsonObject.optString(key);
        return result;
    }






}
