package pet_tests;

public class TestDataCreator
{
    public static int random = (int) (Math.random()*100);
    public static String baseID = "300";

    public static String getPet_ID()
    {
        return baseID + random;
    }

    public static String getPet_name (String name)
    {
        return name + random;
    }

}
