import java.util.LinkedList;

import org.junit.Test;

import org.junit.Assert;



public class Programmers_캐시 {

	@Test
	public void mainTest(){
		int cacheSize=0;
		String[] cities= {"Jeju", "Pangyo", "NewYork", "newyork"};
		int actual=solution(cacheSize, cities);
		Assert.assertEquals(20, actual);
		
		cacheSize=0;
		cities=new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		actual=solution(cacheSize, cities);
		Assert.assertEquals(25, actual);
		
	}
	
	
	    public int solution(int cacheSize, String[] cities) {
	        int answer = 0;
	        LinkedList<String> cache  = new LinkedList<String>();
	        
	        for(int i = 0 ; i < cities.length ; i ++){
	            String city = cities[i].toLowerCase();
	            if(cache.contains(city)){
	                answer+=1;
	                cache.remove(city);
	                cache.addFirst(city);   
	            }else{
	                answer+=5;
	                if(cache.size()<cacheSize){
	                    cache.addFirst(city);
	                }else{
	                	if(cache.size()>0){
	                		cache.removeLast();
	                		cache.addFirst(city);	                    
	                	}
	                	
	                	//1. 처음엔 if(cache.size()>0) 로 분기처리 안해서 cache 사이즈가 0인경우 죽었음
	                	
	                	//2. 두번째엔 삭제 안하면 자리도 없는데 추가를 해버려서 틀렸음. 
	                	/*
	                	 * if(cache.size()>0){
	                		cache.removeLast();
	                		                    
	                	}
	                	cache.addFirst(city);	
	                	*/
	                	
	                	//정리: 즉 기본적인 마인드로다가 사이즈가 정해져있을때 다찼다면 자리가 비워져야 들어가는데 그냥 생각없이 코딩부터해서 틀림. 꼼꼼히 생각하고하자
	                }
	                
	            }
	            
	        }
	        
	        return answer;
	}
}
