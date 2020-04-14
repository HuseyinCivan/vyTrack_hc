package com.automation.pages.fleet;

import com.automation.utilities.AbstractPageBase;
import com.automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;
//"1.Verify that truck driver should be able to see all Vehicle information once navigate to Vehicle page.
//2.Verify that when user click on any car on the grid , system should display general information
//about the car
//3.Verify that truck driver can add Event and it should display under Activity tab and General
//information page as well .
//4.Verify that Truck driver can reset the Grid by click on Grid setting"

public class VehiclesPage extends AbstractPageBase {

    //****AC 1****//
    @FindBy(css = "[class=\"column-manager dropdown\"]")
    private WebElement gridSettingsBtn;

    @FindBy(linkText = "Select All")
    private WebElement selectAllBtn;

    @FindBy(xpath = "//tbody[@class=\"ui-sortable\"]/tr")
    private List<WebElement> vehicleInfoNames;


    @FindBy(css = "[class=\"grid-header\"]>tr>th[class^=\"grid\"]")
    private List<WebElement> vehicleInfoTableHeader;


    @FindBy(xpath = "//div[@class=\"dropdown-menu\"]//span[@class=\"close\"]")
    private WebElement closeBtn;


    //****AC 2 ****//
    @FindBy(xpath = "//label[@class=\"dib\" and starts-with(text(), 'of')]")
    private WebElement numberOfPages;

    @FindBy(css = "[type=\"number\"]")
    private WebElement pageNumberBox;

//    @FindBy(css = "tbody>tr>td[class$='LicensePlate']")
//    private List<WebElement> carsOnCurrentPage;

    @FindBy(tagName = "h5")
    private WebElement generalInfo;

    //****AC 3 ****//

    @FindBy(css = "[title=\"Add an event to this record\"]")
    private WebElement addEventBtn;

    @FindBy(css = "[id^=\"oro_calendar_event_form_title-uid\"]")
    private WebElement titleTextBox;


    @FindBy(xpath = "//*[text()=\"Save\"]")
    private WebElement saveBtn;


    @FindBy(xpath = "//*[@class=\"message-item message\"]")
    private List<WebElement> eventTitlesOnActivity;


//    @FindBy(xpath = "(//*[@class=\"message-item message\"])[1]")
//    private WebElement lastCreatedEventTitle;

    @FindBy(css = "[data-column-label=\"Title\"]")
    private List<WebElement> allTitlesUnderCalenderEvent;


    @FindBy(xpath = "//ul[@class=\"icons-holder\"]//*[contains(text(),\"Next\")]")
    private WebElement nextBtnOnCalenderEvents;




    //****AC 1****//
    public void clickOnGridSettings() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.elementToBeClickable(gridSettingsBtn)).click();
    }

    public void clickSelectAll() {
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(selectAllBtn)).click();
    }

    public Set<String> getAllVehicleInfoNamesFromGrid() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody[@class=\"ui-sortable\"]/tr")));
        Set<String> allVehicleInfoNamesFromGrid = new HashSet<>();
        for (WebElement vehicleInfoName : vehicleInfoNames) {
            if (vehicleInfoName.getText().equals("Catalog Value (VAT Incl.)")) {
                allVehicleInfoNamesFromGrid.add("CVVI");
            } else {
                allVehicleInfoNamesFromGrid.add(vehicleInfoName.getText().toUpperCase());
            }
        }
        return allVehicleInfoNamesFromGrid;
    }

    public void clickClose() {
        wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
    }

    public Set<String> getVehicleInfoFromTableHeader() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=\"grid-header-row\"]>th[class^=\"grid-cell grid-header-cell grid-header-cell-\"]")));
        Set<String> allVehicleInfoNamesFromHeader = new HashSet<>();
        for (WebElement element : vehicleInfoTableHeader) {
            allVehicleInfoNamesFromHeader.add(element.getText().toUpperCase());
        }
        return allVehicleInfoNamesFromHeader;
    }


    //****AC 2****//
    public void randomlySelectACar() {
        int totalPageNumber = Integer.parseInt(numberOfPages.getText().split(" ")[1]);
        //  System.out.println("Total pageNumber = " + totalPageNumber);
        Random random = new Random();
        int pageNum = random.nextInt(totalPageNumber) + 1;
        //  System.out.println(pageNum);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('[type=\"number\"]').setAttribute('value', '')");

        pageNumberBox.sendKeys(pageNum + "", Keys.ENTER);

        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.wait(1);

        List<WebElement> carsOnCurrentPage = driver.findElements(By.cssSelector("tbody>tr>td[class$='LicensePlate']"));
        int carIndexNum = random.nextInt(carsOnCurrentPage.size());

        wait.until(ExpectedConditions.elementToBeClickable(carsOnCurrentPage.get(carIndexNum)));

        carsOnCurrentPage.get(carIndexNum).click();

    }

    public String getInfoText() {
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h5")));
        return generalInfo.getText();
    }

    //****AC 3****//

    public void clickAddEvent(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(addEventBtn)).click();
    }

    public void sendTextToTitle(String title){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(titleTextBox)).sendKeys(title);

    }

    public void clickSaveBtn(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public List<String > getTitlesOfActivity(){
        BrowserUtils.waitForPageToLoad(10);
BrowserUtils.wait(3);
        List<String > titlesOfActivities=new ArrayList<>();
        for (int x = 0; x < eventTitlesOnActivity.size() ; x++) {
            titlesOfActivities.add(eventTitlesOnActivity.get(x).getText());
        }
        return titlesOfActivities;
    }



}
