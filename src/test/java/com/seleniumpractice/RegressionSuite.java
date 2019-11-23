package com.seleniumpractice;

import com.seleniumpractice.pageelements.LocationHelper;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class RegressionSuite extends Hooks {


    private LocationHelper helper = new LocationHelper();

    @Test
    public void searchTest() {
        //driver.findElement(By.id("search")).sendKeys("Laptop");
        // driver.findElement(By.cssSelector(".dc-search-fieldset__submit-button")).click();
        String searchTerm = "laptops";
        helper.search(searchTerm);
        helper.clickSearch();
        //String actualTitle1 = driver.getTitle();        // here assertion can be done in 3 ways
        String actualTitle = helper.getPageTitle();        // 1.get pagetitle
        System.out.println(actualTitle);
        String actualThumbNail = helper.getThumbNail();    //2.get Thumbnail ---- Home>Laptop >......//breadcrumbs
        System.out.println(actualThumbNail);
        String actualCurrentUrl = helper.getCurrentUrl();   //3.current url contains search term name
        System.out.println(actualCurrentUrl);

        assertThat(actualCurrentUrl,containsString(searchTerm));
        assertThat(actualThumbNail,is(equalToIgnoringCase(searchTerm)));
        assertThat(actualTitle,is(equalToIgnoringCase(searchTerm)));



    }

    @Test
    public void addProductToBasketTest() {
        //driver.findElement(By.id("search")).sendKeys("Laptop");
        // driver.findElement(By.cssSelector(".dc-search-fieldset__submit-button")).click();

        helper.search("Microwaves");
        helper.clickSearch();
        //String actualTitle = driver.getTitle();
        // String actualTitle=helper.getPageTitle();
        //System.out.println(actualTitle);
        String expected = helper.selectAnyProduct();
        System.out.println("expected is   "  +expected);
        helper.addProductToBasket();
        String actual = helper.getProductsInBasket();
        System.out.println("actual is   " +actual);
        assertThat(actual,is(equalToIgnoringCase(expected)));


    }

    @Test
    public void suggestedDepSearchTest() {       // need to be careful with uppercase,lowercase,spaces while doing assertions
        String searchTerm = "cables";
        helper.search(searchTerm);
        //helper.enterSearchItem(searchTerm);
        String actual=helper.selectDepartmentFromSuggestions(searchTerm);
        System.out.println("actual is   "+actual);
        assertThat(actual,containsString(searchTerm));


    }

    @Test
    public void selectProductsFromSuggestions() {
        String searchTerm="USB ";                   //here space after USB is important for assertions

        //String searchTerm = "USB flashdrives";
        helper.search(searchTerm);
       String actual= helper.selectProductsFromSuggestions(searchTerm);
       System.out.println("actual is  " +actual);
       assertThat(actual,containsString(searchTerm));


    }

    @Test
    public void filterPrice() {
        String searchTerm = "Laptops";   //searchTerm="kettle"
        helper.search(searchTerm);

        helper.clickSearch();
        String priceRange = "£299.00 - £499.00";               //with pound symbol we cannot ,so we are replacing with nothing
        helper.selectPrice(priceRange);                    //and splitting string on basis of - which reurns string array
        //we are coverting as string list
        List<String> expectedList = Arrays.asList(priceRange.replace("£", "").split("-"));
        double min = Double.valueOf(expectedList.get(0)); //converting to double
        double max = Double.valueOf(expectedList.get(1));
        List<Double> actualPriceList = helper.getAllProductsPrices();                                               //we should not get products <299.00 and >499.00
        //for that reason we get all the prices of the search

        //assertThat(actualPriceList,both(everyItem(is(greaterThanOrEqualTo(min)))).and(everyItem(is(lessThanOrEqualTo(max)))));
        assertThat(actualPriceList, everyItem(is(greaterThanOrEqualTo(min))));
        assertThat(actualPriceList, everyItem(is(lessThanOrEqualTo(max))));


    }

}


