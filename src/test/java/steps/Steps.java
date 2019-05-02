package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import io.qameta.allure.model.StepResult;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Steps {
    @Given("the user is on a page {string}")
    public void theUserIsOnAPage(String page) {
        login("johndoe", "1234");
    }

    @When("the user does something")
    public void theUserDoesSomething() {
        WebElement el1 = null;
        WebElement el2 = null;

        check(el1, Arrays.asList("Item1", "Item2", "Item3"), true);
        check(el2, Arrays.asList("ABC", "DEF", "GHI"), false);
    }

    @Then("something happens")
    public void somethingHappens() {
        theUserIsOnAPage("sub step call");
    }

    @Step("Login as {username}")
    public void login(String username, String password) {
        assertThat(username, not(equalTo("abc")));
    }

    //@Step("Check values")
    public void check(WebElement element, List<String> expected, boolean bool) {
//        Allure.getLifecycle().updateStep(stepResult -> stepResult.setStatus(Status.FAILED));
//        Allure.getLifecycle().updateTestCase(testResult -> testResult.setStatus(Status.BROKEN));

//        String uuid = Allure.getLifecycle().getCurrentTestCase().get();
//        Allure.getLifecycle().updateTestCase(uuid, testResult -> testResult
//                .setStatus(Status.BROKEN)
//                .setName("xxxxxxxxxxxxxxxxxxxxxxxx")
//        );
//
//        System.out.println(uuid);
//
//        Allure.getLifecycle().
        StepResult result = new StepResult();
        result
                .setName("Check Values")
                .setParameters(Arrays.asList(
                        new Parameter().setName("field1").setValue("value1"),
                        new Parameter().setName("field2").setValue("value2")
                ))
            .setDescriptionHtml("<b>Something bold is <em>emp</em></b>");

        Allure.getLifecycle().startStep(UUID.randomUUID().toString(), result);

        try {
            assertThat("Some problem", true, equalTo(bool));
            Allure.getLifecycle().updateStep(stepResult -> stepResult
                    .setStatus(Status.PASSED)
            );
        } catch (AssertionError e) {
            Allure.attachment("Error", e.toString());
            Allure.getLifecycle().updateStep(stepResult -> stepResult
                    .setStatus(Status.BROKEN)
            );
        }
        Allure.getLifecycle().stopStep();
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setStatus(Status.FAILED));
    }

    @Then("something else happens")
    public void somethingElseHappens() {
        login("abc", "def");
        assertThat("Here is the reason", Arrays.asList("abc", "def"), hasItem("xyz"));
    }
}

