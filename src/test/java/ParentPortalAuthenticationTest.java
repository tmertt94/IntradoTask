import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class ParentPortalAuthenticationTest {




    private static final String CHROMEDRIVER_BINARY_PATH =
            System.getProperty("user.dir")
                    + "/src/main/resources/webdriver/chromedriver.exe";

   /*
      This test performs these steps:

      - Browse to the Parent Portal login page at
         "https://go.schoolmessenger.com/#/account/login"
      - Enter invalid credentials
      - Click the Login button
      - Ensure that the "Authentication has failed" message appears when
     logging in with invalid credentials.
    */

    @Test
    public void testInvalidCredentials() throws InterruptedException {

        String emailAddress = "incorrect@email.com";
        String password = "WrongPassword1!";
        WebDriver driver = getNewDriver();


        ParentPortalLoginPage.openWith( driver )
                .setEmail( emailAddress )
                .setPassword( password )
                .clickLogInExpectingError()
                .assertAuthenticationError(
                        "Did not get proper error message "
                                + "when logging in with username '"
                                + emailAddress + "' and password '" + password );
        driver.quit();

    }

    private WebDriver getNewDriver() {
        // Full path to chromedriver executable.
        System.setProperty( "webdriver.chrome.driver", CHROMEDRIVER_BINARY_PATH );

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type", "--incognito", "--start-maximized");

        return new ChromeDriver(options);
    }





}
