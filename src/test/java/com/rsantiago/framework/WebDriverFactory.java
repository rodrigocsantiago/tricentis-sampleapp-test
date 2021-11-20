package com.rsantiago.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.rsantiago.framework.BrowserType.*;

public class WebDriverFactory {

    private static final Map<BrowserType, Supplier<WebDriver>> webDriverMap = new HashMap<>();

    //  Details for each particular driver

    private static final Supplier<WebDriver> chromeSupplier = () -> {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    };

    private static final Supplier<WebDriver> edgeSupplier = () -> {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    };

    private static final Supplier<WebDriver> firefoxSupplier = () -> {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    };

    static {
        webDriverMap.put(CHROME, chromeSupplier);
        webDriverMap.put(EDGE, edgeSupplier);
        webDriverMap.put(FIREFOX, firefoxSupplier);
    }

    public static WebDriver getDriver(BrowserType browserType) {
        return webDriverMap.get(browserType).get();
    }
}
