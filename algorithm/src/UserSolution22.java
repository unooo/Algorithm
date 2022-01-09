

import java.util.ArrayList;
import java.util.HashMap;

class UserSolution22 {
	Node Right;
	Node Left;
	static int Mode = 0;
	HashMap<Character, ArrayList<Node>> hMap;

	void init(char mStr[]) {
		Right = new Node();
		Left = new Node();
		Left.right = Right;
		Right.left = Left;
		hMap = new HashMap<>();
		appendWord(mStr);

	}

	void appendRight(Node tpNode) {
		tpNode.left = Right.left;
		tpNode.right = Right;
		Right.left.right = tpNode;
		Right.left = tpNode;
	}

	void appendLeft(Node tpNode) {
		tpNode.right = Left.right;
		tpNode.left = Left;
		Left.right.left = tpNode;
		Left.right = tpNode;
	}

	void appendWord(char mWord[]) {
		int idx = 0;
		while (true) {
			if (mWord[idx] == '\0' || idx == 30001)
				break;
			Node temp = new Node();
			temp.ch = mWord[idx];
			if (Mode == 0)
				appendRight(temp);
			else
				appendLeft(temp);

			if (hMap.get(mWord[idx]) == null)
				hMap.put(mWord[idx], new ArrayList<Node>());
			hMap.get(mWord[idx]).add(temp);
			idx++;
		}

	}

	void cut(int k) {
		for (int i = 0; i < k; i++) {
			if (Mode == 0) {
				Node target = Right.left;
				if (hMap.get(target.ch) != null)
					hMap.get(target.ch).remove(target);
				Right.left.left.right = Right;
				Right.left = Right.left.left;
			} else {
				Node target = Left.right;
				if (hMap.get(target.ch) != null)
					hMap.get(target.ch).remove(target);
				Left.right.right.left = Left;
				Left.right = Left.right.right;
			}
		}

	}

	void reverse() {
		if (Mode == 0)
			Mode = 1;
		else
			Mode = 0;
	}

	int countOccurrence(char mWord[]) {
		int ret = 0;
		ArrayList<Node> candidateList = hMap.get(mWord[0]);
		if (candidateList == null)
			return 0;
		int len = candidateList.size();
		Outer: for (int i = 0; i < len; i++) {
			Node tpNode = candidateList.get(i);
			int idx = 0;
			while (true) {
				if (tpNode == null)
					continue Outer;

				if (mWord[idx] == '\0') {
					ret++;
					continue Outer;
				} else if (mWord[idx] != tpNode.ch)
					continue Outer;
				idx++;
				tpNode = Mode == 0 ? tpNode.right : tpNode.left;
			}
		}
		return ret;
	}

//	void print() {
//		Node target = null;
//		target = Mode == 0 ? Left : Right;
//		while (true) {
//			if (target.right == null) {
//				System.out.println();
//				return;
//			}
//			System.out.print(target.ch);
//			target = Mode == 0 ? target.right : target.left;
//		}
//
//	}

	static class Node {
		Node left;
		Node right;
		char ch;
	}
}
