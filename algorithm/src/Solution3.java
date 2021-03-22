import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution3 {

	public static void main(String[] args) {
		String program = "line";
		String flag_rules[] = { "-s STRING", "-num NUMBER", "-e NULL", "-n ALIAS -num" };
		String commands[] = { "line -n 100 -s hi -e", "line -n 100 -e -num 150"};
		solution(program, flag_rules, commands);
	}

	static HashMap<String, String> flagRuleMap = new HashMap<>();
	static HashMap<String, String> aliasMap = new HashMap<>();

	public static boolean[] solution(String program, String[] flag_rules, String[] commands) {
		boolean[] answer = new boolean[commands.length];

		makeFlagRule(flag_rules);

		Outer: for (int i = 0; i < commands.length; i++) {
			if(isFlagDuplicate(commands[i])){
				answer[i] = false;
				continue;
			}
			String[] cmdTemp = commands[i].split(" ");
			if (!checkProgramName(program, cmdTemp[0])) {
				answer[i] = false;
				continue;
			}
			int tokenIdx = 1;

			while (true) {
				if (tokenIdx >= cmdTemp.length)
					break;

				String flag_name = cmdTemp[tokenIdx];
				tokenIdx++;
				String flag_argument_type = flagRuleMap.get(flag_name);
				if (flag_argument_type == null) {
					flag_argument_type = aliasMap.get(flag_name);
					if (flag_argument_type == null) {
						answer[i] = false;
						continue Outer;
					}
				}
				LinkedList<String> flag_argument_list = new LinkedList<>();
				String flag_argument = null;
				while (true) {
					if (flag_argument_type.equals("NULL")) {
						flag_argument_list.add("");
						break;
					} else {
						flag_argument = cmdTemp[tokenIdx++];
						flag_argument_list.add(flag_argument);
					}
					if (tokenIdx >= cmdTemp.length)
						break;
					if (flagRuleMap.containsKey(cmdTemp[tokenIdx]))
						break;
				}

				boolean ruleFlag = checkFlagRule(flag_argument_type, flag_argument_list);
				if (ruleFlag == false) {
					answer[i] = false;
					continue Outer;
				}

			}
			answer[i] = true;

		}

		return answer;
	}
	
	 /* -----------------------------------------------------
	    * 메서드명 : isFlagDuplicate 
	    * 기능       : flag_rule 에 따른 "<flag_name_1> ALIAS <flag_name_2>"라고 정의.
	    *         flag_name_1은 flag_name_2의 ALIAS입니다. 
	    *         따라서 flag_argument_type도 같고, flag_name_1과 flag_name_2는 동시에 입력할 수 없음을 체크하는 함수.
	    * 
	    * 초기화 파라미터 : 1. command - 사용자 입력 명령어
	    *리턴타입   : boolean(중복 존재시  true 리턴)
	    ----------------------------------------------------- */

	public static boolean isFlagDuplicate( String command){
		boolean ret =false;
		
		for(String key : aliasMap.keySet()){
			if(command.contains(key)&&command.contains(aliasMap.get(key))){
				return true;
			}
		}
		
		return ret;
	}

	   /* -----------------------------------------------------
	    * 메서드명 : makeFlagRule 
	    * 기능       : flag_rule 에 따른 
	    *          "<flag_name> <flag_argument_type>" 은 flagRuleMap을 초기화
	    *         "<flag_name_1> ALIAS <flag_name_2>"은 aliasMap을 초기화
	    * 파라미터 : flag_rules - "<flag_name> <flag_argument_type>" 를 정의한 String[] 
	    * 리턴타입 : boolean(성공적으로 초기화시 true 리턴;)
	    ----------------------------------------------------- */

	public static boolean makeFlagRule(String[] flag_rules) {
		if (flag_rules == null)
			return false;
		for (int i = 0; i < flag_rules.length; i++) {
			String[] fRuleTemp = flag_rules[i].split(" ");
			if (fRuleTemp[1].equals("ALIAS")) {
				if (fRuleTemp.length != 3)
					return false;
				aliasMap.put(fRuleTemp[0], fRuleTemp[2]);
			} else {
				if (fRuleTemp.length != 2)
					return false;
				flagRuleMap.put(fRuleTemp[0], fRuleTemp[1]);
			}
		}
		return true;
	}

	/* -----------------------------------------------------
	    * 메서드명 : checkFlagRule 
	    * 기능       : argument 타입에 맞는 argument가 맞는지 확인하는 메서드
	    * 파라미터 : flag_argument_type - flag에 해당하는 argument의 타입 
	    *         flag_argument_list - 사용자가 입력한 argument의 리스트 
	    * 리턴타입 : boolean(옳바른 경우 true 리턴)
	    ----------------------------------------------------- */

	public static boolean checkFlagRule(String flag_argument_type, LinkedList<String> flag_argument_list) {

		if (flag_argument_type == null || flag_argument_list == null)
			return false;

		for (String flag_argument : flag_argument_list)
			if (flag_argument == null)
				return false;

		Pattern pattern = null;
		Matcher matcher = null;

		for (String flag_argument : flag_argument_list) {
			switch (flag_argument_type) {
			case "NUMBER":
				if (flag_argument_list.size() > 1)
					return false;
			case "NUMBERS":
				pattern = Pattern.compile("[^0-9]");
				matcher = pattern.matcher(flag_argument);
				if (matcher.find()) {
					return false;
				}
				break;
			case "STRING":
				if (flag_argument_list.size() > 1)
					return false;
			case "STRINGS":
				pattern = Pattern.compile("[^a-zA-Z]");
				matcher = pattern.matcher(flag_argument);
				if (matcher.find()) {
					return false;
				}
				;
				break;
			case "NULL":
				if (!flag_argument.equals(""))
					return false;
				break;

			}
		}

		return true;

	}

	 /* -----------------------------------------------------
	    * 메서드명 :checkProgramName 
	    * 기능       : 프로그램 이름이 옳바른지 확인하는 메서드 
	    * 파라미터 : shellProgram - 리눅스 내장 프로그램 이름 
	    *         cmdProgram - 사용자 명령어 프로그램 이름 
	    * 리턴타입 : boolean(옳바른 경우 true 리턴)
	    ----------------------------------------------------- */

	public static boolean checkProgramName(String shellProgram, String cmdProgram) {
		if (shellProgram == null || cmdProgram == null)
			return false;

		if (shellProgram.equals(cmdProgram))
			return true;
		else
			return false;
	}

}
