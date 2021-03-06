import java.util.LinkedList;

import org.junit.Test;

import org.junit.Assert;



public class Programmers_ĳ�� {

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
	                	
	                	//1. ó���� if(cache.size()>0) �� �б�ó�� ���ؼ� cache ����� 0�ΰ�� �׾���
	                	
	                	//2. �ι�°�� ���� ���ϸ� �ڸ��� ���µ� �߰��� �ع����� Ʋ����. 
	                	/*
	                	 * if(cache.size()>0){
	                		cache.removeLast();
	                		                    
	                	}
	                	cache.addFirst(city);	
	                	*/
	                	
	                	//����: �� �⺻���� ���ε�δٰ� ����� ������������ ��á�ٸ� �ڸ��� ������� ���µ� �׳� �������� �ڵ������ؼ� Ʋ��. �Ĳ��� �����ϰ�����
	                }
	                
	            }
	            
	        }
	        
	        return answer;
	}
}
