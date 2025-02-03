import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    private static void start() {
        System.out.println("Начать игру или выйыти? [Y,N]");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String answer = scanner.nextLine();
//            if (answer.equals("N")) {
//                break;
//            }
            String word = getWord();
            System.out.println(word);
            slideWord(word);
        }
//        System.out.println("Игра окончена");
    }

    private static String getWord() {
        File file = new File("src/main/resources/words.txt");
        List<String> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine().trim().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Random random = new Random();
        String myWord = words.get(random.nextInt(words.size()));
        return myWord;
    }
    private static void slideWord(String word) {
        int countSlide = word.length();
        String[] slideWords = new String[countSlide];
        for (int i = 0; i < countSlide; i++) {
            slideWords[i] = "*";
        }
        System.out.println(slideWords.toString());
    }
    public static void main(String[] args) {
        start();
    }
}
