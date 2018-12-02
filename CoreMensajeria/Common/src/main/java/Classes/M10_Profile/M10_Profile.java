package Classes.M10_Profile;

import Classes.Sql;
import Classes.M01_Login.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M10_Profile {

    private Connection _conn;
    private ResultSet _result;

    public M10_Profile() {
        _conn = Sql.getConInstance();
    }

    /**
     * @param username Nombre de usuario a buscar
     * @return User con todos los datos del usuario
     */
    public ArrayList<User> searchUser(String username) {
        String consulta= "SELECT use_id, use_password, use_username, use_type, use_email, use_phone,use_country," +
                " use_city, use_address, use_date_of_birth, use_gender From public.user " +
                "WHERE use_username =?";
        ArrayList<User> userList = new ArrayList<>();
        try {
        PreparedStatement preparedStatement = _conn.prepareStatement(consulta);
        preparedStatement.setString(1, username);
        _result = preparedStatement.executeQuery();



            while (_result.next()) {
                User user = new User();
                user.set_idUser(_result.getInt("use_id"));
                user.set_passwordUser(_result.getString("use_password"));
                user.set_usernameUser(_result.getString("use_username"));
                user.set_typeUser(_result.getInt("use_type"));
                user.set_emailUser(_result.getString("use_email"));
                user.set_phoneUser(_result.getString("use_phone"));
                user.set_countryUser(_result.getString("use_country"));
                user.set_dateOfBirthUser(_result.getDate("use_date_of_birth"));
                user.set_cityUser(_result.getString("use_city"));
                user.set_addressUser(_result.getString("use_address"));
                user.set_genderUser(_result.getString("use_gender"));
                userList.add(user);
            }
        } catch (SQLException ex) {
            userList=null;
        }
        finally{
            Sql.bdClose(_conn);
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
                    "use_phone=?,use_email=? ,use_address=?"+
                    "WHERE use_id = ?";

            PreparedStatement preparedStatement = _conn.prepareStatement(query);
            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, address);
            preparedStatement.setInt(4, id);

            //Se realiza query, falta codigo aqui para validar si se realizo correctamente el query
            preparedStatement.executeQuery();
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
                    "TO_TIMESTAMP('"+ user.get_birthdateUser() +"','DD/MM/YYYY'), '"+ user.get_genderUser() +"')";



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

    public ArrayList<Rol> getAllRoles(){
        ArrayList<Rol> rols = new ArrayList<>();
        try {
            PreparedStatement st = _conn.prepareCall("{call m10_getallroles()}");
            ResultSet _result = st.executeQuery();
            int id = _result.getInt("rol_id");
            String name = _result.getString("rol_name");
            Rol rol = new Rol(id,name);

            while (_result.next()) {
                rols.add(rol);
            }
            return rols;

        } catch (SQLException e) {
            System.out.println("Error bd");
        } finally {
            Sql.bdClose(_conn);
        }
        return rols;
    }
}

