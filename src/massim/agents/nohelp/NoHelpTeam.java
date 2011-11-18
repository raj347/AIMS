package massim.agents.nohelp;

import massim.RowCol;
import massim.Team;

/**
 * 
 * 
 * @author Omid Alemi
 * @version 2.0 2011/10/31
 */
public class NoHelpTeam extends Team {	
	
	/**
	 * The default constructor
	 */
	public NoHelpTeam() {
		super();		
			
		NoHelpAgent[] selfishAgents = new NoHelpAgent[Team.teamSize];
		
		for(int i=0;i<Team.teamSize;i++)
			selfishAgents[i] = new NoHelpAgent(i,commMedium());
		
		setAgents(selfishAgents);
	}	
			
}

