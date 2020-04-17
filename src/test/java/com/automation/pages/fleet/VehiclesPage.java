package com.automation.pages.fleet;

import com.automation.utilities.AbstractPageBase;
import com.automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
//"1.Verify that truck driver should be able to see all Vehicle information once navigate to Vehicle page.
//2.Verify that when user click on any car on the grid , system should display general information
//about the car
//3.Verify that truck driver can add Event and it should display under Activity tab and General
//information page as well .
//4.Verify that Truck driver can reset the Grid by click on Grid setting"

public class VehiclesPage extends AbstractPageBase {

//#US1

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

    @FindBy(css = "[class=\"btn add-list-item\"]")
    private WebElement addRemindersBtn;

    @FindBy(css = "[id^=\"oro_calendar_event_form_reminders_2_method-uid\"]")
    private WebElement addRemainderEmailBtn;
    @FindBy(className = "number inline-field")
    private WebElement numberInlineBox;
    @FindBy(className = "unit inline-field")
    private WebElement unitInlineBox;


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


    @FindBy(xpath = "//*[starts-with(text(),\"View per page\")]/..//button")
    private WebElement view_per_page_cars;


    @FindBy(css = "[class=\"action btn reset-action mode-text-only\"]>i")
    private WebElement reset_btn;



        /*

        "2. As a store manager and Sales manager I should be able to create Vehicle"

    "1.Verify that Store manager or sales manager should be able to see all vehicle information once navigate to Vehicle page
    2. Verify that Store manager or sales manager should be able to create and cancel car
    3.Verify that Store manager or sales manager should be able to add Vehicle Module and Vehicle Make
    4.Verify that Store manager or sales manager should be able to edit or delete the car
    5.Verify that Store manager or sales manager should be able to add event
    6.Verifty that Store manager or sales manager should be able reset Grid by click on Grid setting"


     */

//#US2

    //****AC 2 ****//


    @FindBy(css = "[title=\"Create Car\"]")
    private WebElement create_car_btn;

    @FindBy(css = "[id^=\"custom_entity_type_LicensePlate-uid\"]")
    private WebElement license_plate;


    @FindBy(css = "[for^=\"custom_entity_type_Tags_\"]")
    private List<WebElement> vehicles_tags;


    @FindBy(css = "[id^=\"custom_entity_type_Driver-uid-\"]")
    private WebElement driver_name;


    @FindBy(css = "[id^=\"custom_entity_type_ModelYear-uid-\"]")
    private WebElement model_year;


    @FindBy(css = "[id^=\"date_selector_custom_entity_type_FirstContractDate-uid-\"]")
    private WebElement first_contract_date;

    @FindBy(css = "[data-handler=\"selectMonth\"]")
    private WebElement month;

    @FindBy(css = "[data-handler=\"selectYear\"]")
    private WebElement year;

    @FindBy(css = "[class^=\"ui-state-default\"]")
    public List<WebElement> day;

    @FindBy(xpath = "//*[text()=\"Vehicle Model\"]//..//..//button")
    private WebElement vehicle_Model_add;

    @FindBy(css = "[title=\"Filters\"]")
    private WebElement filter_btn;

    @FindBy(css = "[class=\"btn filter-criteria-selector oro-drop-opener oro-dropdown-toggle filter-default-value\"]")
    private WebElement model_name_search;

    @FindBy(css = "[class=\"value-field-frame\"]>input[name=\"value\"]")
    private WebElement model_searh_text_box;


    @FindBy(css = "[class=\"boolean-cell grid-cell grid-body-cell grid-body-cell-assigned\"]>input")
    private WebElement model_name_checkbox;


    @FindBy(css = "[class=\"btn btn-primary\"]")
    private WebElement select_vehicle_btn;


    @FindBy(xpath = "//div[text()=\"Entity saved\"]")
    private WebElement saved_message;


    @FindBy(xpath = "//*[@class=\"btn btn-success action-button\" and contains(text(),\"Save and Close\")]")
    private WebElement save_and_close_btn;

