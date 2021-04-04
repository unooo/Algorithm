package Gabia;


import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution3_2 {

	public static void main(String[] args) {
		int coffee_times[] = { 4, 2, 2, 5, 3 };
		int N = 3;
		solution(N, coffee_times);

	}

	public static int[] solution(int N, int[] coffee_times) {
		int[] answer = new int[coffee_times.length];
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
		int completeIdx = 0;
		int now=0;
		while (true) {
			if (coffeeIdx >= coffee_times.length && machine.size() == 0) {
				break;
			}

			if (machine.size() != 0) {

				Coffee coffee = machine.poll();
				answer[completeIdx] = coffee.id + 1;
				completeIdx++;
				while(!machine.isEmpty()) {
					Coffee tpCoffee=machine.peek();
					if(tpCoffee.time-coffee.time==0) {
						machine.poll();
						answer[completeIdx] = tpCoffee.id + 1;
						completeIdx++;
					}else
						break;
				}
				
				for (Coffee tpCoffee : machine) {
					tpCoffee.time -= coffee.time;
				}
				
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

	static class Coffee {
		int id, time;

		public Coffee(int id, int time) {
			super();
			this.id = id;
			this.time = time;
		}

	}
}
