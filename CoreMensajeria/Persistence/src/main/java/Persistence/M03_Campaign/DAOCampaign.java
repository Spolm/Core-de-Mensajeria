package Persistence.M03_Campaign;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M01_Login.User;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Entities.Sql;
import Exceptions.CampaignDoesntExistsException;
import Persistence.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DAOCampaign extends DAO implements IDAOCampaign {

    final String UPDATE_CAMPAIGN_STATUS = "{CALL m03_changecampaignstatus(?,?)}";
    final String SELECT_CAMPAIGN_BY_ID = "{CALL  m03_getcampaignbyid(?)}";
    final String SELECT_CAMPAIGN_BY_USER = "{Call m03_getcampaignsbyuser(?)}";
    final String SELECT_ALL_CAMPAIGNS = "{Call m03_getcampaignsall( )}";
    final String SELECT_CAMPAIGN_USER_COMPANY = " {Call m03_getcampaignbycompany(?,?)} ";
    final String SELECT_CAMPAIGN_BY_COMPANY = "{Call m03_getcampaigns(?)}";
    final String ADD_CAMPAIGN = "{Call m03_addcampaign(?,?,?,?,?,?)}" ;
    final String UPDATE_CAMPAIGN = "{Call m03_updatecampaign(?,?,?,?,?,?,?)}" ;


    @Override
    public Entity campaign(Entity e) throws SQLException {
        return null;
    }

    @Override
    public void changeStatusCampaign( Entity e ) {
        Campaign _ca = ( Campaign ) e;
        Connection _conn = this.getBdConnect();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = _conn.prepareCall( UPDATE_CAMPAIGN_STATUS );
            preparedStatement.setInt(1, _ca.get_idCampaign() );
            preparedStatement.setBoolean(2, !( _ca.get_statusCampaign() ) );
            preparedStatement.execute();
        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }
        this.closeConnection();

    }

    public Campaign getCampaign( ResultSet _result ) throws SQLException {

        Campaign _campaign = EntityFactory.CreateFullCampaign(
                _result.getInt( "cam_id" ),
                _result.getString( "cam_name" ),
                _result.getString( "cam_description" ),
                _result.getBoolean( "cam_status" ),
                _result.getDate( "cam_start_date" ),
                _result.getDate( "cam_end_date" ),
                _result.getInt( "cam_company_id" ));
        return _campaign;
    }

    public ArrayList<Entity> resultList( ResultSet _result ) throws SQLException {
        ArrayList<Entity> _caList= new ArrayList<>();
        while ( _result.next() ){
            _caList.add( getCampaign(_result ) );
        }
        this.closeConnection();
        return _caList;
    }


    @Override
    public Campaign campaignById(Campaign e) {
        Campaign _campaign =  e;
        Connection _conn = this.getBdConnect();

        try {
            PreparedStatement  preparedStatement = _conn.prepareCall( SELECT_CAMPAIGN_BY_ID );
            preparedStatement.setInt( 1, _campaign.get_id() );
            ResultSet _result = preparedStatement.executeQuery();
            while ( _result.next() ) {
                _campaign = getCampaign( _result );
            }
        }
        catch (SQLException exc) {
            exc.printStackTrace();
        }
        this.closeConnection();
        return _campaign;

    }

    @Override
    public ArrayList<Entity> campaignListByUserCompany (  Entity _comp ) {
       Company _company = ( Company ) _comp;
       ArrayList<Entity> _caList= new ArrayList<>();
        Connection _conn = this.getBdConnect();

        try {
            PreparedStatement _ps = _conn.prepareCall( SELECT_CAMPAIGN_USER_COMPANY );
            _ps.setInt( 1, _company.get_idCompany() );
            _ps.setInt( 2, _company.get_idUser() );
            ResultSet _result = _ps.executeQuery();
            _caList = resultList( _result );

        }
        catch ( Exception exc ) {
            exc.printStackTrace();
        }
        this.closeConnection();
        return _caList;
    }

    @Override
    public ArrayList<Entity> campaignListByUser(  Entity e ) {
        ArrayList<Entity> _caList= new ArrayList<>();
        Company _user = ( Company ) e;
        Connection _conn = this.getBdConnect();

        try {
            PreparedStatement _ps = _conn.prepareCall(SELECT_CAMPAIGN_BY_USER);
            _ps.setInt(1, _user.get_idCompany());
            ResultSet _result = _ps.executeQuery();
            while( _result.next() ){
                _caList.add( getCampaign( _result ) );
            }
        }
        catch ( Exception exc ) {
            exc.printStackTrace();
        }
        this.closeConnection();
        return _caList;
    }

    @Override
    public ArrayList<Entity> campaignList() {
            ArrayList<Entity> _caList= new ArrayList<>();
            Connection _conn = this.getBdConnect();

        try {
                PreparedStatement _ps = _conn.prepareCall(SELECT_ALL_CAMPAIGNS);
                ResultSet _result = _ps.executeQuery();
                resultList( _result );


        }
        catch (SQLException e) {
                e.printStackTrace();
        }
        this.closeConnection();
        return _caList;
        }


    public ArrayList< Entity > campaignListCompany( Entity _e ) {
        ArrayList<Entity> _caList = new ArrayList<>();
        PreparedStatement _ps;
        Company _company = (Company) _e;
        Connection _conn = this.getBdConnect();

        try {
            _ps = _conn.prepareCall(SELECT_CAMPAIGN_BY_COMPANY);
            _ps.setInt(1, _company.get_id());
            ResultSet _result = _ps.executeQuery();

            while( _result.next() ){
                _caList.add( getCampaign( _result ) );
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        this.closeConnection();
        return _caList;
    }


    @Override
    public void create( Entity e ) {
        Campaign _ca = ( Campaign ) e;
        Connection _conn = this.getBdConnect();
        Logger logger = Logger.getLogger(DAOCampaign.class.getName());
        logger.info("Objeto compania recibido en Create campaña" + _ca.get_idCampaign() + " " +
                    _ca.get_nameCampaign() + " "+ _ca.get_statusCampaign() + " " + _ca.get_descCampaign()+""+
                    _ca.get_startCampaign()+""+_ca.get_endCampaign()+"id:"+_ca.get_idCompany() );
        try {
            PreparedStatement preparedStatement = _conn.prepareCall(ADD_CAMPAIGN);
            preparedStatement.setString(1, _ca.get_nameCampaign() );
            preparedStatement.setString(2, _ca.get_descCampaign() );
            preparedStatement.setBoolean(3, _ca.get_statusCampaign() );
            preparedStatement.setDate( 4, new java.sql.Date(_ca.get_startCampaign().getTime()) );
            preparedStatement.setDate( 5, new java.sql.Date(_ca.get_endCampaign().getTime()) );
            preparedStatement.setInt( 6, _ca.get_idCompany() );
            preparedStatement.execute();

        }catch ( Exception exc ){
            exc.printStackTrace();
        }
        this.closeConnection();

    }

    @Override
    public Entity read( Entity e) {
        return null;
    }

    @Override
    public Entity update( Entity e ) {
        Campaign _ca = ( Campaign ) e;
        Connection _conn = this.getBdConnect();
        try{
            PreparedStatement _ps = _conn.prepareCall(UPDATE_CAMPAIGN);
            _ps.setString( 1, _ca.get_nameCampaign() );
            _ps.setString( 2, _ca.get_descCampaign( ) );
            _ps.setBoolean( 3, _ca.get_statusCampaign() );
            _ps.setDate( 4, new java.sql.Date( _ca.get_startCampaign().getTime()) );
            _ps.setDate( 5, new java.sql.Date( _ca.get_endCampaign().getTime()) );
            _ps.setInt( 6, _ca.get_idCompany() );
            _ps.setInt( 7, _ca.get_idCampaign() );
            _ps.execute();

        }catch ( Exception _exc ) {
            _exc.printStackTrace();
        }
        this.closeConnection();
        return _ca;
    }



}
