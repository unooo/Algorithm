

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

class UserSolution {
	int nowTime;
	TreeMap<Integer, TreeSet<Robot>> robotCenter = new TreeMap<>();
	Robot[] robotAry;
	Work[] workAry;
	int N;

	public void init(int N) {
		nowTime = 0;
		robotCenter.clear();
		robotAry = new Robot[50001];
		workAry = new Work[50001];
		this.N = N;
		TreeSet<Robot> robotCenterHatch = new TreeSet<>(new Mode() {
		});
		for (int i = 0; i < N; i++) {
			robotAry[i + 1] = new Robot(i + 1);
			robotCenterHatch.add(robotAry[i + 1]);
		}
		robotCenter.put(0, robotCenterHatch);

	}

	static interface Mode extends Comparator<Robot> {
		@Override
		default int compare(Robot o1, Robot o2) {
			// TODO Auto-generated method stub
			return o1.rID - o2.rID;
		}
	}

	public int callJob(int cTime, int wID, int mNum, int mOpt) {
		int ret = 0;
		//doDiff(cTime);
		Work work = new Work();
		work.wID = wID;
		workAry[wID] = work;
		int idx = 0;
		ArrayList<Robot> brokenRobotList = new ArrayList<>();
		Outer: while (true) {

			TreeSet<Robot> tempHatch = null;
			if (mOpt == 0) {
				tempHatch = robotCenter.pollFirstEntry().getValue();
			} else {
				tempHatch = robotCenter.pollLastEntry().getValue();
			}
			Inner: while (!tempHatch.isEmpty()) {
				Robot robot = tempHatch.pollFirst();
				if (robot.isBroken) {					
					brokenRobotList.add(robot);
					continue Inner;
				}
				robot.wID = wID;
				robot.workStartTime=cTime;
				work.rSet.add(robot.rID);
				idx++;
				ret += robot.rID;
				if (mNum == idx) {
					if (!tempHatch.isEmpty())
						robotCenter.put(robot.antiInteli, tempHatch);
					break Outer;
				}
			}
		}
		TreeSet<Robot> ts = robotCenter.computeIfAbsent(0, k -> new TreeSet<Robot>(new Mode() {
		}));
		ts.addAll(brokenRobotList);
		return ret;
	}

	public void returnJob(int cTime, int wID) {
		//doDiff(cTime);
		Work work = workAry[wID];
		for (Integer idx : work.rSet) {
			Robot robot = robotAry[idx];
			robot.antiInteli+=cTime-robot.workStartTime;
			robot.wID = -1;
			TreeSet<Robot> robotCenterHatch = robotCenter.computeIfAbsent(robot.antiInteli, k -> new TreeSet<>(new Mode() {
			}));
			robotCenterHatch.add(robot);
		}
		work.rSet.clear();
	}

	public void broken(int cTime, int rID) {
		//doDiff(cTime);
		Robot robot = robotAry[rID];
		if (robot.isBroken || robot.wID == -1)
			return;
		robot.isBroken = true;
Work work = workAry[robot.wID];
		work.rSet.remove(robot.rID);
		robot.wID = -1;

	}


	public void repair(int cTime, int rID) {
		//doDiff(cTime);
		Robot robot = robotAry[rID];
		if (!robot.isBroken)
			return;
		robot.wID = -1;
		robot.isBroken = false;
		robot.antiInteli=cTime;
		TreeSet<Robot> robotCenterHatch = robotCenter.computeIfAbsent(robot.antiInteli, k -> new TreeSet<>(new Mode() {
		}));
		robotCenterHatch.add(robot);
	}

	public int check(int cTime, int rID) {
		Robot robot = robotAry[rID];
		if (robot.isBroken)
			return 0;
		if (robot.wID != -1)
			return robot.wID * -1;

		return cTime-robot.antiInteli;
	}

//	public void doDiff(int cTime) {
//		int diff = cTime - nowTime;
//		TreeMap<Integer, TreeSet<Robot>> newRobotCenter = new TreeMap<>();
//		while (!robotCenter.isEmpty()) {
//			Entry<Integer, TreeSet<Robot>> entry = robotCenter.pollFirstEntry();
//			for (Robot robot : entry.getValue()) {
//				if (!robot.isBroken) {
//					robot.inteli += diff;
//				}
//				TreeSet<Robot> tpSet = newRobotCenter.computeIfAbsent(robot.inteli,
//						(k) -> new TreeSet<Robot>(new Mode() {
//						}));
//				tpSet.add(robot);
//			}
//		}
//		robotCenter = newRobotCenter;
//		nowTime = cTime;
//	}

	static class Robot {
		int rID;
		int wID;
		int workStartTime;
		int antiInteli;
		boolean isBroken;

		Robot(int rID) {
			this.rID = rID;
			wID = -1;
		}
	}

	static class Work {
		int wID;
		// ArrayList<Integer> rAry;
		HashSet<Integer> rSet;

		Work() {
			rSet = new HashSet<>();
		}
	}
}