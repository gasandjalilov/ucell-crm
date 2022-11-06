package uz.hayot.camunda.tasks.exception;

public class DataAlreadyExists extends RuntimeException{

    public DataAlreadyExists(String message) {
        super(message);
    }
}
