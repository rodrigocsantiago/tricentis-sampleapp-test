package com.rsantiago.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SeleniumUtils {
    //  Select a value from a SELECT element
    public static void selectValueFromElement(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public static void checkSingleValue(List<WebElement> elementList, String value) {
        elementList.stream()
                .filter(g -> g.getAttribute("value").equalsIgnoreCase(value))
                .forEach(element -> {
                    //  The element to be clicked is the next sibling of INPUT element
                    element.findElement(By.xpath("following-sibling::*[1]")).click();
                });
    }

    public static void checkMultipleValues(List<WebElement> elementList, String attributeToCheck, String[] values) {
        Set<String> valuesSet = Arrays.stream(values).collect(Collectors.toSet());
        elementList.stream()
                .filter(h -> valuesSet.contains(h.getAttribute(attributeToCheck))) // filter by attribute=attribute to check
                .forEach(element -> {
                    //  The element to be clicked is the next sibling of INPUT element
                    element.findElement(By.xpath("following-sibling::*[1]")).click();
                });
    }
}
