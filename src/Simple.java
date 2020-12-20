import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Simple extends JFrame {

    public static void main(String[] args) {
        var f = new Simple();
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
        f.setTitle("Refuelings");
    }

    Simple() {

        var jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose input file");
        var returnValue = jfc.showOpenDialog(null);
        var filePath = "";

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            var selectedFile = jfc.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
        }

        var refuelings = new ArrayList<Refueling>();

        String line;
        if (!filePath.equals("")) {
            try {
                var reader = new BufferedReader(new FileReader(filePath));
                while ((line = reader.readLine()) != null) {
                    var refueling = new Refueling(line);
                    refuelings.add(refueling);
                }

                var i = 0;
                var array = new Object[refuelings.size()][4];

                for (Refueling refueling : refuelings) {
                    array[i][0] = refueling.getFuelName();
                    array[i][1] = refueling.getFuelPrice();
                    array[i][2] = refueling.getFuelAmount();
                    array[i][3] = refueling.getRefuellingDate();
                    i++;
                }

                String[] columnNames = {"fuel name", "fuel price", "fuel amount", "refuelling date"};
                var table = new JTable(array, columnNames);
                table.getColumnModel().getColumn(1).setCellRenderer(new DecimalFormatRenderer());
                getContentPane().add(new JScrollPane(table));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    static class DecimalFormatRenderer extends DefaultTableCellRenderer {
        private static final DecimalFormat formatter = new DecimalFormat("0.000");

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            // First format the cell value as required

            value = formatter.format(value);

            // And pass it on to parent class

            return super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
        }
    }
}

