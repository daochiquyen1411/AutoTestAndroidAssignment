package config;


import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import io.appium.java_client.remote.MobileCapabilityType;

public class Configs {
	
    public static final URL serverUrl(){
        try {
            return new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static final DesiredCapabilities androidDesiredCapabilities(){
        File appDir = new File("src");
        File app = new File("/Users/daoch/eclipse-workspace/android/Serenity.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability (MobileCapabilityType.APP, app.getAbsolutePath ());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus S API 26");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability("autoGrantPermissions", true);
        return capabilities;
    }


}
