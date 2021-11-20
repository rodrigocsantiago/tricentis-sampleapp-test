package com.rsantiago.framework;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;

import java.util.EnumSet;

public class WebDriverManager {

    private static final ThreadLocal<WebDriver> instance = new ThreadLocal<>();

    private WebDriverManager() {}

    public static WebDriver getDriver(BrowserType browserType) {
        if (instance.get() == null) {
            instance.set(WebDriverFactory.getDriver(browserType));
        }
        return instance.get();
    }

    public static WebDriver getDriverFromArguments() {
        BrowserType browserType = BrowserType.CHROME;
        String givenBrowser = System.getProperty("browser.type");
        if (givenBrowser != null) {
            browserType = EnumSet.allOf(BrowserType.class).stream()
                    .filter(browser -> browser.isEqualTo(givenBrowser))
                    .findAny()
                    .orElseThrow(() -> { throw new InvalidArgumentException("browser.type argument provided is not a valid browser type"); });
        }
        return WebDriverManager.getDriver(browserType);
    }

    public static void closeDriver() {
        if (instance.get() != null) {
            instance.get().close();
            instance.remove();
        }
    }
}
