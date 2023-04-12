import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class TestBase {

    private static final Logger logger = LoggerFactory.getLogger(TestBase.class);

    private static String executionMode;

    @BeforeAll
    static void setUp() {
        // Для аллюр отчета
        SelenideLogger.addListener("allure", new AllureSelenide());

        // Configuration.holdBrowserOpen = true;

        Configuration.pageLoadStrategy = "eager";

        logger.info("");
        // Пример первой обработки параметра

        logger.info("Sistem property browserSize: " + System.getProperty("browserSize"));
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
		logger.info("Set Configuration.browserSize browserSize: " + Configuration.browserSize + "\n");

        // Пример второй обработки параметра

        logger.info("System property remoteURL: " + System.getProperty("remoteURL"));
        Configuration.remote = System.getProperty("remoteURL", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
        logger.info("Set configuration.remote: " + Configuration.remote  + "\n");

        // Конфигурация без указания в джобе:
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
        logger.info("Set configuration.browserCapabilities: " + Configuration.browserCapabilities  + "\n");

    }

    @AfterEach
    void addAttachments() {
/*        Attach.screenshotAs("Last screen");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (executionMode.equals("remote")) {
            Attach.addVideo();
        }
		*/
    }

}
