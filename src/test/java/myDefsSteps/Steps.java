package myDefsSteps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Steps {

    private WebDriver driver;

    private final String doMistake_member_lastname = "member_lastname";
    private final String doMistake_password = "password";
    private final String doMistake_terms = "terms";

    @After
    public void tearDwn() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    private void fillCorrectForm(String doMistake){
        driver.findElement(By.cssSelector("[id='dp']")).sendKeys("02/04/2000");
        driver.findElement(By.cssSelector("[class='container']")).click();
        String email = "cevocok"+System.currentTimeMillis()+"@agromgt.com";
        driver.findElement(By.cssSelector("[id='member_firstname']")).sendKeys("Anna");
        if(doMistake_member_lastname.equals(doMistake)){
            System.out.println("will not populate last name");
        }else{
            driver.findElement(By.cssSelector("[id='member_lastname']")).sendKeys("Ivanova");
        }

        driver.findElement(By.cssSelector("[id='member_emailaddress']")).sendKeys(email);
        driver.findElement(By.cssSelector("[id='member_confirmemailaddress']")).sendKeys(email);
        if(doMistake_password.equals(doMistake)){
            System.out.println("will do mistake in password");
            driver.findElement(By.cssSelector("[id='signupunlicenced_password']")).sendKeys("12!12KKmm33--");
            driver.findElement(By.cssSelector("[id='signupunlicenced_confirmpassword']")).sendKeys("--12!12KKmm33");
        }else{
            driver.findElement(By.cssSelector("[id='signupunlicenced_password']")).sendKeys("12!12KKmm33");
            driver.findElement(By.cssSelector("[id='signupunlicenced_confirmpassword']")).sendKeys("12!12KKmm33");
        }

        if(doMistake_terms.equals(doMistake)){
            System.out.println("will not accept terms");
        }else{
            driver.findElement(By.cssSelector("label[for='sign_up_25'] [class='box']")).click();
        }

        driver.findElement(By.cssSelector("label[for='sign_up_26'] [class='box']")).click();
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] [class='box']")).click();
    }

    @And("Fills form correctly")
    public void fillsFormCorrectly(){
        fillCorrectForm(null);
    }

    @And("Submits the form")
    public void submitsTheForm(){
        driver.findElement(By.cssSelector("[class='btn btn-big red']")).click();
    }



    @Then("Should be redirected to the confirmation page and get the message {string}")
    public void shouldBeRedirectedToTheConfirmationPageAndGetTheMessage(String message) {
        WebElement accountElement = driver.findElement(
                By.xpath("//h2[text()='THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND']"));
        Assert.assertEquals(message, accountElement.getText());
    }

    @Given("Use {string}")
    public void use(String browser) {
        switch (browser){
            case "chrome":
                driver = new ChromeDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("browser " + browser + " is not supported. Contact developer.");

        }
    }

    @When("The user is on the registration page")
    public void theUserIsOnTheRegistrationPage() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");

    }

    @And("Fills form without last name")
    public void fillsFormWithoutLastName() {
        fillCorrectForm(doMistake_member_lastname);
    }

    @Then("Should get error about missing last name")
    public void shouldGetErrorAboutMissingLastName() {
        WebElement lastnameElement = driver.findElement(
                By.xpath("//span[text()='Last Name is required']"));
        Assert.assertEquals("Last Name is required", lastnameElement.getText());
    }

    @And("Fills form with non matching passwords")
    public void fillsFormWithNonMatchingPasswords() {
        fillCorrectForm(doMistake_password);
    }

    @Then("Should get error about mismatched passwords")
    public void shouldGetErrorAboutMismatchedPasswords() {
        WebElement passwordElement = driver.findElement(
                By.xpath("//span[text()='Password did not match']"));
        Assert.assertEquals("Password did not match", passwordElement.getText());
    }

    @And("Fills form and doesn't accept terms and conditions")
    public void fillsFormAndDoesnTAcceptTermsAndConditions() {
        fillCorrectForm(doMistake_terms);
    }

    @Then("Should get error about non accepted terms and conditions")
    public void shouldGetErrorAboutNonAcceptedTermsAndConditions() {
        WebElement termsAndConditionsElement = driver.findElement(
                By.xpath("//span[text()='You must confirm that you have read and accepted our Terms and Conditions']"));
        Assert.assertEquals("You must confirm that you have read and accepted our Terms and Conditions", termsAndConditionsElement.getText());

    }
}
