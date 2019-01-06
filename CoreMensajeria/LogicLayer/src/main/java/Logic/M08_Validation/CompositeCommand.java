package Logic.M08_Validation;

import Logic.Command;

public abstract class CompositeCommand<T> extends Command {
    private Command[] _commandList;

    @Override
    public abstract void execute() throws Exception;

    @Override
    public abstract T Return();

    public Command[] get_commandList() {
        return _commandList;
    }

    public void set_commandList(Command[] _commandList) {
        this._commandList = _commandList;
    }
}
