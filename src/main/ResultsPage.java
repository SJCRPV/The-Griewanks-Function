package main;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
	private ChartPanel PerRunGraph;
	private XYDataset dataset;
	private XYSeriesCollection seriesCollection;
	private JFreeChart chart;
	private Object[][][] fullSwarmData;
	private Object[][][] fullGeneticData;
	private Object[][] swarmData;
	private Object[][] geneticData;
	private ChartPanel AverageGraph;

	private void setGraphOptions(JFreeChart chartToChange)
	{
		
		XYPlot plot = chartToChange.getXYPlot();
		NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		xAxis.setRange(1, Parameters.maxNumOfGenerations);
		xAxis.setTickUnit(new NumberTickUnit(Parameters.maxNumOfGenerations/10));
		yAxis.setRange(0, 1);
		yAxis.setTickUnit(new NumberTickUnit(0.1));
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.BLUE);
		renderer.setSeriesPaint(1, Color.RED);
		
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.WHITE);
		
		plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.PINK);
        
	}
	
	private XYDataset createDataset(boolean wantAverage) 
	{
		XYSeries swarmSeries;
		XYSeries geneticSeries;
		int fitnessTypeIndex = wantAverage ? 2 : 1;
		if(seriesCollection == null || seriesCollection.getSeriesCount() == 0)
		{
			swarmSeries = new XYSeries("Swarm Fitness");
			geneticSeries = new XYSeries("Genetic Fitness");
		}
		else
		{
			swarmSeries = seriesCollection.getSeries("Swarm Fitness");
			geneticSeries = seriesCollection.getSeries("Genetic Fitness");
			seriesCollection.removeSeries(swarmSeries);
			seriesCollection.removeSeries(geneticSeries);
			swarmSeries.clear();
			geneticSeries.clear();
		}
		
		for(int i = 0; i < swarmData.length && i < geneticData.length; i++)
		{
			Double swarmGenFitness = Double.parseDouble(swarmData[i][fitnessTypeIndex].toString());
			Double geneticGenFitness = Double.parseDouble(geneticData[i][fitnessTypeIndex].toString());
			
			swarmSeries.add(i + 1, swarmGenFitness);
			geneticSeries.add(i + 1, geneticGenFitness);
		}
		
		seriesCollection.addSeries(swarmSeries);
		seriesCollection.addSeries(geneticSeries);
		
		return seriesCollection;
	}
	
	private void updateGraph()
	{
		dataset = createDataset(false);
		chart = ChartFactory.createXYLineChart("Algorithm Comparison", "Generation", "Fitness", dataset);
		PerRunGraph = new ChartPanel(chart);
		PerRunGraph.repaint();
	}
	
	private void createGraph(String graphTitle, boolean wantAverage)
	{
		seriesCollection = new XYSeriesCollection();
		dataset = createDataset(wantAverage);
		chart = ChartFactory.createXYLineChart(graphTitle, "Generation", "Fitness", dataset);
        setGraphOptions(chart);
	}
	
	private void updateTable()
	{
		int comboBoxValue;			
		int selectedTab = tabbedPane.getSelectedIndex();

		try
		{
			comboBoxValue = (int) comboBox.getSelectedItem();
		}
		catch(ClassCastException e)
		{
			return;
		}
		
		if(comboBoxValue <= 1)
		{
			comboBoxValue = 1;
			comboBox.setSelectedItem(1);
		}
		if(comboBoxValue >= Parameters.numberOfRuns)
		{
			comboBoxValue = Parameters.numberOfRuns;
			comboBox.setSelectedItem(Parameters.numberOfRuns);
		}
		
		swarmData = fullSwarmData[comboBoxValue - 1];
		SwarmResultTable = new JTable(swarmData, columns);
		geneticData = fullGeneticData[comboBoxValue - 1];
		GeneticResultTable = new JTable(geneticData, columns);

		// If Swarm is selected
		if(selectedTab == 0)
		{
			swarmPane.setViewportView(SwarmResultTable);
			swarmPane.repaint();
			return;
		}
		geneticPane.setViewportView(GeneticResultTable);
		geneticPane.repaint();
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
		tabbedPane.setBounds(0, 20, 500, 200);
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
						updateGraph();
					}
				});
		
		JLabel lblRun = new JLabel("Run: ");
		lblRun.setLabelFor(comboBox);
		lblRun.setHorizontalAlignment(SwingConstants.CENTER);
		lblRun.setBounds(10, 5, 30, 14);
		add(lblRun);
		
		createGraph("ABF Algorithm Comparison", true);
		AverageGraph = new ChartPanel(chart);
		AverageGraph.setBounds(0, 500, 500, 280);
		add(AverageGraph);
		
		createGraph("Algorithm Comparison", false);
		PerRunGraph = new ChartPanel(chart);
		PerRunGraph.setBounds(0, 220, 500, 280);
		add(PerRunGraph);
	}
}
