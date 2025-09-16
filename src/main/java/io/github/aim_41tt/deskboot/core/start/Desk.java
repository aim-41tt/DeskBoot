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
package io.github.aim_41tt.deskboot.core.start;

import java.lang.annotation.Annotation;
import java.util.Set;

import io.github.aim_41tt.deskboot.banner.BannerPrinter;
import io.github.aim_41tt.deskboot.config.EnableGUI;
import io.github.aim_41tt.deskboot.context.Component;
import io.github.aim_41tt.deskboot.context.DeskContext;
import io.github.aim_41tt.deskboot.core.AnnotationScanner;
import io.github.aim_41tt.deskboot.core.PageRegistry;
import io.github.aim_41tt.deskboot.core.module.ModuleManager;
import io.github.aim_41tt.deskboot.core.page.DeskPage;
import io.github.aim_41tt.deskboot.core.page.PageNavigator;
import io.github.aim_41tt.deskboot.core.page.annotation.MainPage;
import io.github.aim_41tt.deskboot.core.page.annotation.Page;
import io.github.aim_41tt.deskboot.core.start.annotation.DeskApp;

/**
 * @author aim_41tt
 * @version 1.0
 * @since 9.09.2025
 */
public class Desk {

	@SuppressWarnings("unchecked")
	public static void run(Class<?> appClass, String[] args) {

		BannerPrinter.print();
		if (!appClass.isAnnotationPresent(DeskApp.class)) {
			throw new IllegalArgumentException("Главный класс должен быть помечен @DeskApp");
		}

		// Проверяем, нужен ли GUI-режим
		boolean guiEnabled = appClass.isAnnotationPresent(EnableGUI.class);

		Runnable appRunner = () -> {
			String basePackage = appClass.getPackageName();

			Set<Class<?>> pageClasses = AnnotationScanner.scan(basePackage, Page.class);
			Set<Class<?>> componentClasses = AnnotationScanner.scan(basePackage, Component.class);

			 // Загружаем модули
	        ModuleManager moduleManager = new ModuleManager();
	        moduleManager.loadModules(appClass);
	        // Сканируем пользовательские аннотации из модулей
	        for (Class<? extends Annotation> ann : moduleManager.getRegistrar().getCustomAnnotations()) {
	            Set<Class<?>> annotated = AnnotationScanner.scan(basePackage, ann);
	            annotated.forEach(cls -> DeskContext.registerBean(cls));
	        }
			
			PageRegistry registry = new PageRegistry();
			Class<? extends DeskPage> startPage = null;

			for (Class<?> cls : pageClasses) {
				if (DeskPage.class.isAssignableFrom(cls)) {
					Page page = cls.getAnnotation(Page.class);
					registry.register(page.path(), (Class<? extends DeskPage>) cls);

					if (cls.isAnnotationPresent(MainPage.class)) {
						startPage = (Class<? extends DeskPage>) cls;
					}
				}
			}
			for (Class<?> cls : componentClasses) {
				DeskContext.registerBean(cls);
			}

			DeskApp deskApp = appClass.getAnnotation(DeskApp.class);
			PageNavigator.init(registry, deskApp.title(), deskApp.width(), deskApp.height());

			if (startPage != null) {
				PageNavigator.go(startPage.getAnnotation(Page.class).path());
			} else if (!registry.getPaths().isEmpty()) {
				String firstPath = registry.getPaths().iterator().next();
				PageNavigator.go(firstPath);
			} else {
				throw new RuntimeException("Не найдено ни одной страницы!");
			}
		};

		if (guiEnabled) {
			// GUI-режим
			System.setProperty("java.awt.headless", "false");
			javax.swing.SwingUtilities.invokeLater(appRunner);
		} else {
			// headless
			appRunner.run();
		}
	}

}