import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    private static void start() {
        int life = 0;
        Guillotine guillotine = new Guillotine();
        System.out.println("Начать игру или выйыти? [Y,N]");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        String word = getWord();
        slideWord(word);
        while (life <= 7) {
            System.out.print("Введите букву: ");
            String answerChar = scanner.nextLine();
            if (!word.contains(answerChar)) {
                System.out.println("Вы не отгдадили букву");
                switch (life) {
                    case 0:
                        guillotine.firstMissTake();
                        break;
                    case 1:
                        guillotine.secondMissTake();
                        break;
                    case 2:
                        guillotine.threeMissTake();
                        break;
                    case 3:
                        guillotine.forMissTake();
                        break;
                    case 4:
                        guillotine.fiveMissTake();
                        break;
                    case 5:
                        guillotine.sixMissTake();
                        break;
                    case 6:
                        guillotine.sevenMissTake();
                        break;
                    case 7:
                        System.out.println("Вы проиграли");
                        break;
                }
                life++;
                continue;
            }
            System.out.println("Вы отгадали букву: " + answerChar);

        }

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
        Arrays.stream(slideWords).forEach(System.out::print);
    }

    public static void main(String[] args) {
        start();
    }
}
