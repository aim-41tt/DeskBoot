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
package io.github.aim_41tt.deskboot.core.page;

import java.awt.BorderLayout;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import io.github.aim_41tt.deskboot.context.DeskContext;
import io.github.aim_41tt.deskboot.core.PageRegistry;
import io.github.aim_41tt.deskboot.core.page.annotation.Page;
import io.github.aim_41tt.deskboot.core.ui.UI;

/**
 * @author aim_41tt
 * @version 1.0
 * @since 9.09.2025
 */
public class PageNavigator {

	private static JFrame frame;
	private static PageRegistry registry;
	private static Stack<String> currentPath;
	private static String title;

	static {
		currentPath = new Stack<String>();
	}

	public static void init(PageRegistry reg, String appTitle, int width, int height) {
		registry = reg;
		title = appTitle;
		frame = new JFrame(appTitle);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLayout(new BorderLayout());
	}

	public static void go(String path) {
		try {
			Class<? extends DeskPage> pageClass = registry.get(path);
			if (pageClass == null) {
				throw new RuntimeException("Page not found: " + path);
			}

			DeskPage page = pageClass.getDeclaredConstructor().newInstance();
			DeskContext.injectDependencies(page);

			JPanel panel = new JPanel();
			UI ui = new UI(panel);
			page.render(ui);

			frame.getContentPane().removeAll();
			frame.add(panel, BorderLayout.CENTER);
			frame.revalidate();
			frame.repaint();
			frame.setTitle(title + " - " + pageClass.getAnnotation(Page.class).title());
			frame.setVisible(true);
			if (currentPath.size() == 0 || !currentPath.peek().equals(pageClass.getAnnotation(Page.class).path())) {
				currentPath.push(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void back() {
		currentPath.pop();
		go(currentPath.peek());

	}
}