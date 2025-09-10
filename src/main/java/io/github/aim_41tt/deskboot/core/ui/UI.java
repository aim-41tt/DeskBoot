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
package io.github.aim_41tt.deskboot.core.ui;

import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * @author aim_41tt
 * @version 1.0
 * @since 9.09.2025
 */
public class UI {
	private JPanel panel;

	public UI(JPanel panel) {
		this.panel = panel;

		// time status off
		// dev deff Layout
//		this.panel.setLayout(new GridLayout(2, 2));
	}

	public void add(JComponent component) {
		panel.add(component);
	}

	public JLabel label(String text) {
		JLabel jl = new JLabel(text);
		panel.add(jl);
		return jl;
	}

	public JButton button(String text, Runnable action) {
		JButton btn = new JButton(text);
		btn.addActionListener(e -> action.run());
		panel.add(btn);
		return btn;
	}

	public JTextField textField(String placeholder) {
		if (placeholder != null) {
			panel.add(new JLabel(placeholder));
		}
		return textField();
	}

	public JTextField textField() {
		JTextField textField = new JTextField();
		panel.add(textField);
		return textField;
	}

	public JPasswordField passwordField(String placeholder) {
		if (placeholder != null) {
			panel.add(new JLabel(placeholder));
		}
		return passwordField();
	}

	public JPasswordField passwordField() {
		JPasswordField passwordField = new JPasswordField();
		panel.add(passwordField);
		return passwordField;
	}

	public JScrollPane tableScroll(Object[][] data, Object[] columnNames, String type) {
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(model);
		JScrollPane pane = new JScrollPane(table);
		panel.add(pane, type);
		return pane;
	}

	public void repaint() {
		panel.repaint();
	}

	public void setLayout(LayoutManager mgr) {
		panel.setLayout(mgr);
	}

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

}
