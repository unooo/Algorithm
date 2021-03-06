package Programmers;
public class Programmers_깊이너비_단어변환 {
	
	public static void main(String[] args){
		String begin="hit";
		String target="cog";
		String[] words={"hot", "dot", "dog", "lot", "log","cog"};
		solution(begin, target, words);
	}

	public static int solution(String begin, String target, String[] words) {
      int answer=0;
      flag=new boolean[words.length];
      dfs(0, words, begin, target);
      if(res==Integer.MAX_VALUE)
    	  return 0;
      else
    	  return res;
	}
	public static boolean flag[];
	public static int res=Integer.MAX_VALUE;
	public static void dfs(int nowStep,String[] words, String nowString,String target){
		
		
		for(int i=0;i<words.length ; i++){
			if(flag[i]==true)
				continue;
			char[] nowStrChAry=nowString.toCharArray();
			char[] comStrChAry=words[i].toCharArray();
			int diff=0;
			for(int j=0;j<words[0].length();j++){
				if(nowStrChAry[j]!=comStrChAry[j])
					diff++;
			}
			if(diff>1)
				continue;
			if(words[i].equals(target)){
				res=Math.min(res, nowStep+1); 
			}
			flag[i]=true;
			dfs(nowStep+1,words,words[i],target);
			flag[i]=false;
		}
	}
}
