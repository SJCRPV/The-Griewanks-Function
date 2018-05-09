package main;

import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;


public class UserInterface 
{
	private JTextField maxNumOfGenerations;
	private JTextField groupSize;
	private JTextField structureLength;
	private JTextField mutationRate;
	private JTextField crossoverRate;
	private JTextField tournamentSize;
	private JTextField localInertia;
	private JTextField innateInertia;
	private JTextField globalInertia;
	
	private ArrayList<JTextField> textFields;
	private ArrayList<String> textFieldsDefaultVals;
	private JTextField textField;
	
	private void setParameters()
	{
		Parameters.maxNumOfGenerations = Integer.parseInt(maxNumOfGenerations.getText());
		Parameters.groupSize = Integer.parseInt(groupSize.getText());
		Parameters.structureLength = Integer.parseInt(structureLength.getText());
		Parameters.mutationRate = Double.parseDouble(mutationRate.getText());
		Parameters.crossoverRate = Double.parseDouble(crossoverRate.getText());
		Parameters.tournamentSize = Integer.parseInt(tournamentSize.getText());
		Parameters.localInertia = Double.parseDouble(localInertia.getText());
		Parameters.innateInertia = Double.parseDouble(innateInertia.getText());
		Parameters.globalIntertia = Double.parseDouble(globalInertia.getText());
	}
	
	private void runAlgorithms()
	{
		setParameters();
	}
	
	private void resetValues()
	{
		for(int i = 0; i < textFields.size(); i++)
		{
			textFields.get(i).setText(textFieldsDefaultVals.get(i));
		}
	}

