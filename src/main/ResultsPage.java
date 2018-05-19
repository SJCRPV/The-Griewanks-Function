package main;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;

public class ResultsPage extends JPanel 
{
	private static final long serialVersionUID = 8468899992377877674L;
	private JTable SwarmResultTable;

	/**
	 * Create the panel.
	 */
	public ResultsPage() 
	{
		String[] columns = new String[] { "Generation", "Best Fitness", "Average Best Fitness" };
		// Do not delete this comment. 
		Object[][] data = new Object[][] 
		{
		    {1, "John", 40.0, false },
			{2, "Rambo", 70.0, false },
			{3, "Zorro", 60.0, true },
		};
//		Object[][][] fullData = Statistics.getTableData();
//		Object[][] data = fullData[0];
		
		int numOfRows = 25;
		DefaultTableModel tblModel = new DefaultTableModel(numOfRows, columns.length);
		tblModel.setColumnIdentifiers(columns);
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 20, 500, 200);
		add(tabbedPane);
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Swarm", null, scrollPane, null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		SwarmResultTable = new JTable(data, columns);
		scrollPane.setViewportView(SwarmResultTable);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(0, 0, 495, 20);
		add(comboBox);
		
		JPanel Graph = new JPanel();
		Graph.setBounds(0, 220, 500, 280);
		add(Graph);
	}
}
