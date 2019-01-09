package Persistence.M02_Company;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M02_Company.Company;
import Entities.M06_DataOrigin.PathHandler;
import Persistence.DAO;
import Persistence.Postgres.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCompany extends DAOPostgres implements IDAOCompany {


    final String UPDATE_COMPANY_STATUS = "{CALL m02_changecompanystatus(?,?)}";
    final String SELECT_COMPANY_BY_ID = "{CALL  m02_getcompanybyid(?)}";
    final String SELECT_COMPANY_BY_RESPONSIBLE ="{CALL m02_getcompaniesbyresponsible(?)}";
    final String SELECT_COMPANIES_BY_USER = "{Call m02_getcompanies(?)}";
    final String CREATE_COMPANY=  "{Call m02_addcompany(?,?,?,?,?)}";
    final String SELECT_ALL_COMPANIES = "{Call m02_getcompaniesall}";
    final String UPDATE_COMPANY = "{Call m02_updatecompany(?,?,?,?,?,?)}";


    @Override
    public Entity company(Entity e) throws SQLException {

    return null;
    }

    @Override
    public void changeStatus(Entity e) {

        Company _co = ( Company ) e;
        Connection _conn = DAOPostgres.getConnection();
        PreparedStatement _preparedStatement = null;

        try {
            _preparedStatement = _conn.prepareCall( UPDATE_COMPANY_STATUS );
            _preparedStatement.setInt( 1,  _co.get_idCompany() );
            _preparedStatement.setBoolean( 2,  !( _co.get_status() ) );
            _preparedStatement.execute();

        }catch ( SQLException e1 ) {
            e1.printStackTrace();
        }catch ( NullPointerException en ){
            en.printStackTrace();
        }catch ( Exception ex ){
            ex.printStackTrace();
        }finally{
         try {
            _conn.close();
        }catch ( SQLException e1 ) {
             e1.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Entity> companiesByResponsible( Entity e ) {
        ArrayList<Entity> _coList= new ArrayList<>();
        Company _comp = ( Company ) e;
        Connection _conn = DAOPostgres.getConnection();

        try {
            PreparedStatement  preparedStatement = _conn.prepareCall(SELECT_COMPANY_BY_RESPONSIBLE);
            preparedStatement.setInt( 1, _comp.get_idCompany() );
            ResultSet _result = preparedStatement.executeQuery();
            while ( _result.next() ) {
                _coList.add( getCompany( _result ) );
            }
        }catch (SQLException exc) {
            exc.printStackTrace();
        }catch (NullPointerException en) {
            en.printStackTrace();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally{
         try {
             _conn.close();
         }catch ( SQLException e1 ) {
              e1.printStackTrace();
            }
        }
        return  _coList;
    }


    public Company getCompany( ResultSet _result ) throws SQLException {

        Company _company = EntityFactory.CreateFullCompany(
                _result.getInt( "com_id" ),
                _result.getString( "com_name" ),
                _result.getString( "com_description" ),
                _result.getBoolean( "com_status" ),
                _result.getString( "com_route_link" ),
                _result.getInt( "com_user_id" ) );
        return _company;
    }

    @Override
    public Company companyById( Company e ) {
        Company _company =  e;
        Connection _conn = DAOPostgres.getConnection();

        try {
            PreparedStatement  _preparedStatement = _conn.prepareCall( SELECT_COMPANY_BY_ID );
            _preparedStatement.setInt( 1, _company.get_idCompany() );
            ResultSet _result = _preparedStatement.executeQuery();
                while ( _result.next() ) {
                    _company = getCompany( _result );
                }
        }catch (SQLException exc) {
            exc.printStackTrace();
        }catch (NullPointerException en) {
            en.printStackTrace();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally{
         try {
            _conn.close();
        }catch ( SQLException e1 ) {
                e1.printStackTrace();
            }
        }
        return _company;

    }

    @Override
    public ArrayList<Entity> companiesByUser( Company e ) {
        ArrayList<Entity> _coList= new ArrayList<>();
        Company _company =  e;
        Connection _conn = DAOPostgres.getConnection();

        try {
            PreparedStatement _ps = _conn.prepareCall(SELECT_COMPANIES_BY_USER);
            _ps.setInt(1, _company.get_idCompany());
            ResultSet _result = _ps.executeQuery();
            while( _result.next() ){
                _coList.add( getCompany( _result ) );
            }
        }catch (SQLException exc) {
            exc.printStackTrace();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally{
         try {
             _conn.close();
         }catch ( SQLException e1 ) {
             e1.printStackTrace();
            }
        }
        return _coList;
    }


    @Override
    public ArrayList<Entity> companiesEnabled() {
        return null;
    }

    @Override
    public ArrayList<Company> allCompanies() {
        ArrayList<Company> _coList = new ArrayList<>();
        Connection _conn = DAOPostgres.getConnection();

        try {
            PreparedStatement _ps = _conn.prepareCall(SELECT_ALL_COMPANIES);
            ResultSet _result = _ps.executeQuery();
            while( _result.next() ){
                _coList.add( getCompany( _result ) );
            }
        }
        catch (SQLException exc) {
            exc.printStackTrace();
        }catch (NullPointerException en) {
            en.printStackTrace();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally{
         try {
            _conn.close();
        }catch ( SQLException e1 ) {
             e1.printStackTrace();
            }
        }
        return _coList;
    }


    @Override
    public void create( Entity e ) {
        Company _co = ( Company ) e;
        PathHandler _ph  = new PathHandler();
        Connection _conn = DAOPostgres.getConnection();

        try {

            PreparedStatement preparedStatement = _conn.prepareCall(CREATE_COMPANY);
            preparedStatement.setString( 1, _co.get_name() );
            preparedStatement.setString( 2, _co.get_desc() );
            preparedStatement.setBoolean( 3, _co.get_status() );
            preparedStatement.setString( 4, _ph.generatePath(_co) );
            preparedStatement.setInt( 5, _co.get_idUser() );
            preparedStatement.execute();

        }catch (SQLException exc) {
            exc.printStackTrace();
        }catch (NullPointerException en) {
            en.printStackTrace();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally{
         try {
            _conn.close();
        }catch( SQLException e1 ) {
            e1.printStackTrace();
            }
        }
    }

    @Override
    public Entity read(Entity e) {return null; }

    @Override
    public Entity update( Entity e ) {
        Company _co = ( Company ) e;
        PathHandler ph  = new PathHandler();
        Connection _conn = DAOPostgres.getConnection();

        try {
            PreparedStatement _ps = _conn.prepareCall( UPDATE_COMPANY );
            _ps.setString( 1, _co.get_name() );
            _ps.setString( 2, _co.get_desc() );
            _ps.setBoolean( 3, _co.get_status() );
            _ps.setString( 4, ph.generatePath(_co) );
            _ps.setInt( 5, _co.get_idUser() );
            _ps.setInt( 6, _co.get_idCompany() );
            _ps.execute();
        }catch (SQLException exc) {
            exc.printStackTrace();
        }catch (NullPointerException en) {
            en.printStackTrace();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally{
         try {
             _conn.close();
         }catch ( SQLException e1 ) {
              e1.printStackTrace();
            }
        }
        return _co;
    }


  /*  public ArrayList<Company> allCompanies2() {
        ArrayList<Company> _coList = new ArrayList<>();
        Connection _conn = this.getBdConnect();

        try {
            PreparedStatement _ps = _conn.prepareCall(SELECT_ALL_COMPANIES);
            ResultSet _result = _ps.executeQuery();
            while( _result.next() ){
                _coList.add( getCompany( _result ) );
            }
        }
        catch (SQLException exc) {
            exc.printStackTrace();
        }

        return _coList;
    }*/

}



