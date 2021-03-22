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
	    * �޼���� : isFlagDuplicate 
	    * ���       : flag_rule �� ���� "<flag_name_1> ALIAS <flag_name_2>"��� ����.
	    *         flag_name_1�� flag_name_2�� ALIAS�Դϴ�. 
	    *         ���� flag_argument_type�� ����, flag_name_1�� flag_name_2�� ���ÿ� �Է��� �� ������ üũ�ϴ� �Լ�.
	    * 
	    * �ʱ�ȭ �Ķ���� : 1. command - ����� �Է� ��ɾ�
	    *����Ÿ��   : boolean(�ߺ� �����  true ����)
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
	    * �޼���� : makeFlagRule 
	    * ���       : flag_rule �� ���� 
	    *          "<flag_name> <flag_argument_type>" �� flagRuleMap�� �ʱ�ȭ
	    *         "<flag_name_1> ALIAS <flag_name_2>"�� aliasMap�� �ʱ�ȭ
	    * �Ķ���� : flag_rules - "<flag_name> <flag_argument_type>" �� ������ String[] 
	    * ����Ÿ�� : boolean(���������� �ʱ�ȭ�� true ����;)
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
	    * �޼���� : checkFlagRule 
	    * ���       : argument Ÿ�Կ� �´� argument�� �´��� Ȯ���ϴ� �޼���
	    * �Ķ���� : flag_argument_type - flag�� �ش��ϴ� argument�� Ÿ�� 
	    *         flag_argument_list - ����ڰ� �Է��� argument�� ����Ʈ 
	    * ����Ÿ�� : boolean(�ǹٸ� ��� true ����)
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
	    * �޼���� :checkProgramName 
	    * ���       : ���α׷� �̸��� �ǹٸ��� Ȯ���ϴ� �޼��� 
	    * �Ķ���� : shellProgram - ������ ���� ���α׷� �̸� 
	    *         cmdProgram - ����� ��ɾ� ���α׷� �̸� 
	    * ����Ÿ�� : boolean(�ǹٸ� ��� true ����)
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
