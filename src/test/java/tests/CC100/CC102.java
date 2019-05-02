package tests.CC100;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("CC-100")
@Feature("CC-102")
public class CC102 {
    @Test
    public void SearchByPolicyNumber() {
        someStep("abc123");
        assertThat(true, equalTo(true));
    }

    @Test
    public void SearchByCountry() {
        someStep("def456");
        assertThat(true, equalTo(false));
    }

    @Step("Another step: {step}")
    public void someStep(String step) {

    }
}
