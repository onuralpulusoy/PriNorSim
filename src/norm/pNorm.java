package norm;

import java.util.ArrayList;

public class pNorm {

	int action;
	ArrayList<Integer> coOwners = new ArrayList<Integer>();
	String highestConTypeIndex;
	
	
	public pNorm(int action,ArrayList<Integer> coOwners,String highestConTypeIndex ) {
		this.action = action;
		this.coOwners = coOwners;
		this.highestConTypeIndex = highestConTypeIndex;
	}
	
	public int getAction() {
		return this.action;
	}
	
	public ArrayList<Integer> getcoOwners() {
		return this.coOwners;
	}
	
	public String gethighestConTypeIndex() {
		return this.highestConTypeIndex;
	}
}
