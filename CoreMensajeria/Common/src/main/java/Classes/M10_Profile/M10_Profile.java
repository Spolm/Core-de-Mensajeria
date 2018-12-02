package Classes.M10_Profile;

import Classes.Sql;
import Classes.M01_Login.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M10_Profile {

    private Connection _conn;

    public M10_Profile() {
        _conn = Sql.getConInstance();
    }

    /**
     * @param username Nombre de usuario a buscar
     * @return User con todos los datos del usuario
     */
    public ArrayList<User> searchUserList(String username) {
        String consulta= "SELECT use_id, use_password, use_username, use_type, use_email, use_phone,use_country," +
                " use_city, use_address, use_date_of_birth, use_gender From public.user " +
                "WHERE use_username ='" +username+"'";
        ArrayList<User> userList = new ArrayList<>();
        Sql db = new Sql();

        try {
            ResultSet rs = db.sqlConn(consulta);
            while (rs.next()) {
                User user = new User();
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
                userList.add(user);
            }
        } catch (SQLException ex) {
            userList=null;
        }
        return userList;
    }

    /**
     *
     * @param id id del usuario a editar
     * @param email email del usuario
     * @param phone telefono del usuario
     * @param address   direccion
     * @return  String con mensaje de exito
     * @throws SQLException
     */
    public String editProfile( int id, String email, String phone,String address ) {

        try {

            //Query a realizar
            String query = "UPDATE public.User SET " +
                    "use_phone='"+phone+"',use_email='"+email+"' ,use_address='"+address+"'"+
                    "WHERE use_id = "+id;

            //Se crea conexion a la dc
            Sql db = new Sql();

            //Se realiza query, falta codigo aqui para validar si se realizo correctamente el query
            db.sqlConn(query);
            return "Perfil editado con éxito";
        }
        catch (SQLException e) {
            System.err.println(e.getStackTrace());
            return "Error al editar el perfil";
        }

    }

    public String addUser(CreateUserRequestBody user){
        try {

            //Query a realizar
            String query = "INSERT INTO public.USER " +
                    "(use_password, use_username, use_type, use_email, use_phone, use_country, use_city, use_address," +
                    " use_date_of_birth, use_gender) " +
                    "values('"+ user.get_passwordUser() +"', '"+ user.get_usernameUser() +"'," +
                    user.get_typeUser() +" , '"+ user.get_emailUser() +"', '"+ user.get_emailUser() +"'," +
                    "'"+ user.get_countryUser() +"', '"+ user.get_cityUser() +"', '"+ user.get_addressUser() +"', " +
                    "TO_TIMESTAMP('"+ user.get_birthdateUser() +"','DD/MM/YY'), '"+ user.get_genderUser() +"')";

            //Se crea conexion a la dc
            Sql db = new Sql();

            //Se realiza query, falta codigo aqui para validar si se realizo correctamente el query
            db.sqlConn(query);
            return "Usuario creado con éxito";
        }
        catch (SQLException e) {
            System.err.println(e.getStackTrace());
            return "Error al crea usuario";
        }
    }
}
