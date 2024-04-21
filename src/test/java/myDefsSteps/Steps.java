package myDefsSteps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class Steps {

    private WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
    }
    @After
    public void tearDwn() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Given("The user is on the registration page")
    public void theUserIsOnTheRegistrationPage() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");

    }

    @When("The user fills out the registration form with valid information")
    public void theUserFillsOutTheRegistrationFormWithValidInformation() {

        driver.findElement(By.cssSelector("[id='dp']")).sendKeys("02/04/2000");
        driver.findElement(By.cssSelector("[class='container']")).click();
    }

    @And("Submits the form")
    public void submitTheForm(){
        String email = "cevocok"+System.currentTimeMillis()+"@agromgt.com";
        driver.findElement(By.cssSelector("[id='member_firstname']")).sendKeys("Anna");
        driver.findElement(By.cssSelector("[id='member_lastname']")).sendKeys("Ivanova");
        driver.findElement(By.cssSelector("[id='member_emailaddress']")).sendKeys(email);
        driver.findElement(By.cssSelector("[id='member_confirmemailaddress']")).sendKeys(email);
        driver.findElement(By.cssSelector("[id='signupunlicenced_password']")).sendKeys("12!12KKmm33");
        driver.findElement(By.cssSelector("[id='signupunlicenced_confirmpassword']")).sendKeys("12!12KKmm33");
        driver.findElement(By.cssSelector("label[for='sign_up_25'] [class='box']")).click();
        driver.findElement(By.cssSelector("label[for='sign_up_26'] [class='box']")).click();
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] [class='box']")).click();
        driver.findElement(By.cssSelector("[class='btn btn-big red']")).click();
    }

    @Then("assert")
    public void iGetTheResult() {

        System.out.println("And get the result");
//        assertEquals(expected, actual);
    }

    @Then("The user should be receive a confirmation email")
    public void theUserShouldBeReceiveAConfirmationEmail() {


    }

    @And("Should be redirected to the confirmation page and get the message {string}")
    public void shouldBeRedirectedToTheConfirmationPageAndGetTheMessage(String message) {
        WebElement accountElement = driver.findElement(
                By.xpath("//h2[text()='THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND']"));
        Assert.assertEquals(message, accountElement.getText());
        System.out.println("accountElement.getText() " + accountElement.getText());

    }

    @And("Should be able to log in with the newly created credentials")
    public void shouldBeAbleToLogInWithTheNewlyCreatedCredentials() {
    }



}
