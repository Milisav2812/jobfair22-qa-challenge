import logic.FindThePrize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        FindThePrize game = FindThePrize.init(2 , 1, 10);
        List<Integer> guesses = new ArrayList<>(Arrays.asList(1,2,1,2,1,1,2,1,2,1));
        int points = game.playGame(guesses);
        System.out.printf("Number of points: %d\n", points);
    }
}