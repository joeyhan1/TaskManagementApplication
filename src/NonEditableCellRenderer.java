import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * This class implements a TableCellRenderer that renders cells as non-editable using the DefaultTableCellRenderer.
 */
public class NonEditableCellRenderer extends DefaultTableCellRenderer {

    /**
     * Returns the renderer component for the cell, inheriting the behavior of DefaultTableCellRenderer.
     *
     * @param table the JTable that contains the cell
     * @param value the value of the cell
     * @param isSelected true if the cell is selected; otherwise, false
     * @param hasFocus true if the cell has focus; otherwise, false
     * @param row the row index of the cell
     * @param column the column index of the cell
     * @return the renderer component for the cell
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        return rendererComponent;
    }
}

