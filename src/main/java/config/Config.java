package config;

import com.google.gson.Gson;

public class Config
{
    public static Gson gson = new Gson();
    public static BaseConfig baseConfig;
    static String projectPath = System.getProperty("user.dir");


    static {

        // --------------- Using config.json file ---------------------------------

        String json = ReadConfigFile.readConfigFileFromJson(projectPath + "/config/config.json");
        baseConfig = gson.fromJson(json, BaseConfig.class);

        // --------------- Using config.properties file

//        String json = ReadConfigFile.readConfigPropertiesFile(projectPath + "/config/test.properties");
//        baseConfig = gson.fromJson(json, BaseConfig.class);

        // ---------------- Using config.xml file

//        String json = ReadConfigFile.readConfigFromXMLFile(projectPath + "/config/config.xml");
//        baseConfig = gson.fromJson(json, BaseConfig.class);




    }
}
