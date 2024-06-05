package glue.stepDefs;

import glue.Driver.DriverSingleton;
import glue.context.PageContextUI;
import glue.pages.RegisterPage;
import io.cucumber.java8.En;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class Login implements En {

    WebDriver driver;
    String creds;

    public Login(PageContextUI pageContextUI){

        Given("User is Registered", () -> {
            pageContextUI.getRegisterPage().navigateToRegisterPage();
            creds = RegisterPage.getName();
            pageContextUI.getRegisterPage().registerUser(creds);
        });

        Given("User is on Home Page", () -> {
            pageContextUI.getHomePage().navigateToHomePage();
        });

        When("User enters UserName and Password", () -> {
            pageContextUI.getHomePage().clickLoginButton();
        });

        When("click on submit button", () -> {
            // Write code here that turns the phrase above into concrete actions
            pageContextUI.getLoginPage().performLogin(creds);
            Thread.sleep(500);
        });

        Then("User logged in Successfully", () -> {
            pageContextUI.getLoginPage().verifyLogin();
        });
    }

    @Before
    public void setupDriver(){
        driver = DriverSingleton.getInstance().getDriver();
        System.out.println("Driver Initialised");
    }

    @After
    public void tearDown(){
        DriverSingleton.closeDriver();
        System.out.println(DriverSingleton.getInstance().getDriver().hashCode());
    }
}
