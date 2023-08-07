package POM.Settings

import org.junit.Assert
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor

import static com.codeborne.selenide.Condition.*
import static com.codeborne.selenide.Selenide.*
import static com.codeborne.selenide.WebDriverRunner.getWebDriver

class PageAutoDrom {

    static String urlFirstBullInList(){
        $('[data-ftid="bulls-list_bull"]').getAttribute("href")
    }

    static void openSecondPageAutoDrom(){
        $('[data-ftid="component_pagination-item-next"]').should(visible).click()
    }

    static void markAuto(String mark){
        $("div[data-ftid=sales__filter_fid]").should(visible).click()
        $("input[placeholder=Марка]").should(visible).sendKeys(mark)
        $("div[aria-label='Марка'] div[role='option']").should(visible, text(mark)).click()

    }

    static SelenideElement modelAuto(String model){
        $(".css-1hred6a.eaz9b4y0").should(disappear)
        $("div[data-ftid=sales__filter_mid]").should(visible).click()
        $('input[placeholder="Модель"]').should(visible).sendKeys(model)
        $("div[aria-label='Модель'] div[role='option']").should(visible, text(model)).click()
    }

    static SelenideElement fuelType(String fuel){
        $('div[data-ftid=sales__filter_fuel-type]').should(visible).click()
        $x("//div[@aria-label='Топливо']//child::div[contains(text(), '${fuel}')]").should(visible).click()
    }

    static SelenideElement minYear(String year){
        $('div[data-ftid="sales__filter_year-from"]').should(visible).click()
        $x("//div[@aria-label='Год от']//child::div[contains(text(), '${year}')]").should(visible).click()
    }

    static void advancedFilter(){
        $('button[data-ftid="sales__filter_advanced-button"]').should(visible).click()
    }

    static void filterUnsold(){
        $('label[for="sales__filter_unsold"]').should(visible).click()
    }

    static SelenideElement minMileage(String mileage){
        $('[data-ftid="sales__filter_mileage-from"]').should(visible).sendKeys(mileage)
    }

    static void submitSearch(){
        $('[data-ftid="sales__filter_submit-button"]').should(visible).click()
    }

    static void searchLocation(location){
        $$('[data-ga-stats-track-click="filter"]').findBy(text(location)).click()
    }

    static void  showDropDownBrands(){
        $("[data-ftid=sales__filter_fid]").should(visible).click()
    }

    static ElementsCollection checkUnsold() {
        $$('.css-1e5mzqy.e3f4v4l2')
                .each {bulletin ->
                    bulletin.shouldBe(visible)}
    }

    static ElementsCollection checkMinYear(minYear) {
        $$('[data-ftid="bull_title"]')
                .each {
                    Assert.assertTrue(it.text.replaceAll("^.+(?<=\\,\\s)", "").toInteger() >= minYear)
                }
    }

    static SelenideElement checkMileage() {
        $('[data-ftid="component_inline-bull-description"]').should(visible, matchText("[\\d\\s]*км"))
    }

    static SelenideElement addToBookmarkFromViewdir(){
        $('[data-ftid="bulls-list_bull"]').scrollTo().find(By.cssSelector("svg")).click()
    }

    static ElementsCollection takeListBrands(){

        JavascriptExecutor js = (JavascriptExecutor) webDriver

        showDropDownBrands()

        js.executeScript('document.querySelector(\'[data-ftid="component_select_dropdown"]\').style.maxHeight="6650px"')

        HashMap<String, Integer> brands = new HashMap<>()

        $$('div[aria-label="Марка"] div[role="option"]')
                .each {
                    String[] arr = it.getText().split(" \\(")
                    brands.put(arr[0], arr.length > 1 ? Integer.valueOf(arr[1].substring(0, arr[1].length() - 1)) : 0)
                }

        String format = "| %-20s | %-21s |\n"
        printf(format, "Фирма", "Количество объявлений")

        brands.entrySet().stream()
                .sorted(Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue).reversed())
                .limit(20)
                .forEach(entry -> System.out.printf(format, entry.getKey(), entry.getValue()))
    }

}
