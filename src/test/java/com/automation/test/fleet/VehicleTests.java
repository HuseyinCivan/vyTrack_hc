package com.automation.test.fleet;

import com.automation.pages.fleet.VehiclesPage;
import com.automation.pages.login.LoginPage;
import com.automation.utilities.AbstractTestBase;
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
        test.info("Login as store manager");

        loginPage.login("user1","UserUser123");
        vehiclesPage.navigateTo("Fleet","Vehicles");
        vehiclesPage.clickOnGridSettings();
        vehiclesPage.clickSelectAll();

        Set<String > expected =vehiclesPage.getAllVehicleInfoNamesFromGrid();
            for (String s : expected) {
                    System.out.println(s);
            }

        System.out.println("##########################################################");
        Set<String > actual=vehiclesPage.getVehicleInfoFromTableHeader();
            for (String s : actual) {
                    System.out.println(s);
            }

       Assert.assertEquals(actual,expected);

      test.pass("All Vehicle Information were verified");




}




}
