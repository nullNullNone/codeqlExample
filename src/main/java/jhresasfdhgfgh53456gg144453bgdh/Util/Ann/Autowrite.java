package jhresasfdhgfgh53456gg144453bgdh.Util.Ann;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowrite {
    String value() default "";
}
