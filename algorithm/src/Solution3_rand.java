

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class Solution3_rand {

	public static void main(String[] args) {
		int coffee_times[] = {4, 2, 4};
	
		long testCase=0;
		while(true) {
			System.out.println(testCase);
			int N = (int) (Math.random()*30000)+1;

			coffee_times=new int[N];
			for(int i = 0 ; i < N ; i ++) {
				coffee_times[i]=(int) (Math.random()*10000)+1;
			}

			int [] ret1=solution(N, coffee_times);
			int [] ret2=solution2(N, coffee_times);
			for(int i = 0 ; i < N ; i++) {
				if(ret1[i]!=ret2[i]) {
					System.out.println();
					break;
				}
			}
			testCase++;
		}
	

	}
	public static int[] solution(int N, int[] coffee_times) {
        int[] answer = new int[coffee_times.length];
        PriorityQueue<Coffee> machine = new PriorityQueue<>(new Comparator<Coffee>() {

            @Override
            public int compare(Coffee o1, Coffee o2) {

                if( o1.time > o2.time)
                    return 1;
                else if(o1.time==o2.time)
                    return o1.id-o2.id;
                else
                    return -1;
            }
        });
        int coffeeIdx = 0;
        int completeIdx = 0;
        while (true) {
            if (coffeeIdx >= coffee_times.length && machine.size() == 0) {
                break;
            }

            if (machine.size() != 0) {

                Coffee coffee = machine.poll();
                for (Coffee tpCoffee : machine) {
                    tpCoffee.time -= coffee.time;
                }
                answer[completeIdx]=coffee.id+1;
                completeIdx++;
            }

            if (machine.size() == N)
                continue;

            while (true) {
                if (coffeeIdx >= coffee_times.length || machine.size() == N)
                    break;
                machine.add(new Coffee(coffeeIdx, coffee_times[coffeeIdx]));
                coffeeIdx++;
            }

        }

        // return ret.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
	
	public static int[] solution2(int N, int[] coffee_times) {
		LinkedList<Integer> ret = new LinkedList<>();
		PriorityQueue<Coffee> machine = new PriorityQueue<>(new Comparator<Coffee>() {
			@Override
			public int compare(Coffee o1, Coffee o2) {

				if (o1.time > o2.time)
					return 1;
				else if (o1.time == o2.time)
					return o1.id - o2.id;
				else
					return -1;
			}
		});

		int coffeeIdx = 0;
		int now = 0;
		while (true) {
			if (coffeeIdx >= coffee_times.length && machine.isEmpty()) {
				break;
			}

			if (!machine.isEmpty()) {
				Coffee coffee = machine.poll();
				ret.add(coffee.id + 1);
				now = coffee.time;
			}

			/*
			 * if (machine.size() == N) continue;
			 */

			while (true) {
				if (coffeeIdx >= coffee_times.length || machine.size() >= N)
					break;
				machine.add(new Coffee(coffeeIdx, coffee_times[coffeeIdx] + now));
				coffeeIdx++;
			}

		}
		return ret.stream().mapToInt(Integer::intValue).toArray();
	}

	static class Coffee {
		int id, time;

		public Coffee(int id, int time) {
			super();
			this.id = id;
			this.time = time;
		}

	}
}
