package io.github.aim_41tt.deskboot.core.ui.dsl;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.Color;
import java.awt.Dimension;
import io.github.aim_41tt.deskboot.core.ui.UI;

public class UIJScrollPaneBuilder {
	private final UI ui;
	private JTable table;
	private JScrollPane scrollPane;

	public UIJScrollPaneBuilder(UI ui) {
		this.ui = ui;
	}

	// Создаём таблицу с данными и колонками
	public UIJScrollPaneBuilder createTable(Object[][] data, Object[] columns) {
		this.table = new JTable(new DefaultTableModel(data, columns));
		return this;
	}

	// Создаём таблицу с кастомной моделью
	public UIJScrollPaneBuilder createTable(TableModel model) {
		this.table = new JTable(model);
		return this;
	}

	// Оборачиваем таблицу в JScrollPane
	public UIJScrollPaneBuilder withScroll() {
		if (table == null) {
			throw new IllegalStateException("Table must be created before adding scroll.");
		}
		this.scrollPane = new JScrollPane(table);
		return this;
	}

	// Устанавливаем preferred size для scrollPane или table
	public UIJScrollPaneBuilder preferredSize(Dimension size) {
		if (scrollPane != null) {
			scrollPane.setPreferredSize(size);
		} else if (table != null) {
			table.setPreferredSize(size);
		}
		return this;
	}

	// Устанавливаем цвет фона
	public UIJScrollPaneBuilder background(Color color) {
		if (table != null) {
			table.setBackground(color);
		}
		if (scrollPane != null) {
			scrollPane.setBackground(color);
		}
		return this;
	}

	// Делаем таблицу editable (влияет на enabled, для full editable need model
	// override)
	public UIJScrollPaneBuilder editable(boolean editable) {
		if (table != null) {
			table.setEnabled(editable);
		}
		return this;
	}

	// Добавляем компонент (scrollPane или table) на панель
	public UIJScrollPaneBuilder build() {
		if (scrollPane != null) {
			ui.addComponent(scrollPane);
		} else if (table != null) {
			ui.addComponent(table);
		} else {
			throw new IllegalStateException("No table or scroll pane to build.");
		}
		return this;
	}

	// Возвращаем сам компонент: если есть scrollPane, то его; иначе table
	public JComponent getComponent() {
		if (scrollPane != null) {
			return scrollPane;
		} else if (table != null) {
			return table;
		}
		throw new IllegalStateException("Build the component first.");
	}
}