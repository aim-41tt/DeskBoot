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

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JPanel;

import io.github.aim_41tt.deskboot.core.ui.dsl.UIButtonBuilder;
import io.github.aim_41tt.deskboot.core.ui.dsl.UIJScrollPaneBuilder;
import io.github.aim_41tt.deskboot.core.ui.dsl.UILabelBuilder;
import io.github.aim_41tt.deskboot.core.ui.dsl.UIPasswordFieldBuilder;
import io.github.aim_41tt.deskboot.core.ui.dsl.UITextFieldBuilder;
import io.github.aim_41tt.deskboot.core.ui.dsl.UIComboBoxBuilder;

/**
 * @author aim_41tt
 * @version 2.0
 * @since 9.09.2025
 */
public class UI {
	private final JPanel panel;

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

	// ---- DSL BUILDERS ----
	// ---label---
	public UILabelBuilder label(String text) {
		return new UILabelBuilder(this, text);
	}

	// ---textField---
	public UITextFieldBuilder textField(String labelText) {
		return new UITextFieldBuilder(this, labelText);
	}

	public UITextFieldBuilder textField() {
		return new UITextFieldBuilder(this);
	}

	// ---passwordField---
	public UIPasswordFieldBuilder passwordField(String labelText) {
		return new UIPasswordFieldBuilder(this, labelText);
	}

	public UIPasswordFieldBuilder passwordField() {
		return new UIPasswordFieldBuilder(this);
	}

	// ---button---
	public UIButtonBuilder button(String text) {
		return new UIButtonBuilder(this, text);
	}

	// ---table---
	public UIJScrollPaneBuilder table() {
		return new UIJScrollPaneBuilder(this);
	}

	// ---comboBox---
	public <T> UIComboBoxBuilder<T> comboBox(String labelText) {
		return new UIComboBoxBuilder<>(this, labelText);
	}

	public <T> UIComboBoxBuilder<T> comboBox() {
		return new UIComboBoxBuilder<>(this);
	}

	// ---- UTILS ----
	public void addComponent(Component comp) {
		panel.add(comp);
	}

	public java.util.Map<String, JComponent> components = new java.util.HashMap<>();

	public void register(String id, javax.swing.JComponent comp) {
		if (id != null)
			components.put(id, comp);
	}

	public String getText(String id) {
		var comp = components.get(id);
		if (comp instanceof javax.swing.JTextField tf) {
			return tf.getText();
		}
		return null;
	}

	public String getPassword(String id) {
		var comp = components.get(id);
		if (comp instanceof javax.swing.JPasswordField pf) {
			return new String(pf.getPassword());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T getSelectedItem(String id) {
		var comp = components.get(id);
		if (comp instanceof javax.swing.JComboBox<?> cb) {
			return (T) cb.getSelectedItem();
		}
		return null;
	}

	public void repaint() {
		panel.repaint();
	}
}