
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

class UserSolution_파일다운 {

	HashMap<String, Node> nodeHMap = new HashMap<>();
	ArrayList<Node> sysFileList = new ArrayList<>();
	int nodeIdx=1;
	void init() {
		nodeHMap.clear();
		sysFileList.clear();
		Node rootNode = new Node();
		rootNode.isSysFile = false;
		rootNode.fileFullPath = new String("/");
		rootNode.connectedList = new ArrayList<>();
		rootNode.id=0;
		nodeHMap.put(rootNode.fileFullPath, rootNode);
        nodeIdx=1;
	}

	void makeDir(char path[], char dirname[]) {

		String pathStr = charAryToStr(path);
		String dirNamd = charAryToStr(dirname);
		String fullPath = pathStr + dirNamd + "/";
		
		Node node = nodeHMap.get(fullPath);
		node = new Node();
		node.connectedList = new ArrayList<>();
		node.fileFullPath = fullPath;
		node.isSysFile = false;
        node.id=nodeIdx;
        nodeIdx++;

		Node upperNode = nodeHMap.get(pathStr);
		node.connectedList.add(upperNode);
		upperNode.connectedList.add(node);
		nodeHMap.put(node.fileFullPath, node);
	}

	void makeLink(char path1[], char path2[]) {
		String node1Str = charAryToStr(path1);
		String node2Str = charAryToStr(path2);
		Node node1 = nodeHMap.get(node1Str);
		Node node2 = nodeHMap.get(node2Str);
		node2.connectedList.add(node1);
	}

	void makeSystemFile(char path[]) {
		String pathStr = charAryToStr(path);
		Node node = nodeHMap.get(pathStr);
		node.isSysFile = true;
		sysFileList.add(node);
	}

	void findDownloadDir(char downloadPath[]) {

		Deque<Node> que = new ArrayDeque<>();
        int visit[]=new int[10000];
		String maxStr = null;
		int max = Integer.MIN_VALUE;
		for (Node tpNode : sysFileList) {
			que.add(tpNode);
            visit[tpNode.id]=1;
			maxStr = tpNode.fileFullPath;
		}

		while (!que.isEmpty()) {
			Node node = que.poll();
			for (Node nextNode : node.connectedList) {
                int step = visit[nextNode.id];
				if (step == 0) {
					int nextStep = visit[node.id]+1;
					visit[nextNode.id]=nextStep;
					que.add(nextNode);
					if (max < nextStep) {
						max = nextStep;
						maxStr = nextNode.fileFullPath;
					}
				}
			}
		}
		int strLen = maxStr.length();
		for (int i = 0; i < strLen; i++) {
			downloadPath[i] = maxStr.charAt(i);
		}
		downloadPath[strLen] = 0;
	}

	String charAryToStr(char path[]) {
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		while (true) {
			if (path[idx] == '\0')
				break;
			sb.append(path[idx]);
			idx++;
		}
		return sb.toString();
	}

	static class Node {
		String fileFullPath;
		boolean isSysFile;
        int id;
		ArrayList<Node> connectedList;
	}
}