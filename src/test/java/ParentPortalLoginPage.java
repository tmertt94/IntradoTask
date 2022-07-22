import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



import java.util.NoSuchElementException;

import static org.testng.internal.InstanceCreator.newInstance;


public  class ParentPortalLoginPage {

    private static WebDriver _driver;

    public static ParentPortalLoginPage openWith(WebDriver driver) {
        _driver = driver;
        _driver.get("https://go.schoolmessenger.com/#/account/login");
        return newInstance(ParentPortalLoginPage.class);
    }

    public  ParentPortalLoginPage setEmail(String email)
    {


        _driver.findElement(By.name("username")).sendKeys(email);

        return this;
    }



    public ParentPortalLoginPage setPassword(String password)
    {
        _driver.findElement(By.name("password")).sendKeys(password);

        return this;
    }


    public ParentPortalLoginPage clickLogInExpectingError()
    {
        _driver.findElement(By.xpath("//button[@type='submit']")).click();

        return this;
    }

    public ParentPortalLoginPage assertAuthenticationError(String message) throws InterruptedException {
        boolean errorAppear = false;

        try {
            WebDriverWait wait = new WebDriverWait(_driver,5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-heading")));
            errorAppear = _driver.findElement(By.className("error-heading")).getText().contains("Authentication has failed");
       }
        catch (NoSuchElementException e)
        {
            e.printStackTrace();
        }
        finally {
            Assert.assertTrue(errorAppear,message);
        }

        return this;
    }
}

