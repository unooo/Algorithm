

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
 
public class UserSolution2 {
    static class Robot implements Comparable<Robot> {
        int rID;
        int antiIQ;
        int wID;
        boolean broken;
         
        Robot(int rID) {
            this.rID = rID;
        }
         
        @Override
        public int compareTo(Robot o) {
            return rID - o.rID;
        }
    }
 
    static class Job {
        HashSet<Robot> bots;
        int startTime;
    }
 
    static class MinMaxPQ<T extends Comparable<T>> {
 
        TreeMap<Integer, TreeSet<T>> map = new TreeMap<>();
         
        public void insert(int key, T value) {
            map.computeIfAbsent(key, k -> new TreeSet<>()).add(value);
        }
 
        public HashSet<T> getN(int n, boolean max) {
            HashSet<T> ret = new HashSet<T>();
            while (n > 0) {
                Entry<Integer, TreeSet<T>> entry = max ? map.lastEntry() : map.firstEntry();
                TreeSet<T> set = entry.getValue();
                if (set.size() > n) {
                    while (n-- > 0) {
                        ret.add(set.pollFirst());
                    }
                } else {
                    ret.addAll(set);
                    n -= set.size();
                    map.remove(entry.getKey());
                }
            }
            return ret;
        }
    }
     
    MinMaxPQ<Robot> avail;
    Robot[] robots = new Robot[50001];
    Job[] jobs = new Job[50001];
 
    void init(int N) {
        avail = new MinMaxPQ<>();
        TreeSet<Robot> all = new TreeSet<>();
        for (int i = 1; i <= N; i++) {
            all.add(robots[i] = new Robot(i));
        }
        avail.map.put(0, all);
    }
 
    int callJob(int cTime, int wID, int mNum, int mOpt) {
        Job j = new Job();
        j.bots = avail.getN(mNum, mOpt == 1);
        j.startTime = cTime;
        jobs[wID] = j;
        int idSum = 0;
        for (Robot r : j.bots) {
            r.wID = wID;
            idSum += r.rID;
        }
        return idSum;
    }
 
    void returnJob(int cTime, int wID) {
        Job j = jobs[wID];
        int antiIQ = cTime - j.startTime;
        for (Robot r : j.bots) {
            r.antiIQ += antiIQ;
            r.wID = 0;
            avail.insert(r.antiIQ, r);
        }
    }
 
    void broken(int cTime, int rID) {
        Robot r = robots[rID];
        if (!r.broken && r.wID > 0) {
            jobs[r.wID].bots.remove(r);
            r.wID = 0;
            r.broken = true;
        }
    }
 
    void repair(int cTime, int rID) {
        Robot r = robots[rID];
        if (r.broken) {
            r.broken = false;
            r.antiIQ = cTime;
            avail.insert(r.antiIQ, r);
        }
    }
 
    int check(int cTime, int rID) {
        Robot r = robots[rID];
        if (r.broken) {
            return 0;
        } else if (r.wID > 0) {
            return -r.wID;
        } else {
            return cTime - r.antiIQ;
        }
    }
}
