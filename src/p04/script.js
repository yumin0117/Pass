// 질문 데이터
// 0~2 : E / I
// 3~5 : S / N
// 6~8 : T / F
// 9~11 : J / P

const questions = [
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
];

// 질문 번호 배열
let order = [];

// 사용자 답변 저장
let answers = new Array(questions.length).fill(0);

// HTML 요소
const questionList = document.getElementById("questionList");
const resultBtn = document.getElementById("resultBtn");
const warning = document.getElementById("warning");
const resultBox = document.getElementById("resultBox");
const mbtiResult = document.getElementById("mbtiResult");
const restartBtn = document.getElementById("restartBtn");


// 질문 번호 배열 만들기
function makeOrder() {
    order = [];

    for (let i = 0; i < questions.length; i++) {
        order.push(i);
    }
}


// 질문 순서 랜덤으로 섞기
function shuffleOrder() {
    for (let i = order.length - 1; i > 0; i--) {
        let randomIndex = Math.floor(Math.random() * (i + 1));

        let temp = order[i];
        order[i] = order[randomIndex];
        order[randomIndex] = temp;
    }
}


// 질문 화면에 출력
function showQuestions() {
    questionList.innerHTML = "";

    for (let i = 0; i < order.length; i++) {
        let qNum = order[i];

        let questionBox = document.createElement("div");
        questionBox.className = "question-box";

        questionBox.innerHTML = `
            <div class="question-number">
                ${i + 1} / ${questions.length}
            </div>

            <div class="question-text">
                ${questions[qNum]}
            </div>

            <div class="options">
                ${createOptions(qNum)}
            </div>

            <div class="option-description">
                <span>매우 아니다</span>
                <span>매우 그렇다</span>
            </div>
        `;

        questionList.appendChild(questionBox);
    }

    addOptionEvents();
}


// 1~5 선택지 만들기
function createOptions(qNum) {
    let html = "";

    for (let score = 1; score <= 5; score++) {
        html += `
            <label class="option-label">
                <input
                    type="radio"
                    name="question${qNum}"
                    value="${score}"
                    data-question="${qNum}"
                >
                <span>${score}</span>
            </label>
        `;
    }

    return html;
}


// 선택지 클릭 이벤트
function addOptionEvents() {
    let radioButtons = document.querySelectorAll(
        'input[type="radio"]'
    );

    radioButtons.forEach(function(radio) {
        radio.addEventListener("change", function() {
            let qNum = Number(this.dataset.question);
            let score = Number(this.value);

            answers[qNum] = score;

            warning.textContent = "";
        });
    });
}


// 모든 질문에 답했는지 확인
function checkAnswers() {
    for (let i = 0; i < answers.length; i++) {
        if (answers[i] === 0) {
            return false;
        }
    }

    return true;
}


// 점수 계산
function calculateMBTI() {
    let scores = [0, 0, 0, 0];

    for (let qNum = 0; qNum < answers.length; qNum++) {
        let scoreIndex = Math.floor(qNum / 3);

        scores[scoreIndex] += answers[qNum];
    }

    let firstType = ["E", "S", "T", "J"];
    let secondType = ["I", "N", "F", "P"];

    let mbti = "";

    for (let i = 0; i < scores.length; i++) {
        if (scores[i] > 9) {
            mbti += firstType[i];
        } else {
            mbti += secondType[i];
        }
    }

    return mbti;
}


// 결과 확인 버튼
resultBtn.addEventListener("click", function() {
    if (!checkAnswers()) {
        warning.textContent =
            "아직 답하지 않은 질문이 있습니다. 모든 질문에 답해주세요.";

        return;
    }

    let mbti = calculateMBTI();

    mbtiResult.textContent = mbti;

    resultBox.style.display = "block";

    resultBox.scrollIntoView({
        behavior: "smooth"
    });
});


// 다시 검사하기
restartBtn.addEventListener("click", function() {
    answers = new Array(questions.length).fill(0);

    warning.textContent = "";

    resultBox.style.display = "none";

    makeOrder();
    shuffleOrder();
    showQuestions();

    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
});


// 처음 실행
function start() {
    makeOrder();
    shuffleOrder();
    showQuestions();
}

start()