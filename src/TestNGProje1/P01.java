package TestNGProje1;
import Utlity.BaseDriver;
import Utlity.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class P01 extends BaseDriver {

    @Test
    @Parameters({"mesaj", "mesaj2"})

    void registrationsTest(String mail, String Password) {

        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        WebElement registerbttn = driver.findElement(By.xpath("//a[@class='ico-register']"));
        registerbttn.click();

        Tools.Bekle(1);
        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys("kadir");

        Tools.Bekle(1);
        WebElement lastname = driver.findElement(By.id("LastName"));
        lastname.sendKeys("caglar");
        wait.until(ExpectedConditions.elementToBeClickable(lastname));

        Tools.Bekle(1);
        WebElement day = driver.findElement(By.name("DateOfBirthDay"));
        Select select = new Select(day);
        select.selectByVisibleText("12");

        Tools.Bekle(1);
        WebElement month = driver.findElement(By.name("DateOfBirthMonth"));
        Select select1 = new Select(month);
        select1.selectByVisibleText("May");

        Tools.Bekle(1);
        WebElement year = driver.findElement(By.name("DateOfBirthYear"));
        Select select2 = new Select(year);
        select2.selectByVisibleText("1990");

        Tools.Bekle(1);
        WebElement email = driver.findElement(By.id("Email"));
        email.sendKeys(mail);

        Tools.Bekle(1);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(Password);

        Tools.Bekle(1);
        WebElement confirmPassword = driver.findElement(By.id("ConfirmPassword"));
        confirmPassword.sendKeys(Password);

        Tools.Bekle(1);
        WebElement submit = driver.findElement(By.id("register-button"));
        submit.click();

        Tools.Bekle(1);
        WebElement dogrulama = driver.findElement(By.className("result"));
        Assert.assertTrue(dogrulama.getText().contains("Your registration completed"));
        Tools.Bekle(1);
    }

    @Test
    @Parameters({"mesaj", "mesaj2"})
    void loginTest(String mail, String Password) {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F ");

        Tools.Bekle(1);
        WebElement login = driver.findElement(By.linkText("Log in"));
        login.click();

        Tools.Bekle(1);
        WebElement email = driver.findElement(By.xpath("//*[@id='Email']"));
        email.sendKeys(mail);

        Tools.Bekle(1);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(Password);

        Tools.Bekle(1);
        WebElement login2 = driver.findElement(By.xpath("//button[@class='button-1 login-button']"));
        login2.click();

        Tools.Bekle(1);
        WebElement dogrulama2 = driver.findElement(By.linkText("My account"));
        Assert.assertTrue(dogrulama2.getText().contains("My account"));
        Tools.Bekle(1);
    }

    @Parameters("mesaj, mesaj2")
    @Test(dataProvider = "mailData")
    void DataProviderLoginTest(String mail, String Password) {


        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F ");

        Tools.Bekle(1);
        WebElement login = driver.findElement(By.linkText("Log in"));
        login.click();

        Tools.Bekle(1);
        WebElement email1 = driver.findElement(By.xpath("//*[@id='Email']"));
        email1.sendKeys(mail);

        Tools.Bekle(1);
        WebElement password1 = driver.findElement(By.id("Password"));
        password1.sendKeys(Password);

        Tools.Bekle(1);
        WebElement login2 = driver.findElement(By.xpath("//button[@class='button-1 login-button']"));
        login2.click();
        Tools.Bekle(1);


    }

    @DataProvider
    public Object[][] mailData() {

        Object[][] data = {
                {"kcaglar14@gmail.com", "352347"},
                {"kcaglar@gmail.com", "123456"}
        };
        return data;
    }


    @Test
    void tabMenuTest() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F%22");

        Tools.Bekle(1);
        List<String> isimler = new ArrayList<>();

        Tools.Bekle(1);
        isimler.add("Computer, Electronics, Apparel, Digital download, Books, Jewelry, Gift Cards");

        Tools.Bekle(1);
        List<WebElement> tapMenu = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']"));
        for (WebElement a : tapMenu)
            Assert.assertTrue(a.getText().toLowerCase().contains("computer"));
        Tools.Bekle(1);

    }

    @Test
    void orderGiftsTest() {

        driver.get("https://demo.nopcommerce.com/");

        Tools.Bekle(1);
        WebElement giftsButton = driver.findElement(By.linkText("Gift Cards"));
        giftsButton.click();

        Tools.Bekle(1);
        List<WebElement> giftItems = driver.findElements(By.xpath("//div[@class='products-container']"));
        Tools.Bekle(1);
        Random rnd = new Random();
        int randomIndex = rnd.nextInt(giftItems.size());
        WebElement selectedGiftItem = giftItems.get(randomIndex);
        selectedGiftItem.click();

        Tools.Bekle(1);
        WebElement recipientsName = driver.findElement(By.id("giftcard_44_RecipientName"));
        recipientsName.sendKeys("EmirAffan Caglar");

        Tools.Bekle(1);
        WebElement yourName = driver.findElement(By.id("giftcard_44_SenderName"));
        yourName.sendKeys("Kadir Caglar");

        Tools.Bekle(1);
        WebElement message = driver.findElement(By.id("giftcard_44_Message"));
        message.sendKeys("Lütfen Hediye Paketi Olsun");

        Tools.Bekle(1);
        WebElement addToCart = driver.findElement(By.id("add-to-cart-button-44"));
        addToCart.click();

        Tools.Bekle(1);
        WebElement dogrula = driver.findElement(By.xpath("//p[@class='content']"));
        Assert.assertTrue(dogrula.getText().contains("The product has been added to your "));
        Tools.Bekle(1);

    }

    @Test
    void orderComputerTest() {

        driver.get("https://demo.nopcommerce.com/");

        Tools.Bekle(1);
        WebElement computer = driver.findElement(By.xpath("//a[text()='Computers ']"));
        WebElement desktops = driver.findElement(By.xpath("//a[text()='Desktops ']"));

        Tools.Bekle(1);
        Actions aksiyonlar = new Actions(driver);
        aksiyonlar.moveToElement(computer).build().perform();
        aksiyonlar.moveToElement(desktops).build().perform();

        Tools.Bekle(1);
        WebElement build_your_own_computer = driver.findElement(By.linkText("Build your own computer"));
        build_your_own_computer.click();

        Tools.Bekle(1);
        List<WebElement> random=driver.findElements(By.cssSelector("[id='product_attribute_2']>option[value='0']~option"));
        int i = (int) (Math.random() * random.size());
        random.get(i).click();

        Tools.Bekle(1);
        List<WebElement> hdd=driver.findElements(By.cssSelector("ul[data-attr='3'] input"));
        int i1=(int) (Math.random() * hdd.size());
        hdd.get(i1).click();

        Tools.Bekle(1);
        WebElement addToCart = driver.findElement(By.id("add-to-cart-button-1"));
        addToCart.click();

        Tools.Bekle(1);
        WebElement dogrulama = driver.findElement(By.className("content"));
        Assert.assertTrue(dogrulama.getText().contains("The product has been added to your "));
        Tools.Bekle(1);

    }
    @Test
    @Parameters("mesaj3")
    void ParametreliSearcTest(String CS4){


        driver.get("https://demo.nopcommerce.com");

        WebElement adobePhotoshopCS4 = driver.findElement(By.id("small-searchterms"));
        adobePhotoshopCS4.sendKeys(CS4);

        WebElement searchButton = driver.findElement(By.xpath("//button[@class='button-1 search-box-button']"));
        searchButton.click();

        WebElement dogrulama = driver.findElement(By.linkText("Adobe Photoshop CS4"));
        Assert.assertTrue(dogrulama.getText().contains("Adobe Photoshop CS4"));

    }

}















































