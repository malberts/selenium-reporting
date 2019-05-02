package tests.CC200;

import io.qameta.allure.*;
import io.qameta.allure.model.Status;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("CC-200")
@Feature("CC-201")
public class CC201 {
    @Test
    public void CreateQuickClaim() {
        assertThat(true, equalTo(true));
        Allure.step("Sub step", Status.FAILED);
    }

    @Test
    public void CreateFullClaim() {
        assertThat(true, equalTo(true));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(true, true,"Reason 1");
        softAssert.assertEquals(true, false,"Reason 2");
        softAssert.assertAll();
    }

    @Test
    @Stories({@Story("Hansel and Gretel"), @Story("Three blind mice")})
    public void CreatePropertyClaim() {
        System.out.println(10/0);
        assertThat(true, equalTo(true));
    }
}
