package pt.isel.ls.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MapperInfo {
    String dto() default "NO_DTO_ASSOCIATED";
    String table() default "NO_TABLE_ASSOCIATED";
}
