import logic.FindThePrize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        FindThePrize game = FindThePrize.init(5 , 1, 10);
        List<Integer> guesses = new ArrayList<>(Arrays.asList(1,2,3,4,5,1,2,3,4,5));
        int points = game.playGame(guesses);
        System.out.printf("Number of points: %d\n", points);
    }
}