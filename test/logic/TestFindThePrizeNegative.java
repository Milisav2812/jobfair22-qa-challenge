package logic;

import customexceptions.ArgumentLessOrEqualToZeroException;
import customexceptions.IllegalGuessIndexException;
import customexceptions.IllegalNumberOfGuessesException;
import customexceptions.IllegalNumberOfPrizesException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class TestFindThePrizeNegative {

    // guess()
    @Test
    public void guessWithIndexLessThanZero(){
        assertThrows(IllegalGuessIndexException.class, () -> {
            FindThePrize game = FindThePrize.init(2 , 1, 2);
            game.guess(-1);
        });
    }

    @Test
    public void guessWithIndexGreaterThanNumberOfOptions(){
        assertThrows(IllegalGuessIndexException.class, () -> {
            FindThePrize game = FindThePrize.init(2 , 1, 2);
            game.guess(3);
        });
    }

    // init()
    @Test
    public void initWithNegativeNumberOfOptions(){
        assertThrows(ArgumentLessOrEqualToZeroException.class, () -> {
            FindThePrize.init(-1 , 1, 2);
        });
    }

    @Test
    public void initWithNegativeNumberOfPrizes(){
        assertThrows(ArgumentLessOrEqualToZeroException.class, () -> {
            FindThePrize.init(1 , -1, 2);
        });
    }

    @Test
    public void initWithNegativeNumberOfRounds(){
        assertThrows(ArgumentLessOrEqualToZeroException.class, () -> {
            FindThePrize.init(1 , 1, -1);
        });
    }

    @Test
    public void initWithNumberOfPrizesGreaterThanNumberOfOptions(){
        assertThrows(IllegalNumberOfPrizesException.class, () -> {
            FindThePrize.init(1 , 4, 1);
        });
    }

    // playGame()
    @Test
    public void playGameWithNumberOfGuessesLessThanNumberOfRounds(){
        assertThrows(IllegalNumberOfGuessesException.class, () -> {
            FindThePrize game = FindThePrize.init(3 , 1, 4);
            List<Integer> guesses = new ArrayList<>(Arrays.asList(1,2,3));
            game.playGame(guesses);
        });
    }

    @Test
    public void playGameWithNumberOfGuessesGreaterThanNumberOfRounds(){
        assertThrows(IllegalNumberOfGuessesException.class, () -> {
            FindThePrize game = FindThePrize.init(5 , 1, 4);
            List<Integer> guesses = new ArrayList<>(Arrays.asList(1,2,3,5,2));
            game.playGame(guesses);
        });
    }

}
