package p04;

public class QuestionData {
    private String[] questions = {
            // E / I
            "사람들과 함께 있을 때 에너지가 생긴다.",
            "처음 만난 사람에게 먼저 말을 거는 편이다.",
            "혼자 있는 것보다 여러 사람과 어울리는 것을 좋아한다.",

            // S / N
            "상상보다는 실제 경험과 사실을 중요하게 생각한다.",
            "설명을 들을 때 구체적인 예시가 있는 것이 좋다.",
            "미래의 가능성보다 현재 상황에 집중하는 편이다.",

            // T / F
            "결정을 내릴 때 감정보다 논리를 중요하게 생각한다.",
            "친구의 고민을 들으면 공감보다 해결 방법을 먼저 생각한다.",
            "사람의 기분보다 객관적인 사실이 더 중요하다고 생각한다.",

            // J / P
            "일을 시작하기 전에 계획을 세우는 편이다.",
            "약속이나 과제는 미리 준비하는 것이 마음이 편하다.",
            "계획 없이 즉흥적으로 행동하는 것보다 정해진 일정이 좋다."
    };

    private String optionsText =
            "[ 1.매우 아니다 | 2.아니다 | 3.보통 | 4.그렇다 | 5.매우 그렇다 ]";

    public String[] getQuestions() {
        return questions;
    }

    public String getOptionsText() {
        return optionsText;
    }
}
