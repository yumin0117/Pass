package p04;

import java.util.Random;
import java.util.Scanner;

public class MbtiTest {
    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();

    private QuestionData questionData = new QuestionData();
    private ScoreManager scoreManager = new ScoreManager();

    private String[] questions = questionData.getQuestions();
    private String optionsText = questionData.getOptionsText();

    private int[] order = new int[questions.length];

    public void start() {
        makeOrder();
        shuffleOrder();
        printStart();
        startTest();

        String mbti = scoreManager.calculateMBTI();

        printResult(mbti);
        sc.close();
    }

    public void makeOrder() {
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
    }

    public void shuffleOrder() {
        for (int i = order.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);

            int temp = order[i];
            order[i] = order[randomIndex];
            order[randomIndex] = temp;
        }
    }

    public void printStart() {
        System.out.println("======================================");
        System.out.println("          미니 MBTI 검사기");
        System.out.println("======================================");
        System.out.println("각 질문에 1~5 사이의 숫자로 답해주세요.");
    }

    public void startTest() {
        for (int i = 0; i < order.length; i++) {
            int qNum = order[i];

            System.out.println();
            System.out.println("--------------------------------------");
            System.out.println((i + 1) + " / " + questions.length);

            int answer = askQuestion(
                    sc,
                    questions[qNum],
                    optionsText
            );

            scoreManager.addScore(qNum, answer);
        }
    }

    public int askQuestion(Scanner sc, String question, String options) {
        int answer;

        while (true) {
            System.out.println(question);
            System.out.println(options);
            System.out.print("답변 입력 : ");

            answer = sc.nextInt();

            if (answer >= 1 && answer <= 5) {
                return answer;
            }

            System.out.println("1부터 5 사이의 숫자를 입력해주세요.");
        }
    }

    public void printResult(String mbti) {
        System.out.println();
        System.out.println("======================================");
        System.out.println("              검사 완료!");
        System.out.println("======================================");
        System.out.println();
        System.out.println("          ★ 당신의 MBTI는 ★");
        System.out.println("              [ " + mbti + " ]");
        System.out.println();
        System.out.println("======================================");
    }
}