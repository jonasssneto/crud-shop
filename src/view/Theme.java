package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 *  Definição de tema padrão para a aplicação. 
 */
public class Theme {
    public static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 20);
    public static final Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 14);
    public static final Font TABLE_HEADER_FONT = new Font("SansSerif", Font.BOLD, 14);
    public static final Font TABLE_FONT = new Font("SansSerif", Font.PLAIN, 13);

    public static final Color PRIMARY_COLOR = new Color(59, 89, 182);
    public static final Color CONTRAST_COLOR = Color.WHITE;
    public static final Color GRID_COLOR = Color.LIGHT_GRAY;

    public static final Border WINDOW_PADDING = BorderFactory.createEmptyBorder(15, 25, 15, 25);

    public static void apply() {
        UIManager.put("Button.font", BUTTON_FONT);
        UIManager.put("Button.background", PRIMARY_COLOR);
        UIManager.put("Button.foreground", CONTRAST_COLOR);
        UIManager.put("Label.font", TABLE_FONT);
        UIManager.put("Table.font", TABLE_FONT);
        UIManager.put("TableHeader.font", TABLE_HEADER_FONT);
        UIManager.put("Table.gridColor", GRID_COLOR);
        UIManager.put("Table.showGrid", Boolean.TRUE);
    }
}
