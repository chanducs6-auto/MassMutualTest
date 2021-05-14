package PageObject;


import org.openqa.selenium.By;



public class PageObject {

	public By balance1 = By.id("txt_val_1");
	public By balance2 = By.id("txt_val_2");
	public By balance3 = By.id("txt_val_4");
	public By balance4 = By.id("txt_val_5");
	public By balance5 = By.id("txt_val_6");
	public By totalBalance = By.id("txt_ttl_val");
	public By allBalance = By.xpath("//*[contains(@id,'txt_va')]");
	
}