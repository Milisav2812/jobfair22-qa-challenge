package logic;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


public class TestFindThePrize {

    //Unit test example for addPoint function
    @Test
    public void testPointIncrease () throws Exception {
        FindThePrize game = FindThePrize.init(2 , 1, 2);
        game.addPoint();
        assertEquals(1, game.getNumberOfPoints());
    }

    // newRound
    @Test
    public void testNewRound() throws Exception{
        int numberOfPrizes = 2;
        int numberOfOptions = 5;

        // Create new game
        FindThePrize game = FindThePrize.init(numberOfOptions , numberOfPrizes, 1);

        // Set prizes to gameSequence
        game.newRound();

        // Count number of true positions in gameSequence
        int count = 0;
        for(int i=0; i < numberOfOptions; i++){
            if(game.getGameSequence().get(i)){
                count++;
            }
        }

        // Validate that count is same as numberOfPrizes
        assertEquals(numberOfPrizes, count);
    }

    // guess
    @Test
    public void testGuess() throws Exception{
        // Create new game
        FindThePrize game = FindThePrize.init(5 , 4, 1);

        // Set prizes to gameSequence
        game.newRound();

        // Call guess() and verify response
        int guessIndex = 2;
        boolean guessResponse = game.guess(guessIndex);
        assertEquals(game.getGameSequence().get(guessIndex-1), guessResponse);
    }

    // generatePrizePositions
    @Test
    public void testGeneratePrizePositions() throws Exception{
        int numberOfOptions = 5;

        // Create new game
        FindThePrize game = FindThePrize.init(numberOfOptions , 4, 1);

        // Generate prize positions
        List<Integer> numbers = game.generatePrizePositions(numberOfOptions);

        // Verify that there are no duplicate values in the positions List
        Set<Integer> tempSet = new HashSet<>();
        for(int i=0; i<numberOfOptions; i++){
            if(tempSet.contains(numbers.get(i))){
                fail();
            }
            tempSet.add(numbers.get(i));
        }
    }

    // playRound
    @Test
    public void testPlayRound() throws Exception{
        int guessIndex = 1;
        List<Boolean> gameSequence;

        // Create new game
        FindThePrize game = FindThePrize.init(4 , 2, 1);

        // Call playRound and get gameSequence element for guessIndex
        boolean playRoundResponse = game.playRound(guessIndex);
        gameSequence = game.getGameSequence();

        // Assert playRound Response
        assertEquals(gameSequence.get(guessIndex-1), playRoundResponse);

    }


}
