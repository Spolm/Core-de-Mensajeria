package Logic.M10_Profile;

import Entities.M01_Login.User;
import Logic.Command;

import java.util.ArrayList;

public class GetAllUsersCommand extends Command {
    private ArrayList<User> users;

    @Override
    public void execute() throws Exception {

    }

    @Override
    public ArrayList<User> Return() {
        return null;
    }
}
