package POM.Settings

import com.codeborne.selenide.SelenideElement

import static com.codeborne.selenide.Selenide.*
import static com.codeborne.selenide.Condition.visible

class Personal {

    static SelenideElement checkPersonalPage(){
        $(".user-profile-title").should(visible)
    }

}
