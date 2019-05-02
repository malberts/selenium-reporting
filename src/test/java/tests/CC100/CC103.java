package tests.CC100;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("CC-100")
@Feature("CC-102")
public class CC103 {
    @Test
    public void SearchBySomething() {
        someStep("abc123");
        assertThat(true, equalTo(true));
    }

    @Test
    public void SearchBySomethingElse() {
        someStep("def456");
        assertThat(true, equalTo(false));
    }

    @Step("A different step")
    public void someStep(String step) {
    }
}
