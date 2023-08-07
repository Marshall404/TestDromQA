package POM.Settings

import com.codeborne.selenide.SelenideElement

import static com.codeborne.selenide.Condition.*
import static com.codeborne.selenide.Selenide.$

class Sign {

    static SelenideElement getSignInput() {
        $("#sign").should(visible)
    }

    static SelenideElement getPasswordInput() {
        $("#password").should(visible)
    }

    static SelenideElement signSubmit() {
        $("#signbutton").click()
    }

}
