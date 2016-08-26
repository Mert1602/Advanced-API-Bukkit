package me.mert1602.advancedapi.setting;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import me.mert1602.advancedapi.BukkitLog;
import me.mert1602.advancedapi.ReflectionUtilsBukkit;
import me.mert1602.advancedapi.basic.BasicContentManager;

public final class SettingManagerInjector extends BasicContentManager<Object, SettingManager<?>>{

	@Getter private static SettingManagerInjector instance;

	private List<Class<?>> classList;
	private SettingManagerInjectorPhase currentPhase;

	public SettingManagerInjector(SettingManager<?> content) {
		super(content);

		if(SettingManagerInjector.instance != null) return;
		SettingManagerInjector.instance = this;

		this.classList = new ArrayList<Class<?>>();
		this.currentPhase = SettingManagerInjectorPhase.INIT;

	}

	
	
	@Override
	public void load() {

		this.currentPhase = SettingManagerInjectorPhase.LOAD;

		this.parseAll();

	}

	@Override
	public void start() {

		this.currentPhase = SettingManagerInjectorPhase.START;

		this.parseAll();

	}

	@Override
	public void reload() {

		this.parseAll();

	}

	@Override
	public void stop() {

		this.currentPhase = SettingManagerInjectorPhase.STOP;

		this.parseAll();
		this.unregisterAll();

	}

	
	
	public List<Class<?>> getClassList(){
		return Collections.unmodifiableList(new ArrayList<Class<?>>(this.classList));
	}

	
	
	@Override
	public Object register(Object object) {

		if(object.getClass().isAnnotation()) return null;
		if(object.getClass().isEnum()) return null;
		if(object.getClass().isInterface()) return null;

		if(super.register(object) == null) return null;

		this.parseObject(object, this.currentPhase, true);

		return object;

	}

	public Class<?> register(Class<?> clazz){

		if(clazz.isAnnotation()) return null;
		if(clazz.isEnum()) return null;
		if(clazz.isInterface()) return null;

		if(this.getClassList().contains(clazz)) return null;
		this.classList.add(clazz);

		this.parseClass(clazz, this.currentPhase, true);

		return clazz;
	}

	
	
	private void parseAll(){

		for(Class<?> clazz : this.getClassList()){
			this.parseClass(clazz, this.currentPhase, false);
		}

		for(Object object : this.getList()){
			this.parseObject(object, this.currentPhase, false);
		}

	}

	private void parseClass(Class<?> clazz, SettingManagerInjectorPhase phase, boolean first){

		boolean configChanged = false;

		for(Field field : clazz.getDeclaredFields()){

			//Check if Field is valid
			if(Modifier.isStatic(field.getModifiers()) == false) continue;
			if(field.isAnnotationPresent(SettingValue.class) == false) continue;

			Class<?> fieldTypeClass = ReflectionUtilsBukkit.DataType.getPrimitive(field.getType());
			SettingValue iSettingValue = field.getAnnotation(SettingValue.class);


			//Check
			if(first & (iSettingValue.parseIfRegister() == false)) continue;

			boolean found = false;
			for(SettingManagerInjectorPhase otherPhase : iSettingValue.phases()){

				if(otherPhase == phase){
					found = true;
					break;
				}

			}

			if(found == false){
				continue;
			}

			if(Modifier.isFinal(field.getModifiers())){

				try {

					Field modifiersField = Field.class.getDeclaredField("modifiers");

					if(modifiersField.isAccessible() == false){
						modifiersField.setAccessible(true);
					}

					modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

					if(modifiersField.isAccessible()){
						modifiersField.setAccessible(false);
					}

				} catch (Throwable e) {
					e.printStackTrace();
				}

			}

			if(field.isAccessible() == false){
				field.setAccessible(true);
			}

			//Set
			boolean set = false;

			for(SettingManager<?> settingManager : SettingManager.getSettingManagers()){

				if(settingManager.getSetting(iSettingValue.path()) == null) continue;

				try {

					field.set(null, settingManager.getSetting(iSettingValue.path()).get());
					set = true;

					break;

				} catch (Throwable e) {
					BukkitLog.error(e.getMessage());
					continue;
				}

			}

			//Set default
			if((set == false) && iSettingValue.parseDefault()){

				for(final SettingType type : SettingType.values()){

					if(type.hasDefaultObject() == false) continue;

					if(type.isList() && fieldTypeClass.isAssignableFrom(List.class)){

						Type genericType = field.getGenericType();

						if(genericType instanceof ParameterizedType){

							ParameterizedType pGenericType = (ParameterizedType) genericType;
							Class<?> listObjectClass = ReflectionUtilsBukkit.DataType.getPrimitive((Class<?>) pGenericType.getActualTypeArguments()[0]);

							if(listObjectClass.isAssignableFrom(type.getDefaultObjectClass()) == false) continue;

							Setting<?> setting = null;

							if(iSettingValue.copyToDefaultConfigIfNotExists()){

								setting = this.getContent().addSetting(iSettingValue.path(), type.getDefaultObject());
								configChanged = true;

							}

							try {
								field.set(null, setting != null ? setting.get() : type.getDefaultObject());
							} catch (Throwable e) {
								BukkitLog.error("ISettingManager: Class: '" + clazz.getSimpleName() + "' Field: '" + field.getName() +
										"' FieldType: '" + field.getType().getSimpleName() +  "' TypeDefaultObjectClass: '" + type.getDefaultObjectClass().getSimpleName() +
										"' Type: '" + type.getClass().getSimpleName() + "' Error: '" + e.getMessage() + "'");
							}

						}

					}else if(type.isList() == false){

						if(fieldTypeClass.isAssignableFrom(type.getDefaultObjectClass()) == false) continue;

						Setting<?> setting = null;

						if(iSettingValue.copyToDefaultConfigIfNotExists()){

							setting = this.getContent().addSetting(iSettingValue.path(), type.getDefaultObject());
							configChanged = true;

						}

						try {
							field.set(null, setting != null ? setting.get() : type.getDefaultObject());
						} catch (Throwable e) {
							BukkitLog.error("ISettingManager: Class: '" + clazz.getSimpleName() + "' Field: '" + field.getName() +
									"' FieldType: '" + field.getType().getSimpleName() +  "' TypeDefaultObjectClass: '" + type.getDefaultObjectClass().getSimpleName() +
									"' Type: '" + type.getClass().getSimpleName() + "' Error: '" + e.getMessage() + "'");
						}

					}

				}

			}

			if(field.isAccessible()){
				field.setAccessible(false);
			}

		}

		if(configChanged){
			this.getContent().repair();
		}

	}

