package com.automation.pages.fleet;

import com.automation.utilities.AbstractPageBase;
import com.automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VehiclesPage extends AbstractPageBase {

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


 public void clickOnGridSettings(){
     BrowserUtils.waitForPageToLoad(20);
     wait.until(ExpectedConditions.elementToBeClickable(gridSettingsBtn)).click();

 }

 public void clickSelectAll(){
     BrowserUtils.waitForPageToLoad(10);
    wait.until(ExpectedConditions.elementToBeClickable(selectAllBtn)).click();
 }


    public Set<String > getAllVehicleInfoNamesFromGrid(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody[@class=\"ui-sortable\"]/tr")));
       Set<String> allVehicleInfoNamesFromGrid= new HashSet<>();
        for (WebElement vehicleInfoName : vehicleInfoNames) {
            if(vehicleInfoName.getText().equals("Catalog Value (VAT Incl.)")){
                allVehicleInfoNamesFromGrid.add("CVVI");
            }else{
                allVehicleInfoNamesFromGrid.add(vehicleInfoName.getText().toUpperCase());
            }
        }
        return allVehicleInfoNamesFromGrid;
    }



 public void clickClose(){
     wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
 }



    public Set<String> getVehicleInfoFromTableHeader(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=\"grid-header-row\"]>th[class^=\"grid-cell grid-header-cell grid-header-cell-\"]")));
        Set<String> allVehicleInfoNamesFromHeader=new HashSet<>();
        for (WebElement element : vehicleInfoTableHeader) {
            allVehicleInfoNamesFromHeader.add(element.getText().toUpperCase());
        }
        return allVehicleInfoNamesFromHeader;
    }

}
