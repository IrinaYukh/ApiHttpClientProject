package config;

import com.google.gson.Gson;

public class BaseConfig {

    public APIConfig api = new APIConfig();

    static String projectPath = System.getProperty("user.dir");

    public static void main(String[]args){
        Gson gson = new Gson();

        //-------- config.json----------

        String json = ReadConfigFile.readConfigFileFromJson(projectPath + "/config/config.json");
        BaseConfig baseConfig = gson.fromJson(json, BaseConfig.class);
        System.out.println(baseConfig.toString());


        //---------- config.properties-------------

//        String json = ReadConfigFile.readConfigPropertiesFile(projectPath + "/config/test.properties");
//        BaseConfig baseConfig = gson.fromJson(json, BaseConfig.class);
//        System.out.println(baseConfig.toString());


        //-------- config.xml-----------------

//        String json = ReadConfigFile.readConfigFromXMLFile(projectPath + "/config/config.xml");
//        BaseConfig baseConfig = gson.fromJson(json, BaseConfig.class);
//        System.out.println(baseConfig.toString());

    }
}
