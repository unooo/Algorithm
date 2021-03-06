import java.util.HashSet;
import java.util.LinkedList;

public class Programmers_ÈÄº¸Å° {
	
	public static void main(String[] args){
		String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
		solution(relation);
	}

	public static int solution(String[][] relation) {
        int answer = 0;        
        for(int i = 0 ; i <relation[0] .length ; i++){
        	path = new int[relation[0].length];
        	dfs(0,relation[0].length,i+1,relation,0);
        }
        return ret.size();
    }
	
	public static int path[] = null;
	public static LinkedList<LinkedList<Integer>> ret = new LinkedList<LinkedList<Integer>>();
	
	public static void dfs(int next,int n, int r,String[][] relation,int idx){
		if(next>=r){
			//todo
			
			HashSet<String> hSet = new HashSet<String>();
			for(int i = 0 ; i < relation.length ; i ++){
				String row[] = relation[i];
				String key =new String();
				
				for(int k = 0 ; k < r ; k ++){
				 key+=row[path[k]];
				}
				boolean tp = hSet.add(key);
				if(!tp){
					return;
				}
			}
			
			LinkedList<Integer> keySet = new LinkedList<Integer>();
			for(int i = 0 ; i < r ; i ++)
				keySet.add(path[i]);
			
			for(LinkedList<Integer> temp : ret){
				if(keySet.containsAll(temp))
					return;
			}
			ret.add(keySet);
			return;
		}
		
		for(int i = idx ; i < n; i ++){
			path[next]=i;
			dfs(next+1,n,r,relation,i+1);
		}
	}
}
