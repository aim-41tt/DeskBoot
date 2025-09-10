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
package io.github.aim_41tt.deskboot.version;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author aim_41tt
 * @version 1.0
 * @since 9.09.2025
 */
public class Version {
	private static final String VERSION;

	static {
		String tempVersion = "unknown";
		try (InputStream in = Version.class.getResourceAsStream("/system.properties")) {
			if (in != null) {
				Properties props = new Properties();
				props.load(in);
				tempVersion = props.getProperty("version", "unknown");
			}
		} catch (IOException ignored) {
		}
		VERSION = tempVersion;
	}

	public static String getVersion() {
		return VERSION;
	}
}
