package norm;

public class SocialNormClasses {
	int classId;
	int cT1mean;
	int cT2mean;
	int cT3mean;
	int cT4mean;
	float percentage;
	int size;
	int fDist = 0;
	
	public SocialNormClasses() {
		
	}
	
	public void setclassId(int classId) {
		this.classId = classId;
	}
	
	public void setcT1mean(int cT1mean) {
		this.cT1mean = cT1mean;
	}
	public void setcT2mean(int cT2mean) {
		this.cT2mean = cT2mean;
	}
	public void setcT3mean(int cT3mean) {
		this.cT3mean = cT3mean;
	}
	public void setcT4mean(int cT4mean) {
		this.cT4mean = cT4mean;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setFDist(int fDist) {
		this.fDist = fDist;
	}
	
	public int getFDist() {
		return this.fDist;
	}
	
	
	public int getclassId() {
		return this.classId;
	}
	
	public int getcT1mean() {
		return this.cT1mean;
	}
	public int getcT2mean() {
		return this.cT2mean;
	}
	public int getcT3mean() {
		return this.cT3mean;
	}
	public int getcT4mean() {
		return this.cT4mean;
	}
	public float getPercentage() {
		return this.percentage;
	}
	
	public int getSize() {
		return this.size;
	}
}
