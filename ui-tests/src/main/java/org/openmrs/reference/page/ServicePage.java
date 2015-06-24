package org.openmrs.reference.page;

import org.openmrs.uitestframework.page.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by tomasz on 24.06.15.
 */
public class ServicePage extends AbstractBasePage {
    public static final String URL_PATH = "/appointmentschedulingui/appointmentType.page";
    public static final By NAME_FIELD = By.id("name-field");
    public static final By DURATION_FIELD = By.id("duration-field");
    public static final By DESCRIPTION_FIELD = By.id("description-field");
    public static final By SAVE_BUTTON = By.id("save-button");
    public static final By CANCEL_BUTTON = By.className("cancel");
    public static final By NEW_SERVICE_TYPE = By.xpath("//button[@onclick=\"location.href='/openmrs/appointmentschedulingui/appointmentType.page'\"]");
    public static final By APPOINTMENT_SCHEDULING = By.cssSelector("#appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension > i.icon-calendar");
    public static final By MANAGE_SERVICE_TYPES = By.id("appointmentschedulingui-manageAppointmentTypes-app");
    public static final By CONFIRM = By.cssSelector("div.simplemodal-wrap > #delete-appointment-type-dialog > div.dialog-content > button.confirm.right");
    public static final String CURRENT_SERVICE_TYPE = "appointmentschedulingui-delete-";
    public ServicePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String expectedUrlPath() {
        return URL_ROOT + URL_PATH;
    }

    public void putServiceData(String name, String duration, String description) {
        putName(name);
        putDuration(duration);
        putDescription(description);
        save();
    }

    private void putName(String name) {
        findElement(NAME_FIELD).clear();
        findElement(NAME_FIELD).sendKeys(name);

    }

    private void putDuration(String duration) {
        findElement(DURATION_FIELD).clear();
        findElement(DURATION_FIELD).sendKeys(duration);

    }

    private void putDescription(String description) {
        findElement(DESCRIPTION_FIELD).clear();
        findElement(DESCRIPTION_FIELD).sendKeys(description);

    }

    private void save() {
        findElement(SAVE_BUTTON).click();
    }

    public void cancel() {
        findElement(CANCEL_BUTTON).click();
    }

    public void openManageServiceTypes()
    {
        findElement(APPOINTMENT_SCHEDULING).click();
        findElement(MANAGE_SERVICE_TYPES).click();
    }

    public void addNewService() {
        findElement(NEW_SERVICE_TYPE).click();
    }

    public boolean serviceExists(String service) {
        int i =1;
        try {
            while (!serviceExistsOnPage(service)) {
                i++;
                findElement(By.linkText(i + "")).click();
            }
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    public boolean serviceExistsOnPage(String service) {
        try {
            if (findElement(By.id(CURRENT_SERVICE_TYPE + service)) != null) {
                return true;
            }
            return false;
        } catch(Exception e) {
            return false;
        }
    }



    public void deleteService(String service) {
        findElement(By.id(CURRENT_SERVICE_TYPE + service)).click();
        findElement(CONFIRM).click();
    }
}
