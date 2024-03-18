package DuckDuckGo.Utils;// Generated by Selenium IDE

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.MalformedJsonException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v121.fetch.Fetch;
import org.openqa.selenium.devtools.v122.network.Network;
import org.openqa.selenium.devtools.v122.network.model.Headers;
import org.openqa.selenium.devtools.v122.network.model.RequestId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.devtools.*;

import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class VisatestTest {
    private Map<String, Object> vars;
    JavascriptExecutor js;

    String responseBody, expectedResponseBody;

    String date;

    JsonArray jsonArray;

    LocalDate localDate;

    boolean repeat = true;

    String expectedDate = "2025-09-12";

    int year, month, day;

    @Before
    public void setUp() {
        js = (JavascriptExecutor) Driver.getDriver();
        vars = new HashMap<String, Object>();
    }

    @After
    public void tearDown() {
        //Driver.getDriver().quit();
    }

    @Test
    public void visatest() {
        Driver.getDriver().get("https://ais.usvisa-info.com/");
        Driver.getDriver().manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        //Driver.getDriver().manage().window().setSize(new Dimension(1936, 1168));
        Driver.getDriver().findElement(By.linkText("Nonimmigrant Visa Applicants")).click();
        Driver.getDriver().findElement(By.cssSelector("section:nth-child(1) > .row:nth-child(8) > .columns:nth-child(1) li:nth-child(2) > a")).click();
        Driver.getDriver().findElement(By.xpath("(//a[contains(text(),\'Sign In\')])[2]")).click();
        Driver.getDriver().findElement(By.id("user_email")).click();
        Driver.getDriver().findElement(By.id("user_email")).sendKeys("hevaltasdemir@gmail.com");
        Driver.getDriver().findElement(By.id("user_password")).click();
        Driver.getDriver().findElement(By.id("user_password")).sendKeys("438255.Hh");
        Driver.getDriver().findElement(By.cssSelector(".icheckbox")).click();
        Driver.getDriver().findElement(By.name("commit")).click();
        Driver.getDriver().findElement(By.linkText("Continue")).click();
        Driver.getDriver().findElement(By.xpath("//h5[contains(.,\'Reschedule Appointment\')]")).click();
        vars.put("k", js.executeScript("return false"));
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));


        // Create DevTools session
        DevTools devTools = ((ChromeDriver) Driver.getDriver()).getDevTools();
        devTools.createSession();
        devTools.send(Network.clearBrowserCache());
        devTools.send(Network.setCacheDisabled(true));

        final RequestId[] requestIds = new RequestId[1];
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.of(100000000)));
        devTools.addListener(Network.responseReceived(), requests -> {
            requestIds[0] = requests.getRequestId();
            responseBody =  devTools.send(Network.getResponseBody(requestIds[0])).getBody();
            if (responseBody.contains("business_day\":true")) {
                expectedResponseBody = responseBody;
            }

        });

        Driver.getDriver().findElement(By.xpath("//a[contains(text(),\'Reschedule Appointment\')]")).click();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("document.getElementById('appointments_consulate_appointment_date').removeAttribute('readonly');");
        do {
            try {
                //For Toronto
                WebElement cityDropdown = Driver.getDriver().findElement(By.id("appointments_consulate_appointment_facility_id"));
                cityDropdown.findElement(By.xpath("//option[. = 'Toronto']")).click();
                Thread.sleep(2000);
                wait.until(ExpectedConditions.textToBe(By.id("appointments_consulate_address"), "Consular Address\n" +
                        "225 Simcoe Street\n" +
                        "Toronto, ON, Ontario, M5G 1S4\n" +
                        "Canada"));
                jsonArray = JsonParser.parseString(expectedResponseBody).getAsJsonArray();
                date = jsonArray.get(0).getAsJsonObject().get("date").toString().replaceAll("\"", "");

                WebElement dateInputField = Driver.getDriver().findElement(By.id("appointments_consulate_appointment_date"));
                WebElement timeDropdownWebElement = Driver.getDriver().findElement(By.id("appointments_consulate_appointment_time"));
                Select timeDropdown = new Select(timeDropdownWebElement);

                if (date.compareTo(expectedDate) < 0) {
                    System.out.println("Date compare is working");

                    //dateInputField.click();
                    dateInputField.sendKeys(date);
                    dateInputField.sendKeys(Keys.ENTER);
                    dateInputField.sendKeys(Keys.ESCAPE);

                    Thread.sleep(1000);
                    timeDropdown.selectByIndex(1);

                    Driver.getDriver().findElement(By.id("appointments_submit")).click();
                    Driver.getDriver().findElement(By.xpath("//a[.='Confirm']")).click();
                    repeat = false;
                }

                DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("yyyy-MM-dd").toFormatter(Locale.ENGLISH);
                LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
                //System.out.println("Toronto Response: "+responseBody);
                System.out.println("Earliest date for Toronto: " + localDate);


                //For Vancouver
                cityDropdown.findElement(By.xpath("//option[. = 'Vancouver']")).click();
                Thread.sleep(2000);
                wait.until(ExpectedConditions.textToBe(By.id("appointments_consulate_address"), "Consular Address\n" +
                        "1075 West Pender Street\n" +
                        "Vancouver, BC, V6E 2M6\n" +
                        "Canada"));
                jsonArray = JsonParser.parseString(expectedResponseBody).getAsJsonArray();
                date = jsonArray.get(0).getAsJsonObject().get("date").toString().replaceAll("\"", "");

                if (date.compareTo(expectedDate) < 0) {
                    System.out.println("Date compare is working");

                    dateInputField.click();
                    dateInputField.sendKeys(date);
                    dateInputField.sendKeys(Keys.ENTER);
                    dateInputField.sendKeys(Keys.ESCAPE);
                    Thread.sleep(1000);
                    timeDropdown.selectByIndex(1);

                    Driver.getDriver().findElement(By.id("appointments_submit")).click();
                    Driver.getDriver().findElement(By.xpath("//a[.='Confirm']")).click();
                    repeat = false;
                }

                localDate = LocalDate.parse(date, dateTimeFormatter);
                //System.out.println("Vancouver: "+responseBody);
                System.out.println("Earliest date for Vancouver: " + localDate);

                //For Calgary
                cityDropdown.findElement(By.xpath("//option[. = 'Calgary']")).click();
                Thread.sleep(2000);
                wait.until(ExpectedConditions.textToBe(By.id("appointments_consulate_address"), "Consular Address\n" +
                        "615 Macleod Trail, SE\n" +
                        "Suite 1000\n" +
                        "Calgary, AB, T2G 4T8\n" +
                        "Canada"));
                jsonArray = JsonParser.parseString(expectedResponseBody).getAsJsonArray();
                date = jsonArray.get(0).getAsJsonObject().get("date").toString().replaceAll("\"", "");

                if (date.compareTo(expectedDate) < 0) {
                    System.out.println("Date compare is working");

                    dateInputField.click();
                    dateInputField.sendKeys(date);
                    dateInputField.sendKeys(Keys.ENTER);
                    dateInputField.sendKeys(Keys.ESCAPE);
                    Thread.sleep(2000);
                    timeDropdown.selectByIndex(1);

                    Driver.getDriver().findElement(By.id("appointments_submit")).click();
                    Driver.getDriver().findElement(By.xpath("//a[.='Confirm']")).click();
                    repeat = false;
                }

                localDate = LocalDate.parse(date, dateTimeFormatter);
                //System.out.println("Calgary: "+responseBody);
                System.out.println("Earliest date for Calgary: " + localDate);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception accused! System rebooting!");
                Driver.getDriver().get(Driver.getDriver().getCurrentUrl());
            }
        } while (repeat == true);