    @FindBy(css = "[title=\"Cancel\"]")
    private WebElement cancel_btn;


    //****AC 4 ****//

    @FindBy(css = "[title=\"Edit Car\"]")
    private WebElement edit_btn;










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
    public void clickAddEvent() {
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(addEventBtn)).click();
    }

    public void sendTextToTitle(String title) {
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(titleTextBox)).sendKeys(title);

    }

    public void addReminder() {
        BrowserUtils.wait(2);
        addRemindersBtn.click();
        Select select = new Select(addRemainderEmailBtn);
        select.selectByVisibleText("Email");
        wait.until(ExpectedConditions.elementToBeClickable(numberInlineBox)).sendKeys("2");
        Select select1 = new Select(unitInlineBox);
        select1.selectByVisibleText("days");
    }


    public void clickSaveBtn() {
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public List<String> getTitlesOfActivity() {
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.wait(3);
        List<String> titlesOfActivities = new ArrayList<>();
        for (int x = 0; x < eventTitlesOnActivity.size(); x++) {
            titlesOfActivities.add(eventTitlesOnActivity.get(x).getText());
        }
        return titlesOfActivities;
    }


    //****AC 4****//
    public int number_of_cars_per_page() {
        BrowserUtils.waitForPageToLoad(10);

        return Integer.parseInt(view_per_page_cars.getText().trim());
    }

    public void change_view_per_page_100() {
        BrowserUtils.waitForPageToLoad(10);

        view_per_page_cars.click();
        BrowserUtils.waitForPageToLoad(2);

        BrowserUtils.wait(2);
        WebElement view_per_page_100 = driver.findElement(By.xpath("//a[@class=\"dropdown-item\" and contains(text(),\"100\")]"));
        new Actions(driver).click(view_per_page_100).perform();
        BrowserUtils.wait(4);

    }

    public void click_reset_btn() {
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(reset_btn)).click();
        BrowserUtils.wait(5);
    }


    //#US2
    //****AC 2****//

    public void click_create_a_car() {
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(create_car_btn)).click();
        BrowserUtils.wait(3);
    }

    public void set_new_vehicle_info(String licence, String driverName, String modelYear) {
        BrowserUtils.waitForPageToLoad(10);
        license_plate.sendKeys(licence);
        BrowserUtils.wait(3);
        int tag_vehicle_size = vehicles_tags.size();
        int tags_index = new Random().nextInt(tag_vehicle_size);
        vehicles_tags.get(tags_index).click();
        BrowserUtils.wait(2);
        driver_name.sendKeys(driverName);
        model_year.sendKeys(modelYear);
        first_contract_date.click();
        BrowserUtils.wait(3);
        int month_index = new Random().nextInt(12);
        new Select(month).selectByIndex(month_index);
        BrowserUtils.wait(3);
        Select select_year = new Select(year);
        int year_index = new Random().nextInt(select_year.getOptions().size());
        select_year.selectByIndex(year_index);
        BrowserUtils.wait(3);
        int day_index = new Random().nextInt(day.size());
        day.get(day_index).click();
        BrowserUtils.wait(3);
        vehicle_Model_add.click();
        filter_btn.click();
        BrowserUtils.wait(2);
        model_name_search.click();
        BrowserUtils.wait(3);
        model_searh_text_box.sendKeys("haci", Keys.ENTER);
        BrowserUtils.wait(2);
        model_name_checkbox.click();
        BrowserUtils.wait(2);
        select_vehicle_btn.click();
        BrowserUtils.wait(3);
        save_and_close_btn.click();
        BrowserUtils.wait(3);

    }

    public String get_saved_message() {
        return saved_message.getText();
    }

    public void click_cancel_btn(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.elementToBeClickable(cancel_btn)).click();
        BrowserUtils.wait(4);


    }

    public String  getPageTitle(){
        return driver.getTitle();
    }



}
