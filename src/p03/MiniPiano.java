package p03;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Scanner;

public class MiniPiano {

    // 실제 소리 파일을 재생하는 함수
    public static void playSound(String filePath) {
        try {
            File soundPath = new File(filePath);
            if (soundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("사운드 파일을 찾을 수 없습니다: " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 숫자에 맞는 음계를 찾아주는 함수
    public static void playNote(int key) {
        if (key == 1) { playSound("sounds/do.wav"); System.out.println("🎵 도!"); }
        else if (key == 2) { playSound("sounds/re.wav"); System.out.println("🎵 레!"); }
        else if (key == 3) { playSound("sounds/mi.wav"); System.out.println("🎵 미!"); }
        else if (key == 4) { playSound("sounds/fa.wav"); System.out.println("🎵 파!"); }
        else if (key == 5) { playSound("sounds/sol.wav"); System.out.println("🎵 솔!"); }
        else if (key == 6) { playSound("sounds/la.wav"); System.out.println("🎵 라!"); }
        else if (key == 7) { playSound("sounds/si.wav"); System.out.println("🎵 시!"); } // '시' 추가 완료!
    }

    // 연주
    public static void autoPlay() {
        System.out.println("\n▶️ [학교종이땡땡땡] 자동 연주를 시작합니다!");

        int[] melody = {5, 5, 6, 6, 5, 5, 3};

        for (int i = 0; i < melody.length; i++) {
            int note = melody[i];
            playNote(note);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("연주 끝!\n");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("=================================");
        System.out.println("      🎹 자바 미니 피아노 🎹      ");
        System.out.println("=================================");
        System.out.println("건반 설명: 1(도) ~ 7(시) | 9(학교종이땡땡땡) | 0(종료)");

        while (true) {
            System.out.print("\n건반을 누르세요: ");
            int key = s.nextInt();

            if (key == 0) {
                System.out.println("피아노를 종료합니다.");
                break;
            } else if (key == 9) {
                autoPlay();
            } else if (key >= 1 && key <= 7) {
                playNote(key);
            } else {
                System.out.println("없는 건반입니다. 1~7 사이, 혹은 9번을 눌러주세요.");
            }
        }
        s.close();
    }
}