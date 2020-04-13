package agent;

import java.util.ArrayList;
import norm.Norm;
import norm.pNorm;

public class Agent {
	
	int id;
	String name;
	ArrayList<AgentRelation> relations = new ArrayList<AgentRelation>();
	ArrayList<Norm> norms = new ArrayList<Norm>();
	ArrayList<pNorm> pNorms = new ArrayList<pNorm>();
	int gender = 0;
	int education = 0;
	
	
	
	public Agent(int id, String name, ArrayList<Norm> norms) {
		this.id = id;
		this.name = name;
		this.norms = norms;
	}
	
	public Agent(int id, int gender, int education) {
		this.id = id;
		this.gender = gender;
		this.education = education;
	}
	
	public String getAgentName() {
		return this.name;
	}
	
	public int getAgentID() {
		return this.id;
	}
	
	public int getGender() {
		return this.gender;
	}
	
	public int getEducation() {
		return this.education;
	}
	
	public void addNorm(Norm norm) {
		norms.add(norm);
	}
	
	public ArrayList<Norm> getNorms() {
		return this.norms;
	}
	
	public void addpNorm(pNorm pnorm) {
		this.pNorms.add(pnorm);
	}
	
	public ArrayList<pNorm> getpNorms() {
		return this.pNorms;
	}
	
}
