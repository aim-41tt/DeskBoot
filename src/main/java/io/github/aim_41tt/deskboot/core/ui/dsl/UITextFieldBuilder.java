package io.github.aim_41tt.deskboot.core.ui.dsl;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import io.github.aim_41tt.deskboot.core.ui.UI;

public class UITextFieldBuilder {
    private final UI ui;
    private final JTextField textField;
    private JLabel label; // optional
    private String id;

    public UITextFieldBuilder(UI ui, String labelText) {
        this.ui = ui;
        this.textField = new JTextField();
        if (labelText != null && !labelText.isEmpty()) {
            this.label = new JLabel(labelText);
            this.label.setHorizontalAlignment(SwingConstants.LEFT);
        }
    }

    public UITextFieldBuilder(UI ui) {
        this(ui, null);
    }

    // Задаём id для регистрации (регистрируем в build)
    public UITextFieldBuilder id(String id) {
        this.id = id;
        return this;
    }

    // Устанавливаем количество колонок
    public UITextFieldBuilder columns(int cols) {
        textField.setColumns(cols);
        return this;
    }

    // Устанавливаем текст по умолчанию
    public UITextFieldBuilder text(String text) {
        textField.setText(text);
        return this;
    }

    // Устанавливаем горизонтальное выравнивание
    public UITextFieldBuilder horizontalAlignment(int alignment) {
        textField.setHorizontalAlignment(alignment); // SwingConstants.LEFT, CENTER, RIGHT
        return this;
    }

    // Устанавливаем цвет фона
    public UITextFieldBuilder background(Color color) {
        textField.setBackground(color);
        return this;
    }

    // Устанавливаем цвет текста
    public UITextFieldBuilder foreground(Color color) {
        textField.setForeground(color);
        return this;
    }

    // Устанавливаем шрифт
    public UITextFieldBuilder font(Font font) {
        textField.setFont(font);
        return this;
    }

    // Делаем поле редактируемым/нередактируемым
    public UITextFieldBuilder editable(boolean editable) {
        textField.setEditable(editable);
        return this;
    }

    // Добавляем компонент на панель и регистрируем если есть id
    public UITextFieldBuilder build() {
        if (label != null) {
            ui.addComponent(label);
        }
        ui.addComponent(textField);
        if (id != null) {
            ui.register(id, textField);
        }
        return this;
    }

    // Возвращаем сам компонент для дальнейшего использования
    public JTextField getComponent() {
        return textField;
    }
}