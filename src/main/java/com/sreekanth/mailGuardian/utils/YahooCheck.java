package com.sreekanth.mailGuardian.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sreekanth.mailGuardian.constants.Literals;
import com.sreekanth.mailGuardian.constants.Logging;

public class YahooCheck {

    static Logger logger = LoggerFactory.getLogger(Literals.AUDIT_LOGGER);

    public static boolean checkYahooUserExists(String uname, String trace) {

        try {
            WebDriver driver = new HtmlUnitDriver();

            logger.info(Logging.YAHOO_MAIL_IS_LOG + uname + "~" + trace);

            driver.get(Literals.YAHOO_LOGIN_URL);

            WebElement usernameField = driver.findElement(By.id(Literals.USERNAME_INPUT_ID));
            usernameField.sendKeys(uname);

            WebElement submitButton = driver.findElement(By.id(Literals.SUBMIT_BUTTON_ID));
            submitButton.click();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String pageSource = driver.getPageSource();
            driver.quit();

            if (pageSource.contains(Literals.INVALID_USERNAME_MESSAGE)) {
                logger.info(Logging.YAHOO_MAIL_NOT_EXIST_LOG + trace);
                return false;
            } else {
                logger.info(Logging.YAHOO_MAIL_EXIST_LOG + trace);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
