package Persistence.M01_Login;

import Entities.Entity;
import Entities.M01_Login.User;
import Persistence.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetUserDao extends DAO implements  IDAOUser {

    @Override
    public Entity create(Entity e) {
        return null;
    }

    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }

    @Override
    public Entity user(Entity e) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<User> getUsers(Entity u) throws SQLException {
        User us = (User) u;
        ArrayList<User> userList = new ArrayList<>();
        PreparedStatement preStatement;
        ResultSet result;
        try {
            Connection conexion = DAO.getBdConnect();

            //Invocando el SP
            preStatement = conexion.prepareCall("");
            //Metiendo los parametros al SP
//            preStatement.setInt(1, 1);
            //Ejecucion del query
            result = preStatement.executeQuery();
            while(result.next()){
//                int _idNot = result.getInt("not_id");
//                Date _fechaNot = result.getDate("not_fecha");
//                Boolean _desechado = result.getBoolean("not_desechado");
//                String _tituloVid = result.getString("vid_titulo");
//                String _imagenVid = result.getString("vid_imagen");
//                String _descripcionVid = result.getString("vid_descripcion");
//                String _urlVid = result.getString("vid_url");
//                String _userName = result.getString("usu_login");
//                Video vid = new Video();
//                Usuario usu = new Usuario();
//                vid.setImagen(_imagenVid);
//                vid.setUrl(_urlVid);
//                vid.setDescripcion(_descripcionVid);
//                vid.setNombre(_tituloVid);
//                usu.set_name_user(_userName);
//                Notificacion notif = EntityFactory.notificacion(_idNot, vid, usu, _desechado, _fechaNot);
//                listaNotif.add(notif);
            }
            result.close();
        } catch ( SQLException e) {
//            throw new PLConnectException1(e);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return userList;
    }
}
