import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.EventObject;

/**
 * This class implements a TableCellEditor that renders cells as non-editable JTextField components in a JTable.
 */
public class NonEditableCellEditor implements TableCellEditor {

    /**
     * Returns a non-editable JTextField component to render the cell.
     *
     * @param table the JTable that contains the cell
     * @param value the value of the cell
     * @param isSelected true if the cell is selected; otherwise, false
     * @param row the row index of the cell
     * @param column the column index of the cell
     * @return a non-editable JTextField component representing the cell
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        //Return a non-editable text field
        return new JTextField(value.toString());
    }

    /**
     * Returns null as no cell editor value is needed since cells are non-editable.
     *
     * @return null
     */
    @Override
    public Object getCellEditorValue() {
        return null; //No cell editor value needed since cells are non-editable
    }

    /**
     * Returns false to make cells non-editable.
     *
     * @param anEvent the event object
     * @return false
     */
    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return false; //Make cells non-editable
    }

    /**
     * Returns false as cells should not be selectable.
     *
     * @param anEvent the event object
     * @return false
     */
    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return false; //Cells should not be selectable
    }

    /**
     * Returns true to stop editing.
     *
     * @return true
     */
    @Override
    public boolean stopCellEditing() {
        return true; //Stop editing
    }

    /**
     * Performs no action when editing is canceled.
     */
    @Override
    public void cancelCellEditing() {
        //No action needed when editing is canceled
    }

    /**
     * Performs no action for cell editor listener.
     *
     * @param l the CellEditorListener to be added
     */
    @Override
    public void addCellEditorListener(CellEditorListener l) {
        //No action needed for cell editor listener
    }

    /**
     * Performs no action for cell editor listener.
     *
     * @param l the CellEditorListener to be removed
     */
    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        //No action needed for cell editor listener
    }
}
