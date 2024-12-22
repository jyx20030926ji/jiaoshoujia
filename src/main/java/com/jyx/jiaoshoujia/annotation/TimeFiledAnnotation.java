package com.jyx.jiaoshoujia.annotation;




import com.jyx.jiaoshoujia.menu.AopLogMenu;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeFiledAnnotation {

    AopLogMenu value();

}
