package strony;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class PageWithResults {

    private WebDriver driver;

    @FindBy(xpath = "//h4[contains(@class,'list_title')]//b")
    public List<WebElement> listOfTitle;

    public PageWithResults(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public PageWithResults getTitle() {
        List<String> hotelNames = listOfTitle.stream()
                .map(el -> el.getText())
                //można użyć innej metody jeśli nie wszystkie nazwy się załadowały
                //.map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
        System.out.println(hotelNames.size());
        hotelNames.forEach(el -> System.out.println(el));


        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
//        for (WebElement title : listOfTitle) {
//            System.out.println(title.getText());
//        }
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
//        softAssert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
//        softAssert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
//        softAssert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
//        softAssert.assertAll();
        return new PageWithResults(driver);
    }

}