	private void parseObject(Object object, SettingManagerInjectorPhase phase, boolean first){

		boolean configChanged = false;

		for(Field field : object.getClass().getDeclaredFields()){

			//Check if Field is valid
			if(Modifier.isStatic(field.getModifiers())) continue;
			if(field.isAnnotationPresent(SettingValue.class) == false) continue;

			Class<?> fieldTypeClass = ReflectionUtilsBukkit.DataType.getPrimitive(field.getType());
			SettingValue iSettingValue = field.getAnnotation(SettingValue.class);

			//Check
			if(first & (iSettingValue.parseIfRegister() == false)) continue;

			boolean found = false;
			for(SettingManagerInjectorPhase otherPhase : iSettingValue.phases()){

				if(otherPhase == phase){
					found = true;
					break;
				}

			}

			if(found == false){
				continue;
			}

			if(field.isAccessible() == false){
				field.setAccessible(true);
			}

			//Set
			boolean set = false;

			for(SettingManager<?> settingManager : SettingManager.getSettingManagers()){

				if(settingManager.getSetting(iSettingValue.path()) == null) continue;

				try {

					field.set(object, settingManager.getSetting(iSettingValue.path()).get());
					set = true;

					break;

				} catch (Throwable e) {
					e.printStackTrace();
					BukkitLog.error("ISettingManager: " + e.getMessage());
					continue;
				}

			}

			//Set default
			if((set == false) && iSettingValue.parseDefault()){

				for(final SettingType type : SettingType.values()){

					if(type.hasDefaultObject() == false) continue;

					if(type.isList() && fieldTypeClass.isAssignableFrom(List.class)){

						Type genericType = field.getGenericType();

						if(genericType instanceof ParameterizedType){

							ParameterizedType pGenericType = (ParameterizedType) genericType;
							Class<?> listObjectClass = ReflectionUtilsBukkit.DataType.getPrimitive((Class<?>) pGenericType.getActualTypeArguments()[0]);

							if(listObjectClass.isAssignableFrom(type.getDefaultObjectClass()) == false) continue;

							Setting<?> setting = null;

							if(iSettingValue.copyToDefaultConfigIfNotExists()){

								setting = this.getContent().addSetting(iSettingValue.path(), type.getDefaultObject());
								configChanged = true;

							}

							try {
								field.set(object, setting != null ? setting.get() : type.getDefaultObject());
							} catch (Throwable e) {
								BukkitLog.error("ISettingManager: Class: '" + object.getClass().getSimpleName() + "' Field: '" + field.getName() +
										"' FieldType: '" + field.getType().getSimpleName() +  "' TypeDefaultObjectClass: '" + type.getDefaultObjectClass().getSimpleName() +
										"' Type: '" + type.getClass().getSimpleName() + "' Error: '" + e.getMessage() + "'");
							}

						}

					}else if(type.isList() == false){

						if(fieldTypeClass.isAssignableFrom(type.getDefaultObjectClass()) == false) continue;

						Setting<?> setting = null;

						if(iSettingValue.copyToDefaultConfigIfNotExists()){

							setting = this.getContent().addSetting(iSettingValue.path(), type.getDefaultObject());
							configChanged = true;

						}

						try {
							field.set(object, setting != null ? setting.get() : type.getDefaultObject());
						} catch (Throwable e) {
							BukkitLog.error("ISettingManager: Class: '" + object.getClass().getSimpleName() + "' Field: '" + field.getName() +
									"' FieldType: '" + field.getType().getSimpleName() +  "' TypeDefaultObjectClass: '" + type.getDefaultObjectClass().getSimpleName() +
									"' Type: '" + type.getClass().getSimpleName() + "' Error: '" + e.getMessage() + "'");
						}

					}

				}

			}

			if(field.isAccessible()){
				field.setAccessible(false);
			}

		}

		if(configChanged){
			this.getContent().repair();
		}

	}

}
