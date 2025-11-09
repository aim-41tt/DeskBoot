package io.github.aim_41tt.deskboot.core.ui.dsl;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import io.github.aim_41tt.deskboot.core.ui.UI;

public class UILabelBuilder {
    private final UI ui;
    private final JLabel label;

    public UILabelBuilder(UI ui, String text) {
        this.ui = ui;
        this.label = new JLabel(text);
    }

    // Выравнивание влево
    public UILabelBuilder left() {
        label.setHorizontalAlignment(SwingConstants.LEFT);
        return this;
    }

    // Выравнивание по центру
    public UILabelBuilder center() {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return this;
    }

    // Выравнивание вправо
    public UILabelBuilder right() {
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        return this;
    }

    // Установка шрифта
    public UILabelBuilder font(Font font) {
        label.setFont(font);
        return this;
    }

    // Устанавливаем цвет фона
    public UILabelBuilder background(Color color) {
        label.setBackground(color);
        return this;
    }

    // Устанавливаем цвет текста
    public UILabelBuilder foreground(Color color) {
        label.setForeground(color);
        return this;
    }

    // Устанавливаем вертикальное выравнивание
    public UILabelBuilder verticalAlignment(int alignment) {
        label.setVerticalAlignment(alignment); // SwingConstants.TOP, CENTER, BOTTOM
        return this;
    }

    // Устанавливаем tooltip
    public UILabelBuilder toolTip(String tip) {
        label.setToolTipText(tip);
        return this;
    }

    // Добавляем компонент на панель
    public UILabelBuilder build() {
        ui.addComponent(label);
        return this;
    }

    // Возвращаем сам компонент для дальнейшего использования
    public JLabel getComponent() {
        return label;
    }
}