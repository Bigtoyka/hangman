import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;
import java.util.Random;

public class Main {
    private static final String PATH_TO_FILE = "src/main/resources/words.txt";
    public static void main(String[] args) {
        System.out.println("Игра началась");
        List<String> list = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        Set<Character> alreadyEnterWords = new HashSet<>();
        Guillotine guillotine = new Guillotine();

        Scanner scan = new Scanner(System.in);
        File file = new File(PATH_TO_FILE);
        String word = getWordsFromFile(list, file);
        addCharOfWordToSet(set, word);
        int life = 0;
        while (true) {
            if (set.isEmpty() || life < 1) {
                System.out.println("Хотите начать новую игру? press [Y] - если хотите");
                String answer = scan.nextLine();
                if (answer.equals("Y")) {
                    life = 7;
                    word = getWordsFromFile(list, file);
                    addCharOfWordToSet(set, word);
                    continue;
                }
                break;
            }
            System.out.println("Всего букв: " + word.length());
            revealHiddenLetter(word, set);
            System.out.println("\nВведите букву: ");
            String c = scan.nextLine().toLowerCase().trim();
            if (c.length() != 1 || !Character.isLetter(c.charAt(0)) || alreadyEnterWords.contains(c.charAt(0))) {
                System.out.println("Вы вводите не букву или букву, которую уже вводили!");
                continue;
            }
            alreadyEnterWords.add(c.charAt(0));
            if (set.contains(c.charAt(0))) {
                System.out.println("Вы отгадали букву!");
                set.remove(c.charAt(0));
            } else {
                selectGuillotine(life, guillotine);
                life--;
            }
        }
    }

    public static String getWordsFromFile(List<String> list, File file) {
        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine().trim().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    public static void addCharOfWordToSet(Set set, String word) {
        for (int i = 0; i < word.length(); i++) {
            set.add(word.charAt(i));
        }
    }

    public static void revealHiddenLetter(String word, Set<Character> set) {
        for (int i = 0; i < word.length(); i++) {
            if (!set.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
            } else {
                System.out.print("*");
            }
        }
    }

    public static void selectGuillotine(int life, Guillotine guillotine) {
        switch (life) {
            case 7:
                guillotine.firstMissTake();
                break;
            case 6:
                guillotine.secondMissTake();
                break;
            case 5:
                guillotine.threeMissTake();
                break;
            case 4:
                guillotine.forMissTake();
                break;
            case 3:
                guillotine.fiveMissTake();
                break;
            case 2:
                guillotine.sixMissTake();
                break;
            case 1:
                guillotine.sevenMissTake();
                break;
        }
    }
}
