package com.rsantiago.framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumBasePage {
    //  Maximum wait in seconds for an element to load until timeout.
    private static final int WAIT_TIMEOUT = 30;

    protected WebDriver driver;
    protected WebDriverWait wait;

    public SeleniumBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIMEOUT), this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    protected void waitForPageLoad() {
        wait.until((drv) -> String.valueOf(((JavascriptExecutor) drv).executeScript("return document.readyState"))
                .equalsIgnoreCase("complete"));
    }
}
