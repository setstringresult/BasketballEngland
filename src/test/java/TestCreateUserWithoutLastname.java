import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCreateUserWithoutLastname {

    @Test
    public void testCreateUserWithoutLastname() throws InterruptedException{
        WebDriver driver = new ChromeDriver();
        String email = "cevocok"+System.currentTimeMillis()+"@agromgt.com"; //текущее время в миллисекундах
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("[id='dp']")).sendKeys("02/04/2000");
        driver.findElement(By.cssSelector("[class='container']")).click();
        driver.findElement(By.cssSelector("[id='member_firstname']")).sendKeys("Anna");
        driver.findElement(By.cssSelector("[id='member_emailaddress']")).sendKeys(email);
        driver.findElement(By.cssSelector("[id='member_confirmemailaddress']")).sendKeys(email);
        driver.findElement(By.cssSelector("[id='signupunlicenced_password']")).sendKeys("12!12KKmm33");
        driver.findElement(By.cssSelector("[id='signupunlicenced_confirmpassword']")).sendKeys("12!12KKmm33");
        driver.findElement(By.cssSelector("label[for='sign_up_25'] [class='box']")).click();
        driver.findElement(By.cssSelector("label[for='sign_up_26'] [class='box']")).click();
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] [class='box']")).click();
        driver.findElement(By.cssSelector("[class='btn btn-big red']")).click();
        WebElement lastnameElement = driver.findElement(
                By.xpath("//span[text()='Last Name is required']"));
        Assert.assertEquals("Last Name is required", lastnameElement.getText());
        System.out.println("lastnameElement.getText() " + lastnameElement.getText());


    }
}
