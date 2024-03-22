package DuckDuckGo.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Driver {

    private Driver(){

    }

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    public static WebDriver getDriver(){
        if (driverPool.get() == null) {
            String browserType = ConfigurationReader.getProperty("browser");

            switch (browserType) {
                case "chrome" :
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless=new");
                    driverPool.set(new ChromeDriver(chromeOptions));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
                    break;
            }
        }
        return driverPool.get();
    }

    public static void closeDriver(){
        if (driverPool.get() != null){
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
