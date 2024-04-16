import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A custom cell renderer for rendering JComboBox as a TableCellRenderer in a JTable.
 * This renderer displays JComboBox in each cell of a specific column in the table.
 */
public class ComboBoxCellRenderer extends JComboBox implements TableCellRenderer {

    /**
     * Constructs a ComboBoxCellRenderer with the specified list of items.
     *
     * @param items The list of items to populate the combo box.
     */
    public ComboBoxCellRenderer(ArrayList<String> items) {
        super(items.toArray());
    }

    /**
     * Returns the component used for drawing the cell. This method is called each time a cell's value is rendered.
     *
     * @param table      The JTable that is asking the renderer to draw.
     * @param value      The value of the cell to be rendered.
     * @param isSelected True if the cell is to be rendered with the selection highlighted.
     * @param hasFocus   True if the cell has the focus.
     * @param row        The row index of the cell being drawn.
     * @param column     The column index of the cell being drawn.
     * @return The component used for drawing the cell.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setSelectedItem(value);
        return this;
    }
}