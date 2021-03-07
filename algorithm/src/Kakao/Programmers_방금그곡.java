package Kakao;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Programmers_¹æ±Ý±×°î {
	
	public static void main(String[] args){
		String m = "ABC";
		String[] musicinfos = 	{"00:00,00:05,HI,ABC#ABC"};
		System.out.println(solution(m, musicinfos));
	}
	
	public static String replace(String m){
		 m=m.replaceAll("C#", "c");
	        m=m.replaceAll("D#", "d");
	        m=m.replaceAll("F#", "f");
	        m=m.replaceAll("G#", "g");
	        m=m.replaceAll("A#", "a");
	        m=m.replaceAll("E#", "e");
	        
	       return m;
		
	}
	
	public static String solution(String m, String[] musicinfos) {

        m=replace(m);
        LinkedList<Node> candidate = new LinkedList<Node>();
        Arrays.sort(musicinfos,(a,b)->{
        	return a.substring(0,5).compareTo(b.substring(0,5));
        });
        for(int i = 0 ; i < musicinfos.length ; i ++){
        	
        	String[] infoDetail=musicinfos[i].split(",");
        	String startTime=infoDetail[0], endTime=infoDetail[1],musicName=infoDetail[2],melody=infoDetail[3];
        	melody=replace(melody);
        	int startTimeHour=Integer.parseInt(startTime.split(":")[0].replace("^0", ""));
        	int startTimeMin=Integer.parseInt(startTime.split(":")[1].replace("^0", ""));
        	int startTimeTotal=startTimeHour*60+startTimeMin;
        	
        	int endTimeHour=Integer.parseInt(endTime.split(":")[0].replace("^0", ""));
        	int endTimeMin=Integer.parseInt(endTime.split(":")[1].replace("^0", ""));
        	int endTimeTotal=endTimeHour*60+endTimeMin;
        	
        	int playTime=endTimeTotal-startTimeTotal;
        	
        	StringBuilder realMelody = new StringBuilder();
        	for(int j = 0 ; j < playTime ; j ++){
        		realMelody.append(melody.charAt(j%melody.length()));
        	}
        	
        	if(realMelody.toString().contains(m)){
        		candidate.add(new Node(musicName,playTime,startTimeTotal));
        	}
        	
        }
        
        Collections.sort((candidate));
        if(candidate.size()==0)
        	return "(None)";
        else{
        	return candidate.getFirst().name;
        }
        
    }
	
	static class Node implements Comparable<Node>{
		String name;
		int playTime;
		int order;
		
		public Node(String name, int playTime, int order) {
			super();
			this.name = name;
			this.playTime = playTime;
			this.order = order;
		}
		@Override
		public int compareTo(Node arg0) {
			// TODO Auto-generated method stub
			return arg0.playTime-this.playTime;
		}
		
	}

}
