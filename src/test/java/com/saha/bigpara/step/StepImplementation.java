package com.saha.bigpara.step;


import com.saha.bigpara.base.BaseTest;

import com.saha.bigpara.mapping.MapMethodType;
import com.saha.bigpara.mapping.Mapper;
import com.saha.bigpara.util.ContexPage;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import static com.saha.bigpara.mapping.Mapper.foundActivity;

public class StepImplementation extends BaseTest {

    private WebDriverWait wait;


    @BeforeScenario
    public void setUpStep() throws MalformedURLException {
        setUp();
        driver = BaseTest.driver;
        wait = new WebDriverWait(driver, 60);

    }

    @Step("<button> butonuna tıkla")
    public void clickButton(String button){
        wait.until(ExpectedConditions.elementToBeClickable(foundActivity(MapMethodType.CLICK_ELEMENT, button)));
        driver.findElement(Mapper.foundActivity(MapMethodType.CLICK_ELEMENT,button)).click();
    }

    @Step("<i> saniye bekle")
    public void waitSecods(int i){
        ContexPage.waitSeconds(i);
    }

    @Step("<by> alanına <text> yaz")
    public void inputText(String by, String text){
        wait.until(ExpectedConditions.presenceOfElementLocated(foundActivity(MapMethodType.INPUT_ELEMENT,by)));
        log.info(text);
        driver.findElement(Mapper.foundActivity(MapMethodType.INPUT_ELEMENT,by)).sendKeys(text);
    }

    @Step("<list> listesinden <i> numaralı elementi seç")
    public void selectFromList(String list, int i) {
        wait.until(ExpectedConditions.presenceOfElementLocated(foundActivity(MapMethodType.SELECT_ELEMENT, list)));
        driver.findElements(Mapper.foundActivity(MapMethodType.SELECT_ELEMENT, list));
        List<MobileElement> listElement = driver.findElements(foundActivity(MapMethodType.SELECT_ELEMENT, list));
        listElement.get(i - 1).click();
    }

    @Step("<i> kere sağa git")
    public void swipeLeft(int i){
        for (int j=0 ; j<i; j++) {
            ContexPage.swipeLeftAccordingToPhoneSize();
        }
    }

    @Step("<i> kere sola git")
    public void swipeRight(int i){
        for (int j=0 ; j<i; j++) {
            ContexPage.swipeRightAccordingToPhoneSize();
        }
    }

    @Step("<i> kere aşağı in")
    public void swipeUp(int i){
        for (int j=0 ; j<i; j++) {
            ContexPage.swipeUpAccordingToPhoneSize();
        }
    }

    @Step("<i> kere yukarı çık")
    public void swipeDown(int i){
        for (int j=0 ; j<i; j++) {
            ContexPage.swipeDownAccordingToPhoneSize();
        }
    }

    @Step("<by> elementini görene kadar aşağı in")
    public void swipeUpUntilSeeElement(String by) {
        for (int i=0 ; i<1000; i++) {
            try {
                driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).isDisplayed();
                log.info(by + "elementi görünür");
                break;
            } catch (Exception ex) {
                log.info(by + "elementi görünür değil");
                ContexPage.swipeUpAccordingToPhoneSize();
            }
        }
    }

    @Step("<by> elementini görene kadar yukarı çık")
    public void swipeDownUntilSeeElement(String by) {
        for (int i=0 ; i<1000; i++) {
            try {
                driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).isDisplayed();
                log.info(by + "elementi görünür");
                break;
            } catch (Exception ex) {
                log.info(by + "elementi görünür değil");
                ContexPage.swipeDownAccordingToPhoneSize();
            }
        }
    }

    @Step("<by> elementini görene kadar sola git")
    public void swipeRightUntilSeeElement(String by) {
        for (int i=0 ; i<1000; i++) {
            try {
                driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).isDisplayed();
                log.info(by + "elementi görünür");
                break;
            } catch (Exception ex) {
                log.info(by + "elementi görünür değil");
                ContexPage.swipeRightAccordingToPhoneSize();
            }
        }
    }

    @Step("<by> elementini görene kadar sağa git")
    public void swipeLeftUntilSeeElement(String by) {
        for (int i=0 ; i<1000; i++) {
            try {
                driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).isDisplayed();
                log.info(by + "elementi görünür");
                break;
            } catch (Exception ex) {
                log.info(by + "elementi görünür değil");
                ContexPage.swipeLeftAccordingToPhoneSize();
            }
        }
    }

    @Step("<by> elementinin görünür olmasını bekle")
    public void waitForElement(String by){
        driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).isDisplayed();
        log.info(by + "elementi görünür");
    }

    @Step("<by> elementinin görünürlüğünü kontrol et")
    //aşağıda kalan elementler için
    public void checkElementDisplayed(String by){
        for (int i=0 ; i<10; i++) {
            try {
                driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).isDisplayed();
                log.info(by + "elementi görünür");
                break;
            } catch (Exception ex) {
                log.info(by + "elementi görünür değil");
                ContexPage.swipeUpAccordingToPhoneSize();
            }
        }
    }

    @Step("<list> listesindeki elementlerin görünürlüğünü kontrol et")
    public void checkElementsDisplayed(String list){
        driver.findElements(Mapper.foundActivity(MapMethodType.SELECT_ELEMENT, list));
        List<MobileElement> listElement = driver.findElements(foundActivity(MapMethodType.SELECT_ELEMENT, list));
        for(int i =0 ; i< listElement.size() ; i++) {
            try {
                listElement.get(i).isDisplayed();
                log.info(i+1 + "." + list + " elementi görünür");
            }catch(Exception ex){
                log.info(i+1 + "." + list + " elementi görünür değil");
            }
        }
    }

    @Step("<by> elementinin textini al")
    public void getText(String by){
            String text = driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).getText();
            if(text.length() != 0) {
                log.info(by + ":" + text);
            }else{
                log.info(by+" texti boş");
            }
    }


    @Step("<by> alanını kontrol et")
    public void check(String by){

    }

    @Step("<by> alanındaki texti <value> değişkenine ata")
    public void saveParameter(String by, String value){
        MobileElement element = ContexPage.getElement(Mapper.foundActivity(MapMethodType.IS_ELEMENT,by));
        String elementText = element.getText();
        Assert.assertNotNull(elementText+" null geldi",elementText);
        ContexPage.setParameter(elementText,value);
    }

    @Step("<value> değişkenine atanan texti sayfada ara")
    public void searchText(String value){
            Assert.assertTrue("texti sayfada bulunamadı", driver.getPageSource().contains(value));
            log.info(value + " texti sayfada bulundu");
    }

    @Step("<by1> ve <by2> elementlerinin eşitliğini kontrol et")
    public void checkElementsEquals(String by1, String by2){
    try{
        Assert.assertEquals(by1,by2);
            log.info(by1 + " ve " + by2 + " elementleri eşit");
    }catch(AssertionError e){
            log.info(by1 + " ve " + by2 + " elementleri eşit değil");
        }
    }

    @AfterScenario
    public void tearDownStep() throws IOException {
        super.tearDown();
    }

}
