package Classes.M10_Profile;

import Classes.M02_Company.Company;
import Classes.Sql;
import Classes.M01_Login.User;
import Exceptions.CompanyDoesntExistsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase de acceso a datos que nos permite
 * obtener informacion relacionada al perfil
 * del usuario.
 *
 * @return User con todos los datos del usuario
 * @Author Alexander De Acevedo.
 * @Author Sergio Garcia.
 * @Author Ramiro Vargas.
 */

public class M10_Profile {
    private Connection _conn;
    private ResultSet _result;

    public M10_Profile() {
        _conn = Sql.getConInstance();
    }

    public ArrayList<Company> getCompanies() {
        ArrayList<Company> coList = new ArrayList<>();
        try {
            PreparedStatement ps = _conn.prepareCall("{call m10_getallcompanies()}");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Company co = new Company();
                co.set_idCompany(result.getInt("com_id"));
                co.set_name(result.getString("com_name"));
                coList.add(co);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Sql.bdClose(_conn);
            return coList;
        }
    }


    /**
     * Metodo que permite editar el perfil de un usuario
     *
     * @param id      id del usuario a editar
     * @param email   email del usuario
     * @param phone   telefono del usuario
     * @param address direccion
     * @return String con mensaje de exito
     * @throws SQLException
     */

    public String editProfile(int id, String email, String phone, String address) {
        try {
            //Query a realizar
            String query = "UPDATE public.User SET " +
                    "use_phone=?,use_email=? ,use_address=?" +
                    "WHERE use_id = ?";

            PreparedStatement preparedStatement = _conn.prepareStatement(query);
            preparedStatement.setString(1, phone);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, address);
            preparedStatement.setInt(4, id);

            //Se realiza query, falta codigo aqui para validar si se realizo correctamente el query
            preparedStatement.executeQuery();
            return "Perfil editado con éxito";
        } catch (SQLException e) {
            System.err.println(e.getStackTrace());
            return "Error al editar el perfil";
        } finally {
            Sql.bdClose(_conn);
        }
    }


    /**
     * Metodo que nos permite obtener los roles
     * de los usuarios que se encuentran en el sistema.
     *
     * @return Lista de los Roles que estan almacenados en el sistema
     * @throws SQLException en caso de que exista un error de comunicacion a la base de datos.
     * @see Rol
     */

    public ArrayList<Rol> getAllRoles() {
        ArrayList<Rol> rols = new ArrayList<>();
        try {
            PreparedStatement st = _conn.prepareCall("{call m10_getallroles()}");
            ResultSet _result = st.executeQuery();

            while (_result.next()) {
                int id = _result.getInt("rol_id");
                String name = _result.getString("rol_name");
                Rol rol = new Rol(id, name);
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

    public String addUser(CreateUserRequestBody user) {
        try {
            //Query a realizar
            String query = "INSERT INTO public.USER " +
                    "(use_password, use_username, use_type, use_email, use_phone, use_country, use_city, use_address," +
                    " use_date_of_birth, use_gender) " +
                    "values('" + user.get_passwordUser() + "', '" + user.get_usernameUser() + "'," +
                    user.get_typeUser() + " , '" + user.get_emailUser() + "', '" + user.get_emailUser() + "'," +
                    "'" + user.get_countryUser() + "', '" + user.get_cityUser() + "', '" + user.get_addressUser() + "', " +
                    "TO_TIMESTAMP('" + user.get_birthdateUser() + "','DD/MM/YYYY'), '" + user.get_genderUser() + "')";
            //Se crea conexion a la dc
            Sql db = new Sql();

            //Se realiza query, falta codigo aqui para validar si se realizo correctamente el query
            db.sqlConn(query);
            return "Usuario creado con éxito";
        } catch (SQLException e) {
            System.err.println(e.getStackTrace());
            return "Error al crea usuario";
        } finally {
            Sql.bdClose(_conn);
        }
    }

}

