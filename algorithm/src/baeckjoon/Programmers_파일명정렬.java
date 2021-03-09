package baeckjoon;

import java.util.Collections;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmers_파일명정렬 {
	
	public static void main(String[] args){
	    String[] files ={"foo0.zip"};

	    args=solution(files);
	    System.out.println();
	}

	 public static String[] solution(String[] files) {
	     
	        LinkedList<File> list = new LinkedList<File>();
	        Pattern patternHead = Pattern.compile("^[^0-9]+");
	        Pattern patternNumber = Pattern.compile("^[0-9]+");
	        
	        for(int i = 0 ; i < files.length ; i ++){
	        	String tempHead = null,Number = null,Tail;
	        	String str = files[i];
	        	Matcher matcher = patternHead.matcher(str);
	        	if(matcher.find()){
	        		tempHead=matcher.group();
	        		str=matcher.replaceFirst("");
	        	}
	        	
	        	
	        	matcher = patternNumber.matcher(str);
	        	String orgNumber=null;
	        	if(matcher.find()){
	        		Number=matcher.group();
	        		orgNumber=Number;
	        		Number=Number.replaceFirst("^[0]+", "");
	        		str=matcher.replaceFirst("");
	        		if(Number.equals(""))
	        			Number="0";
	        	}
	        	Tail=str;
	        	list.add(new File(tempHead.toLowerCase(),Tail,tempHead,Tail,orgNumber,Integer.parseInt(Number)));
	        }
	        
	        Collections.sort(list);
	        return list.stream().map(item->{
	        	return item.orgHead+item.orgNum+item.orgTail;
	        }).toArray(String[]::new);
	       
	  }
	 
	 static class File implements Comparable<File>{
		 String head,tail,orgHead,orgTail,orgNum;
		 int number;

		@Override
		public int compareTo(File arg0) {
			int first= this.head.compareTo(arg0.head);
			int second=this.number-arg0.number;
			if(first>0){
				return 1;
			}else if(first<0){
				return -1;
			}else{
				return second;
			}
			
		}

	


		public File(String head, String tail, String orgHead, String orgTail, String orgNum, int number) {
			super();
			this.head = head;
			this.tail = tail;
			this.orgHead = orgHead;
			this.orgTail = orgTail;
			this.orgNum = orgNum;
			this.number = number;
		}
		
	 }
}
