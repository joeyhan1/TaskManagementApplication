import javax.swing.*;
import javax.swing.table.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.awt.*;

/**
 * Custom cell editor for date values in a JTable.
 */
public class DateCellEditor extends DefaultCellEditor implements TableCellEditor {
    private JDateChooser dateChooser;
    private SimpleDateFormat dateFormat;

    /**
     * Constructs a DateCellEditor with a JDateChooser component.
     */
    public DateCellEditor() {
        super(new JCheckBox());
        dateChooser = new JDateChooser();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        editorComponent = dateChooser;
        delegate = new DefaultCellEditor.EditorDelegate() {
            @Override
            public void setValue(Object value) {
                dateChooser.setDate((java.util.Date) value);
            }

            @Override
            public Object getCellEditorValue() {
                return dateFormat.format(dateChooser.getDate());
            }
        };
    }

    /**
     * Returns the JDateChooser component as the editor component.
     * @param table the JTable object
     * @param value the value to be edited
     * @param isSelected true if the cell is selected
     * @param row the row index of the cell
     * @param column the column index of the cell
     * @return the JDateChooser component
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        dateChooser.setDate((java.util.Date) value);
        return dateChooser;
    }
}