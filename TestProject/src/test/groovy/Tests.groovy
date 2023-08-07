import POM.Settings.Bookmark
import POM.Settings.MyRegion
import POM.Settings.Personal
import POM.Settings.PageAutoDrom
import POM.Settings.Sign

import org.testng.annotations.Test

import com.codeborne.selenide.CollectionCondition

import static com.codeborne.selenide.Condition.*
import static com.codeborne.selenide.Selenide.open

class Tests {

    @Test
    void SearchAuto(){
       open 'https://auto.drom.ru/'

       PageAutoDrom.markAuto('Toyota')
       PageAutoDrom.modelAuto('Harrier')
       PageAutoDrom.fuelType('Гибрид')
       PageAutoDrom.filterUnsold()
       PageAutoDrom.minYear('2007')
       PageAutoDrom.advancedFilter()
       PageAutoDrom.minMileage('1')

       PageAutoDrom.submitSearch()

       PageAutoDrom.checkUnsold().shouldHave(CollectionCondition.size(0))
       PageAutoDrom.checkMinYear(2007)
       PageAutoDrom.checkMileage()

       PageAutoDrom.openSecondPageAutoDrom()

       PageAutoDrom.checkUnsold().shouldHave(CollectionCondition.size(0))
       PageAutoDrom.checkMinYear(2007)
       PageAutoDrom.checkMileage()
   }

    @Test
    void autorizationUser(){
       open 'https://my.drom.ru/sign'

       def userName = ""
       def password = ""

       Sign.getSignInput().sendKeys(userName)
       Sign.getPasswordInput().sendKeys(password)
       Sign.signSubmit()

       Personal.checkPersonalPage().shouldHave(text(userName))

       open 'https://auto.drom.ru/'

       def urlInList = PageAutoDrom.urlFirstBullInList()

       PageAutoDrom.addToBookmarkFromViewdir()

       open 'https://my.drom.ru/personal/bookmark'

       def urlInBookmark = Bookmark.urlInBookmark()

       assert urlInList == urlInBookmark

       Bookmark.deleteBookmark()
   }

   @Test
   void showPopularAuto(){
      open"https://www.drom.ru/my_region/"

      MyRegion.selectRegion("Приморский край")

      open 'https://auto.drom.ru/'

      PageAutoDrom.searchLocation("Приморский край")

      PageAutoDrom.takeListBrands()
   }

}
