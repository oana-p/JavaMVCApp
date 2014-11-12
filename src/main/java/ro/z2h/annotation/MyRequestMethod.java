package ro.z2h.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMethod {

    String methodType() default "GET"; //GET, POST, DELETE
    String urlPath();
}
