import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.service.ExtentTestManager;
import io.qameta.allure.Step;

public class SearchPage {

    //elements

    @Step("Search by: {name}")
    public void setFirstName(String name) {
        ///
    }

    @Step("Big search")
    public void searchByData(Data data) {
        setFirstName(data.geTName())

            searchBtn.click()
    }


    public void setFirstNameE(String name) {
        ExtentTest test = ExtentTestManager.getTest();
        test.createNode("Sub Step 2").fail();
        test.createNode("Sub Step 3").fail();

        mLog(Type, "Mesxage");

        if (passed) {
            test.pass("setFirstName");
        }else {
            test.fail("setFirstName");
        }
    }

    public void searchByDataE(Data data) {
        ExtentTest test = ExtentTestManager.getTest();

        boolean passed = true;
        setFirstNameE(data.geTName());

        if (passed) {
            test.pass("Big search");
        }else {
            test.fail("Big search");
        }
    }


}
