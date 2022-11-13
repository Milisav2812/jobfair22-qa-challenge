package logic;

import java.util.*;
import java.util.stream.Collectors;

public class FindThePrize {
    private final int numberOfPrizes;
    private final int numberOfRounds;
    private int numberOfPoints;
    //gameSequence is list of possible choices in game for that round
    private final List<Boolean> gameSequence;

    // CONSTRUCTOR
    private FindThePrize(int numberOfOptions, int numberOfPrizes, int numberOfRounds) {
        this.numberOfPrizes = numberOfPrizes;
        this.numberOfRounds = numberOfRounds;
        this.numberOfPoints = 0;
        this.gameSequence = new ArrayList<>(Arrays.asList(new Boolean[numberOfOptions]));
        Collections.fill(gameSequence, Boolean.FALSE);
        /* Bug: gameSequence was initialized with 1 less element */
//        this.gameSequence= new ArrayList<>(Arrays.asList(new Boolean[numberOfOptions-1]))
//                                .stream()
//                                .map(i -> false).collect(Collectors.toList());
    }

    //Function for initializing new game.
    // Game is configurable, so it can contain multiple options for player to guess, but also multiple prizes
    public static FindThePrize init(int numberOfOptions, int numberOfPrizes, int numberOfRounds) {

        if(numberOfOptions <= 0 || numberOfPrizes <= 0 || numberOfRounds <= 0){
            System.out.println("ERROR: Input parameters cannot be less or equal to 0");
            System.exit(0);
        }

        /* Verify that numberOfPrizes is less than numberOfOptions */
        if(numberOfPrizes >= numberOfOptions){
            System.out.println("ERROR: numberOfPrizes cannot be greater than or equal to numberOfOptions");
            System.exit(0);
        }

        return new FindThePrize(numberOfOptions, numberOfPrizes, numberOfRounds);
    }

    //Game core loop. This function gets guesses for every round in game
    public int playGame(List<Integer> roundGuesses) {

        if(roundGuesses.size() != numberOfRounds){
            System.out.println("ERROR: The number of guesses has to be the same as the number of rounds!");
            System.exit(0);
        }

        /* Bug: for loop should go from 0 to numberOfRounds-1 */
        for (int i = 0; i < this.numberOfRounds; i++) {
            int roundGuess = roundGuesses.get(i);

            boolean didWinRound = this.playRound(roundGuess);
            if(!didWinRound) {
                System.out.println("Incorrect guess! You LOST!");
                return 0;
            }
        }

        System.out.println("Congratulations! You WON!");
        return this.numberOfPoints;
    }

    //playing one round and adding points if needed
    public boolean playRound(int roundGuess) {
        this.newRound();
        boolean currentRoundGuess = this.guess(roundGuess);

        if(currentRoundGuess) {
            System.out.println("Correct guess! You gain 1 point!");
            this.addPoint();
        }

        return currentRoundGuess;
    }

    //Initializing new round and setting prizes on random positions
    public void newRound() {
        // Get List of non repeating numbers between 0 and numberOfOptions-1
        List<Integer> prizeLocations = generatePrizeLocations(this.gameSequence.size());

        /* Bug: For has to go from 0 to numberOfPrizes - 1 */
        // Pick first (numberOfPrizes) elements and make them false in gameSequence
        for (int i = 0; i < numberOfPrizes; i++) {
            this.gameSequence.set(prizeLocations.get(i), true);
        }
    }

    public void addPoint() {
        /* Bug: Points were subtracted instead of added */
        this.numberOfPoints++;
    }

    public boolean guess(int index) {
        if(index > gameSequence.size() || index < 0){
            System.out.println("ERROR: Guess index must be between 1 and numberOfOptions!");
            System.exit(0);
        }
        return this.gameSequence.get(index-1);
    }

    // Creates List of Integers (0...numberOfOptions) and returns it shuffled
    private List<Integer> generatePrizeLocations(int numberOfOptions){

        // Create list that contains numbers 0...size
        ArrayList<Integer> prizeLocations = new ArrayList<>();
        for(int i=0; i<numberOfOptions; i++){
            prizeLocations.add(i);
        }

        Collections.shuffle(prizeLocations);

        return prizeLocations;
    }

    // GETTERS & SETTERS
    public int getNumberOfPoints() {
        return numberOfPoints;
    }
}
