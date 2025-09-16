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
package io.github.aim_41tt.deskboot.core.module;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import io.github.aim_41tt.deskboot.context.DeskContext;

/**
 * @author aim_41tt
 * @version 1.0
 * @since 16.09.2025
 */
public class ModuleManager {

	private final List<DeskModule> modules = new ArrayList<>();
	private final ModuleRegistrar registrar = new ModuleRegistrar();

	public void loadModules(Class<?> appClass) {
		DeskContext context = new DeskContext();
		ServiceLoader<DeskModule> loader = ServiceLoader.load(DeskModule.class);
		for (DeskModule module : loader) {
			modules.add(module);
			module.initialize(appClass, registrar, context);
		}

	}

	public ModuleRegistrar getRegistrar() {
		return registrar;
	}

}
