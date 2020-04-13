package content;

import java.util.ArrayList;

public class Sentiment {
	
	String sentimentType;
	int belonginess;
	
	public Sentiment(String sentimentType,int belonginess) {
		this.sentimentType = sentimentType;
		this.belonginess = belonginess;
	}
	
	public String getSentimentType() {
		return this.sentimentType;
	}
	
	public int getBelonginess() {
		return this.belonginess;
	}

}
