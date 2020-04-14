package com.automation.test.fleet;


import com.automation.pages.fleet.VehiclesPage;
import com.automation.pages.login.LoginPage;
import com.automation.utilities.AbstractTestBase;
import com.automation.utilities.BrowserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class VehicleTests extends AbstractTestBase {

    @Test
public void verifyAllVehicleInformations(){

        test=report.createTest("Verify All Vehicle Information");
        LoginPage loginPage=new LoginPage();
        VehiclesPage vehiclesPage=new VehiclesPage();
        test.info("Login as truck driver");

        loginPage.login();
        vehiclesPage.navigateTo("Fleet","Vehicles");
        vehiclesPage.clickOnGridSettings();
        vehiclesPage.clickSelectAll();

        Set<String > expected =vehiclesPage.getAllVehicleInfoNamesFromGrid();
  //          for (String s : expected) {
  //                  System.out.println(s);
  //          }

  //      System.out.println("##########################################################");
        Set<String > actual=vehiclesPage.getVehicleInfoFromTableHeader();
  //          for (String s : actual) {
  //                  System.out.println(s);
  //          }

       Assert.assertEquals(actual,expected);

      test.pass("All Vehicle Information were verified");




}
@Test
public void verifyInfoText(){

    test=report.createTest("Verify General Info Text");
    LoginPage loginPage=new LoginPage();
    VehiclesPage vehiclesPage=new VehiclesPage();
    test.info("Login as truck driver");

    loginPage.login("user1","UserUser123");
    vehiclesPage.navigateTo("Fleet","Vehicles");


    vehiclesPage.randomlySelectACar();
    String actualInfoMessage = vehiclesPage.getInfoText();
    String expectedINfoMessage = "General Information";

    Assert.assertEquals(actualInfoMessage,expectedINfoMessage);
}

@Test
    public void verifyDriverAddEvent(){

    test=report.createTest("Verify Driver add event");
    LoginPage loginPage=new LoginPage();
    VehiclesPage vehiclesPage=new VehiclesPage();
    test.info("Login as truck driver");

    loginPage.login("user1","UserUser123");
    vehiclesPage.navigateTo("Fleet","Vehicles");


    vehiclesPage.randomlySelectACar();
    vehiclesPage.clickAddEvent();

    String testTitle ="test_101";
    vehiclesPage.sendTextToTitle(testTitle);
    vehiclesPage.clickSaveBtn();

//    List<String > activityTitles = vehiclesPage.getTitlesOfActivity();
//    for (String activityTitle : activityTitles) {
//        System.out.println(activityTitle);
//    }
  //  System.out.println(vehiclesPage.getLastEventTitle());

   Assert.assertTrue(vehiclesPage.getTitlesOfActivity().contains(testTitle));

   vehiclesPage.navigateTo("Activities","Calendar Events");



}




}