	public UserInterface() 
	{
	  textFields = new ArrayList<JTextField>();
	  textFieldsDefaultVals = new ArrayList<String>();
	  
	  JFrame f = new JFrame("A JFrame");
	  f.getContentPane().setEnabled(false);
	  f.getContentPane().setBackground(Color.LIGHT_GRAY);
	  f.setSize(500, 500);
	  f.setLocation(300,300);
	  f.getContentPane().setLayout(null);
	  
	  JPanel Common = new JPanel();
	  Common.setBackground(Color.WHITE);
	  Common.setBounds(0, 0, 484, 120);
	  f.getContentPane().add(Common);
	  Common.setLayout(null);
	  
	  JLabel lblCommonParameters = new JLabel("Common Parameters");
	  lblCommonParameters.setHorizontalAlignment(SwingConstants.CENTER);
	  lblCommonParameters.setBounds(10, 10, 464, 14);
	  Common.add(lblCommonParameters);
	  
	  maxNumOfGenerations = new JTextField();
	  maxNumOfGenerations.setBounds(30, 75, 100, 20);
	  Common.add(maxNumOfGenerations);
	  maxNumOfGenerations.setHorizontalAlignment(SwingConstants.CENTER);
	  maxNumOfGenerations.setToolTipText("The number of cycles each algorithm will go through before stopping");
	  maxNumOfGenerations.setText("100");
	  maxNumOfGenerations.setColumns(10);
	  textFields.add(maxNumOfGenerations);
	  textFieldsDefaultVals.add(maxNumOfGenerations.getText());
	  
	  JLabel lblNumberOfGenerations = new JLabel("Number of Generations");
	  lblNumberOfGenerations.setLabelFor(maxNumOfGenerations);
	  lblNumberOfGenerations.setBounds(25, 50, 111, 14);
	  Common.add(lblNumberOfGenerations);
	  lblNumberOfGenerations.setHorizontalAlignment(SwingConstants.CENTER);
	  
	  groupSize = new JTextField();
	  groupSize.setBounds(185, 75, 100, 20);
	  Common.add(groupSize);
	  groupSize.setHorizontalAlignment(SwingConstants.CENTER);
	  groupSize.setToolTipText("The number of objects in a group");
	  groupSize.setText("10");
	  groupSize.setColumns(10);
	  textFields.add(groupSize);
	  textFieldsDefaultVals.add(groupSize.getText());
	  
	  JLabel lblGroupSize = new JLabel("Group Size");
	  lblGroupSize.setLabelFor(groupSize);
	  lblGroupSize.setBounds(185, 50, 100, 14);
	  Common.add(lblGroupSize);
	  lblGroupSize.setHorizontalAlignment(SwingConstants.CENTER);
	  
	  structureLength = new JTextField();
	  structureLength.setBounds(340, 75, 100, 20);
	  Common.add(structureLength);
	  structureLength.setHorizontalAlignment(SwingConstants.CENTER);
	  structureLength.setToolTipText("The number of elements in each object");
	  structureLength.setText("10");
	  structureLength.setColumns(10);
	  textFields.add(structureLength);
	  textFieldsDefaultVals.add(structureLength.getText());
	  
	  JLabel lblStructureLength = new JLabel("Structure Length");
	  lblStructureLength.setLabelFor(structureLength);
	  lblStructureLength.setBounds(340, 50, 100, 14);
	  Common.add(lblStructureLength);
	  lblStructureLength.setHorizontalAlignment(SwingConstants.CENTER);
	  
	  JPanel Genetic = new JPanel();
	  Genetic.setBackground(Color.WHITE);
	  Genetic.setBounds(0, 120, 484, 120);
	  f.getContentPane().add(Genetic);
	  Genetic.setLayout(null);
	  
	  JLabel lblGenAlgParams = new JLabel("Genetic Algorithm Parameters");
	  lblGenAlgParams.setHorizontalAlignment(SwingConstants.CENTER);
	  lblGenAlgParams.setBounds(10, 10, 464, 14);
	  Genetic.add(lblGenAlgParams);
	  
	  mutationRate = new JTextField();
	  mutationRate.setToolTipText("The probability of an object mutating (between 0 and 1)");
	  mutationRate.setText("0.1");
	  mutationRate.setHorizontalAlignment(SwingConstants.CENTER);
	  mutationRate.setColumns(10);
	  mutationRate.setBounds(30, 75, 100, 20);
	  Genetic.add(mutationRate);
	  textFields.add(mutationRate);
	  textFieldsDefaultVals.add(mutationRate.getText());
	  
	  JLabel lblMutationrate = new JLabel("Mutation Rate");
	  lblMutationrate.setLabelFor(mutationRate);
	  lblMutationrate.setHorizontalAlignment(SwingConstants.CENTER);
	  lblMutationrate.setBounds(30, 50, 100, 14);
	  Genetic.add(lblMutationrate);
	  
	  tournamentSize = new JTextField();
	  tournamentSize.setToolTipText("The number of genes thrown into a selection pool");
	  tournamentSize.setText("5");
	  tournamentSize.setHorizontalAlignment(SwingConstants.CENTER);
	  tournamentSize.setColumns(10);
	  tournamentSize.setBounds(185, 75, 100, 20);
	  Genetic.add(tournamentSize);
	  textFields.add(tournamentSize);
	  textFieldsDefaultVals.add(tournamentSize.getText());
	  
	  JLabel lblTournamentSize = new JLabel("Tournament Size");
	  lblTournamentSize.setLabelFor(tournamentSize);
	  lblTournamentSize.setHorizontalAlignment(SwingConstants.CENTER);
	  lblTournamentSize.setBounds(185, 50, 100, 14);
	  Genetic.add(lblTournamentSize);
	  
	  crossoverRate = new JTextField();
	  crossoverRate.setToolTipText("The probability of a crossover between two objects existing (between 0 and 1)");
	  crossoverRate.setText("10");
	  crossoverRate.setHorizontalAlignment(SwingConstants.CENTER);
	  crossoverRate.setColumns(10);
	  crossoverRate.setBounds(340, 75, 100, 20);
	  Genetic.add(crossoverRate);
	  textFields.add(crossoverRate);
	  textFieldsDefaultVals.add(crossoverRate.getText());
	  
	  JLabel lblCrossoverRate = new JLabel("Crossover Rate");
	  lblCrossoverRate.setLabelFor(crossoverRate);
	  lblCrossoverRate.setHorizontalAlignment(SwingConstants.CENTER);
	  lblCrossoverRate.setBounds(340, 50, 100, 14);
	  Genetic.add(lblCrossoverRate);
	  
	  JPanel Swarm = new JPanel();
	  Swarm.setBackground(Color.WHITE);
	  Swarm.setLayout(null);
	  Swarm.setBounds(0, 240, 484, 120);
	  f.getContentPane().add(Swarm);
	  
	  JLabel lblPartSwarmOptParams = new JLabel("Particle Swarm Optimisation Parameters");
	  lblPartSwarmOptParams.setHorizontalAlignment(SwingConstants.CENTER);
	  lblPartSwarmOptParams.setBounds(10, 10, 464, 14);
	  Swarm.add(lblPartSwarmOptParams);
	  
	  localInertia = new JTextField();
	  localInertia.setToolTipText("How much pull should the local best have? (1 is the base value)");
	  localInertia.setText("0.25");
	  localInertia.setHorizontalAlignment(SwingConstants.CENTER);
	  localInertia.setColumns(10);
	  localInertia.setBounds(30, 75, 100, 20);
	  Swarm.add(localInertia);
	  textFields.add(localInertia);
	  textFieldsDefaultVals.add(localInertia.getText());
	  
	  JLabel lblLocalInertia = new JLabel("Local Inertia");
	  lblLocalInertia.setLabelFor(localInertia);
	  lblLocalInertia.setHorizontalAlignment(SwingConstants.CENTER);
	  lblLocalInertia.setBounds(30, 50, 100, 14);
	  Swarm.add(lblLocalInertia);
	  
	  globalInertia = new JTextField();
	  globalInertia.setToolTipText("How much pull should the global best have? (1 is the base value)");
	  globalInertia.setText("2");
	  globalInertia.setHorizontalAlignment(SwingConstants.CENTER);
	  globalInertia.setColumns(10);
	  globalInertia.setBounds(185, 75, 100, 20);
	  Swarm.add(globalInertia);
	  textFields.add(globalInertia);
	  textFieldsDefaultVals.add(globalInertia.getText());
	  
	  JLabel lblGlobalInertia = new JLabel("Global Inertia");
	  lblGlobalInertia.setLabelFor(globalInertia);
	  lblGlobalInertia.setHorizontalAlignment(SwingConstants.CENTER);
	  lblGlobalInertia.setBounds(185, 50, 100, 14);
	  Swarm.add(lblGlobalInertia);
	  
	  innateInertia = new JTextField();
	  innateInertia.setToolTipText("How much pull should the base result have? (1 is the base value)");
	  innateInertia.setText("0.125");
	  innateInertia.setHorizontalAlignment(SwingConstants.CENTER);
	  innateInertia.setColumns(10);
	  innateInertia.setBounds(340, 75, 100, 20);
	  Swarm.add(innateInertia);
	  textFields.add(innateInertia);
	  textFieldsDefaultVals.add(innateInertia.getText());
	  
	  JLabel lblInnateInertia = new JLabel("Innate Inertia");
	  lblInnateInertia.setLabelFor(innateInertia);
	  lblInnateInertia.setHorizontalAlignment(SwingConstants.CENTER);
	  lblInnateInertia.setBounds(340, 50, 100, 14);
	  Swarm.add(lblInnateInertia);
	  
	  JPanel Execution = new JPanel();
	  Execution.setBackground(Color.WHITE);
	  Execution.setBounds(0, 360, 484, 100);
	  f.getContentPane().add(Execution);
	  Execution.setLayout(null);
	  
	  JButton btnRun = new JButton("Run!");
	  btnRun.setBounds(10, 66, 100, 23);
	  Execution.add(btnRun);
	  btnRun.setToolTipText("Run the program with the parameters you selected");
	  btnRun.addActionListener(
			  new ActionListener() 
			  {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					runAlgorithms();
				}
			  });
	  
	  JButton btnReturnToDefaults = new JButton("Return to Defaults");
	  btnReturnToDefaults.setBounds(344, 66, 130, 23);
	  Execution.add(btnReturnToDefaults);
	  btnReturnToDefaults.setToolTipText("Return all values to their defaults");
	  
	  textField = new JTextField();
	  textField.setToolTipText("How many times will the algorithms run?");
	  textField.setHorizontalAlignment(SwingConstants.CENTER);
	  textField.setText("30");
	  textField.setBounds(185, 30, 100, 20);
	  Execution.add(textField);
	  textField.setColumns(10);
	  
	  JLabel lblNumberOfRuns = new JLabel("Number of runs");
	  lblNumberOfRuns.setHorizontalAlignment(SwingConstants.CENTER);
	  lblNumberOfRuns.setBounds(185, 11, 100, 14);
	  Execution.add(lblNumberOfRuns);
	  f.setVisible(true);
	  btnReturnToDefaults.addActionListener(
			  new ActionListener()
			  {
				@Override
				public void actionPerformed(ActionEvent e) {
					resetValues();
				}
			  });
  
    }
}