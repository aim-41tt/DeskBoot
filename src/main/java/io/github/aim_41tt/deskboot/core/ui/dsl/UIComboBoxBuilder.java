package io.github.aim_41tt.deskboot.core.ui.dsl;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import io.github.aim_41tt.deskboot.core.ui.UI;

public class UIComboBoxBuilder<T> {
	private final UI ui;
	private final JComboBox<T> comboBox;
	private JLabel label; // optional
	private String id;

	public UIComboBoxBuilder(UI ui, String labelText) {
		this.ui = ui;
		this.comboBox = new JComboBox<>();
		if (labelText != null && !labelText.isEmpty()) {
			this.label = new JLabel(labelText);
			this.label.setHorizontalAlignment(SwingConstants.LEFT);
		}
	}

	public UIComboBoxBuilder(UI ui) {
		this(ui, null);
	}

	// Задаём id для регистрации (регистрируем в build)
	public UIComboBoxBuilder<T> id(String id) {
		this.id = id;
		return this;
	}

	// Добавляем элементы в выпадающий список
	public UIComboBoxBuilder<T> items(T[] items) {
		for (T item : items) {
			comboBox.addItem(item);
		}
		return this;
	}

	// Устанавливаем выбранный элемент
	public UIComboBoxBuilder<T> selectedItem(T item) {
		comboBox.setSelectedItem(item);
		return this;
	}

	// Устанавливаем индекс выбранного элемента
	public UIComboBoxBuilder<T> selectedIndex(int index) {
		comboBox.setSelectedIndex(index);
		return this;
	}

	// Делаем выпадающий список редактируемым/нередактируемым
	public UIComboBoxBuilder<T> editable(boolean editable) {
		comboBox.setEditable(editable);
		return this;
	}

	// Устанавливаем цвет фона
	public UIComboBoxBuilder<T> background(Color color) {
		comboBox.setBackground(color);
		return this;
	}

	// Устанавливаем цвет текста
	public UIComboBoxBuilder<T> foreground(Color color) {
		comboBox.setForeground(color);
		return this;
	}

	// Устанавливаем шрифт
	public UIComboBoxBuilder<T> font(Font font) {
		comboBox.setFont(font);
		return this;
	}

	// Устанавливаем preferred size
	public UIComboBoxBuilder<T> preferredSize(Dimension size) {
		comboBox.setPreferredSize(size);
		return this;
	}

	// Делаем компонент enabled/disabled
	public UIComboBoxBuilder<T> enabled(boolean enabled) {
		comboBox.setEnabled(enabled);
		return this;
	}

	// Устанавливаем tooltip
	public UIComboBoxBuilder<T> toolTip(String tip) {
		comboBox.setToolTipText(tip);
		return this;
	}

	// Добавляем компонент на панель и регистрируем если есть id
	public UIComboBoxBuilder<T> build() {
		if (label != null) {
			ui.addComponent(label);
		}
		ui.addComponent(comboBox);
		if (id != null) {
			ui.register(id, comboBox);
		}
		return this;
	}

	// Возвращаем сам компонент для дальнейшего использования
	public JComboBox<T> getComponent() {
		return comboBox;
	}
}