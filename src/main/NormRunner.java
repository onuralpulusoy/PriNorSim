package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import agent.Agent;
import content.Content;
import content.Sentiment;
import norm.Norm;
import norm.SocialNormBase;
import norm.SocialNormClasses;
import norm.pNorm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class NormRunner {
	public static void main(String[] args) throws IOException {
		
		ArrayList<SocialNormBase> sNormBase = new 	ArrayList<SocialNormBase>();
		ArrayList<SocialNormClasses> sClasses = new ArrayList<SocialNormClasses>();
		ArrayList<Agent> agents = new ArrayList<Agent>();
		//agents = initAgents(agents);
		agents = initAgentsSnap(agents);
		ArrayList<Content> contents = new ArrayList<Content>();
		//contents = initContents(contents);
		contents = initContentsSnapPicalert(contents);
		//agents = initAgentMnormsSnapPicalert(agents);
		agents = initAgentMnormsPicalert(agents,contents);
		for(int i=0; i<agents.size();i++) {
			//System.out.println(agents.get(i).getAgentID() + "-" + agents.get(i).getGender() + "-" + agents.get(i).getEducation());
		}
		for(int i=0; i<1000;i++) {
			//System.out.println(contents.get(i).getID() + "-" + contents.get(i).getSentiments().get(0).getSentimentType() + "-" + contents.get(i).getSentiments().get(1).getSentimentType() + "-" + contents.get(i).getSentiments().get(2).getSentimentType() + "-" + contents.get(i).getSentiments().get(3).getSentimentType());
			for(int j=0; j<contents.get(i).getCoowners().size();j++) {
			//System.out.println(contents.get(i).getCoowners().get(j)+"-");
			}
		}
		
		for(Agent agent:agents) {
			//System.out.println("---"+agent.getAgentID()+"---");
			for(Norm norm:agent.getNorms()) {
				//System.out.println(norm.getConType()+ "-"+norm.getBehavior());
			}
		}
		//doCombinations();
		//orderEdges();
		
		//runWithoutNorms(agents,contents,sNormBase,sClasses);
		//runWithNorms(agents,contents,sNormBase,sClasses);
		runWithNormsNew(agents,contents,sNormBase,sClasses);
		
	}
	
	public static class Edge {
		int parent;
		int current;
		ArrayList<Integer> doneList;
		public Edge(int first, int current) {
			doneList = new ArrayList<Integer>();
			this.parent = first;
			this.current = current;
		}
	}
	
	public static void orderEdges()throws IOException{
		
		FileWriter fw = new FileWriter("D:\\ordered2.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw);
	    
	    for(int i=1913;i<2660;i++) {
	    	for(int j =i+1;j<2661;j++) {
	    		File file = new File("D:\\1912.edges"); 
	    		BufferedReader br = new BufferedReader(new FileReader(file));
	    		String st; 
	    	    while ((st = br.readLine()) != null) {

	    	    	ArrayList<String> list = new ArrayList<String>(Arrays.asList(st.split(" ")));
	    	    	if(list.get(0).equals(String.valueOf(i)) && list.get(1).equals(String.valueOf(j))) {
	    	    		out.println(st);
	    	    		System.out.println(st);
	    	    	}
	    	    }
	    	}
	    }
	    out.close();
	    
	    
		
	}
	
	public static void testus(ArrayList<Edge> edges)throws IOException{
		ArrayList<ArrayList<Edge>> circlea = new ArrayList<ArrayList<Edge>>();
		ArrayList<Integer> doneFG = new ArrayList<Integer>();
		int counter = 0;
		for(Edge edge:edges) {
			if(counter == 0) {
			counter++;
			ArrayList<Edge> circles = new ArrayList<Edge>();
			Edge tempEdge = new Edge(-1,edge.parent);
			circles.add(tempEdge);
			int getTracker = 0;
			Boolean notentered = true;
			if(!doneFG.contains(edge.parent)) {
				doneFG.add(edge.parent);
			while(circles.get(getTracker).parent > 0 || notentered) {
				notentered = false;
				//System.out.println(circles.get(getTracker).current);
				Boolean changeMade=false;
				//System.out.println("hersse");
				for(int j=0; j<edges.size();j++) {
					if(edges.get(j).parent == circles.get(getTracker).current && !circles.get(getTracker).doneList.contains(edges.get(j).current)) {
						Boolean allParents = true;
						for(int k=0;k<circles.size();k++) {
						Boolean match = false;
						for(Edge dege:edges) {
							if (dege.parent == circles.get(k).current && edges.get(j).current == dege.current) {
								match = true;
							}
						}
						if(!match) {
							allParents = false;
						}
						}
						
						if(allParents) {
						circles.get(getTracker).doneList.add(edges.get(j).current);
						Edge tempEdge2 = new Edge(circles.get(getTracker).current,edges.get(j).current);
						circles.add(tempEdge2);
						getTracker++;
						changeMade=true;
						j = edges.size();
						//System.in.read();
						for(Edge edgess : circles) {
							//System.out.print(edgess.current+"-");
						}
						}
					}
				}
				if(!changeMade && getTracker >0) {
					getTracker--;
					circlea.add(circles);
					
					FileWriter fw = new FileWriter("D:\\testino2.txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw);
				    for(Edge edgezz : circles) {
						out.print(edgezz.current+",");
					}
				    out.println();
				    out.close();
				    
					circles.remove(getTracker+1);
				}	

			}
		}
			}
		}
		
		for(ArrayList<Edge> circlez:circlea) {
			for(Edge edge : circlez) {
				System.out.print(edge.current+"-");
			}
			System.out.println("");
		}
	}
	
	public static void doCombinations() throws IOException{

		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i = 1913;i<2661;i++) {
			File file = new File("D:\\ordered2.txt"); 
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st; 
    	    while ((st = br.readLine()) != null) {
    	    	ArrayList<String> list = new ArrayList<String>(Arrays.asList(st.split(" ")));
    	    	if(list.get(0).equals(String.valueOf(i)) && i< Integer.valueOf(list.get(1))) {
    	    		Edge edge = new Edge(i,Integer.valueOf(list.get(1)));
    	    		edges.add(edge);

    	    	}
    	    }
		}
		for(Edge edge: edges) {
			System.out.println(edge.parent + "-"+ edge.current);
		}
		System.out.println("---------------------");

		testus(edges);
		while(edges.size()>0) {
		edges.remove(0);
		System.out.println(edges.size());
		testus(edges);
		}
		//FileWriter fw = new FileWriter("D:\\SedgeTest.txt", true);
	    //BufferedWriter bw = new BufferedWriter(fw);
	    //PrintWriter out = new PrintWriter(bw);
	    //out.println(list2.get(0)+","+list2.get(1));
	    //out.close();
		
	}
	
	
	
	public static ArrayList<Agent> initAgentsSnap(ArrayList<Agent> agents) throws IOException{
		File file = new File("D:\\SnapNetwork\\facebook.tar\\facebook\\0.feat"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		
		String st; 
	    while ((st = br.readLine()) != null) {
	    	ArrayList<String> list = new ArrayList<String>(Arrays.asList(st.split(" ")));
	    	
	    	int gender = 0;
	    	int education = 0;
	    	
	    	if (list.get(78).equals("1"))
	    		gender = 1;
	    	if (list.get(79).equals("1"))
	    		gender = 2;
	    	
	    	if (list.get(56).equals("1"))
	    		education = 1;
	    	else if (list.get(55).equals("1"))
	    		education = 2;
	    	else if (list.get(54).equals("1"))
	    		education = 3;
	    			
	    	
	    	Agent agent = new Agent(Integer.valueOf(list.get(0)),gender,education);
			agents.add(agent);
	    	
	    }
		br.close();
		return agents;
	}
	
	public static ArrayList<Content> initContentsSnapPicalert(ArrayList<Content> contents) throws IOException{
		
		File file2 = new File("D:\\testino.txt"); 
		BufferedReader br2 = new BufferedReader(new FileReader(file2)); 
		ArrayList<String> str2 = new ArrayList<String>();
		String st2;
	    while ((st2 = br2.readLine()) != null){
	    	str2.add(st2);
	    }
	    br2.close();
		
		File file = new File("D:\\PrivacyWork\\Results.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st; 
	    while ((st = br.readLine()) != null) {
	    	ArrayList<String> list = new ArrayList<String>(Arrays.asList(st.split(",")));
	    	

		    
		    int randoCoOwn = new Random().nextInt(str2.size());
		    

	    	ArrayList<Integer> coOwners = new ArrayList<Integer>();

	    	ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(str2.get(randoCoOwn).split(",")));
	    	if(list2.size()>10) {
	    		for(int i=1;i<11;i++) {
	    			if(!list2.get(i).equals(""))
	    			coOwners.add(Integer.valueOf(list2.get(i)));
	    		}
	    	}
	    	else {
	    		for(int i=1;i<list2.size();i++) {
	    			coOwners.add(Integer.valueOf(list2.get(i)));
	    		}
	    	}
	    	
	    	Content content = new Content(list.get(0),coOwners);
	    	int belonginess = 100;
	    	//belonginess = new Random().nextInt(50)+50;
	    	Sentiment sentiment = new Sentiment(list.get(2),belonginess);
			content.addSentiment(sentiment);
	    	//belonginess = new Random().nextInt(50)+50;
			Sentiment sentiment2 = new Sentiment(list.get(3),belonginess);
			content.addSentiment(sentiment2);
	    	//belonginess = new Random().nextInt(50)+50;
			Sentiment sentiment3 = new Sentiment(list.get(4),belonginess);
			content.addSentiment(sentiment3);
	    	//belonginess = new Random().nextInt(50)+50;
			Sentiment sentiment4 = new Sentiment(list.get(5),belonginess);
			content.addSentiment(sentiment4);
			
			contents.add(content);
			
	    }
	    br.close();
		return contents;
	}
	
	public static ArrayList<Agent> initAgentMnormsPicalert(ArrayList<Agent> agents,ArrayList<Content> contents) throws IOException{
		
		
		/*The Part to Get Privacy Outputs From cleaned Picalert csv
		File file = new File("D:\\PrivacyWork\\cleaned2.csv"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st;
		while ((st = br.readLine()) != null) {
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(st.split("	")));
			for (int i=20000;i<25000; i++){
				if(contents.get(i).getID().equals(list.get(0))) {
					File file2 = new File("D:\\PrivacyWork\\agentMatch.csv"); 
					BufferedReader br2 = new BufferedReader(new FileReader(file2)); 
					String st2;
					while ((st2 = br2.readLine()) != null) {
						ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(st2.split(",")));
						if(list.get(1).equals(list2.get(1)) && contents.get(i).getCoowners().contains(Integer.valueOf(list2.get(0)))) {
							FileWriter fw = new FileWriter("D:\\POTPUTN1T10.txt", true);
						    BufferedWriter bw = new BufferedWriter(fw);
						    PrintWriter out = new PrintWriter(bw);
						    if(list.get(3).equals("private")) {
						    	out.println(list2.get(0) +","+contents.get(i).getSentiments().get(0).getSentimentType()+",0");
						    	out.println(list2.get(0) +","+contents.get(i).getSentiments().get(1).getSentimentType()+",0");
						    	out.println(list2.get(0) +","+contents.get(i).getSentiments().get(2).getSentimentType()+",0");
						    	out.println(list2.get(0) +","+contents.get(i).getSentiments().get(3).getSentimentType()+",0");
						    }
						    if(list.get(3).equals("public")) {
						    	out.println(list2.get(0) +","+contents.get(i).getSentiments().get(0).getSentimentType()+",1");
						    	out.println(list2.get(0) +","+contents.get(i).getSentiments().get(1).getSentimentType()+",1");
						    	out.println(list2.get(0) +","+contents.get(i).getSentiments().get(2).getSentimentType()+",1");
						    	out.println(list2.get(0) +","+contents.get(i).getSentiments().get(3).getSentimentType()+",1");
						    }
						    out.close();
						}
					
					}
					br2.close();
				}
				
			}
		}
		br.close();
		*/
		
		/*This part is for summing public-private decisions
		File file = new File("D:\\POTPUTN1T10.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st;
		
		FileWriter fw = new FileWriter("D:\\publicN1T10.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw);
	    
	    FileWriter fw2 = new FileWriter("D:\\privateN1T10.txt", true);
	    BufferedWriter bw2 = new BufferedWriter(fw2);
	    PrintWriter out2 = new PrintWriter(bw2);
		

		ArrayList<String> seenDecisionsPr = new ArrayList<String>();
		ArrayList<String> seenDecisionsPu = new ArrayList<String>();
	    
		while ((st = br.readLine()) != null) {
			System.out.println(st);
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(st.split(",")));
			Boolean prExists = false;
			for(int i=0;i<seenDecisionsPr.size();i++) {
				ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(seenDecisionsPr.get(i).split(";")));
				if(list2.get(0).equals(st) && list.get(2).equals("0")) {
					prExists = true;
					seenDecisionsPr.set(i, st + ";" + String.valueOf((Integer.valueOf(list2.get(1))+1)))  ;
				}
			}

			if(!prExists && list.get(2).equals("0")){
				seenDecisionsPr.add(st+";1");
			}
			
			Boolean puExists = false;
			for(int i=0;i<seenDecisionsPu.size();i++) {
				ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(seenDecisionsPu.get(i).split(";")));
				if(list2.get(0).equals(st) && list.get(2).equals("1")) {
					puExists = true;
					seenDecisionsPu.set(i, st + ";" + String.valueOf((Integer.valueOf(list2.get(1))+1)))  ;
				}
			}

			if(!puExists && list.get(2).equals("1")){
				seenDecisionsPu.add(st+";1");
			}
		}
		
		for(String str : seenDecisionsPr) {
			out2.println(str);
		}
		
		for(String str : seenDecisionsPu) {
			out.println(str);
		}
		out.close();
		out2.close();
		//
		
		/*To check if same agent has conflicting decisions for  the same tag
		File file = new File("D:\\public2.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st;
		
		File file2 = new File("D:\\private2.txt"); 
		BufferedReader br2 = new BufferedReader(new FileReader(file2)); 
		String st2;
		
		FileWriter fw = new FileWriter("D:\\mNorms2.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw);
		
		while ((st = br.readLine()) != null) {
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(st.split(",")));
			while ((st2 = br2.readLine()) != null) {
				ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(st2.split(",")));
				if(list2.get(0).equals(list.get(0)) && list2.get(1).equals(list.get(1))) {
					ArrayList<String> list3 = new ArrayList<String>(Arrays.asList(st.split(";")));
					ArrayList<String> list4 = new ArrayList<String>(Arrays.asList(st2.split(";")));
					if(Integer.valueOf(list3.get(1))>Integer.valueOf(list4.get(1))) {
						out.println(list.get(0)+ "," + list.get(1) + ",1," + String.valueOf(Integer.valueOf(list3.get(1)) - Integer.valueOf(list4.get(1))));
					}
					
					if(Integer.valueOf(list4.get(1))>Integer.valueOf(list3.get(1))) {
						out.println(list.get(0)+ "," + list.get(1) + ",0," + String.valueOf(Integer.valueOf(list4.get(1)) - Integer.valueOf(list3.get(1))));
					}
				}
			}
		}
		out.close();
		*/
		
		//
		for (Agent agent: agents){
			
			File file = new File("D:\\publicN1T1.txt"); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String st;
			
			File file2 = new File("D:\\privateN1T1.txt"); 
			BufferedReader br2 = new BufferedReader(new FileReader(file2)); 
			String st2;
			
			
			while ((st = br.readLine()) != null) {
				ArrayList<String> list = new ArrayList<String>(Arrays.asList(st.split(",")));
				if(agent.getAgentID()==Integer.valueOf(list.get(0))) {
					Norm norm = new Norm (0,list.get(1),1);
					agent.addNorm(norm);
				}
			}
			
			while ((st2 = br2.readLine()) != null) {
				ArrayList<String> list = new ArrayList<String>(Arrays.asList(st2.split(",")));
				if(agent.getAgentID()==Integer.valueOf(list.get(0))) {
					Norm norm = new Norm (0,list.get(1),0);
					agent.addNorm(norm);
				}
			}
			
			
		}
		 //
		return agents;
	}
	
	public static ArrayList<Agent> initAgentMnormsSnapPicalert(ArrayList<Agent> agents) throws IOException{
		
		ArrayList<String> tags = new ArrayList<String>();
		File file = new File("D:\\PrivacyWork\\output1tag.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st;
		while ((st = br.readLine()) != null) {
			tags.add(st);
		}
		br.close();
		
		for (Agent agent: agents){
			ArrayList<Norm> norms = new ArrayList<Norm>();
			for (int i=0; i<1000;i++) {
				ArrayList<String> tagg = new ArrayList<String>(Arrays.asList(tags.get(i).split(",")));
				int dec = 0;
				if(i<500) {
					int rdec = new Random().nextInt(100);
					if(agent.getGender() == 1) {
						if(rdec<=100*Double.parseDouble(tagg.get(1))/(Double.parseDouble(tagg.get(2)) + Double.parseDouble(tagg.get(2)))) {
							Norm norm = new Norm (0,tagg.get(0),0);
							norms.add(norm);
						}
						else {
							Norm norm = new Norm (0,tagg.get(0),1);
							norms.add(norm);
						}
					}
					if(agent.getGender() == 2) {
						if(rdec>=100*Double.parseDouble(tagg.get(1))/(Double.parseDouble(tagg.get(2)) + Double.parseDouble(tagg.get(2)))) {
							Norm norm = new Norm (0,tagg.get(0),0);
							norms.add(norm);
						}
						else {
							Norm norm = new Norm (0,tagg.get(0),1);
							norms.add(norm);
						}
						
					}
				}
				else {
					
					int rdec = new Random().nextInt(100);

					if(agent.getEducation() == 1) {
						if(rdec>=100*Double.parseDouble(tagg.get(1))/(Double.parseDouble(tagg.get(2)) + Double.parseDouble(tagg.get(2)))) {
							Norm norm = new Norm (0,tagg.get(0),0);
							norms.add(norm);
						}
						else {
							Norm norm = new Norm (0,tagg.get(0),1);
							norms.add(norm);
						}
						
					}
					if(agent.getEducation() == 2) {
						if(rdec<=100*Double.parseDouble(tagg.get(1))/(Double.parseDouble(tagg.get(2)) + Double.parseDouble(tagg.get(2)))) {
							Norm norm = new Norm (0,tagg.get(0),0);
							norms.add(norm);
						}
						else {
							Norm norm = new Norm (0,tagg.get(0),1);
							norms.add(norm);
						}
					}
					
					if(agent.getEducation() == 3) {
						if(rdec<=90) {
							Norm norm = new Norm (0,tagg.get(0),0);
							norms.add(norm);
						}
						else {
							Norm norm = new Norm (0,tagg.get(0),1);
							norms.add(norm);
						}
						
					}
					
				}
			}
		}
		return agents;
	}
	
	public static ArrayList<Agent> initAgents(ArrayList<Agent> agents){
		
		int relType = 0;
		int randoConType = 0;
		int randoDec = 0;
		
		int randoNormSize = 0;
		
		
		for(Integer ac=0;ac<100;ac++) {
			
			randoNormSize = new Random().nextInt(5);
			ArrayList<Norm> norms = new ArrayList<Norm>();
			for(int i=0;i<randoNormSize;i++) {
				randoConType = new Random().nextInt(5);
				
				if(new Random().nextInt(100) >-1)
					randoDec = 0;
				else
					randoDec = new Random().nextInt(2);

				Boolean conTypeNormExists = false;
				
				for(int j=0;j<norms.size();j++) {
					if(norms.get(j).getConType().equals(String.valueOf(randoConType)))
							conTypeNormExists = true;
				}
				
				if(!conTypeNormExists) {
					Norm norm = new Norm (relType,String.valueOf(randoConType),randoDec);
					norms.add(norm);
				}
				
			}

			Agent agent = new Agent(ac,"a"+ac.toString(),norms);
			agents.add(agent);
		}
		
		return agents;
	}
	
	public static ArrayList<Content> initContents(ArrayList<Content> contents){

		int coOwnerSize = 0;
		int agentID = 0;
		String sentimentType = "0";
		int belonginess = 0;
		
		for(Integer cc=0;cc<10000;cc++) {
			
			coOwnerSize = new Random().nextInt(3) + 2;
			ArrayList<Integer> coOwners = new ArrayList<Integer>();
			for(Integer i=0;i<coOwnerSize;i++) {
				agentID = new Random().nextInt(100);
				if(!coOwners.contains(agentID)) {
					coOwners.add(agentID);
				}
				else {
					i--;
				}
			}
			
			Content content = new Content(String.valueOf(cc),coOwners);
			
			for(Integer i=0;i<3;i++) {
				sentimentType = Integer.toString(new Random().nextInt(10));
				belonginess = new Random().nextInt(100);
				
				Boolean sentimentExists = false;
				
				for(int j=0; j< content.getSentiments().size();j++) {
					if(content.getSentiments().get(j).getSentimentType().equals(sentimentType)) {
						sentimentExists = true;
					}
				}
				if(sentimentExists)
					i--;
				else {
					Sentiment sentiment = new Sentiment(sentimentType,belonginess);
					content.addSentiment(sentiment);
				}
			}
			contents.add(content);
		}
		return contents;
	}
	
	public static void runWithoutNorms(ArrayList<Agent> agents,ArrayList<Content> contents,ArrayList<SocialNormBase> sNormBase, ArrayList<SocialNormClasses> sClasses) {
		//Norm relation type isn't checked yet since only one relation is present but still stored so it can be modified later on
		int shared = 0;
		int notShared = 0;
		int indecisive = 0;
		
		int action = -1;
		
		for(int i=0;i<contents.size();i++) {

			System.out.println("Content " + i + " - Type " + contents.get(i).getHighestContentTypeIndex());
			System.out.println("Coowners:");
			
			
			int cShare = 0;
			int cNShare = 0;
			for(int j=0;j< contents.get(i).getCoowners().size();j++) {
				Boolean normFound = false;
				System.out.println("Coowner " + contents.get(i).getCoowners().get(j) + ":");
				for(int k=0;k<agents.get(contents.get(i).getCoowners().get(j)).getNorms().size();k++) {
					if(agents.get(contents.get(i).getCoowners().get(j)).getNorms().get(k).getConType() == String.valueOf(contents.get(i).getHighestContentTypeIndex())) {
						System.out.println("Norm found. Behavior is " + agents.get(contents.get(i).getCoowners().get(j)).getNorms().get(k).getBehavior() );
						if(agents.get(contents.get(i).getCoowners().get(j)).getNorms().get(k).getBehavior() == 0) {
							cNShare++;
						}
						else {
							cShare++;
						}
						normFound = true;
					}
				}
				if(!normFound) {
					System.out.println("No related norm found for the coowner");
					//no norms for given content, possibilities, don't bid or bid randomly
				}
			}
			if(cNShare>cShare) {
				notShared++;
				System.out.println("Content not shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getRelationship(), contents.get(i).getConType(), 0, contents.get(i).getID());
				sNormBase.add(sNBase);
				action = 0;
				
			}
			else if(cNShare<cShare) {
				shared++;
				System.out.println("Content shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getRelationship(), contents.get(i).getConType(), 1, contents.get(i).getID());
				sNormBase.add(sNBase);
				action = 1;
			}
			else {
				indecisive++;
				System.out.println("Content indecisive");
			}

			//pNorm pnorm = new pNorm(action,contents.get(i).getCoowners(),contents.get(i).getHighestContentTypeIndex());
			for(int j=0;j< contents.get(i).getCoowners().size();j++) {
				for(int k=0;k< agents.size();k++) {
					if (agents.get(k).getAgentID() == contents.get(i).getCoowners().get(j) ) {
						//agents.get(k).addpNorm(pnorm);
					}
				}
			}
			
		}
		
		System.out.println("SocialNormBase Size: " + sNormBase.size());
		//kMeans(sNormBase,sClasses);
		for(int i=0;i<sNormBase.size();i++) {
			//System.out.println(sNormBase.get(i).getKClass() + " - " + sNormBase.get(i).getAction());
			//System.out.println(sNormBase.get(i).getConType().get(0));
		}
		
		for(int i=0;i<sClasses.size();i++) {
			System.out.println(sClasses.get(i).getclassId());
			System.out.println(sClasses.get(i).getcT1mean() + " - " + sClasses.get(i).getcT2mean() + " - " + sClasses.get(i).getcT3mean() + " - " + sClasses.get(i).getcT4mean());
			System.out.println(sClasses.get(i).getPercentage());
			System.out.println(sNormBase.get(i).getKClass() + " - " + sNormBase.get(i).getAction());
			System.out.println(sNormBase.get(i).getConType().get(0));
		}
		
		for(int i=0;i<agents.size();i++) {
			System.out.println("Agent " + i);
			for(int j=0;j<agents.get(i).getpNorms().size();j++) {
				System.out.println("Content action and type: " + agents.get(i).getpNorms().get(j).getAction() + " - " + agents.get(i).getpNorms().get(j).gethighestConTypeIndex());
				for(int k=0; k<agents.get(i).getpNorms().get(j).getcoOwners().size();k++) {
					System.out.print(agents.get(i).getpNorms().get(j).getcoOwners().get(k) + " - ");
					System.out.println("");
				}
			}
		}
		
	}
	
	public static void runWithNormsNew(ArrayList<Agent> agents,ArrayList<Content> contents,ArrayList<SocialNormBase> sNormBase, ArrayList<SocialNormClasses> sClasses) throws IOException {
		int action = -1;
		
		int shared = 0;
		int notShared = 0;
		int indecisive = 0;
		
		int pNormDecisions = 0;
		int difpNormDecisions = 0;
		int corpNormDecisions = 0;
		int mNormDecisions = 0;
		int sNormDecisions = 0;
		int difsNormDecisions = 0;
		int corsNormDecisions = 0;
		
		FileWriter fw = new FileWriter("Results.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw);
	    
	    FileWriter fw2 = new FileWriter("NormCount.txt", true);
	    BufferedWriter bw2 = new BufferedWriter(fw2);
	    PrintWriter out2 = new PrintWriter(bw2);
	    
	    for(int i=0;i<contents.size();i++) {
	    	action = -1;

			int cShare = 0;
			int cNShare = 0;
			

			int pShare = 0;
			int pNShare = 0;
			int cpShare = 0;
			int cpNShare = 0;
			
			int sShare = 0;
			int sNShare = 0;
			int csShare = 0;
			int csNShare = 0;
	    	
			System.out.println("CONTENT " + i + "########################################################");
			for(int j=0;j<contents.get(i).getCoowners().size();j++) {
				for(int k=0;k<agents.size();k++) {
					if(contents.get(i).getCoowners().get(j) == agents.get(k).getAgentID()) {
						System.out.println("Agent " + agents.get(k).getAgentID() + ":");
						int decision = checkpNormsNew(agents.get(k), contents.get(i));
						int decisionM = checkmNormsNew(agents.get(k), contents.get(i));
						if(decision == 0)
							pNShare++;
						if(decision == 1)
							pShare++;
						if(decisionM == 0)
							cpNShare++;
						if(decisionM == 1)
							cpShare++;
					}
				}
			}
			
			
			if(pNShare>pShare) {
				notShared++;
				System.out.println("Content not shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getConType(),contents.get(i).getSentiments(), 0, contents.get(i).getID());
				sNormBase.add(sNBase);
				action = 0;
				pNormDecisions++;
				if(cpNShare>=cpShare){
					corpNormDecisions++;
				}
				else{
					difpNormDecisions++;
				}
			}
			else if(pNShare<pShare) {
				shared++;
				System.out.println("Content shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getConType(),contents.get(i).getSentiments(), 1, contents.get(i).getID());
				sNormBase.add(sNBase);
				action = 1;
				pNormDecisions++;
				if(cpNShare<=cpShare){
					corpNormDecisions++;
				}
				else{
					difpNormDecisions++;
				}
			}
			else {
				indecisive++;
				System.out.println("Content indecisive");
			}
			

			if(action == -1) {
			
			if(i > 999) {

				
				//create content specific normbase
				ArrayList<SocialNormBase> sNormBaseContent = new 	ArrayList<SocialNormBase>();

				
				for(SocialNormBase sBase : sNormBase) {
					ArrayList<Integer> conType = new ArrayList<Integer>();
					for(int k=0;k<4;k++) {
						conType.add(0);
					}
					Boolean haveSentiment = false;
					for(Sentiment sent : sBase.getSentiments()) {
						for(int z=0;z<contents.get(i).getSentiments().size();z++) {
							if(sent.getSentimentType().equals(contents.get(i).getSentiments().get(z).getSentimentType())) {
								conType.set(z, sent.getBelonginess());
								haveSentiment = true;
								//System.out.println("HERAE WEA" + sent.getSentimentType() + "-" + sent.getBelonginess());
							}
						}
					}
					if(haveSentiment) {
						SocialNormBase sNBase = new SocialNormBase(conType,contents.get(i).getSentiments(), sBase.getAction(), sBase.getID());
						sNormBaseContent.add(sNBase);
					}
					//System.out.println("HERAE WEA" + sBase.getSentiments().get(0).getSentimentType() + "-" + sBase.getSentiments().get(0).getBelonginess());
				}
				System.out.println("Contento:" + contents.get(i).getID() + "----" + 
				contents.get(i).getSentiments().get(0).getSentimentType() + "-" + contents.get(i).getSentiments().get(0).getBelonginess() +
				contents.get(i).getSentiments().get(1).getSentimentType() + "-" + contents.get(i).getSentiments().get(1).getBelonginess() +
				contents.get(i).getSentiments().get(2).getSentimentType() + "-" + contents.get(i).getSentiments().get(2).getBelonginess() +
				contents.get(i).getSentiments().get(3).getSentimentType() + "-" + contents.get(i).getSentiments().get(3).getBelonginess() );
				//for(SocialNormBase base : sNormBaseContent) {
					//System.out.println(base.getID()+"$$$$$"+base.getConType().get(0) + "-" +
							//base.getConType().get(1) + "-"+
							//base.getConType().get(2) + "-"+
							//base.getConType().get(3));
				//}
				
				Boolean contin = true;
				int sizeParam = 2;
				if(sNormBaseContent.size()<5) {
					contin = false;
					sClasses.clear();
				}
				while(contin) {
				int normClCount = 0;
				sizeParam++;
				for(int k=0;k<sClasses.size();k++) {
					if (sClasses.get(k).getPercentage() > 66 || sClasses.get(k).getPercentage() < 34)
						normClCount++;
					if (sClasses.get(k).getSize() < 10)
						contin = false;
				}
				
				if(normClCount>sClasses.size()/4 && sClasses.size()>1)
					contin = false;
				
				sClasses.clear();
				System.out.println("SocialNormBase Size: " + sNormBaseContent.size());
				kMeansAging(sNormBaseContent,sClasses,sizeParam,i);
				//kMeans(sNormBase,sClasses,sizeParam);
				}
			}

				for(int j=0;j<contents.get(i).getCoowners().size();j++) {
					for(int k=0;k<agents.size();k++) {
						if(contents.get(i).getCoowners().get(j) == agents.get(k).getAgentID()) {
							System.out.println("Agent " + agents.get(k).getAgentID() + ":");
							int decision = checksNormsNew(agents.get(k), contents.get(i),sClasses);
							int decisionM = checkmNormsNew(agents.get(k), contents.get(i));
							if(decision == 0)
								sNShare++;
							if(decision == 1)
								sShare++;
							if(decisionM == 0)
								csNShare++;
							if(decisionM == 1)
								csShare++;
						}
					}
				}
				
				
				if(sNShare>sShare) {
					notShared++;
					System.out.println("Content not shared");
					action = 0;
					sNormDecisions++;
					if(csNShare>=csShare){
						corsNormDecisions++;
					}
					else{
						difsNormDecisions++;
					}
				}
				else if(sNShare<sShare) {
					shared++;
					System.out.println("Content shared");
					action = 1;
					sNormDecisions++;
					if(csNShare<=csShare){
						corsNormDecisions++;
					}
					else{
						difsNormDecisions++;
					}
				}
				else {
					indecisive++;
					System.out.println("Content indecisive");
				}
			}
			
			
			if(action == -1) {
				for(int j=0;j<contents.get(i).getCoowners().size();j++) {
					for(int k=0;k<agents.size();k++) {
						if(contents.get(i).getCoowners().get(j) == agents.get(k).getAgentID()) {
							System.out.println("Agent " + agents.get(k).getAgentID() + ":");
							int decision = checkmNormsNew(agents.get(k), contents.get(i));
							if(decision == 0)
								cNShare++;
							if(decision == 1)
								cShare++;
						}
					}
				}
				
				if(cNShare>cShare) {
					notShared++;
					System.out.println("Content not shared");
					SocialNormBase sNBase = new SocialNormBase(contents.get(i).getConType(), contents.get(i).getSentiments(), 0, contents.get(i).getID());
					sNormBase.add(sNBase);
					action = 0;
					mNormDecisions++;
				}
				else if(cNShare<cShare) {
					shared++;
					System.out.println("Content shared");
					SocialNormBase sNBase = new SocialNormBase(contents.get(i).getConType(), contents.get(i).getSentiments(), 1, contents.get(i).getID());
					sNormBase.add(sNBase);
					action = 1;
					mNormDecisions++;
				}
				else {
					indecisive++;
					System.out.println("Content indecisive");
				}
				
				if(action != -1) {
				for(Sentiment senti:contents.get(i).getSentiments()) {
				pNorm pnorm = new pNorm(action,contents.get(i).getCoowners(),senti.getSentimentType());
				for(int j=0;j< contents.get(i).getCoowners().size();j++) {
					for(int k=0;k< agents.size();k++) {
						if (agents.get(k).getAgentID() == contents.get(i).getCoowners().get(j) ) {
							agents.get(k).addpNorm(pnorm);
						}
					}
				}
				
			}

				}
			}
			

		    int NormCluster = 0;
			for(int j=0;j<sClasses.size();j++) {
				System.out.println("***" + sClasses.get(j).getclassId());
				System.out.println(sClasses.get(j).getcT1mean() + " - " + sClasses.get(j).getcT2mean() + " - " + sClasses.get(j).getcT3mean() + " - " + sClasses.get(j).getcT4mean());
				System.out.println(sClasses.get(j).getPercentage());
				if(sClasses.get(j).getPercentage()>66)
					NormCluster++;
				System.out.println(sClasses.get(j).getSize());
			}
			
			if(mNormDecisions==0)
				mNormDecisions = 1;
			out.println(mNormDecisions+","+pNormDecisions+","+sNormDecisions+","+
					corpNormDecisions+","+difpNormDecisions+","+
					corsNormDecisions+","+difsNormDecisions+","+
					(double)100*mNormDecisions/(mNormDecisions+pNormDecisions+sNormDecisions)+","+
					(double)100*pNormDecisions/(mNormDecisions+pNormDecisions+sNormDecisions)+","+
					(double)100*sNormDecisions/(mNormDecisions+pNormDecisions+sNormDecisions)
					);
	    }
	    out.close();
	    
	    
		
		System.out.println("mNorm: " + mNormDecisions + " pNorm: " + pNormDecisions + " sNorm: " + sNormDecisions);
	}
	
	public static int checkmNormsNew(Agent agent, Content content) {
		System.out.println("Checking mNorms");
			for(int k=0;k<agent.getNorms().size();k++) {
				for(Sentiment senti : content.getSentiments()) {
					if(agent.getNorms().get(k).getConType().equals(senti.getSentimentType())){
						if(agent.getNorms().get(k).getBehavior() == 0) {
							System.out.println("Norm found. Behavior is " + agent.getNorms().get(k).getBehavior() );
							return 0;
						}
					}
				}
				
				for(Sentiment senti : content.getSentiments()) {
					if(agent.getNorms().get(k).getConType().equals(senti.getSentimentType())){
						if(agent.getNorms().get(k).getBehavior() == 1) {
							System.out.println("Norm found. Behavior is " + agent.getNorms().get(k).getBehavior() );
							return 1;
						}
					}
				}
			}
			System.out.println("No related norm found for the coowner");
			return -1;
				//no norms for given content, possibilities, don't bid or bid randomly
	}
	
	public static int checkpNormsNew(Agent agent, Content content) {
		System.out.println("Checking pNorms");
		float share =0;
		float nShare=0;
		

		//System.out.println("cONTENT COOWNERS");
		for(int k=0; k<content.getCoowners().size();k++) {
			//System.out.print(content.getCoowners().get(k) + " - ");
		}
		for(Sentiment senti : content.getSentiments()) {
		for(int i = 0; i < agent.getpNorms().size();i++) {
			
			if(agent.getpNorms().get(i).gethighestConTypeIndex().equals(senti.getSentimentType())) {
				int jumpDif = content.getCoowners().size() - agent.getpNorms().get(i).getcoOwners().size();
				Boolean allIncluded = true;
				
				//System.out.println("");
				
				for(int j = 0; j < agent.getpNorms().get(i).getcoOwners().size();j++) {
					Boolean coownerIncluded = false;
					for(int k = 0; k < content.getCoowners().size();k++) {

						if(agent.getpNorms().get(i).getcoOwners().get(j).equals(content.getCoowners().get(k))) {
							coownerIncluded = true;
						}
					}
					if(!coownerIncluded) {
						allIncluded = false;
					}
				}
				if(allIncluded) {
					//System.out.println("Possible pNorm found. The jump distance is " + jumpDif);
					//System.out.println("Possible pNorm found. The jump distance is " + jumpDif);
					for(int k=0; k<agent.getpNorms().get(i).getcoOwners().size();k++) {
						System.out.print(agent.getpNorms().get(i).getcoOwners().get(k) + " - ");
					}
					//System.out.println("");
					if(agent.getpNorms().get(i).getAction() == 0)
						nShare += 1/Math.pow(2, jumpDif);
					if(agent.getpNorms().get(i).getAction() == 1)
						share += 1/Math.pow(2, jumpDif);
				}
			}
		}
	}
		
		if(share/(share+nShare) > 0.7) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Share pNorm found");
			return 1;
		}
		
		if(nShare/(share+nShare) > 0.7) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Not share pNorm found");
			return 0;
		}
		
		System.out.println("No related pNorm found for the coowner");
		return -1;
	}
	
	public static int checksNormsNew(Agent agent, Content content,ArrayList<SocialNormClasses> sClasses) throws IOException {
		System.out.println("Checking sNorms");
		int closestClass = -1;
		int distance = 99999;
		int tempDistance;

		Boolean nShNormExists = false;
		Boolean sNormExists = false;
		int classs = -1;
		for(int i=0;i<sClasses.size();i++) {
			
			tempDistance = Math.abs(content.getConType().get(0) - sClasses.get(i).getcT1mean()) +
					Math.abs(content.getConType().get(1) - sClasses.get(i).getcT2mean()) +
					Math.abs(content.getConType().get(2) - sClasses.get(i).getcT3mean()) +
					Math.abs(content.getConType().get(3) - sClasses.get(i).getcT4mean());
			if(tempDistance < distance) {
				distance = tempDistance;
				closestClass = i;
				if (sClasses.get(i).getPercentage() > 66) {
					nShNormExists = true;
					classs = i;
				}
				else
					nShNormExists = false;
				if (sClasses.get(i).getPercentage() < 34) {
					sNormExists = true;
					classs = i;
				}
				else
					sNormExists = false;
			}
		}
		
		if(sNormExists) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Share sNorm found");
			FileWriter fw = new FileWriter("ssResults.txt", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw);
			out.println(content.getSentiments().get(0).getSentimentType() + "-"+sClasses.get(classs).getcT1mean());
			out.println(content.getSentiments().get(1).getSentimentType() + "-"+sClasses.get(classs).getcT2mean());
			out.println(content.getSentiments().get(2).getSentimentType() + "-"+sClasses.get(classs).getcT3mean());
			out.println(content.getSentiments().get(3).getSentimentType() + "-"+sClasses.get(classs).getcT4mean());
			out.close();
			return 1;
		}
		if(nShNormExists) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Not Share sNorm found");
			FileWriter fw = new FileWriter("nsResults.txt", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw);
			out.println(content.getSentiments().get(0).getSentimentType() + "-"+sClasses.get(classs).getcT1mean());
			out.println(content.getSentiments().get(1).getSentimentType() + "-"+sClasses.get(classs).getcT2mean());
			out.println(content.getSentiments().get(2).getSentimentType() + "-"+sClasses.get(classs).getcT3mean());
			out.println(content.getSentiments().get(3).getSentimentType() + "-"+sClasses.get(classs).getcT4mean());
			out.close();
			return 0;
		}
		
		return -1;
	}
	
	public static void runWithNorms(ArrayList<Agent> agents,ArrayList<Content> contents,ArrayList<SocialNormBase> sNormBase, ArrayList<SocialNormClasses> sClasses) throws IOException {
		int action = -1;
		
		int shared = 0;
		int notShared = 0;
		int indecisive = 0;
		
		int pNormDecisions = 0;
		int difpNormDecisions = 0;
		int corpNormDecisions = 0;
		int mNormDecisions = 0;
		int sNormDecisions = 0;
		int difsNormDecisions = 0;
		int corsNormDecisions = 0;
		
		FileWriter fw = new FileWriter("Results.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw);
	    
	    FileWriter fw2 = new FileWriter("NormCount.txt", true);
	    BufferedWriter bw2 = new BufferedWriter(fw2);
	    PrintWriter out2 = new PrintWriter(bw2);
	    
	

		
		for(int i=0;i<contents.size();i++) {
			action = -1;

			int cShare = 0;
			int cNShare = 0;
			

			int pShare = 0;
			int pNShare = 0;
			int cpShare = 0;
			int cpNShare = 0;
			
			int sShare = 0;
			int sNShare = 0;
			int csShare = 0;
			int csNShare = 0;
			
			System.out.println("CONTENT " + i + "########################################################");
			for(int j=0;j<contents.get(i).getCoowners().size();j++) {
				for(int k=0;k<agents.size();k++) {
					if(contents.get(i).getCoowners().get(j) == agents.get(k).getAgentID()) {
						System.out.println("Agent " + agents.get(k).getAgentID() + ":");
						int decision = checkpNorms(agents.get(k), contents.get(i));
						int decisionM = checkmNorms(agents.get(k), contents.get(i));
						if(decision == 0)
							pNShare++;
						if(decision == 1)
							pShare++;
						if(decisionM == 0)
							cpNShare++;
						if(decisionM == 1)
							cpShare++;
					}
				}
			}
			
			
			if(pNShare>pShare) {
				notShared++;
				System.out.println("Content not shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getRelationship(), contents.get(i).getConType(), 0, contents.get(i).getID());
				sNormBase.add(sNBase);
				action = 0;
				pNormDecisions++;
				if(cpNShare>=cpShare){
					corpNormDecisions++;
				}
				else{
					difpNormDecisions++;
				}
			}
			else if(pNShare<pShare) {
				shared++;
				System.out.println("Content shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getRelationship(), contents.get(i).getConType(), 1, contents.get(i).getID());
				sNormBase.add(sNBase);
				action = 1;
				pNormDecisions++;
				if(cpNShare<=cpShare){
					corpNormDecisions++;
				}
				else{
					difpNormDecisions++;
				}
			}
			else {
				indecisive++;
				System.out.println("Content indecisive");
			}
			
			if(action == -1) {
				for(int j=0;j<contents.get(i).getCoowners().size();j++) {
					for(int k=0;k<agents.size();k++) {
						if(contents.get(i).getCoowners().get(j) == agents.get(k).getAgentID()) {
							System.out.println("Agent " + agents.get(k).getAgentID() + ":");
							int decision = checksNormsNewMetric(agents.get(k), contents.get(i),sClasses);
							int decisionM = checkmNorms(agents.get(k), contents.get(i));
							if(decision == 0)
								sNShare++;
							if(decision == 1)
								sShare++;
							if(decisionM == 0)
								csNShare++;
							if(decisionM == 1)
								csShare++;
						}
					}
				}
				
				
				if(sNShare>sShare) {
					notShared++;
					System.out.println("Content not shared");
					action = 0;
					sNormDecisions++;
					if(csNShare>=csShare){
						corsNormDecisions++;
					}
					else{
						difsNormDecisions++;
					}
				}
				else if(sNShare<sShare) {
					shared++;
					System.out.println("Content shared");
					action = 1;
					sNormDecisions++;
					if(csNShare<=csShare){
						corsNormDecisions++;
					}
					else{
						difsNormDecisions++;
					}
				}
				else {
					indecisive++;
					System.out.println("Content indecisive");
				}
			}
			
			if(action == -1) {
			for(int j=0;j<contents.get(i).getCoowners().size();j++) {
				for(int k=0;k<agents.size();k++) {
					if(contents.get(i).getCoowners().get(j) == agents.get(k).getAgentID()) {
						System.out.println("Agent " + agents.get(k).getAgentID() + ":");
						int decision = checkmNorms(agents.get(k), contents.get(i));
						if(decision == 0)
							cNShare++;
						if(decision == 1)
							cShare++;
					}
				}
			}
			
			if(cNShare>cShare) {
				notShared++;
				System.out.println("Content not shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getRelationship(), contents.get(i).getConType(), 0, contents.get(i).getID());
				sNormBase.add(sNBase);
				action = 0;
				mNormDecisions++;
			}
			else if(cNShare<cShare) {
				shared++;
				System.out.println("Content shared");
				SocialNormBase sNBase = new SocialNormBase(contents.get(i).getRelationship(), contents.get(i).getConType(), 1, contents.get(i).getID());
				sNormBase.add(sNBase);
				action = 1;
				mNormDecisions++;
			}
			else {
				indecisive++;
				System.out.println("Content indecisive");
			}
			
			//pNorm pnorm = new pNorm(action,contents.get(i).getCoowners(),contents.get(i).getHighestContentTypeIndex());
			for(int j=0;j< contents.get(i).getCoowners().size();j++) {
				for(int k=0;k< agents.size();k++) {
					if (agents.get(k).getAgentID() == contents.get(i).getCoowners().get(j) ) {
						//agents.get(k).addpNorm(pnorm);
					}
				}
			}

			}
			
			if((i % 250) == 0 && i > 999) {

				Boolean contin = true;
				int sizeParam = 4;
				while(contin) {
				int normClCount = 0;
				sizeParam++;
				for(int k=0;k<sClasses.size();k++) {
					if (sClasses.get(k).getPercentage() > 66 || sClasses.get(k).getPercentage() < 34)
						normClCount++;
					if (sClasses.get(k).getSize() < 100)
						contin = false;
				}
				
				if(normClCount>sClasses.size()/4 && sClasses.size()>1)
					contin = false;
				
				sClasses.clear();
				System.out.println("SocialNormBase Size: " + sNormBase.size());
				kMeansAging(sNormBase,sClasses,sizeParam,i);
				//kMeans(sNormBase,sClasses,sizeParam);
				}
			}
			if(i==9999) {
				Boolean contin = true;
				int sizeParam = 4;
				while(contin) {
				int normClCount = 0;
				sizeParam++;
				for(int k=0;k<sClasses.size();k++) {
					if (sClasses.get(k).getPercentage() > 66 || sClasses.get(k).getPercentage() < 33)
						normClCount++;
					if (sClasses.get(k).getSize() < 100)
						contin = false;
				}
				
				if(normClCount>sClasses.size()/4 && sClasses.size()>1)
					contin = false;

				//sClasses.clear();
				System.out.println("SocialNormBase Size: " + sNormBase.size());
				kMeansAging(sNormBase,sClasses,sizeParam,i);
				//kMeans(sNormBase,sClasses,sizeParam);
				}
			}
			
			if(mNormDecisions==0)
				mNormDecisions = 1;
			out.println(mNormDecisions+","+pNormDecisions+","+sNormDecisions+","+
					corpNormDecisions+","+difpNormDecisions+","+
					corsNormDecisions+","+difsNormDecisions+","+
					(double)100*mNormDecisions/(mNormDecisions+pNormDecisions+sNormDecisions)+","+
					(double)100*pNormDecisions/(mNormDecisions+pNormDecisions+sNormDecisions)+","+
					(double)100*sNormDecisions/(mNormDecisions+pNormDecisions+sNormDecisions)
					);
		}
		out.close();
		System.out.println("mNorm: " + mNormDecisions + " pNorm: " + pNormDecisions + " sNorm: " + sNormDecisions);
		//System.out.println("SocialNormBase Size: " + sNormBase.size());
		//kMeans(sNormBase,sClasses);
		for(int i=0;i<sNormBase.size();i++) {
			//System.out.println(sNormBase.get(i).getKClass() + " - " + sNormBase.get(i).getAction());
			//System.out.println(sNormBase.get(i).getConType().get(0));
		}
		
		int NormCluster = 0;
		for(int i=0;i<sClasses.size();i++) {
			System.out.println("***" + sClasses.get(i).getclassId());
			System.out.println(sClasses.get(i).getcT1mean() + " - " + sClasses.get(i).getcT2mean() + " - " + sClasses.get(i).getcT3mean() + " - " + sClasses.get(i).getcT4mean());
			System.out.println(sClasses.get(i).getPercentage());
			if(sClasses.get(i).getPercentage()>66)
				NormCluster++;
			System.out.println(sClasses.get(i).getSize());
		}
		out2.println(sClasses.size()+","+NormCluster);
		out2.close();
		
	}
	
	public static int checkmNorms(Agent agent, Content content) {
		System.out.println("Checking mNorms");
			for(int k=0;k<agent.getNorms().size();k++) {
				if(agent.getNorms().get(k).getConType() == String.valueOf(content.getHighestContentTypeIndex())) {
					System.out.println("Norm found. Behavior is " + agent.getNorms().get(k).getBehavior() );
					if(agent.getNorms().get(k).getBehavior() == 0) {
						return 0;
					}
					else {
						return 1;
					}
				}
			}
			System.out.println("No related norm found for the coowner");
			return -1;
				//no norms for given content, possibilities, don't bid or bid randomly
	}
	
	public static int checkpNorms(Agent agent, Content content) {
		System.out.println("Checking pNorms");
		float share =0;
		float nShare=0;
		

		//System.out.println("cONTENT COOWNERS");
		for(int k=0; k<content.getCoowners().size();k++) {
			//System.out.print(content.getCoowners().get(k) + " - ");
		}
		
		for(int i = 0; i < agent.getpNorms().size();i++) {
			//if(agent.getpNorms().get(i).gethighestConTypeIndex() == content.getHighestContentTypeIndex()) {
				int jumpDif = content.getCoowners().size() - agent.getpNorms().get(i).getcoOwners().size();
				Boolean allIncluded = true;
				

				//System.out.println("");
				
				for(int j = 0; j < agent.getpNorms().get(i).getcoOwners().size();j++) {
					Boolean coownerIncluded = false;
					for(int k = 0; k < content.getCoowners().size();k++) {
						if(agent.getpNorms().get(i).getcoOwners().get(j) == content.getCoowners().get(k))
							coownerIncluded = true;
					}
					if(!coownerIncluded) {
						allIncluded = false;
					}
				}
				if(allIncluded) {
					//System.out.println("Possible pNorm found. The jump distance is " + jumpDif);
					for(int k=0; k<agent.getpNorms().get(i).getcoOwners().size();k++) {
						//System.out.print(agent.getpNorms().get(i).getcoOwners().get(k) + " - ");
					}
					//System.out.println("");
					if(agent.getpNorms().get(i).getAction() == 0)
						nShare += 1/Math.pow(2, jumpDif);
					if(agent.getpNorms().get(i).getAction() == 1)
						share += 1/Math.pow(2, jumpDif);
				}
			}
		//}
		
		
		if(share/(share+nShare) > 0.7) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Share pNorm found");
			return 1;
		}
		
		if(nShare/(share+nShare) > 0.7) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Not share pNorm found");
			return 0;
		}
		
		System.out.println("No related pNorm found for the coowner");
		return -1;
	}
	

	public static int checksNorms(Agent agent, Content content,ArrayList<SocialNormClasses> sClasses) {
		System.out.println("Checking sNorms");
		int closestClass = -1;
		int distance = 99999;
		int tempDistance;

		Boolean nShNormExists = false;
		Boolean sNormExists = false;
		
		for(int i=0;i<sClasses.size();i++) {
			
			tempDistance = Math.abs(content.getConType().get(0) - sClasses.get(i).getcT1mean()) +
					Math.abs(content.getConType().get(1) - sClasses.get(i).getcT2mean()) +
					Math.abs(content.getConType().get(2) - sClasses.get(i).getcT3mean()) +
					Math.abs(content.getConType().get(3) - sClasses.get(i).getcT4mean());
			if(tempDistance < distance) {
				distance = tempDistance;
				closestClass = i;
				if (sClasses.get(i).getPercentage() > 66) {
					nShNormExists = true;
				}
				else
					nShNormExists = false;
				if (sClasses.get(i).getPercentage() < 34) {
					sNormExists = true;
				}
				else
					sNormExists = false;
			}
		}
		
		if(sNormExists) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Share sNorm found");
			return 1;
		}
		if(nShNormExists) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Not Share sNorm found");
			return 0;
		}
		
		return -1;
	}
	
	public static int checksNormsNewMetric(Agent agent, Content content,ArrayList<SocialNormClasses> sClasses) {
		System.out.println("Checking sNorms");
		int closestClass = -1;
		float distance = 99999;
		float tempDistance;
		float alpha=(float)0.2;
		float cs=0;
		float ds=0;
		float result=0;
		
		
		Boolean nShNormExists = false;
		Boolean sNormExists = false;
		
		for(int i=0;i<sClasses.size();i++) {
			
			tempDistance = Math.abs(content.getConType().get(0) - sClasses.get(i).getcT1mean()) +
					Math.abs(content.getConType().get(1) - sClasses.get(i).getcT2mean()) +
					Math.abs(content.getConType().get(2) - sClasses.get(i).getcT3mean()) +
					Math.abs(content.getConType().get(3) - sClasses.get(i).getcT4mean());
			if(tempDistance < distance) {
				distance = tempDistance;
				int dsCounter=0;
				for(int j=0;j<agent.getNorms().size();j++) {
					System.out.println("sssss" + agent.getNorms().get(j).getConType() + "-" + content.getHighestContentTypeIndex());
					if(agent.getNorms().get(j).getConType() == String.valueOf(content.getHighestContentTypeIndex())){
						System.out.println("HHEHEHEHERE");
						System.out.println(dsCounter);
						System.out.println(agent.getNorms().get(j).getBehavior() + "-" + sClasses.get(i).getPercentage());
						if(agent.getNorms().get(j).getBehavior()==0 && sClasses.get(i).getPercentage()>50)
							dsCounter++;
						if(agent.getNorms().get(j).getBehavior()==1 && sClasses.get(i).getPercentage()<50)
							dsCounter++;
						if(agent.getNorms().get(j).getBehavior()==0 && sClasses.get(i).getPercentage()<50)
							dsCounter--;
						if(agent.getNorms().get(j).getBehavior()==1 && sClasses.get(i).getPercentage()>50)
							dsCounter--;
						System.out.println("dsdsds" + dsCounter);
					}
				}
				if(agent.getNorms().size()>0)
					ds = (float)(100*dsCounter / agent.getNorms().size());
				else
					ds = 0;
				cs = (sClasses.get(i).getFDist() - tempDistance)/sClasses.get(i).getFDist();
				closestClass = i;
				System.out.println("cs: " + cs + " ds: " + ds + " perc: " +sClasses.get(i).getPercentage() + " alpha: " + alpha);
				
				
				if (sClasses.get(i).getPercentage() > 50)
					result = (alpha*cs*sClasses.get(i).getPercentage() + (1-alpha)* ds);
				if (sClasses.get(i).getPercentage() < 50)
					result = (alpha*cs*(100-sClasses.get(i).getPercentage()) + (1-alpha)* ds);
				int rando = new Random().nextInt(100);
				System.out.println("Results: " + (alpha*cs*sClasses.get(i).getPercentage() + (1-alpha)* ds));
				
				if (sClasses.get(i).getPercentage() > 50 && rando <= result) {
					nShNormExists = true;
				}
				else
					nShNormExists = false;
				if (sClasses.get(i).getPercentage() < 50 && rando <= result) {
					sNormExists = true;
				}
				else
					sNormExists = false;
			}
		}
		
		if(sNormExists) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Share sNorm found");
			return 1;
		}
		if(nShNormExists) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$Not Share sNorm found");
			return 0;
		}
		
		return -1;
	}
	
	public static void kMeans(ArrayList<SocialNormBase> sNormBase, ArrayList<SocialNormClasses> sClasses,int sizeParam) {
		int countK = sNormBase.size() / (sNormBase.size()/sizeParam) + 1;
		System.out.println("Count k:"+ countK);
		int cCounter = 0;
		int clas = 0;
		for(int i=0;i<sNormBase.size();i++) {
			sNormBase.get(i).setKClass(clas);
			cCounter++;
			if(cCounter==(sNormBase.size()/countK)&&i<sNormBase.size()-countK) {
				clas++;
				cCounter = 0;
			}
		}
	Boolean swap=true;	
for(int loop=0;loop<100&&swap;loop++) {
		ArrayList<Integer> actionPercentage = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> classDimensionMeans = new ArrayList<ArrayList<Integer>>();
		//System.out.println("Loop #: " + loop);
		for(int i=0;i<clas+1;i++) {
			int classSize = 0;
			int noShare = 0;
			//actionPercentage.add(0);
			ArrayList<Integer> conType = new ArrayList<Integer>();
			int t1=0;
			int t2=0;
			int t3=0;
			int t4=0;
			//classDimensionMeans.add(conType);
			for(int j=0;j<sNormBase.size();j++) {
				if(sNormBase.get(j).getKClass() == i) {
					classSize++;
					if(sNormBase.get(j).getAction() == 0)
						noShare++;
					t1+=sNormBase.get(j).getConType().get(0);
					t2+=sNormBase.get(j).getConType().get(1);
					t3+=sNormBase.get(j).getConType().get(2);
					t4+=sNormBase.get(j).getConType().get(3);
				}
			}
			//System.out.println("Class " +i+ " size:"+ classSize);
			if(classSize != 0) {
			t1=t1/classSize;
			t2=t2/classSize;
			t3=t3/classSize;
			t4=t4/classSize;
			}
			else {
				classSize = 1;
				t1=1;
				t2=1;
				t3=1;
				t4=1;
			}
			//System.out.println("Dimension means: " + t1 + "-" + t2 + "-" + t3 + "-" + t4);
			//actionPercentage.add(noShare*100/classSize);
			//System.out.println("Action Percentage: " + noShare*100 / classSize);
			
			Boolean classExists = false;
			int pickedClass = 0;
			for(int k=0;k<sClasses.size();k++) {
				if(i==sClasses.get(k).getclassId()) {
					classExists = true;
					pickedClass = i;
				}
			}
			
			if(classExists) {
				sClasses.get(pickedClass).setcT1mean(t1);
				sClasses.get(pickedClass).setcT2mean(t2);
				sClasses.get(pickedClass).setcT3mean(t3);
				sClasses.get(pickedClass).setcT4mean(t4);
				sClasses.get(pickedClass).setPercentage(noShare*100 / classSize);
				sClasses.get(pickedClass).setSize(classSize);
			}
			else {
				SocialNormClasses newClas = new SocialNormClasses();
				newClas.setclassId(i);;
				newClas.setcT1mean(t1);
				newClas.setcT2mean(t2);
				newClas.setcT3mean(t3);
				newClas.setcT4mean(t4);
				newClas.setPercentage(noShare*100 / classSize);
				newClas.setSize(classSize);
				sClasses.add(newClas);
			}
			conType.add(t1);
			conType.add(t2);
			conType.add(t3);
			conType.add(t4);
			classDimensionMeans.add(conType);
		}
		//System.out.println(actionPercentage.size());
		swap = false;
		int swapCount = 0;
		for(int i=0;i<sNormBase.size();i++) {
			int lowestDif = 999;
			for(int j=0;j<classDimensionMeans.size();j++) {
				int dif = 0;
				dif += Math.abs(sNormBase.get(i).getConType().get(0) - classDimensionMeans.get(j).get(0));
				dif += Math.abs(sNormBase.get(i).getConType().get(1) - classDimensionMeans.get(j).get(1));
				dif += Math.abs(sNormBase.get(i).getConType().get(2) - classDimensionMeans.get(j).get(2));
				dif += Math.abs(sNormBase.get(i).getConType().get(3) - classDimensionMeans.get(j).get(3));
				if(dif<lowestDif && sNormBase.get(i).getKClass()!=j) {
					lowestDif = dif;
					//System.out.println("Changed " + sNormBase.get(i).getKClass()+ " to " + j);
					sNormBase.get(i).setKClass(j);
					swap = true;
					swapCount++;
				}
			}
		}
		//System.out.println("Swap count: " + swapCount);
		
	}
}
	
	public static void kMeansAging(ArrayList<SocialNormBase> sNormBase, ArrayList<SocialNormClasses> sClasses,int sizeParam, int currentCont) {
		int countK = sNormBase.size() / (sNormBase.size()/sizeParam) + 1;
		System.out.println("Count k:"+ countK);
		int cCounter = 0;
		int clas = 0;
		for(int i=0;i<sNormBase.size();i++) {
			sNormBase.get(i).setKClass(clas);
			cCounter++;
			if(cCounter==(sNormBase.size()/countK)&&i<sNormBase.size()-countK) {
				clas++;
				cCounter = 0;
			}
		}
	Boolean swap=true;	
for(int loop=0;loop<100&&swap;loop++) {
		ArrayList<Integer> actionPercentage = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> classDimensionMeans = new ArrayList<ArrayList<Integer>>();
		//System.out.println("Loop #: " + loop);
		for(int i=0;i<clas+1;i++) {
			float classSize = 0;
			float noShare = 0;
			int total = 0;
			//actionPercentage.add(0);
			ArrayList<Integer> conType = new ArrayList<Integer>();
			int t1=0;
			int t2=0;
			int t3=0;
			int t4=0;
			//classDimensionMeans.add(conType);
			for(int j=0;j<sNormBase.size();j++) {
				if(sNormBase.get(j).getKClass() == i) {
					//System.out.println("CLAASS:"+ classSize + "nossss:"+ total + "ID:"+sNormBase.get(j).getID());
					classSize+=(float)Math.pow(Math.E,-((float)(currentCont-j)/1000));
					total++;
					//System.out.println("SSSSCLAASS:"+ classSize + "nossss:"+ total + "ID:"+ sNormBase.get(j).getID());
					if(sNormBase.get(j).getAction() == 0)
						noShare+=(float)Math.pow(Math.E,-((float)(currentCont-j)/1000));
					t1+=sNormBase.get(j).getConType().get(0);
					t2+=sNormBase.get(j).getConType().get(1);
					t3+=sNormBase.get(j).getConType().get(2);
					t4+=sNormBase.get(j).getConType().get(3);
				}
				//System.out.println("CLAASS:"+ classSize + "nossss:"+ noShare + sNormBase.get(j).getID());
			}
			//System.out.println("Class " +i+ " size:"+ classSize);
			if(total != 0) {
			t1=t1/total;
			t2=t2/total;
			t3=t3/total;
			t4=t4/total;
			}
			else {
				total = 1;
				t1=1;
				t2=1;
				t3=1;
				t4=1;
			}
			//System.out.println("Dimension means: " + t1 + "-" + t2 + "-" + t3 + "-" + t4);
			//actionPercentage.add(noShare*100/classSize);
			//System.out.println("Action Percentage: " + noShare*100 / classSize);
			
			Boolean classExists = false;
			int pickedClass = 0;
			for(int k=0;k<sClasses.size();k++) {
				if(i==sClasses.get(k).getclassId()) {
					classExists = true;
					pickedClass = i;
				}
			}
			
			if(classExists) {
				sClasses.get(pickedClass).setcT1mean(t1);
				sClasses.get(pickedClass).setcT2mean(t2);
				sClasses.get(pickedClass).setcT3mean(t3);
				sClasses.get(pickedClass).setcT4mean(t4);
				sClasses.get(pickedClass).setPercentage(noShare*100 / classSize);
				sClasses.get(pickedClass).setSize(total);
			}
			else {
				SocialNormClasses newClas = new SocialNormClasses();
				newClas.setclassId(i);;
				newClas.setcT1mean(t1);
				newClas.setcT2mean(t2);
				newClas.setcT3mean(t3);
				newClas.setcT4mean(t4);
				newClas.setFDist(0);
				newClas.setPercentage(noShare*100 / classSize);
				newClas.setSize(total);
				sClasses.add(newClas);
			}
			conType.add(t1);
			conType.add(t2);
			conType.add(t3);
			conType.add(t4);
			classDimensionMeans.add(conType);
		}
		//System.out.println(actionPercentage.size());
		swap = false;
		int swapCount = 0;
		for(int i=0;i<sNormBase.size();i++) {
			int lowestDif = 999;
			for(int j=0;j<classDimensionMeans.size();j++) {
				int dif = 0;
				dif += Math.abs(sNormBase.get(i).getConType().get(0) - classDimensionMeans.get(j).get(0));
				dif += Math.abs(sNormBase.get(i).getConType().get(1) - classDimensionMeans.get(j).get(1));
				dif += Math.abs(sNormBase.get(i).getConType().get(2) - classDimensionMeans.get(j).get(2));
				dif += Math.abs(sNormBase.get(i).getConType().get(3) - classDimensionMeans.get(j).get(3));
				if(dif<lowestDif && sNormBase.get(i).getKClass()!=j) {
					lowestDif = dif;
					//System.out.println("Changed " + sNormBase.get(i).getKClass()+ " to " + j);
					sNormBase.get(i).setKClass(j);
					swap = true;
					swapCount++;
				}
			}
		}
		//Furthest content calculation
		for(int i= 0;i<sClasses.size();i++){
			for(int j=0;j<sNormBase.size();j++) {
				int dist = 0;
				if(sNormBase.get(j).getKClass() == sClasses.get(i).getclassId()){
					dist = Math.abs(sNormBase.get(j).getConType().get(0) - sClasses.get(i).getcT1mean()) +
							Math.abs(sNormBase.get(j).getConType().get(1) - sClasses.get(i).getcT2mean()) +
							Math.abs(sNormBase.get(j).getConType().get(2) - sClasses.get(i).getcT3mean()) +
							Math.abs(sNormBase.get(j).getConType().get(3) - sClasses.get(i).getcT4mean());
					if(dist > sClasses.get(i).getFDist())
						sClasses.get(i).setFDist(dist);
				}
			}
			//System.out.println("Furthest content " + sClasses.get(i).getclassId()+  ":" + sClasses.get(i).getFDist());
		}
		
		//System.out.println("Swap count: " + swapCount);
		
	}
}
	
	public static void deepkMeans(ArrayList<SocialNormBase> sNormBase, ArrayList<SocialNormClasses> sClasses) {

		ArrayList<SocialNormBase> tempNormBase = new ArrayList<SocialNormBase>();
		for (int i=0;i<sNormBase.size();i++) {
			tempNormBase.add(sNormBase.get(i));
		}
		System.out.println("Snormbases:"+ sNormBase.size());
		tempNormBase.clear();
		System.out.println("Ssnormbases:"+ sNormBase.size());
		
		int countK = sNormBase.size() / (sNormBase.size()/5) + 1;
		System.out.println("Count k:"+ countK);
		
		int cCounter = 0;
		int clas = 0;
		for(int i=0;i<sNormBase.size();i++) {
			sNormBase.get(i).setKClass(clas);
			cCounter++;
			if(cCounter==(sNormBase.size()/countK)&&i<sNormBase.size()-countK) {
				clas++;
				cCounter = 0;
			}
		}
	Boolean swap=true;	
for(int loop=0;loop<100&&swap;loop++) {
		ArrayList<Integer> actionPercentage = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> classDimensionMeans = new ArrayList<ArrayList<Integer>>();
		//System.out.println("Loop #: " + loop);
		for(int i=0;i<clas+1;i++) {
			int classSize = 0;
			int noShare = 0;
			//actionPercentage.add(0);
			ArrayList<Integer> conType = new ArrayList<Integer>();
			int t1=0;
			int t2=0;
			int t3=0;
			int t4=0;
			//classDimensionMeans.add(conType);
			for(int j=0;j<sNormBase.size();j++) {
				if(sNormBase.get(j).getKClass() == i) {
					classSize++;
					if(sNormBase.get(j).getAction() == 0)
						noShare++;
					t1+=sNormBase.get(j).getConType().get(0);
					t2+=sNormBase.get(j).getConType().get(1);
					t3+=sNormBase.get(j).getConType().get(2);
					t4+=sNormBase.get(j).getConType().get(3);
				}
			}
			//System.out.println("Class " +i+ " size:"+ classSize);
			if(classSize != 0) {
			t1=t1/classSize;
			t2=t2/classSize;
			t3=t3/classSize;
			t4=t4/classSize;
			}
			else {
				classSize = 1;
				t1=1;
				t2=1;
				t3=1;
				t4=1;
			}
			//System.out.println("Dimension means: " + t1 + "-" + t2 + "-" + t3 + "-" + t4);
			//actionPercentage.add(noShare*100/classSize);
			//System.out.println("Action Percentage: " + noShare*100 / classSize);
			
			Boolean classExists = false;
			int pickedClass = 0;
			for(int k=0;k<sClasses.size();k++) {
				if(i==sClasses.get(k).getclassId()) {
					classExists = true;
					pickedClass = i;
				}
			}
			
			if(classExists) {
				sClasses.get(pickedClass).setcT1mean(t1);
				sClasses.get(pickedClass).setcT2mean(t2);
				sClasses.get(pickedClass).setcT3mean(t3);
				sClasses.get(pickedClass).setcT4mean(t4);
				sClasses.get(pickedClass).setPercentage(noShare*100 / classSize);
			}
			else {
				SocialNormClasses newClas = new SocialNormClasses();
				newClas.setclassId(i);;
				newClas.setcT1mean(t1);
				newClas.setcT2mean(t2);
				newClas.setcT3mean(t3);
				newClas.setcT4mean(t4);
				newClas.setPercentage(noShare*100 / classSize);
				sClasses.add(newClas);
			}
			conType.add(t1);
			conType.add(t2);
			conType.add(t3);
			conType.add(t4);
			classDimensionMeans.add(conType);
		}
		//System.out.println(actionPercentage.size());
		swap = false;
		int swapCount = 0;
		for(int i=0;i<sNormBase.size();i++) {
			int lowestDif = 999;
			for(int j=0;j<classDimensionMeans.size();j++) {
				int dif = 0;
				dif += Math.abs(sNormBase.get(i).getConType().get(0) - classDimensionMeans.get(j).get(0));
				dif += Math.abs(sNormBase.get(i).getConType().get(1) - classDimensionMeans.get(j).get(1));
				dif += Math.abs(sNormBase.get(i).getConType().get(2) - classDimensionMeans.get(j).get(2));
				dif += Math.abs(sNormBase.get(i).getConType().get(3) - classDimensionMeans.get(j).get(3));
				if(dif<lowestDif && sNormBase.get(i).getKClass()!=j) {
					lowestDif = dif;
					//System.out.println("Changed " + sNormBase.get(i).getKClass()+ " to " + j);
					sNormBase.get(i).setKClass(j);
					swap = true;
					swapCount++;
				}
			}
		}
		//System.out.println("Swap count: " + swapCount);
		
	}
	}
	
}
