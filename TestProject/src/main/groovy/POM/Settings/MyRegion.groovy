package POM.Settings

import static com.codeborne.selenide.Condition.text
import static com.codeborne.selenide.Selenide.$$

class MyRegion {

    static void selectRegion(region){
        $$(".regionLink").findBy(text(region)).click()
    }

}
