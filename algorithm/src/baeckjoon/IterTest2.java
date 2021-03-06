package baeckjoon;

import java.util.ArrayList;
import java.util.LinkedList;

public class IterTest2 {

	public static void main(String[] args){
		ArrayList<String> list = new ArrayList<String>();

		list.add("AAAA");
		list.add("BBBB");
		list.add("CCCC");
		list.add("DDDD");
		list.add("EEEE");
		list.add("FFFF");
		list.add("GGGG");

		
		for(int i = 0 ; i < list.size();i++){
			System.out.println(list.get(i));
			if(list.get(i).startsWith("C")){
				list.remove(i);
				i--;
			}
		}
		
		System.out.println("Result: " + list);
	}
}
