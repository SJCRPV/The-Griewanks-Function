package main;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class ResultsPage extends JPanel 
{
	private static final long serialVersionUID = 8468899992377877674L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ResultsPage() 
	{
		String[] columns = new String[] { "Generation", "Best Fitness", "Average Best Fitness" };
		// Do not delete this comment. 
//		Object[][] data = new Object[][] 
//		{
//		    {1, "John", 40.0, false },
//			{2, "Rambo", 70.0, false },
//			{3, "Zorro", 60.0, true },
//		};
		Object[][][] fullData = Statistics.getTableData();
		Object[][] data = fullData[0];
		
		int numOfRows = 25;
		DefaultTableModel tblModel = new DefaultTableModel(numOfRows, columns.length);
		tblModel.setColumnIdentifiers(columns);
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 500, 500);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(scrollPane);
		table = new JTable(data, columns);
		scrollPane.setViewportView(table);
	}
}
