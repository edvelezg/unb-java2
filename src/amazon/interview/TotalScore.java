package amazon.interview;// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED

import java.util.ArrayList;
import java.util.List;

// CLASS BEGINS, THIS CLASS IS REQUIRED
public class TotalScore {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public int totalScore(int num, List<String> blocks) {
        int total = 0;

        // Clean out the Zs and the elements that don't count
        List<String> scores = new ArrayList<>();
        for (int i = 0; i < blocks.size(); i++) {

            String block = blocks.get(i);
            if (block.equalsIgnoreCase("Z")) {
                if (!scores.isEmpty()) {
                    scores.remove(scores.size() - 1);
                }
            } else {
                scores.add(block);
            }
        }

        System.out.println(scores);

        // Calculate the scores with the remaining rules
        int lastScore = 0;
        int totalScore = 0;
        int prevScore = 0;
        List<Integer> totalScores = new ArrayList<>();
        List<Integer> prevScores = new ArrayList<>();
        List<Integer> lastScores = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            String score = scores.get(i);

            System.out.println(totalScore);

            if (score.equalsIgnoreCase("X")) {
                prevScore = lastScore;
                lastScore *= 2;
                totalScore += lastScore;

            } else if (score.equalsIgnoreCase("+")) {
                int sum = lastScore + prevScore;
                prevScore = lastScore;
                lastScore = sum;
                totalScore += lastScore;

            } else {
                prevScore = lastScore;
                lastScore = Integer.parseInt(score);
                totalScore += lastScore;
            }

            prevScores.add(prevScore);
            lastScores.add(lastScore);
            totalScores.add(totalScore);

        }

        System.out.println(prevScores);
        System.out.println(lastScores);
        System.out.println(totalScores);

        return totalScore;
        // WRITE YOUR CODE HERE
    }

    public static void main(String[] args) {
        List<String> blocks = new ArrayList<>();
        blocks.add("5");
        blocks.add("-2");
        blocks.add("4");
        blocks.add("Z");
        blocks.add("X");
        blocks.add("9");
        blocks.add("+");
        blocks.add("+");


        System.out.println(new TotalScore().totalScore(8, blocks));
    }
    // METHOD SIGNATURE ENDS
}