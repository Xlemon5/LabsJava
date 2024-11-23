package org.example;

import java.lang.annotation.*;

/** @AutoInjectable: Помечает поля, которые должны быть инъецированы. **/

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {
}