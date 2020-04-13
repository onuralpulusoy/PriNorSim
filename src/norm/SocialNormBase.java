package norm;

import java.util.ArrayList;
import content.*;

public class SocialNormBase {
	int majorRelationship = 0;
	ArrayList<Integer> conType = new ArrayList<Integer>();
	int action = -1;
	int kClass = -1;
	String id = "-1";
	Boolean isSNorm = false;
	ArrayList<Sentiment> senType = new ArrayList<Sentiment>();
	
	public SocialNormBase(int majorRelationship, ArrayList<Integer> conType, int action, String id) {
		this.majorRelationship = majorRelationship;
		this.conType = conType;
		this.action = action;
		this.id = id;
	}
	
	public SocialNormBase(ArrayList<Integer> conType, ArrayList<Sentiment> senType, int action, String id) {
		this.majorRelationship = 0;
		this.conType = conType;
		this.senType = senType;
		this.action = action;
		this.id = id;
	}
	
	public void setNormTrue() {
		this.isSNorm = true;
	}
	
	public void setNormFalse() {
		this.isSNorm = false;
	}
	
	public void setKClass(int kClass) {
		this.kClass = kClass;
	}
	
	public int getKClass() {
		return this.kClass;
	}
	
	public int getAction() {
		return this.action;
	}
	
	public String getID() {
		return this.id;
	}
	
	public ArrayList<Integer> getConType() {
		return this.conType;
	}
	
	public void updateConType (ArrayList<Integer> conType){
		this.conType = conType;
	}
	
	public ArrayList<Sentiment> getSentiments() {
		return this.senType;
	}
}
