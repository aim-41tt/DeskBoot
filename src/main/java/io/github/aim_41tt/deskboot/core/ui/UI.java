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
import javax.swing.JComboBox;
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
 * @version 2.0
 * @since 9.09.2025
 */
public class UI {
	private final JPanel panel;

	public UI() {
		this.panel = new JPanel();
	}

	public UI(LayoutManager layout) {
		this.panel = new JPanel(layout);
	}

	public UI(JPanel panel) {
		this.panel = panel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public UI layout(LayoutManager layout) {
		panel.setLayout(layout);
		return this;
	}

	public UI add(JComponent component, Object constraints) {
		if (constraints != null) {
			panel.add(component, constraints);
		} else {
			panel.add(component);
		}
		return this;
	}

	public UI add(JComponent component) {
		return add(component, null);
	}

	public JLabel label(String text, Integer swingConstants, Object constraints) {
		JLabel label = (swingConstants == null) ? new JLabel(text) : new JLabel(text, swingConstants);
		add(label, constraints);
		return label;
	}

	public JLabel label(String text) {
		return label(text, null, null);
	}

	public JLabel label(String text, Integer swingConstants) {
		return label(text, swingConstants, null);
	}

	public JButton button(String text, Runnable action, Object constraints) {
		JButton btn = new JButton(text);
		if (action != null) {
			btn.addActionListener(e -> action.run());
		}
		add(btn, constraints);
		return btn;
	}

	public JButton button(String text, Runnable action) {
		return button(text, action, null);
	}

// -----
	public JTextField textField(String labelText, Object constraints) {
		if (labelText != null && !labelText.isEmpty()) {
			label(labelText, null);
		}
		JTextField tf = new JTextField();
		add(tf, constraints);
		return tf;
	}

	public JTextField textField(String lableText) {
		return textField(lableText, null);
	}

	public JTextField textField(Object constraints) {
		return textField(null, constraints);
	}

// -----
	public JPasswordField passwordField(String labelText, Object constraints) {
		if (labelText != null && !labelText.isEmpty()) {
			label(labelText, null);
		}
		JPasswordField pf = new JPasswordField();
		add(pf, constraints);
		return pf;
	}

	public JPasswordField passwordField(String labelText) {
		return passwordField(labelText, null);
	}

	public JPasswordField passwordField(Object constraints) {
		return passwordField(null, constraints);
	}

	public JScrollPane table(Object[][] data, Object[] columns, Object constraints) {
		JTable table = new JTable(new DefaultTableModel(data, columns));
		JScrollPane scroll = new JScrollPane(table);
		add(scroll, constraints);
		return scroll;
	}

// --------------COMBO-BOX------------------
	public <T> JComboBox<T> comboBox(T[] data, Runnable action, Object constraints) {
		JComboBox<T> comboBox = new JComboBox(data);
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(e -> action.run());
		add(comboBox, constraints);
		return comboBox;
	}

	public UI nested(LayoutManager layout, Object constraints) {
		JPanel nested = new JPanel(layout);
		add(nested, constraints);
		return new UI(nested);
	}

	public void repaint() {
		panel.revalidate();
		panel.repaint();
	}
}
