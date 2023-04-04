package testy;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import strony.Base;
import strony.PageRegister;
import strony.PageWithParameters;
import strony.PageWithResults;
import utils.ExcelReader;

import java.io.IOException;

public class ChooseParametersTest extends Base {

    @Test
    public void userChooseParameters() {
        openBrowser("http://www.kurs-selenium.pl/demo/");
        PageRegister pageRegister = new PageRegister(driver);
        PageWithParameters pageWithParameters = new PageWithParameters(driver);
        pageWithParameters
                .setCity("London")
                .setDates("01/03/2023", "04/03/2023")
                .setPeople()
                .performSearch()
                .getTitle();
        closeBrowser();
    }
    @Test(dataProvider = "data")
    public void userChooseParametersWithDataProvider(String city, String hotel) {
        ExtentTest test = extentReports.createTest("Search Hotel Test");
        openBrowser("http://www.kurs-selenium.pl/demo/");
        PageRegister pageRegister = new PageRegister(driver);
        PageWithParameters pageWithParameters = new PageWithParameters(driver);
        pageWithParameters
                .setCity(city)
                .setDates("01/03/2023", "04/03/2023")
                .setPeople()
                .performSearch();
        test.log(Status.PASS, "All parameters done");
        Assert.assertEquals("Jumeirah Beach Hotel", hotel);
        test.log(Status.PASS, "Assertion passed");
        closeBrowser();
    }

    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.resdExcel("testData.xls");
    }

}
