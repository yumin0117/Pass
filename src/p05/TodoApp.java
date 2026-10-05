package p05;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// 1. 데이터 객체
class Task {
    int id;
    String date;
    String content;
    boolean isDone;

    public Task(int id, String date, String content, boolean isDone) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.isDone = isDone;
    }

    public String toFileFormat() {
        return id + "," + date + "," + content + "," + isDone;
    }
}

// 2. 로직 처리
class TaskManager {
    private ArrayList<Task> taskList;
    private final String FILE_NAME = "todo_data.txt";
    private int nextId = 1;

    public TaskManager() {
        taskList = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    int id = Integer.parseInt(data[0]);
                    String date = data[1];
                    String content = data[2];
                    boolean isDone = Boolean.parseBoolean(data[3]);

                    taskList.add(new Task(id, date, content, isDone));
                    if (id >= nextId) nextId = id + 1;
                }
            }
        } catch (IOException e) {
            System.out.println("[오류] 파일을 읽어오는 중 문제가 발생했습니다.");
        }
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : taskList) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("[오류] 파일을 저장하는 중 문제가 발생했습니다.");
        }
    }

    public void addTask(String date, String content) {
        taskList.add(new Task(nextId++, date, content, false));
        saveToFile();
        System.out.println("[" + date + "] '" + content + "' 항목이 추가되었습니다.");
    }

    public void deleteTask(int id) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).id == id) {
                System.out.println("[삭제] '" + taskList.get(i).content + "' 항목이 삭제되었습니다.");
                taskList.remove(i);
                saveToFile();
                return;
            }
        }
        System.out.println("[알림] 해당 ID의 항목을 찾을 수 없습니다.");
    }

    public void completeTask(int id) {
        for (Task task : taskList) {
            if (task.id == id) {
                if (task.isDone) {
                    System.out.println("[알림] 이미 완료된 항목입니다.");
                    return;
                }
                task.isDone = true;
                saveToFile();
                System.out.println("[완료] " + task.content + " 항목을 완료했습니다!");
                return;
            }
        }
        System.out.println("[알림] 해당 ID의 항목을 찾을 수 없습니다.");
    }

    public void undoTask(int id) {
        for (Task task : taskList) {
            if (task.id == id) {
                if (!task.isDone) {
                    System.out.println("[알림] 아직 완료되지 않은 항목입니다.");
                    return;
                }
                task.isDone = false;
                saveToFile();
                System.out.println("[취소] " + task.content + " 항목의 완료 상태를 취소했습니다.");
                return;
            }
        }
        System.out.println("[알림] 해당 ID의 항목을 찾을 수 없습니다.");
    }

    public void printList(String targetDate) {
        System.out.println("\n========= TO-DO LIST =========");

        int totalCount = 0;
        int doneCount = 0;

        for (Task task : taskList) {
            if (targetDate.equals("all") || task.date.equals(targetDate)) {
                totalCount++;
                String status = task.isDone ? "[V]" : "[ ]";
                System.out.printf("%d. [%s] %s %s\n", task.id, task.date, status, task.content);
                if (task.isDone) doneCount++;
            }
        }

        if (totalCount == 0) {
            System.out.println("해당 조건에 등록된 할 일이 없습니다.");
            System.out.println("==============================\n");
            return;
        }

        int percent = (int) ((double) doneCount / totalCount * 100);
        int barLength = percent / 10;

        System.out.print("\n진행률: [");
        for (int i = 0; i < 10; i++) {
            if (i < barLength) System.out.print("■");
            else System.out.print("□");
        }
        System.out.println("] " + percent + "%");
        System.out.println("==============================\n");
    }
}

// 3. 메인 실행 및 날짜 검증 로직
public class TodoApp {

    // [자바 내장 날짜 API 적용]
    public static boolean isValidDate(String dateStr) {
        try {
            // "M.d" 포맷을 사용하면 "10.05" 뿐만 아니라 "1.5", "05.1" 등도 유연하게 해석합니다.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M.d");

            // 자바 내장 객체인 MonthDay가 1~12월, 그리고 달마다 다른 마지막 날짜를 완벽히 검증합니다.
            MonthDay.parse(dateStr, formatter);

            return true;
        } catch (DateTimeParseException e) {
            // 파싱(변환)에 실패하면 유효하지 않은 날짜(예: 13.01, 2.30 등)로 간주합니다.
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        System.out.println("🔥 개발자용 터미널 To-Do 매니저 시작 🔥");
        System.out.println("기본 명령어: list, add [월.일] [내용], done [번호], undo [번호], del [번호], exit");
        System.out.println("사용 예시: add 10.05 자바과제 / list 10.05");

        while (true) {
            System.out.print("\ntodo> ");
            String input = sc.nextLine().trim();

            if (input.equals("exit")) {
                System.out.println("프로그램을 종료합니다. 데이터는 안전하게 백업되었습니다.");
                break;
            }
            else if (input.startsWith("list")) {
                String[] parts = input.split(" ");
                if (parts.length > 1) {
                    if (isValidDate(parts[1])) {
                        manager.printList(parts[1]);
                    } else {
                        System.out.println("[오류] 올바른 날짜 형식이 아닙니다. (예: 10.05)");
                    }
                } else {
                    manager.printList("all");
                }
            }
            else if (input.startsWith("add ")) {
                String[] parts = input.split(" ", 3);
                if (parts.length < 3) {
                    System.out.println("[오류] 포맷을 맞춰주세요. 예: add 10.05 자바과제");
                } else {
                    if (isValidDate(parts[1])) {
                        manager.addTask(parts[1], parts[2]);
                    } else {
                        System.out.println("[오류] 존재하지 않는 날짜입니다! (자바 내장 함수로 검증됨)");
                    }
                }
            }
            else if (input.startsWith("done ")) {
                try {
                    manager.completeTask(Integer.parseInt(input.substring(5)));
                } catch (Exception e) {
                    System.out.println("[오류] 번호를 정확히 입력해주세요. (예: done 1)");
                }
            }
            else if (input.startsWith("undo ")) {
                try {
                    manager.undoTask(Integer.parseInt(input.substring(5)));
                } catch (Exception e) {
                    System.out.println("[오류] 번호를 정확히 입력해주세요. (예: undo 1)");
                }
            }
            else if (input.startsWith("del ")) {
                try {
                    manager.deleteTask(Integer.parseInt(input.substring(4)));
                } catch (Exception e) {
                    System.out.println("[오류] 번호를 정확히 입력해주세요. (예: del 1)");
                }
            }
            else {
                System.out.println("[알림] 알 수 없는 명령어입니다.");
            }
        }
        sc.close();
    }
}