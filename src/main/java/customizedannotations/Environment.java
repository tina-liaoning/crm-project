package customizedannotations;

import enums.Browser;
import enums.OperatingSystem;
import enums.Platform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Environment {
    Browser browser();
    String browserVersion();
    OperatingSystem operatingSystem();
    double osVersion();
    Platform platform();

}
