/*
 * Copyright 2025 DeskBoot by aim_41tt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.aim_41tt.deskboot.context;

import java.util.HashMap;
import java.util.Map;

import java.lang.reflect.Field;

/**
 * @author aim_41tt
 * @version 1.0
 * @since 9.09.2025
 */
public class DeskContext {

	private static final Map<Class<?>, Object> beans = new HashMap<>();

	public static <T> void registerBean(Class<T> clazz) {
		try {
			T instance = clazz.getDeclaredConstructor().newInstance();
			beans.put(clazz, instance);
		} catch (Exception e) {
			throw new RuntimeException("Не удалось создать бин: " + clazz, e);
		}
	}
	/**
     * Регистрирует уже готовый бин с явным классом.
     */
	public static void registerInitBean(Object instance) {
	    Class<?> clazz = instance.getClass();

	    System.out.println(clazz);
	    
	    // 1. Кладём сам прокси по его классу
	    beans.put(clazz, instance);

	    // 2. Кладём по всем интерфейсам, которые он реализует
	    for (Class<?> iface : clazz.getInterfaces()) {
	        beans.put(iface, instance);
	    }

	    // 3. Кладём по суперклассу (если есть)
	    Class<?> superClass = clazz.getSuperclass();
	    if (superClass != null && superClass != Object.class) {
	        beans.put(superClass, instance);
	    }
	}



	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		return (T) beans.get(clazz);
	}

	public static void injectDependencies(Object target) {
		System.out.println(target.getClass());
		for (Field field : target.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Inject.class)) {
				Object dependency = beans.get(field.getType());
				if (dependency == null) {
					throw new RuntimeException("Нет зарегистрированного бина для " + field.getType());
				}
				field.setAccessible(true);
				try {
					field.set(target, dependency);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Ошибка при внедрении зависимости", e);
				}
			}
		}
	}
}
