package io.github.aim_41tt.deskboot.core.ui.dsl;

import javax.swing.JButton;
import javax.swing.Icon;
import java.awt.Color;
import java.awt.Font;
import io.github.aim_41tt.deskboot.core.ui.UI;

public class UIButtonBuilder {
    private final UI ui;
    private final JButton button;

    public UIButtonBuilder(UI ui, String text) {
        this.ui = ui;
        this.button = new JButton(text);
    }

    // Добавляем обработчик клика
    public UIButtonBuilder onClick(Runnable action) {
        button.addActionListener(e -> action.run());
        return this;
    }

    // Устанавливаем иконку
    public UIButtonBuilder icon(Icon icon) {
        button.setIcon(icon);
        return this;
    }

    // Устанавливаем цвет фона
    public UIButtonBuilder background(Color color) {
        button.setBackground(color);
        return this;
    }

    // Устанавливаем цвет текста
    public UIButtonBuilder foreground(Color color) {
        button.setForeground(color);
        return this;
    }

    // Устанавливаем шрифт
    public UIButtonBuilder font(Font font) {
        button.setFont(font);
        return this;
    }

    // Делаем кнопку enabled/disabled
    public UIButtonBuilder enabled(boolean enabled) {
        button.setEnabled(enabled);
        return this;
    }

    // Устанавливаем tooltip
    public UIButtonBuilder toolTip(String tip) {
        button.setToolTipText(tip);
        return this;
    }

    // Добавляем компонент на панель
    public UIButtonBuilder build() {
        ui.addComponent(button);
        return this;
    }

    // Возвращаем сам компонент для дальнейшего использования
    public JButton getComponent() {
        return button;
    }
}