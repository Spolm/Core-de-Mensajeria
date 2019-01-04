package Persistence.M03_Campaign;

import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M01_Login.User;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Entities.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCampaign implements IDAOCampaign {
    private Connection _conn = Sql.getConInstance();
    final String UPDATE_CAMPAIGN_STATUS = "{CALL m03_changecampaignstatus(?,?)}";
    final String SELECT_CAMPAIGN_BY_ID = "{CALL  m03_getcampaignbyid(?)}";
    final String SELECT_CAMPAIGN_BY_USER = "{Call m03_getcampaignsbyuser(?)}";
    final String SELECT_ALL_CAMPAIGNS = "{Call m03_getcampaignsall( )}";
    final String SELECT_CAMPAIGN_USER_COMPANY = " {Call m03_getcampaignsbycompany(?,?)} ";
    final String SELECT_CAMPAIGN_BY_COMPANY = "{Call m03_getcampaigns(?)}";


    @Override
    public Entity campaign(Entity e) throws SQLException {
        return null;
    }

    @Override
    public void changeStatusCampaign(Entity e) {
        Campaign _ca = ( Campaign ) e;
        _conn = Sql.getConInstance();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = _conn.prepareCall( UPDATE_CAMPAIGN_STATUS );
            preparedStatement.setInt(1, _ca.get_id() );
            preparedStatement.setBoolean(2, _ca.get_habilitado() );
            preparedStatement.execute();
        } catch ( SQLException e1 ) {
            e1.printStackTrace();
        }
    }

    public Campaign getCampaign( ResultSet _result ) throws SQLException {

        Campaign _campaign = EntityFactory.CreateCampaignWithOut_Company(
                _result.getInt( "cam_id" ),
                _result.getString( "cam_name" ),
                _result.getString( "cam_description" ),
                _result.getBoolean( "cam_status" ),
                _result.getDate( "cam_start_date" ),
                _result.getDate( "cam_end_date" ) );
        return _campaign;
    }

    @Override
    public Entity campaignById(Entity e) {
        Campaign _campaign = ( Campaign ) e;

        try {
            PreparedStatement  preparedStatement = _conn.prepareCall( SELECT_CAMPAIGN_BY_ID );
            preparedStatement.setInt( 1, _campaign.get_id() );
            ResultSet _result = preparedStatement.executeQuery();
            resultList( _result );

        }
        catch (SQLException exc) {
            exc.printStackTrace();
        }
        return _campaign;

    }

    @Override
    public ArrayList<Entity> campaignListByUserCompany ( Entity _u , Entity _comp ) {
       User _user = ( User ) _u;
       Company _company = ( Company ) _comp;
       ArrayList<Entity> _caList= new ArrayList<>();
        try {
            PreparedStatement _ps = _conn.prepareCall( SELECT_CAMPAIGN_USER_COMPANY );
            _ps.setInt( 1, _company.get_id() );
            _ps.setInt( 2, _user.get_id() );
            ResultSet _result = _ps.executeQuery();
            resultList( _result );

        }
        catch ( Exception exc ) {
            exc.printStackTrace();
        }
        return _caList;
    }

    @Override
    public ArrayList<Entity> campaignListByUser(  Entity e ) {
        ArrayList<Entity> _caList= new ArrayList<>();
        User _user = ( User ) e;
        try {
            PreparedStatement _ps = _conn.prepareCall(SELECT_CAMPAIGN_BY_USER);
            _ps.setInt(1, _user.get_id());
            ResultSet _result = _ps.executeQuery();
            while( _result.next() ){
                _caList.add( getCampaign( _result ) );
            }
        }
        catch ( Exception exc ) {
            exc.printStackTrace();
        }
        return _caList;
    }

    @Override
    public ArrayList<Entity> campaignList() {
            ArrayList<Entity> _caList= new ArrayList<>();
            try {
                PreparedStatement _ps = _conn.prepareCall(SELECT_ALL_CAMPAIGNS);
                ResultSet _result = _ps.executeQuery();
                resultList( _result );


            } catch (SQLException e) {
                e.printStackTrace();
            }

            return _caList;
        }


    public ArrayList< Entity > campaignListCompany( Entity _e ) {
        ArrayList<Entity> _caList = new ArrayList<>();
        PreparedStatement _ps;
        Company _company = (Company) _e;
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


        return _caList;
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


    public ArrayList<Entity> resultList( ResultSet _result ) throws SQLException {
        ArrayList<Entity> _caList= new ArrayList<>();
        while ( _result.next() ){
            _caList.add( getCampaign(_result ) );
        }
      return _caList;
    }
}
