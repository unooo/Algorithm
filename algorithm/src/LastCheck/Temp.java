package LastCheck;


import java.util.*;

public class Temp {

   static int result = 0;

   //////////////////// ↑, ↖, ←, ↙, ↓, ↘, →, ↗
   static int[] dir = { 1, 2, 3, 4, 5, 6, 7, 8 };
   static int[][] fishMap = new int[4][4];
   static int[][] dir_fishMap = new int[4][4];

   static int[] dir_y = { -1, -1, 0, 1, 1, 1, 0, -1 };
   static int[] dir_x = { 0, -1, -1, -1, 0, 1, 1, 1 };

   public static class Shark {
      int dir, y, x, point = 0;
   }

   static int cur_fish_y = 0;
   static int cur_fish_x = 0;
   static int cur_fish_dir = 0;

   // 상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 구해보자.
   // 상어가 이동할 있는 경우는
   // 상어 방향에 물고기가 없거나, map을 벗어난 경우
   // 이동방향에 여러개의 물고기가 있으면 하나씩 들어간다.

   public static void main(String arg[]) {
      Scanner sc = new Scanner(System.in);

      for (int y = 0; y < 4; y++) {
         for (int x = 0; x < 4; x++) {
            fishMap[y][x] = sc.nextInt();
            dir_fishMap[y][x] = sc.nextInt();
         }
      }

      Shark shark = new Shark();
      shark.dir = dir_fishMap[0][0];
      shark.y = 0;
      shark.x = 0;
      fishMap[0][0] = 0;

      shark.point += fishMap[0][0];

      solve(shark);

      System.out.println(result);

      sc.close();
   }

   public static void findFish(int fishNumber) {
      for (int y = 0; y < 4; y++) {
         for (int x = 0; x < 4; x++) {

            if (fishMap[y][x] == fishNumber) {
               cur_fish_dir = dir_fishMap[y][x];
               cur_fish_y = y;
               cur_fish_x = x;
               return;
            }

         }
      }

      cur_fish_dir = 0;
   }

   public static void fishMove(int fishNumber, Shark shark) {

      findFish(fishNumber);

      if (fishNumber > 16) {
         return;
      }

      if (cur_fish_dir == 0) {

      } else {

         // 방향 8개인데 갈 수 있는 방향이 없으면
         // 반시계 방향으로 45도씩 돌아간다.
         // ↑, ↖, ←, ↙, ↓, ↘, →, ↗ 순서가 이럲다.
         for (int i = cur_fish_dir; i < cur_fish_dir + 8; i++) {

            int index = i - 1;

            if (index > 7) {
               index -= 8;
            }

            // System.out.println("current i : " + i + " index : " + index);

            int new_fish_y = cur_fish_y + dir_y[index];
            int new_fish_x = cur_fish_x + dir_x[index];

            // 영역 벗어나면 continue
            if (new_fish_y < 0 || new_fish_y > 3 || new_fish_x < 0 || new_fish_x > 3) {
               continue;
            }

            // 상어 좌표이면 continue
            if (shark.y == new_fish_y && shark.x == new_fish_x) {
               continue;
            }

            int temp_fish, temp_dir = 0;

            temp_fish = fishMap[new_fish_y][new_fish_x];
            fishMap[new_fish_y][new_fish_x] = fishMap[cur_fish_y][cur_fish_x];
            fishMap[cur_fish_y][cur_fish_x] = temp_fish;

            temp_dir = dir_fishMap[new_fish_y][new_fish_x];
            dir_fishMap[new_fish_y][new_fish_x] = dir_fishMap[cur_fish_y][cur_fish_x];
            dir_fishMap[cur_fish_y][cur_fish_x] = temp_dir;


            break;
         }
      }

      fishMove(fishNumber + 1, shark);

   }

   public static void solve(Shark shark) {

      int[][] copy_fishMap = new int[4][4];
      int[][] copy_dir_fishMap = new int[4][4];

      for (int y = 0; y < 4; y++) {
         for (int x = 0; x < 4; x++) {
            copy_fishMap[y][x] = fishMap[y][x];
         }
      }

      for (int y = 0; y < 4; y++) {
         for (int x = 0; x < 4; x++) {
            copy_dir_fishMap[y][x] = dir_fishMap[y][x];
         }
      }
      
      // 물고기 움직이고
      fishMove(1, shark);

      // 상어가 움직인다.
      // 상어 방향이 물고기가 몇마리인지 확인한다. 물고기는 최대 3마리가 존재
      // 물고기가 없으면 종료


      System.out.println();
      System.out.println("상어움직이자");
      System.out.println();

      Shark cur_shark = new Shark();
      cur_shark = shark;

      for (int i = 1; i <= 3; i++) {

         int new_shark_y = cur_shark.y + dir_y[cur_shark.dir - 1] * i;
         int new_shark_x = cur_shark.x + dir_x[cur_shark.dir - 1] * i;
         
         // 영역 벗어나면 continue
         if (new_shark_y < 0 || new_shark_y > 3 || new_shark_x < 0 || new_shark_x > 3) {
            continue;
         }

         if (fishMap[new_shark_y][new_shark_x] == 0) {
            continue;
         }
         

         int point = fishMap[new_shark_y][new_shark_x];

         cur_shark.dir = dir_fishMap[new_shark_y][new_shark_x];
         cur_shark.point += point;
         fishMap[new_shark_y][new_shark_x] = 0;
         cur_shark.y = new_shark_y;
         cur_shark.x = new_shark_x;

         solve(cur_shark);

         cur_shark = shark;
         
         for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
               fishMap[y][x] = copy_fishMap[y][x];
            }
         }

         for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
               dir_fishMap[y][x] = copy_dir_fishMap[y][x];
            }
         }

      }

      maxValue(shark.point);
   }

   public static void maxValue(int val) {
      result = Math.max(result, val);
   }

}