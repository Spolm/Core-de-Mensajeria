package Logic.M08_Validation;

import Logic.Command;

/**
 * @param <T> hace referencia al tipo de dato que retornara al comando
 */
public abstract class CompositeCommand<T> extends Command {
    private Command[] _commandList;

    /**
     * metodo que nos permite ejecutar el comando
     *
     * @throws Exception
     */
    @Override
    public abstract void execute() throws Exception;

    @Override
    public abstract T Return();

    /**
     * Metodo que nos permite obtener la lista de comandos
     * de la cual se esta compuesto
     *
     * @return _commandList lista de comandos
     */
    public Command[] get_commandList() {
        return _commandList;
    }

    /**
     * Metodo que nos permite asignar los comandos
     *
     * @param _commandList lista de comandos de los que se esta compuesto
     */
    public void set_commandList(Command[] _commandList) {
        this._commandList = _commandList;
    }
}