//      vars.put("i", js.executeScript("return 0"));
//      do {
//        vars.put("elementCount", driver.findElements(By.xpath("//table/tbody/tr/td/a")).size());
//        if ((Boolean) js.executeScript("return (arguments[0] > 0)", vars.get("elementCount"))) {
//          driver.findElement(By.xpath("//table/tbody/tr/td/a[1]")).click();
//          {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[count(option[contains(text(),\':\')]) > 0]")));
//          }
//          {
//            WebElement dropdown = driver.findElement(By.id("appointments_consulate_appointment_time"));
//            dropdown.findElement(By.cssSelector("*:nth-child(1)")).click();
//          }
//          driver.findElement(By.id("appointments_submit")).click();
//          vars.put("i", js.executeScript("return 13"));
//          vars.put("k", js.executeScript("return true"));
//          assertThat(driver.getTitle(), is("a"));
//        } else {
//          driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[2]/div/a/span")).click();
//          vars.put("i", js.executeScript("return arguments[0] + 1", vars.get("i")));
//        }
//      } while ((Boolean) js.executeScript("return (arguments[0] != 13)", vars.get("i")));
//      vars.put("i", js.executeScript("return 0"));
//      driver.get("https://ais.usvisa-info.com/en-ca/niv/schedule/56366072/appointment");
//      {
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("appointments_consulate_appointment_date")));
//      }
//      {
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("appointments_consulate_appointment_time")));
//      }
//      {
//        WebElement dropdown = driver.findElement(By.id("appointments_consulate_appointment_facility_id"));
//        dropdown.findElement(By.xpath("//option[. = 'Vancouver']")).click();
//      }
//      {
//
//        wait.until(ExpectedConditions.textToBe(By.id("appointments_consulate_address"), "Consular Address\\n1075 West Pender Street\\nVancouver, BC, V6E 2M6\\nCanada"));
//      }
//      driver.findElement(By.id("appointments_consulate_appointment_date")).click();
//      do {
//        vars.put("elementCount", driver.findElements(By.xpath("//table/tbody/tr/td/a")).size());
//        if ((Boolean) js.executeScript("return (arguments[0] > 0)", vars.get("elementCount"))) {
//          driver.findElement(By.xpath("//table/tbody/tr/td/a[1]")).click();
//          {
//
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[count(option[contains(text(),\':\')]) > 0]")));
//          }
//          {
//            WebElement dropdown = driver.findElement(By.id("appointments_consulate_appointment_time"));
//            dropdown.findElement(By.cssSelector("*:nth-child(1)")).click();
//          }
//          driver.findElement(By.id("appointments_submit")).click();
//          vars.put("i", js.executeScript("return 13"));
//          vars.put("k", js.executeScript("return true"));
//          assertThat(driver.getTitle(), is("a"));
//        } else {
//          driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[2]/div/a/span")).click();
//          vars.put("i", js.executeScript("return arguments[0] + 1", vars.get("i")));
//        }
//      } while ((Boolean) js.executeScript("return (arguments[0] != 13)", vars.get("i")));
//      vars.put("i", js.executeScript("return 0"));
//      driver.get("https://ais.usvisa-info.com/en-ca/niv/schedule/56366072/appointment");
//      {
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("appointments_consulate_appointment_date")));
//      }
//      {
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("appointments_consulate_appointment_time")));
//      }
//      {
//        WebElement dropdown = driver.findElement(By.id("appointments_consulate_appointment_facility_id"));
//        dropdown.findElement(By.xpath("//option[. = 'Calgary']")).click();
//      }
//      {
//
//        wait.until(ExpectedConditions.textToBe(By.id("appointments_consulate_address"), "Consular Address\\n615 Macleod Trail, SE\\nSuite 1000\\nCalgary, AB, T2G 4T8\\nCanada"));
//      }
//      driver.findElement(By.id("appointments_consulate_appointment_date")).click();
//      do {
//        vars.put("elementCount", driver.findElements(By.xpath("//table/tbody/tr/td/a")).size());
//        if ((Boolean) js.executeScript("return (arguments[0] > 0)", vars.get("elementCount"))) {
//          driver.findElement(By.xpath("//table/tbody/tr/td/a[1]")).click();
//          {
//
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[count(option[contains(text(),\':\')]) > 0]")));
//          }
//          {
//            WebElement dropdown = driver.findElement(By.id("appointments_consulate_appointment_time"));
//            dropdown.findElement(By.cssSelector("*:nth-child(1)")).click();
//          }
//          driver.findElement(By.id("appointments_submit")).click();
//          vars.put("i", js.executeScript("return 13"));
//          vars.put("k", js.executeScript("return true"));
//          assertThat(driver.getTitle(), is("a"));
//        } else {
//          driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[2]/div/a/span")).click();
//          vars.put("i", js.executeScript("return arguments[0] + 1", vars.get("i")));
//        }
//      } while ((Boolean) js.executeScript("return (arguments[0] != 13)", vars.get("i")));
    }
}
