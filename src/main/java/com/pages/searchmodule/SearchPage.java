package com.pages.searchmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class SearchPage {
    public WebDriver driver;
    public WebDriverWait wait;

    @FindBy(name = "q")
    private WebElement textBox;

    @FindBy(id = "search_button_homepage")
    private WebElement searchBtn;

    @FindBy(linkText = "Videos")
    private WebElement videosLink;

    @FindBy(className = "tile--vid")
    private List<WebElement> allVdeos;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,30);
        PageFactory.initElements(driver, this);
    }

    public void goTo() {
        driver.get("https://duckduckgo.com/");
    }

    public void doSearch(String keyword) {
        wait.until(ExpectedConditions.visibilityOf(textBox));
        textBox.sendKeys(keyword);
        searchBtn.click();
    }

    public void goToVideos() {
        wait.until(ExpectedConditions.visibilityOf(videosLink));
        videosLink.click();
    }

    public int getResult() {
        By by = By.className("tile--vid");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
        return allVdeos.size();
    }
}
