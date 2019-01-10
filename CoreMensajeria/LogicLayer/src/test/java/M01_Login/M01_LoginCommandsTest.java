package M01_Login;

import Entities.Entity;
import Entities.M01_Login.User;
import Logic.Command;
import Logic.CommandsFactory;
import Logic.M01_Login.FindByUsernameIdCommand;
import Logic.M01_Login.GetAllUsersCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class M01_LoginCommandsTest {



    @BeforeEach
    void setUp(){}

    @AfterEach
    void tearDown() {
    }

    /**
     * Pruebas unitarias para el Metodo GetAllUsersCommand en LogicLayer/Logic/M01_Login
     *
     * */
    @Test
    void GetAllUsersCommandTest(){
        Command<ArrayList<User>> command;
        command = CommandsFactory.createGetAllUsersCommand();
        ArrayList<Entity> _users;
        try {
            command.execute();
            _users = ((GetAllUsersCommand) command).ReturnList();
            assertNotNull( _users );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Pruebas unitarias para el Metodo FindByUsernameIdCommand en LogicLayer/Logic/M01_Login
     *
     * */
    @Test
    void FindByUsernameIdCommandTest(){
        User _us = new User(1);
        Command _command;
        _command = CommandsFactory.findByUsernameIdCommand(_us.get_idUser());
        Entity _user;

        try {
            _command.execute();
            _user = (User) (_command).Return();
            assertNotNull( _user );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
