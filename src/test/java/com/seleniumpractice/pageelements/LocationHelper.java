package com.seleniumpractice.pageelements;

import com.seleniumpractice.Hooks;
import com.seleniumpractice.utils.RandomClassHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class LocationHelper extends Hooks {

    private String searchItem;
    /* public void search(String item)
    {
       // driver.findElement(By.id("search")).sendKeys("Laptop");
        driver.findElement(By.id("search")).clear();
        driver.findElement(By.id("search")).clear();
        driver.findElement(By.id("search")).sendKeys(item);
        driver.findElement(By.cssSelector(".dc-search-fieldset__submit-button")).click();
    }*/

    public void search(String item) {
        driver.findElement(By.id("search")).clear();
        driver.findElement(By.id("search")).clear();
        //this.searchItem=item;
        enterSearchItem(item);
        //clickSearch();

    }

    public void enterSearchItem(String item) {
        driver.findElement(By.id("search")).sendKeys(item);
    }

    public void clickSearch() {
       // driver.findElement(By.cssSelector(".dc-search-fieldset__submit-button")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public String getPageTitle() {

        return driver.findElement(By.className("pageTitle")).getText();
    }

    public String getThumbNail() {
        return driver.findElement(By.className("current")).getText();

        //return driver.findElement(By.xpath("//div[@class='breadcrumb']/a[3]/span")).getText();
    }

    public String getCurrentUrl() {

        return driver.getCurrentUrl();
    }

    public String selectAnyProduct() {
        List<WebElement> productWebelements = driver.findElements(By.className("productTitle"));
        int productCount = productWebelements.size();
        int randomNumber = new RandomClassHelper().generateRandomNumber(productCount);  // to access Randomclasshelper class ,create
        // object new RandomClassHelper(), then access the
        // method generateRandomNumber

        String productSelected = productWebelements.get(randomNumber).getText();
        System.out.println("product selected is  " +productSelected);
        productWebelements.get(randomNumber).click();
        return productSelected;

    }

    public void addProductToBasket() {
        driver.findElement(By.cssSelector("#product-actions .channels.space-b .space-b.center .Button-hYXUXp.fIBPaH")).click();
        //driver.findElement(By.xpath("//div[@id='product-actions']/div[4]/div[1]/button")).click();    xpath
        driver.findElement(By.xpath("//button[text()='Continue to basket']")).click();

    }

    public String getProductsInBasket() {

        return driver.findElement(By.xpath("//a[@data-element='BasketItemTitle']")).getText();

    }


    //this is suggested search from search box
    public String selectDepartmentFromSuggestions(String item) {                                                             //1.shows depatments
        String selected=selectFromSuggestions(item, By.xpath("//div[@class='dc-search-suggestions__suggestion dc-search-suggestions__suggestion--term']"));
        //selectFromSuggestions(item, By.cssSelector("#searchForm .dc-header-search__suggestions .dc-search-suggestions .dc-search-suggestions__suggestion dc-search-suggestions__suggestion--term"));           //2.shows products
        return selected;
    }

    public String selectProductsFromSuggestions(String item) {
        String selected=selectFromSuggestions(item, By.xpath("//div[@class='dc-search-suggestions__suggestion dc-search-suggestions__suggestion--sayt']"));
        //selectFromSuggestions(item, By.cssSelector(".dc-search-suggestions .dc-search-suggestions__suggestion dc-search-suggestions__suggestion--sayt"));
        return selected;
    }


    public String selectFromSuggestions(String item, By by) {
        List<WebElement> suggestionsElements = driver.findElements(by);
        int listSize = suggestionsElements.size();
        System.out.println(listSize);


        if (listSize == 0) {
            fail("Dont have any suggestions" + item);
        }

        int randomIndex = new RandomClassHelper().generateRandomNumber(listSize);
        WebElement selectedElement = suggestionsElements.get(randomIndex);
        String selectedSuggestion = selectedElement.getText();
        System.out.println("selected suggestion is  " +selectedSuggestion);
        selectedElement.click();
        return selectedSuggestion;


    }

    public void selectPrice(String priceRange) {
        int counter = 0;
        List<WebElement>filters=driver.findElements(By.cssSelector(".dc-filter__option-list .dc-filter__option .dc-checkbox-wrapper"));
        // List<WebElement> filters=driver.findElements(By.xpath("//ul[@class='dc-filter__option-list']/li"));
        //List<WebElement> filters=driver.findElements(By.cssSelector(".dc-filter dc-filter--selected .dc-filter__header .dc-filter__option-list .dc-filter__option .dc-checkbox-wrapper .dc-checkbox dc-checkbox-extra-small .dc-checkbox-label"));
        // List<WebElement> filters=driver.findElements(By.xpath("//aside[@id='filters']/div[1]/section/div/div[2]/nav[1]/ul"));
        //List<WebElement> filters = driver.findElements(By.cssSelector(".dc-filter__option-list .dc-filter__option .dc-checkbox-wrapper .dc-checkbox dc-checkbox-extra-small .dc-checkbox-label"));
        for (WebElement filter : filters) {

            if (filter.getText().equalsIgnoreCase(priceRange)) {
                //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                counter++;
                System.out.println(counter);

                filter.click();
                break;
            }

        }
        if (counter == 0) {
            fail("filter choice is not available" + priceRange);


        }

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Double> getAllProductsPrices()
    {
        List<Double>collectedPrices =new ArrayList<Double>();
       List<WebElement> priceWebElements=driver.findElements(By.cssSelector(".price"));
        for (WebElement priceWebelement:priceWebElements)
              {


          String priceInString= priceWebelement.getText().replace("£","");
          double priceInDouble=Double.valueOf(priceInString);
          collectedPrices.add(priceInDouble);

        }

         if(collectedPrices.size()==0)
         {
             fail("No products collected");
         }
         return collectedPrices;

    }

}



