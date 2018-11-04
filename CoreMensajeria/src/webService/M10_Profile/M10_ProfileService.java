package webService.M10_Profile;

import Classes.Sql;
import Classes.User;

import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class M10_ProfileService {

    private static M10_ProfileService _profileDao = null;

    public static M10_ProfileService getInstance() {
        if (_profileDao== null)
            _profileDao = new M10_ProfileService();
        return _profileDao;

    }

    /**
     * @param username Nombre de usuario a buscar
     * @return User con todos los datos del usuario
     */
    public User searchUser(String username) {
        String consulta= "SELECT use_id, use_password, use_username, use_type, use_email, use_phone,use_country, use_city, use_address, use_date_of_birth, use_gender From public.user WHERE use_username ='" +username+"'";
        Sql db = new Sql();
        User user = new User();
        try {
            ResultSet rs = db.sqlConn(consulta);
            while (rs.next()) {
                user.set_idUser(rs.getInt("use_id"));
                user.set_passwordUser(rs.getString("use_password"));
                user.set_usernameUser(rs.getString("use_username"));
                user.set_typeUser(rs.getInt("use_type"));
                user.set_emailUser(rs.getString("use_email"));
                user.set_phoneUser(rs.getString("use_phone"));
                user.set_countryUser(rs.getString("use_country"));
                user.set_dateOfBirthUser(rs.getDate("use_date_of_birth"));
                user.set_cityUser(rs.getString("use_city"));
                user.set_addressUser(rs.getString("use_address"));
                user.set_genderUser(rs.getString("use_gender"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());
        }
        return user;
    }

    /**
     *
     * @param id id del usuario a editar
     * @param username nombre del usuario
     * @param email email del usuario
     * @param phone telefono del usuario
     * @param country   pais del usuario
     * @param birthday  fecha de nacimiento
     * @param city  ciudad
     * @param address   direccion
     * @return  String con mensaje de exito
     * @throws SQLException
     */
    public String editProfile( String id, String username, String email, String phone, String country, String birthday,
                            String city, String address ) {

        //Query a realizar
        String query = "UPDATE" +
                " users(use_username, use_phone, use_country, use_date_of__birth, use_city, use_address)" +
                " VALUES '"+username+"','"+phone+"','"+country+"',"+birthday+",'"+city+"','"+address+"'"+
                "WHERE use_id = '"+id+"'";

        //Se crea conexion a la dc
        Sql db = new Sql();

        try {
            //Se realiza query, falta codigo aqui para validar si se realizo correctamente el query
            db.sqlConn(query);
        }
        catch (SQLException e) {
            System.err.println(e.getStackTrace());
        }

        return "Perfil editado con éxito";
    }
}
