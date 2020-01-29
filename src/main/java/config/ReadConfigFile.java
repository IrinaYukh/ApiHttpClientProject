package config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.json.XML;
import pl.jalokim.propertiestojson.util.PropertiesToJsonConverter;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadConfigFile {

        static String projectPath = System.getProperty("user.dir");

        public static String readConfigFileFromJson(String path) {
            String json = null;
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(path));
                if (bufferedReader == null) {
                    throw new Exception("Object bufferedReader not created.");
                }
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(bufferedReader).getAsJsonObject();
//            System.out.println(jsonObject);
                json = String.valueOf(jsonObject);
//            System.out.println(json);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return json;
        }

        public static String readConfigPropertiesFile(String path) {
        /*
            This method get the config.properties class and convert to json
            Using import pl.jalokim.propertiestojson.util.PropertiesToJsonConverter;
            https://github.com/mikolajmitura/java-properties-to-json

         */

            String jsonProperty = new PropertiesToJsonConverter().parsePropertiesFromFileToJson(path);
            JsonObject object = new JsonParser().parse(jsonProperty).getAsJsonObject();
            String json = String.valueOf(object);
            return json;
        }

        public static String readConfigFromXMLFile(String path) {

        /*
               Using StringBuffer read all xml string and replace <root></root> tags
         */
            StringBuffer output = null;
            try {
                BufferedReader in
                        = new BufferedReader(new FileReader(path));
                output = new StringBuffer();
                String st;
                while ((st = in.readLine()) != null) {
                    if (!st.equalsIgnoreCase("<root>")) {
                        if (!st.equalsIgnoreCase("</root>")) {
                            output.append(st);
                        }
                    }
                }
                System.out.println(output.toString());
                in.close();
            } catch (Exception fx) {
                System.out.println("Exception " + fx.toString());
            }

            // a.Convert StringBuffer instance to xml String
            // b.Convert xml String to JSON Object
            String temp = output.toString();
            JSONObject object = XML.toJSONObject(temp);
            String json = String.valueOf(object);
            return json;
        }


//        public static void main(String[] args) {
//            String json = readConfigFileFromJson(projectPath + "/config/config.json");
//            System.out.println(json);
//
//            String json2 = readConfigPropertiesFile(projectPath + "/config/config.properties");
//            System.out.println(json2);
//
//            String json3 = readConfigFromXMLFile(projectPath + "/config/config.xml");
//            System.out.println(json3);
//        }
}
