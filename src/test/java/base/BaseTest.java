package base;

import config.Config;
import infra.InfraHttpClient;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseTest extends Config {


    @BeforeSuite
    public void apiSetUp()
    {
        InfraHttpClient.startHttpClient();
    }

    @AfterSuite(alwaysRun = true)
    public void apiTearDown() throws IOException {
        InfraHttpClient.stopHttpClient();
    }
}
