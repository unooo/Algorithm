import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Programmers_Á¤·Ä_Hindex {
	
	public static void main(String[] args){
		int[] citations=new int[]{4, 4, 4};
		System.out.println(solution(citations));
	}

	public static int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        LinkedList<Integer> ret = new LinkedList<Integer>();
        for(int hIndex=10000 ; hIndex >=0 ; hIndex--){
        	int pivot=0;
        	int b=-1;
        	int a=-1;
        	for(int i = citations.length-1 ; i >=0 ; i--){
        		if(citations[i]<=hIndex){
        			a=citations.length-i;
        			b=i;
        			break;
        		}
        	}
        	if(a>=hIndex&&b<=hIndex)
        		ret.add(hIndex);
        	
        		
        }
        Collections.sort(ret,Comparator.reverseOrder());
        return ret.getFirst()+1;
    }
}
