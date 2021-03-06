import java.util.*;
class Programmers_뉴스클러스터링 {
	public static void main(String[] args){
		String str1 = 	"aa1+aa2";
		String str2 = "AAAA12";
		System.out.println(solution(str1, str2));
	}
    public static int solution(String str1, String str2) {
        str1=str1.toLowerCase();
        str2=str2.toLowerCase();
        int answer = 0;
        int str1Len= str1.length();
        int str2Len= str2.length();
        
        HashMap<String, Integer> hMap = new HashMap<String , Integer>();
        
        int temp1=0;
        for(int i = 0 ; i < str1Len-1 ; i++){
            String temp = str1.substring(i,i+2);
            
            if(temp.matches("(.)*[^a-z]+(.)*"))
                continue;
            Integer val = hMap.get(temp);
            if(val==null)
                hMap.put(temp,1);
            else
                hMap.put(temp,val+1);
            temp1++;
        }
        
        double 교집합갯수=0;
        int temp2=0;
        for(int i = 0 ; i < str2Len -1 ; i ++){
            String temp= str2.substring(i,i+2);
            if(temp.matches("(.)*[^a-z]+(.)*"))
                continue;
            temp2++;  
            Integer val = hMap.get(temp);
            if(val==null||val==0)
                continue;            
            else{
                hMap.put(temp,val-1);
                교집합갯수++;
            }
              
        }
        double 합집합갯수=temp1+temp2-교집합갯수;
        double 유사도=1*65536;
        if(합집합갯수!=0)
            유사도=(교집합갯수/합집합갯수)*65536;     
        
        
        return (int)유사도;
    }
}