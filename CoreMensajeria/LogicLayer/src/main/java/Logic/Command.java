package Logic;

public abstract class Command<T> {

    public abstract void execute() throws Exception;

    public abstract T Return() ;

}
