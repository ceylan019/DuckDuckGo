package DuckDuckGo.StepDefinitions;

import DuckDuckGo.Utils.Driver;
import io.cucumber.java.AfterAll;

public class Hook {

    @AfterAll
    public static void afterAll(){
        Driver.closeDriver();
    }
}
