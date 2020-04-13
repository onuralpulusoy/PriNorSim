package norm;

public class Norm {
	int relType;
	String conType;
	//-1:share 0:indifferent 1:share
	int behavior;
	
	public Norm(int relType,String conType, int behavior) {
		this.relType = relType;
		this.conType = conType;
		this.behavior = behavior;
	}
	
	public int getRelType() {
		return this.relType;
	}
	
	public String getConType() {
		return this.conType;
	}
	
	public int getBehavior() {
		return this.behavior;
	}
	

}
