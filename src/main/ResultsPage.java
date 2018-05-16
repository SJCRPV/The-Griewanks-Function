package main;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class ResultsPage extends JPanel {
	private JTable table;
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	public ResultsPage() {
		 String[] columns = new String[] {
		            "Id", "Name", "Hourly Rate", "Part Time"
		        };
		         
		        //actual data for the table in a 2d array
		        Object[][] data = new Object[][] {
		            {1, "John", 40.0, false },
		            {2, "Rambo", 70.0, false },
		            {3, "Zorro", 60.0, true },
		        };
		        
		        int numOfRows = 25;
		        DefaultTableModel tblModel = new DefaultTableModel(numOfRows, columns.length);
		        tblModel.setColumnIdentifiers(columns);
		
		
		
		
		        this.setVisible(true);
		        
		        JScrollPane scrollPane = new JScrollPane();
		        scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		        add(scrollPane);
		        
		        table_1 = new JTable(data, columns);
		        scrollPane.setViewportView(table_1);
		 
		}
}
