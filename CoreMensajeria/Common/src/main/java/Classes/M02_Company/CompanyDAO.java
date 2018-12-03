package Classes.M02_Company;

import Classes.Sql;
import Exceptions.CompanyDoesntExistsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyDAO {

    private Connection conn = Sql.getConInstance();

    //region API Obtener Compañias por usuario

    public ArrayList<Company> companyList(int id) throws CompanyDoesntExistsException {
        ArrayList<Company> coList= new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareCall("{Call m02_getcompanies(?)}");
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Company co = new Company();
                co.set_idCompany(result.getInt("com_id"));
                co.set_name(result.getString("com_name"));
                co.set_desc(result.getString("com_description"));
                co.set_status(result.getBoolean("com_status"));
                co.set_link(result.getString("com_route_link"));
                coList.add(co);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new CompanyDoesntExistsException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }return coList;
    }
    //endregion


    //region Todas Las Campañas
    public ArrayList<Company> companyListAll() throws CompanyDoesntExistsException {
        ArrayList<Company> coList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareCall("{Call m02_getcompaniesall()}");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Company co = new Company();
                co.set_idCompany(result.getInt("com_id"));
                co.set_name(result.getString("com_name"));
                co.set_desc(result.getString("com_description"));
                co.set_status(result.getBoolean("com_status"));
                co.set_link(result.getString("com_route_link"));
                coList.add(co);
            }
        }
            catch (SQLException e) {
                e.printStackTrace();
                throw new CompanyDoesntExistsException(e);
            }
        return coList;
    }
    //endregion

    //region API Detalles Compañia

    public Company getDetails (int id) throws CompanyDoesntExistsException {
        String select = "SELECT * FROM company where com_id = ?";
        Company co = new Company();
        try {

            PreparedStatement ps = conn.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                co.set_idCompany(result.getInt("com_id"));
                co.set_name(result.getString("com_name"));
                co.set_desc(result.getString("com_description"));
                co.set_status(result.getBoolean("com_status"));
                co.set_link(result.getString("com_route_link"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new CompanyDoesntExistsException(e);
        }

        return co;
    }

    //endregion

    public boolean changeStatus(int id) throws CompanyDoesntExistsException {
            Company co = new Company();

        try {
            co = getDetails(id);
            co.set_status(!co.get_status());
            String query = "UPDATE public.company SET" +
                    " com_status ="+co.get_status()+
                    " WHERE com_id =?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new CompanyDoesntExistsException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return co.get_status();
    }

}
