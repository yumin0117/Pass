package p02;

import java.util.Random;
import java.util.Scanner;

public class treasure_hunt {
    static void main() {
        Scanner s = new Scanner(System.in);
        Random random = new Random();

        int[][] map = new int[5][5];

        int Row = random.nextInt(5);
        int Col = random.nextInt(5);
        map[Row][Col] = 1;

        System.out.println("====================");
        System.out.println("       보물찾기       ");
        System.out.println("====================");

        int inputRow = 0;
        int inputCol = 0;
        while (true){
            System.out.println("\n--- 현재 지도 ---");
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (map[r][c] == 0 || map[r][c] == 1) {
                        System.out.print("? ");
                    } else if (map[r][c] == 2) {
                        System.out.print("X ");
                    }
                }
                System.out.println();
            }
            System.out.println("-----------------");

            System.out.print("\n파볼 땅의 행을 입력하세요: ");
            inputRow = s.nextInt() - 1;
            System.out.print("파볼 땅의 열을 입력하세요: ");
            inputCol = s.nextInt() - 1;

            if(inputRow > 4 || inputCol > 4){
                System.out.println("잘못된 값입니다. 1부터 5까지의 숫자 중 하나만 다시 입력해주세요.");
                continue;
            }

            if(map[inputRow][inputCol] == 1){
                System.out.println("축하합니다! 보물을 찾으셨습니다!");
                break;
            }else if(map[inputRow][inputCol] == 0){
                System.out.println("꽝입니다! 다시 도전해보세요!");
                map[inputRow][inputCol] = 2;
            }else{
                System.out.println("이미 판 땅입니다! 다시 선택해주세요!");
            }
        }

        s.close();
    }
}
