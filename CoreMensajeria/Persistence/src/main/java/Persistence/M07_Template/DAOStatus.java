package Persistence.M07_Template;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M07_Template.HandlerPackage.ParameterHandler;
import Entities.M07_Template.MessagePackage.Message;
import Entities.M07_Template.MessagePackage.Parameter;
import Entities.M07_Template.Template;
import Entities.Sql;
import Exceptions.MessageDoesntExistsException;
import Exceptions.ParameterDoesntExistsException;
import Persistence.DAO;
import Persistence.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOStatus extends DAO implements IDAOStatus {
    private final String POST_STATUS_APPROVED = "{CALL m07_postTemplateStatusApproved(?,?)}";
    private final String POST_STATUS_NOT_APPROVED = "{CALL m07_postTemplateStatusNotApproved(?)}";

    @Override
    public Boolean postTemplateStatusApproved(int templateId, int userId) {
        Boolean flag=false;
        Connection _conn = this.getBdConnect();
        PreparedStatement preparedStatement = null;
        try {
            //Insert the new Message on DB
            preparedStatement = _conn.prepareCall(POST_STATUS_APPROVED);
            preparedStatement.setInt( 1,templateId );
            preparedStatement.setInt( 2, userId );
            flag = preparedStatement.execute();

        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }
        this.closeConnection();
        return flag;
    }

    @Override
    public Boolean postTemplateStatusNotApproved(int templateId) {
        Boolean flag=false;
        Connection _conn = this.getBdConnect();
        PreparedStatement preparedStatement = null;
        try {
            //Insert the new Message on DB
            preparedStatement = _conn.prepareCall(POST_STATUS_NOT_APPROVED);
            preparedStatement.setInt( 1,templateId );
            flag = preparedStatement.execute();

        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }
        this.closeConnection();
        return flag;
    }


    @Override
    public void create(Entity e) {

    }

    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }
}
