package topcoder;

import java.util.ArrayList;

public class CantorDust {

	public static void main(String[] args) {

		int ret = occurrencesNumber(new String[]{"X...X"},2);
		System.out.println(ret);
	}

	public static int occurrencesNumber(String[] pattern, int time){
		int ret = 0 ;
		
		String grid = getString(time);
		String patternRow = new String();
		String patternCol = new String();
		
		for(int i = pattern.length-1 ; i  >=0; i --){
			boolean flag = false;
			
			for(int j = 0 ; j <pattern[i].length() ; j ++){
				if(pattern[i].charAt(j)=='X'){ //행제어
					flag = true;
					break;
				}
			}
			
			if(flag==true){
				patternRow+="X";
			}else{
				patternRow+=".";
			}
		}
		
		
		
		for(int i = 0 ; i <pattern[0].length() ; i ++){
			boolean flag = false;
			for(int j = 0 ; j <pattern.length ; j ++){
				if(pattern[j].charAt(i)=='X'){ //열제어
					flag=true;
					break;
				}
			}
			if(flag==true){
				patternCol+="X";
			}else{
				patternCol+=".";
			}
		}
		
		int matchCol = matchNum(grid,patternCol);
		int matchRow = matchNum(grid,patternRow);
		ret= matchCol*matchRow;
		
			
		return ret;		
	}

	public static int matchNum(String source, String target) {
		int ret = 0 ;
		for (int i = 0; i < source.length(); i++) {

			if (source.charAt(i) == target.charAt(0)) {
				
				
					// 검사시작
					boolean flag = true;
					for (int j = 0; j < target.length(); j++) {
						
						
						if ((j+i>source.length()-1)||!(target.charAt(j) == source.charAt(j + i))) {
							flag = false;
							break;
						}
					}
					// 검사완료
					if (flag == true)
						ret++;
				

			}
		}
		return ret;
	}

	public static String getString(int T) {

		if (T == 0)
			return "X";

		String c = getString(T - 1);

		String s = "";
		for (int i = 0; i < c.length(); i++)
			s += ".";
		return c + s + c;

	}

	public ArrayList<Integer> findMatchIndex(String source, String target) {

		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (!source.contains(target))
			return null;
		for (int i = 0; i < source.length(); i++) {

			if (source.charAt(i) == target.charAt(i)) {
				// 검사시작
				boolean flag = true;
				for (int j = 0; j < target.length(); j++) {
					if (!(target.charAt(j) == source.charAt(j + i))) {
						flag = false;
						break;
					}
				}
				// 검사완료
				if (flag == true)
					ret.add(i);
			}

		}

		return ret;

	}

}
