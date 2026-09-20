package p04;

public class ScoreManager {
    private int[] scores = new int[4];

    public void addScore(int qNum, int answer) {
        int scoreIndex = qNum / 3;
        scores[scoreIndex] += answer;
    }

    public String calculateMBTI() {
        String[] firstType = {"E", "S", "T", "J"};
        String[] secondType = {"I", "N", "F", "P"};

        String mbti = "";

        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > 9) {
                mbti += firstType[i];
            } else {
                mbti += secondType[i];
            }
        }

        return mbti;
    }
}
