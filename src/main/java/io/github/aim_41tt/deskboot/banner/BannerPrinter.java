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
package io.github.aim_41tt.deskboot.banner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.github.aim_41tt.deskboot.version.Version;

/**
 * @author aim_41tt
 * @version 1.0
 * @since 9.09.2025
 */
public class BannerPrinter {
	private static final String RESET = "\u001B[0m";
	private static final String GREEN = "\u001B[32m";

	public static void print() {
		String version = Version.getVersion();
		String banner = loadBannerFile();
		if (banner == null) {
			banner = " :: DeskBoot ::";
		}

		banner = banner.replace("${version}", version != null ? version : "DEV");
		banner = banner.replace(":: DeskBoot ::", GREEN + ":: DeskBoot ::" + RESET);

		System.out.println(banner);
	}

	private static String loadBannerFile() {
		try (InputStream is = BannerPrinter.class.getClassLoader().getResourceAsStream("banner-desk.txt")) {
			if (is == null) {
				return null;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append(System.lineSeparator());
			}
			return sb.toString();
		} catch (IOException e) {
			return null;
		}
	}
}
