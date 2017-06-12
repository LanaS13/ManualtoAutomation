import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.util.concurrent.TimeUnit;


public class TestMain {

    private WebDriver driver;

    @BeforeTest
    public void setup (){
        final File file = new File(PropertyLoader.loadProperty("path.webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test (priority = 1)

    public void test1 (){
        driver.navigate().to("http://juliemr.github.io/protractor-demo/");

        Assert.assertEquals(driver.getCurrentUrl(), "http://juliemr.github.io/protractor-demo/", "Wrong URL is used");

    }

    @Test (priority = 2)

    public void elements(){

        try {
           /* driver.findElement(By.xpath(".//h3"));
            Assert.assertEquals(driver.getTitle(), "Super Calculator", "Wrong title name");*/
           WebElement input1 = driver.findElement(By.xpath(".//input[1]"));
           input1.clear();
           String value1 = "1";
           input1.sendKeys(value1);

           Assert.assertEquals(input1.getAttribute("value"), value1, "Value is not set");

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Element is not found");
        }
    }

    @Test (priority = 3)

    public void actions(){

        WebElement actionSelection = driver.findElement(By.xpath(".//select//option[1]"));

        Assert.assertEquals(actionSelection.getText(), "+", "Value is not set");
    }


    @Test (priority = 4)

    public void resultCheck () throws InterruptedException {

        WebElement input1 = driver.findElement(By.xpath(".//input[1]"));
        input1.clear();
        WebElement input2 = driver.findElement(By.xpath(".//input[2]"));
        input2.clear();


        String value1 = "1";
        input1.sendKeys(value1);

        String value2 = "1";
        input2.sendKeys(value2);

        WebElement actionSelection = driver.findElement(By.xpath(".//select//option[1]"));

        try {
                WebElement goButton = driver.findElement(By.xpath(".//*[@id='gobutton']"));
                goButton.click();
                driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Button is not found");
            }

            WebElement sum = driver.findElement(By.xpath(".//h2"));
            Thread.sleep(5000);
            Assert.assertEquals(sum.getText(), "2", "Result of sum action is not counted");
    }

   @AfterTest

    public void close (){

        driver.quit();

    }
}
