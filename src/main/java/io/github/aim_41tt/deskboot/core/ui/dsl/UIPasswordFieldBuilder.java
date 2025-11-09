package io.github.aim_41tt.deskboot.core.ui.dsl;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import io.github.aim_41tt.deskboot.core.ui.UI;

public class UIPasswordFieldBuilder {
	private final UI ui;
	private final JPasswordField passwordField;
	private JLabel label; // optional
	private String id;

	public UIPasswordFieldBuilder(UI ui, String labelText) {
		this.ui = ui;
		this.passwordField = new JPasswordField();
		if (labelText != null && !labelText.isEmpty()) {
			this.label = new JLabel(labelText);
			this.label.setHorizontalAlignment(SwingConstants.LEFT);
		}
	}

	public UIPasswordFieldBuilder(UI ui) {
		this(ui, null);
	}

	// Задаём id для регистрации (регистрируем в build)
	public UIPasswordFieldBuilder id(String id) {
		this.id = id;
		return this;
	}

	// Устанавливаем количество колонок
	public UIPasswordFieldBuilder columns(int cols) {
		passwordField.setColumns(cols);
		return this;
	}

	// Устанавливаем эхо-символ (по умолчанию '*')
	public UIPasswordFieldBuilder echoChar(char echo) {
		passwordField.setEchoChar(echo);
		return this;
	}

	// Устанавливаем горизонтальное выравнивание
	public UIPasswordFieldBuilder horizontalAlignment(int alignment) {
		passwordField.setHorizontalAlignment(alignment); // SwingConstants.LEFT, CENTER, RIGHT
		return this;
	}

	// Устанавливаем цвет фона
	public UIPasswordFieldBuilder background(Color color) {
		passwordField.setBackground(color);
		return this;
	}

	// Устанавливаем цвет текста
	public UIPasswordFieldBuilder foreground(Color color) {
		passwordField.setForeground(color);
		return this;
	}

	// Устанавливаем шрифт
	public UIPasswordFieldBuilder font(Font font) {
		passwordField.setFont(font);
		return this;
	}

	// Делаем поле редактируемым/нередактируемым
	public UIPasswordFieldBuilder editable(boolean editable) {
		passwordField.setEditable(editable);
		return this;
	}

	// Добавляем компонент на панель и регистрируем если есть id
	public UIPasswordFieldBuilder build() {
		if (label != null) {
			ui.addComponent(label);
		}
		ui.addComponent(passwordField);
		if (id != null) {
			ui.register(id, passwordField);
		}
		return this;
	}

	// Возвращаем сам компонент для дальнейшего использования
	public JPasswordField getComponent() {
		return passwordField;
	}
}