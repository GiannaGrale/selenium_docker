package com.pages.newtours;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class FlightAvailabilityPage {
    public WebDriver driver;
    public WebDriverWait wait;

    @FindBy(xpath = "//font[contains(text(),\"Please press your browser's back\")]")
    private WebElement backToHomeTxt;

    public FlightAvailabilityPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,30);
        PageFactory.initElements(driver, this);
    }

    public void checkAvailability(){
        this.wait.until(ExpectedConditions.visibilityOf(this.backToHomeTxt));
        System.out.println(this.backToHomeTxt.getText());
    }
}
