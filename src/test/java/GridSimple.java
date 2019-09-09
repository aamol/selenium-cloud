import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridSimple {

	@Test
	public void test() throws MalformedURLException {
		DesiredCapabilities dr=null;
		dr=DesiredCapabilities.chrome();
		dr.setCapability("CloudType", "GCP");
		dr.setBrowserName("chrome");
		dr.setPlatform(Platform.WINDOWS);
			
		RemoteWebDriver driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dr);
		driver.navigate().to("http://gmail.com");
		driver.findElement(By.xpath("//input[@id='Email']")) .sendKeys("username");
		driver.findElement(By.xpath("//input[@id='Passwd']")) .sendKeys("password");
		driver.close();
	
}

}
