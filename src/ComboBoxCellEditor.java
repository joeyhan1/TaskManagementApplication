import javax.swing.*;
import java.util.ArrayList;

/**
 * This class represents a cell editor for JComboBox in a table cell.
 * It extends DefaultCellEditor to provide functionality for editing JComboBoxes
 * within a JTable.
 */
public class ComboBoxCellEditor extends DefaultCellEditor {

    /**
     * Constructs a ComboBoxCellEditor with the specified list of items.
     *
     * @param items An ArrayList containing the items to be displayed in the JComboBox.
     */
    public ComboBoxCellEditor(ArrayList<String> items) {
        super(new JComboBox(items.toArray()));
    }
}
