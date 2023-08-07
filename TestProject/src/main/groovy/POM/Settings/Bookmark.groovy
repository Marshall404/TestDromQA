package POM.Settings

import org.openqa.selenium.By

import static com.codeborne.selenide.Selenide.*

class Bookmark {

    static String urlInBookmark() {
        $('.bull-item-content__content-wrapper').find(By.cssSelector("a")).getAttribute("href")
    }

    static void deleteBookmark(){
        $(".removeBookmark").click()
    }

}
