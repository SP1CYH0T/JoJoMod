package com.SP1CYH0T.github.jojomod.objects;

import com.SP1CYH0T.github.jojomod.registry.JojoItemGroup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Overrides default BlockItem Properties in JojoBlock
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SpecialBlockItem {
    JojoItemGroup group() default JojoItemGroup.JOJO;
}
