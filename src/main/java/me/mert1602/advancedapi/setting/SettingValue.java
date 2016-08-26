package me.mert1602.advancedapi.setting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SettingValue {

	String path();

	boolean parseDefault() default false;

	boolean parseIfRegister() default false;

	boolean copyToDefaultConfigIfNotExists() default false;

	SettingManagerInjectorPhase[] phases() default {
		SettingManagerInjectorPhase.INIT, SettingManagerInjectorPhase.LOAD, SettingManagerInjectorPhase.START, SettingManagerInjectorPhase.STOP
	};

}