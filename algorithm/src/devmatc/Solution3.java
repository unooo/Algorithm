package devmatc;

import java.util.HashMap;
import java.util.LinkedList;

public class Solution3 {
	public static void main(String[] args) {
		String[] enroll = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" };
		String[] referral = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" };
		String[] seller = { "young", "john", "tod", "emily", "mary" };
		int[] amount = { 12, 4, 2, 5, 10 };
		solution(enroll, referral, seller, amount);
	}

	public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int[] answer = {};
		Member head = new Member("center", new LinkedList<Member>(), 0);
		HashMap<String, Member> hMap = new HashMap<>();
		hMap.put("center",head);
		for (int i = 0; i < enroll.length; i++) {
			Member newMember = new Member(enroll[i], new LinkedList<>(), 0);
			hMap.put(enroll[i], newMember);

			String referralName = referral[i];
			if (referralName.equals("-")) {
				head.childList.add(newMember);
				newMember.parent = head;
				continue;
			}
			Member temp = head;
			initDfs(temp, newMember, referralName);
		}
System.out.println();
		for (int i = 0; i < seller.length; i++) {
			String sellerName = seller[i];
			int income = amount[i] * 100;
			while (true) {
				int parentMoney = (int) (income * 0.1);
				int myMoney = income - parentMoney;
				Member sellerMem = hMap.get(sellerName);
				sellerMem.asset += myMoney;
				if (parentMoney == 0||sellerMem.parent==null)
					break;
				sellerName = sellerMem.parent.name;
				income=parentMoney;
			}

		}
		answer= new int[enroll.length];
		for(int i = 0 ; i < enroll.length;i++) {
			String name=enroll[i];
			answer[i]=hMap.get(name).asset;
		}
				

		return answer;
	}

	public static void initDfs(Member temp, Member newMember, String referralName) {

		for (Member chTemp : temp.childList) {
			if (chTemp.name.equals(referralName)) {
				chTemp.childList.add(newMember);
				newMember.parent = chTemp;
				return;
			}
		}

		for (Member chTemp : temp.childList) {
			initDfs(chTemp, newMember, referralName);
		}

	}

	static class Member {
		String name;
		LinkedList<Member> childList;
		int asset;
		Member parent;

		public Member(String name, LinkedList<Member> childList, int asset) {
			super();
			this.name = name;
			this.childList = childList;
			this.asset = asset;
		}

	}
}
