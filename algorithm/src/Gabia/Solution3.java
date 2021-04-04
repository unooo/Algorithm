package Gabia;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Solution3 {

	public static void main(String[] args) {
		int coffee_times[] = {4, 2, 4};
		int N = 3;
		solution(N, coffee_times);

	}

	public static int[] solution(int N, int[] coffee_times) {
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
