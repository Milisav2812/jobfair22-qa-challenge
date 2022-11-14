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

        // Set prize positions in gameSequence
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
        // Create new game with only one prize
        FindThePrize game = FindThePrize.init( 5 , 1, 1);

        // Set prize positions in gameSequence
        game.newRound();

        // Find prize index
        List<Boolean> gameSequence = game.getGameSequence();
        int prizeIndex = 0;
        for(int i=0; i<gameSequence.size(); i++){
            if(gameSequence.get(i)){
                prizeIndex = i + 1;
                break;
            }
        }

        // Call guess() and verify response
        boolean guessResponse = game.guess(prizeIndex);
        assertEquals(game.getGameSequence().get(prizeIndex-1), guessResponse);
    }

    // generatePrizePositions
    @Test
    public void testGeneratePrizePositions() throws Exception{
        int numberOfOptions = 5;

        // Create new game
        FindThePrize game = FindThePrize.init(numberOfOptions , 4, 1);

        // Generate prize positions
        List<Integer> positions = game.generatePrizePositions(numberOfOptions);

        // Verify that there are no duplicate values in the positions List
        Set<Integer> tempSet = new HashSet<>();
        for(int i=0; i<numberOfOptions; i++){
            if(tempSet.contains(positions.get(i))){
                fail();
            }
            tempSet.add(positions.get(i));
        }
    }

    // playRound
    @Test
    public void testPlayRoundIncreasePointsOnCorrectGuess() throws Exception{
        int guessIndex = 1;

        // Create new game
        FindThePrize game = FindThePrize.init(1 , 1, 1);

        // Instantiate new round
        game.newRound();

        // Call playRound
        boolean playRoundResponse = game.playRound(guessIndex);

        // Verify that playRoundResponse is true (Correct guess)
        assertEquals(true, playRoundResponse);

        // Verify that the number of points has been increased to 1
        assertEquals(1, game.getNumberOfPoints());
    }

    @Test
    public void testPlayRoundIncorrectGuess() throws Exception{
        int guessIndex = 1;

        // Create new game
        FindThePrize game = FindThePrize.init(1 , 1, 1);

        // Call playRound - Incorrect guess
        boolean playRoundResponse = game.playRound(guessIndex);

        // Verify that playRoundResponse is true (Correct guess)
        assertEquals(false, playRoundResponse);

        // Verify that the number of points is 0
        assertEquals(0, game.getNumberOfPoints());
    }

    // playGame
    @Test
    public void testPlayGameWhenPlayerWins() throws Exception{

        // Create new game
        FindThePrize game = FindThePrize.init(1 , 1, 1);

        // Call playGame
        int playGameResponse = game.playGame(Collections.singletonList(1));

        // Verify that the number of points is 1
        assertEquals(1, playGameResponse);
    }

    @Test
    public void testPlayGameWhenPlayerLoses() throws Exception{

        // Call playGame unitl player looses
        int playGameResponse;
        do {
            // Create new game
            FindThePrize game = FindThePrize.init(100 , 1, 1);

            playGameResponse = game.playGame(Collections.singletonList(1));
        }while(playGameResponse != 0);

        // Verify that the number of points is 0
        assertEquals(0, playGameResponse);
    }


}
