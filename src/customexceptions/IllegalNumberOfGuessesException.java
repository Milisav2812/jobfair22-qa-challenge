package customexceptions;

public class IllegalNumberOfGuessesException extends Exception{
    public IllegalNumberOfGuessesException(String message){
        super(message);
    }
}
