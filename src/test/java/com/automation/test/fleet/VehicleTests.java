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
    public void verifyAllVehicleInformations() {

        test = report.createTest("Verify All Vehicle Information");
        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        test.info("Login as truck driver");

        loginPage.login();
        vehiclesPage.navigateTo("Fleet", "Vehicles");
        vehiclesPage.clickOnGridSettings();
        vehiclesPage.clickSelectAll();

        Set<String> expected = vehiclesPage.getAllVehicleInfoNamesFromGrid();
        //          for (String s : expected) {
        //                  System.out.println(s);
        //          }

        //      System.out.println("##########################################################");
        Set<String> actual = vehiclesPage.getVehicleInfoFromTableHeader();
        //          for (String s : actual) {
        //                  System.out.println(s);
        //          }

        Assert.assertEquals(actual, expected);

        test.pass("All Vehicle Information were verified");


    }

    @Test
    public void verifyInfoText() {

        test = report.createTest("Verify General Info Text");
        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        test.info("Login as truck driver");

        loginPage.login("user1", "UserUser123");
        vehiclesPage.navigateTo("Fleet", "Vehicles");


        vehiclesPage.randomlySelectACar();
        String actualInfoMessage = vehiclesPage.getInfoText();
        String expectedINfoMessage = "General Information";

        Assert.assertEquals(actualInfoMessage, expectedINfoMessage);

        test.pass("General Info Text is verified");
    }

    @Test
    public void verifyDriverAddEvent() {

        test = report.createTest("Verify Driver add event");
        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        test.info("Login as truck driver");

        loginPage.login("user1", "UserUser123");
        vehiclesPage.navigateTo("Fleet", "Vehicles");


        vehiclesPage.randomlySelectACar();
        vehiclesPage.clickAddEvent();

        String testTitle = "test_101";
        vehiclesPage.sendTextToTitle(testTitle);
        //   vehiclesPage.addReminder();

        BrowserUtils.wait(5);
        vehiclesPage.clickSaveBtn();

//    List<String > activityTitles = vehiclesPage.getTitlesOfActivity();
//    for (String activityTitle : activityTitles) {
//        System.out.println(activityTitle);
//    }
        //  System.out.println(vehiclesPage.getLastEventTitle());

        Assert.assertTrue(vehiclesPage.getTitlesOfActivity().contains(testTitle));

        test.pass("Driver can add event is verified");
    }

    @Test
    public void verify_reset_button() {
        test = report.createTest("Verify Reset Button");
        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        test.info("Login as truck driver");

        loginPage.login("user1", "UserUser123");
        vehiclesPage.navigateTo("Fleet", "Vehicles");

        int BEFORE_reset = vehiclesPage.number_of_cars_per_page();

        vehiclesPage.change_view_per_page_100();
        int new_per_page = vehiclesPage.number_of_cars_per_page();

        Assert.assertEquals(new_per_page, 100);

        vehiclesPage.click_reset_btn();
        int AFTER_reset = vehiclesPage.number_of_cars_per_page();

        Assert.assertEquals(BEFORE_reset, AFTER_reset);

        test.pass("Reset Button is verified");


    }


    //"1.Verify that Store manager or sales manager should be able to see all vehicle information once navigate to Vehicle page
    @Test
    public void verify_see_all_vehicle_info() {

        test = report.createTest("Verify All Vehicle Information");
        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        test.info("Login as store manager");

        loginPage.login("storemanager85", "UserUser123");
        vehiclesPage.navigateTo("Fleet", "Vehicles");
        vehiclesPage.clickOnGridSettings();
        vehiclesPage.clickSelectAll();

        Set<String> expected = vehiclesPage.getAllVehicleInfoNamesFromGrid();

        Set<String> actual = vehiclesPage.getVehicleInfoFromTableHeader();

        Assert.assertEquals(actual, expected);

        test.pass("All Vehicle Information were verified");

    }

//    2. Verify that Store manager or sales manager should be able to create  car

    @Test
    public void verify_manager_create_car() {
        test = report.createTest("Verify Store Manager should able to create a car");
        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        test.info("Login as store manager");

        loginPage.login("storemanager85", "UserUser123");
        vehiclesPage.navigateTo("Fleet", "Vehicles");
        vehiclesPage.click_create_a_car();
        BrowserUtils.wait(5);
        vehiclesPage.set_new_vehicle_info("ABC123", "King M", "2020");

        //System.out.println(vehiclesPage.get_saved_message());
        Assert.assertEquals(vehiclesPage.get_saved_message(), "Entity saved");
    }


    //    2. Verify that Store manager or sales manager should be able to  cancel car

    @Test
    public void cancel_car_creation() {
        test = report.createTest("Verify Store Manager should able to cancel car creation");
        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        test.info("Login as store manager");

        loginPage.login("storemanager85", "UserUser123");
        vehiclesPage.navigateTo("Fleet", "Vehicles");
        vehiclesPage.click_create_a_car();
        vehiclesPage.click_cancel_btn();
        String expected = "All - Car - Entities - System - Car - Entities - System";
        String actual = vehiclesPage.getPageTitle();
        Assert.assertEquals(actual, expected);


    }

    //5.Verify that Store manager or sales manager should be able to add event

    @Test
    public void verifyMAnagerAddEvent() {

        test = report.createTest("Verify Manager add event");
        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        test.info("Login as store manager");

        loginPage.login("storemanager85", "UserUser123");
        vehiclesPage.navigateTo("Fleet", "Vehicles");


        vehiclesPage.randomlySelectACar();
        vehiclesPage.clickAddEvent();

        String testTitle = "test_101";
        vehiclesPage.sendTextToTitle(testTitle);

        BrowserUtils.wait(5);
        vehiclesPage.clickSaveBtn();

        Assert.assertTrue(vehiclesPage.getTitlesOfActivity().contains(testTitle));

        test.pass("Manager can add event is verified");
    }

    // 6.Verifty that Store manager or sales manager should be able reset Grid by click on Grid setting"

    @Test
    public void verify_manager_reset_button() {
        test = report.createTest("Verify Reset Button");
        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        test.info("Login as store manager driver");

        loginPage.login("storemanager85", "UserUser123");
        vehiclesPage.navigateTo("Fleet", "Vehicles");

        int BEFORE_reset = vehiclesPage.number_of_cars_per_page();

        vehiclesPage.change_view_per_page_100();
        int new_per_page = vehiclesPage.number_of_cars_per_page();

        Assert.assertEquals(new_per_page, 100);

        vehiclesPage.click_reset_btn();
        int AFTER_reset = vehiclesPage.number_of_cars_per_page();

        Assert.assertEquals(BEFORE_reset, AFTER_reset);

        test.pass("Reset Button is verified for store manager");


    }

    //3.Verify that Store manager or sales manager should be able to add Vehicle Module and Vehicle Make


}
