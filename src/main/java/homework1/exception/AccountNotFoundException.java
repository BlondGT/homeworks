package homework1.exception;

public class AccountNotFoundException extends  RuntimeException{
    public AccountNotFoundException(String message){
        super(message);
    }
}
