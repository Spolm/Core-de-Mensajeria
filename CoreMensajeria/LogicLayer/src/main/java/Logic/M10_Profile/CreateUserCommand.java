package Logic.M10_Profile;

import Entities.M01_Login.User;
import Logic.Command;

public class CreateUserCommand extends Command {
    private User user;
    public CreateUserCommand(User user){
        this.user = user;
    }
    @Override
    public void execute() throws Exception {

    }

    @Override
    public Object Return() {
        return null;
    }
}
