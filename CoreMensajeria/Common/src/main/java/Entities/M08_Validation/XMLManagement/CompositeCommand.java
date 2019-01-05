package Entities.M08_Validation.XMLManagement;

public abstract class CompositeCommand extends Command {
    private Command[] _commandList;
    public abstract void execute();

    public CompositeCommand() {
        _commandList = new Command[2];
    }

    public Command[] get_commandList() {
        return _commandList;
    }

    public void set_commandList(Command[] _commandList) {
        this._commandList = _commandList;
    }
}
