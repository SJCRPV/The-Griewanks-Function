package main;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ResultsPage extends JPanel 
{
	private String[] columns = new String[] { "Generation", "Best Fitness", "Average Best Fitness" };
	private static final long serialVersionUID = 8468899992377877674L;
	private JTable SwarmResultTable;
	private JTable GeneticResultTable;
	private JTabbedPane tabbedPane;
	private JScrollPane swarmPane;
	private JScrollPane geneticPane;
	private JComboBox<?> comboBox;
	private Object[][][] fullSwarmData;
	private Object[][][] fullGeneticData;
	private Object[][] swarmData;
	private Object[][] geneticData;
	
	private void updateTable()
	{
		int selectedTab = tabbedPane.getSelectedIndex();
		int comboBoxValue = (int) comboBox.getSelectedItem();
		//Swarm
		if(selectedTab == 0)
		{
			swarmData = fullSwarmData[comboBoxValue];
			SwarmResultTable = new JTable(swarmData, columns);
			swarmPane.setViewportView(SwarmResultTable);
			return;
		}
		geneticData = fullGeneticData[comboBoxValue];
		GeneticResultTable = new JTable(geneticData, columns);
		geneticPane.setViewportView(GeneticResultTable);
	}
	
	public ResultsPage() 
	{
		setBorder(new LineBorder(new Color(0, 0, 0)));
		
		// Do not delete this comment. 
//		Object[][] swarmData = new Object[][] 
//		{
//		    {1, "John", 40.0, false },
//			{2, "Rambo", 70.0, false },
//			{3, "Zorro", 60.0, true },
//		};
//		Object[][] geneticData = new Object[][] 
//		{
//		    {1, "Willaim", 65.0, false },
//			{2, "Carl", 40.0, false },
//			{3, "Theodore", 27.0, true },
//		};
		Statistics.setWantSwarm(true);
		fullSwarmData = Statistics.getTableData();
		swarmData = fullSwarmData[0];
		
		Statistics.setWantSwarm(false);
		fullGeneticData = Statistics.getTableData();
		geneticData = fullGeneticData[0];
		
		int numOfRows = 25;
		DefaultTableModel tblModel = new DefaultTableModel(numOfRows, columns.length);
		tblModel.setColumnIdentifiers(columns);
		setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 20, 490, 200);
		add(tabbedPane);
		
		swarmPane = new JScrollPane();
		tabbedPane.addTab("Swarm", null, swarmPane, null);
		swarmPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		swarmPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		SwarmResultTable = new JTable(swarmData, columns);
		swarmPane.setViewportView(SwarmResultTable);
		
		geneticPane = new JScrollPane();
		tabbedPane.addTab("Genetic", null, geneticPane, null);
		geneticPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		geneticPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GeneticResultTable = new JTable(geneticData, columns);
		geneticPane.setViewportView(GeneticResultTable);
		
		List<Integer> range = IntStream.rangeClosed(1, Parameters.numberOfRuns).boxed().collect(Collectors.toList());
		comboBox = new JComboBox<Object>(range.toArray());
		comboBox.setBounds(45, 1, 450, 20);
		comboBox.setEditable(true);
		add(comboBox);
		comboBox.addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						updateTable();
					}
				});
		
		JLabel lblRun = new JLabel("Run: ");
		lblRun.setLabelFor(comboBox);
		lblRun.setHorizontalAlignment(SwingConstants.CENTER);
		lblRun.setBounds(10, 5, 30, 14);
		add(lblRun);
		
		JPanel Graph = new JPanel();
		Graph.setBounds(0, 220, 500, 280);
		add(Graph);
	}
}
