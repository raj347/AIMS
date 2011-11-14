package experiments.ex1;

import java.text.DecimalFormat;

import massim.Agent;
import massim.SimulationEngine;
import massim.Team;
import massim.agents.advancedactionmap.AdvActionMAPAgent;
import massim.agents.advancedactionmap.AdvActionMapTeam;
import massim.agents.basicactionmap.BasicActionMAPAgent;
import massim.agents.basicactionmap.BasicActionMAPTeam;
import massim.agents.nohelp.NoHelpTeam;

/**
 * This is an experiment in the regular branch v1.2
 *   
 * @author Omid Almie
 *
 */
public class Experiment2 {

	public static void main(String[] args) {
	int numberOfRuns = 500;
		
	SimulationEngine.colorRange = 
		new int[] {0, 1, 2, 3, 4, 5};
	SimulationEngine.numOfColors =  
		SimulationEngine.colorRange.length;
	SimulationEngine.actionCostsRange = 
		new int[] {10, 40, 70, 100, 300, 400, 450,  500};	
	
	/* Create the teams involved in the simulation */
		Team.teamSize = 8;
		Team[] teams = new Team[3];		
		teams[0] = new BasicActionMAPTeam();
		teams[1] = new AdvActionMapTeam();		
		teams[2] = new NoHelpTeam();
			
		
		
		/* Create the SimulationEngine */
		SimulationEngine se = new SimulationEngine(teams);
		
		/* The experiments loop */
		for (int exp=0;exp<11;exp++)
		{
			/* Set the experiment-wide parameters: */
			/* teams-wide, SimulationEngine, etc params */			
			
			Team.initResCoef = 150;
			Team.unicastCost = 7;
			Team.broadcastCost = Team.unicastCost * (Team.teamSize-1);
			Agent.calculationCost = 1;
			Agent.helpOverhead = 50;			
			Agent.cellReward = 100;
			Agent.achievementReward = 20000;
			AdvActionMAPAgent.requestThreshold = 299;
			AdvActionMAPAgent.WLL = 0.8;
			AdvActionMAPAgent.lowCostThreshold = 100;
			BasicActionMAPAgent.requestThreshold = 299;
			
			/* vary the disturbance: */
			SimulationEngine.disturbanceLevel = 0.1 * exp;;  
			
			/* Initialize and run the experiment */
			se.initializeExperiment(numberOfRuns);			
			int[] teamScores = se.runExperiment();
			
			/* Print the results */
			DecimalFormat df = new DecimalFormat("0.0");
			System.out.print(exp+","+ 
						df.format(SimulationEngine.disturbanceLevel));
			for (int i=0;i<teams.length;i++)
				System.out.print(","+ 
						teamScores[i]);
			System.out.println("");
//			(new Scanner(System.in)).nextLine();

		}
	}

}