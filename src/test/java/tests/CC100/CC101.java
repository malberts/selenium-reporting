package tests.CC100;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.service.ExtentService;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import io.qameta.allure.*;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.util.ResultsUtils;
import listeners.TestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("CC-100")
@Feature("CC-101")
//@Listeners({TestListener.class, ExtentIReporterSuiteClassListenerAdapter.class})
@Listeners({TestListener.class, ExtentITestListenerAdapter.class})
public class CC101 {
    SoftAssert softAssert;

    public void check(String name, Runnable runnable) {
        String uuid = UUID.randomUUID().toString();
        StepResult result = new StepResult()
                .setName(name);
        Allure.getLifecycle().startStep(uuid, result);
        try {
            runnable.run();
            Allure.getLifecycle().updateStep(uuid, s -> s.setStatus(Status.PASSED));
        } catch (Throwable e) {
            Allure.getLifecycle().updateStep(uuid, s -> s
                    .setStatus(ResultsUtils.getStatus(e).orElse(Status.FAILED))
            );
            Allure.getLifecycle().addAttachment("Error", "text/plain", "txt", e.toString().getBytes());
            softAssert.fail(name, e);
        } finally {
            Allure.getLifecycle().stopStep(uuid);
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        softAssert = new SoftAssert();
    }

    @Test(description = "TestNG description", groups = {"some-group", "another-group"})
    @Description("Allure description")
    @Story("TestNG Story 1")
    @Story("TestNG Story 2")
    public void SearchByFirstName() {
        someStep();
        assertThat(true, equalTo(true));
    }

    @Test(groups = {"another-group"})
    @Story("TestNG Story 2")
    public void SearchByLastName() {
        // Check lambda
        check("Check Username", () -> {
            assertThat("Here is the reason", Arrays.asList("abc", "def"), hasItem("xyz"));
        });
        check("Check password", () -> {
            assertThat("Here is the other reason", "John Doe", containsString("John"));
        });

        // Manual fail message - a fail inside a child does not fail the parent
        step("Something step", (stepContext) -> {
            try {
                assertThat("Here is another reason", true, equalTo(false));
            } catch (AssertionError e) {
                step("Something step failed", Status.FAILED);
            }
        });

        step("dskjgsgsd", () -> {
            step("Sub step 1", () -> {
                ///
                //attachment("Attachment name", "skjdhgksd hgkjdsg");

            });
            step("Sub step 2", () -> {
                ///
            });
        });

        // Manual step creation
        Allure.getLifecycle().startStep(UUID.randomUUID().toString(), new StepResult().setName("ASSERT"));
        Allure.attachment("Error", "skgskd gksd gksld gks nglks nglksdn g");
        Allure.getLifecycle().updateStep(stepResult -> stepResult
                .setStatus(Status.FAILED)
                .setDescription("xxxxxxxx")
                .setStatusDetails(new StatusDetails().setTrace("sdgsdgdsg").setMessage("sdgsdgsdgsdg").setFlaky(true))
        );
        Allure.getLifecycle().stopStep();

        someSubStep("username", "something");
        someSubStep("password", "something else");
        //assertThat(true, equalTo(false));
        assertThat(true, equalTo(true));
        softAssert.assertAll();

        step("This stepp failed", Status.FAILED);
    }

    @Test
    public void ExtentTest() {
//        Exten
//        ExtentHtmlReporter html = new ExtentHtmlReporter("target/extent/index.html");
//        ExtentReports extent = new ExtentReports();
//        extent.attachReporter(html);

        //ExtentReports extent = ExtentService.getInstance();

        //ExtentTest test = extent.get .createTest("Test Case 1", "PASSED test case");

        ExtentTest test = ExtentTestManager.getTest();
        test.info("Something logged");

        ExtentTest step1 = test.createNode("Step 1");
        step1.info("Something logged");
        step1.pass("Something happened");

        ExtentTest step2 = test.createNode("Step 2");
        step2.info("Something logged");
        step2.fail("Something happened");

        ExtentTest step3 = test.createNode("Step 3");
        step3.info("Something logged");
        step3.pass("Something happened");
        step3.addScreenCaptureFromBase64String("iVBORw0KGgoAAAANSUhEUgAAAAEAAAAfCAYAAAAmyadiAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAHXQAAB10BlYgGnwAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAAeSURBVAiZY+Dj5vzPwMrC/J+BgYHhPxMDAwPDICEA4BkDZJuHzMQAAAAASUVORK5CYII=", "Error Screenshot");

        assertThat(true, equalTo(false));
        //test.pass("Plain step");

        //extent.flush();
    }

    @Test
    public void ExtentTest2() {
//        Exten
//        ExtentHtmlReporter html = new ExtentHtmlReporter("target/extent/index.html");
//        ExtentReports extent = new ExtentReports();
//        extent.attachReporter(html);

        //ExtentReports extent = ExtentService.getInstance();

        //ExtentTest test = extent.get .createTest("Test Case 1", "PASSED test case");

        info("sdhskjdhgsdg");

        ExtentTest test = ExtentTestManager.getTest();
        ExtentService.getInstance().setSystemInfo("os", "win7");
        ExtentService.getInstance().setSystemInfo("browser", "chrome");
        ExtentService.getInstance().setSystemInfo("browser", "chrome");

        test.info("Something logged");
        test.warning("Something logged");
        test.pass("Something logged");
        test.error("Something logged");
        test.fail("Something logged");

        ExtentTest step1 = test.createNode("Step 1");
        step1.info("Something logged");
        step1.addScreenCaptureFromBase64String("iVBORw0KGgoAAAANSUhEUgAAAAEAAAAfCAYAAAAmyadiAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAHXQAAB10BlYgGnwAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAAeSURBVAiZY+Dj5vzPwMrC/J+BgYHhPxMDAwPDICEA4BkDZJuHzMQAAAAASUVORK5CYII=", "A Picture");
        step1.pass("Something happened");

        ExtentTest step2 = test.createNode("Step 2");
        step2.info("Something logged");
        step2.pass("Something happened");

        ExtentTest step3 = test.createNode("Step 3");
        step3.info("Something logged");
        step3.warning("Something happened");
        step3.addScreenCaptureFromBase64String("iVBORw0KGgoAAAANSUhEUgAAAAEAAAAfCAYAAAAmyadiAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAHXQAAB10BlYgGnwAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAAeSURBVAiZY+Dj5vzPwMrC/J+BgYHhPxMDAwPDICEA4BkDZJuHzMQAAAAASUVORK5CYII=", "Error screenshot");

        //test.pass("Plain step");

        //extent.flush();
    }

    public void info(String message) {
        ExtentTest test = ExtentTestManager.getTest();
        test.info(message);
    }


    @Test(description = "ajkfhkjdsfhsdg")
    public void ExtentTest3() {
//        Exten
//        ExtentHtmlReporter html = new ExtentHtmlReporter("target/extent/index.html");
//        ExtentReports extent = new ExtentReports();
//        extent.attachReporter(html);

        //ExtentReports extent = ExtentService.getInstance();

        //ExtentTest test = extent.get .createTest("Test Case 1", "PASSED test case");

        String screenshot = " data:image/png;base64,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";
        ExtentTest test = ExtentTestManager.getTest();
        test.info("Something logged");

        try {
            MediaEntityModelProvider entity;
            entity = MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build();
            test.info("This is a screenshot", entity);
        } catch (IOException e) {
            e.printStackTrace();
            test.info("Something - Missing screenshot");
        }

        ExtentTest step1 = test.createNode("Step 1");
        step1.info("Something logged");
        step1.addScreenCaptureFromBase64String(screenshot, "A Picture");
        step1.pass("Something happened");

        assertThat(true, equalTo(true));
    }

    @Test(description = "This node must fail")
    public void nodeFail() {
        ExtentTest test = ExtentTestManager.getTest();
        ExtentTest step1 = test.createNode("Step should fail");
        assertThat(true, equalTo(false));

    }

    @Test
    @Link("https://example.org")
    @Link(name = "Confluence", value = "123", type = "confluence")
    @Issue("MET-494")
    @Issue("MET-200")
    @TmsLink("MET-100")
    @TmsLink("MET-105")
    public void testLinks() {

    }
//    @AfterMethod
//    @Story("xx")
//    public void checks(ITestResult result) {
//        softAssert.assertAll();
//        if (!result.isSuccess()) {
//            Allure.attachment("Screenshot", "ABCDEFG");
//        }
//    }

    @Step("Doing bla bla")
    public void someStep() {
        assertThat("Bla bla", true, equalTo(false));
    }

    @Step("Sub step: {value1}")
    public void someSubStep(String value1, String value2) {
        ExtentTest test = ExtentTestManager.getTest();
        test.pass("bla bla ba");
        step("Sub step", Status.BROKEN);
    }
}
