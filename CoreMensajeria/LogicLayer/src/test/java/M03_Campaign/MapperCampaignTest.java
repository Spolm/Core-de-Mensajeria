package M03_Campaign;


import DTO.DTOFactory;
import DTO.M02_DTO.*;
import DTO.M03_DTO.*;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M02_Company.Company;
import Entities.M03_Campaign.Campaign;
import Entities.Sql;
import Exceptions.CompanyDoesntExistsException;
import Mappers.CampaignMapper.*;
import Mappers.MapperFactory;
import Persistence.DAOFactory;
import Persistence.M02_Company.DAOCompany;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapperCampaignTest {

    private MapperCampaignWithOut_Company _MCampaignWithOut_Company;
    private MapperCampaignWithOut_id_And_Company _MCampaignWithOut_id_And_Company;
    private MapperFullCampaign _MFullCampaign;
    private MapperIdCampaign _MIdCampaign;
    private MapperIdStatusCampaign _MIdStatusCampaign;

    @BeforeEach
    void setUp(){

        _MCampaignWithOut_Company = MapperFactory.CreateMapperCampaignWithOut_Company();
        _MCampaignWithOut_id_And_Company = MapperFactory.CreateMapperCampaignWithOut_id_And_Company();
        _MFullCampaign = MapperFactory.CreateMapperFullCampaign();
        _MIdCampaign = MapperFactory.createMapperIdCampaign();
        _MIdStatusCampaign   = MapperFactory.createMapperIdStatusCampaign();

    }

    @AfterEach
    void tearDown() {

        _MCampaignWithOut_Company = null ;
        _MCampaignWithOut_id_And_Company = null;
        _MFullCampaign = null ;
        _MIdCampaign = null;
        _MIdStatusCampaign = null;
    }

    /**
     * Pruebas unitarias para el Mapper MapperCampaignWithOut_Company en CampaignMapper
     */
    @Test
    void MapperCampaignWithOut_CompanyTest(){

        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        try {
            //-----CreateEntity----
            DTOCampaignWithOut_Company _DTO = DTOFactory.CreateDtoCampaignWithOut_Company(1,
                                                                        "Company 1", "Des"
                                                                        ,true, sDate , sDate);

            DTOCampaignWithOut_Company _DTO2 = DTOFactory.CreateDtoCampaignWithOut_Company(2,
                                                                        "Company 2", "Des"
                                                                        ,true, sDate , sDate);

            List<DTOCampaignWithOut_Company> _listDto = new ArrayList<>();

            Entity _entity = _MCampaignWithOut_Company.CreateEntity( _DTO );
            Entity _entity2 = _MCampaignWithOut_Company.CreateEntity( _DTO2 );

            Campaign _cam = ( Campaign ) _entity;
            Campaign _cam2 = ( Campaign ) _entity2;

            assertEquals( _cam.get_idCampaign(), _DTO.get_idCampaign() );
            assertEquals( _cam.get_nameCampaign(), _DTO.get_nameCampaign() );
            assertEquals( _cam.get_descCampaign(), _DTO.get_descCampaign() );
            assertEquals( _cam.get_statusCampaign(), _DTO.get_statusCampaign() );
            assertEquals( _cam.get_startCampaign(), _DTO.get_startCampaign() );
            //----fin Asserts Create Entity

            //----Create DTO
            DTOCampaignWithOut_Company _D2 = _MCampaignWithOut_Company.CreateDto( _entity );

            assertEquals( _D2.get_idCampaign(), _cam.get_idCampaign( ));
            assertEquals( _D2.get_nameCampaign(), _cam.get_nameCampaign() );
            assertEquals( _D2.get_descCampaign(), _cam.get_descCampaign() );
            assertEquals( _D2.get_statusCampaign(), _cam.get_statusCampaign() );

            //----fin Asserts CreateDTO

            //----CreateEntityList
            _listDto.add( _DTO );
            _listDto.add( _DTO2 );
            List<Entity> _listEntity = new ArrayList<>();
            _listEntity = _MCampaignWithOut_Company.CreateEntityList( _listDto );

            assertEquals( ( ( Campaign )_listEntity.get( 0 ) ).get_nameCampaign(),
                    _listDto.get( 0 ).get_nameCampaign() );

            assertEquals( ( ( Campaign )_listEntity.get( 1 ) ).get_nameCampaign(),
                    _listDto.get( 1 ).get_nameCampaign() );

            assertEquals( ( ( Campaign )_listEntity.get( 0 ) ).get_idCampaign(), _listDto.get( 0 ).get_idCampaign() );
            assertEquals( ( ( Campaign )_listEntity.get( 1 ) ).get_idCampaign(), _listDto.get( 1 ).get_idCampaign() );
            //----fin Assert CreateEntityList

            //---CreateDtoList
            _listEntity.add( _entity );
            _listEntity.add( _entity2 );
            _listDto = _MCampaignWithOut_Company.CreateDtoList( _listEntity );
            assertEquals( _listDto.get(0).get_idCampaign() , ( ( Campaign ) _listEntity.get(0) ).get_idCampaign() );
            assertEquals( _listDto.get(0).get_nameCampaign(), ( ( Campaign ) _listEntity.get(0) ).get_nameCampaign() );
            assertEquals( _listDto.get(1).get_idCampaign() , ( ( Campaign ) _listEntity.get(1) ).get_idCampaign() );
            assertEquals( _listDto.get(1).get_nameCampaign(), ( ( Campaign ) _listEntity.get(1) ).get_nameCampaign() );
            //----fin Assert CreateDtoList
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Pruebas unitarias para el Mapper MapperCampaignWithOutIdAndLinkCompany en CampaignMapper
     */
    @Test
    void MapperCampaignWithOutIdAndCompanyTest(){

        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        try {
            //-----CreateEntity----
            DTOCampaignWithOut_id_And_Company _DTO = DTOFactory.CreateDtoCampaignWithOut_id_And_Company(
                    "Company 1", "Des",true, sDate , sDate);

            DTOCampaignWithOut_id_And_Company _DTO2 = DTOFactory.CreateDtoCampaignWithOut_id_And_Company(
                    "Company 2", "Des"
                    ,true, sDate , sDate);

            List<DTOCampaignWithOut_id_And_Company> _listDto = new ArrayList<>();

            Entity _entity = _MCampaignWithOut_id_And_Company.CreateEntity( _DTO );
            Entity _entity2 = _MCampaignWithOut_id_And_Company.CreateEntity( _DTO2 );

            Campaign _cam = ( Campaign ) _entity;
            Campaign _cam2 = ( Campaign ) _entity2;

            assertEquals( _cam.get_nameCampaign(), _DTO.get_nameCampaign() );
            assertEquals( _cam.get_descCampaign(), _DTO.get_descCampaign() );
            assertEquals( _cam.get_statusCampaign(), _DTO.get_statusCampaign() );
            assertEquals( _cam.get_startCampaign(), _DTO.get_startCampaign() );
            //----fin Asserts Create Entity

            //----Create DTO
            DTOCampaignWithOut_id_And_Company _D2 = _MCampaignWithOut_id_And_Company.CreateDto( _entity );

            assertEquals( _D2.get_nameCampaign(), _cam.get_nameCampaign() );
            assertEquals( _D2.get_descCampaign(), _cam.get_descCampaign() );
            assertEquals( _D2.get_statusCampaign(), _cam.get_statusCampaign() );

            //----fin Asserts CreateDTO

            //----CreateEntityList
            _listDto.add( _DTO );
            _listDto.add( _DTO2 );
            List<Entity> _listEntity = new ArrayList<>();
            _listEntity = _MCampaignWithOut_id_And_Company.CreateEntityList( _listDto );

            assertEquals( ( ( Campaign )_listEntity.get( 0 ) ).get_nameCampaign(),
                    _listDto.get( 0 ).get_nameCampaign() );

            assertEquals( ( ( Campaign )_listEntity.get( 1 ) ).get_nameCampaign(),
                    _listDto.get( 1 ).get_nameCampaign() );

            //----fin Assert CreateEntityList

            //---CreateDtoList
            _listEntity.add( _entity );
            _listEntity.add( _entity2 );
            _listDto = _MCampaignWithOut_id_And_Company.CreateDtoList( _listEntity );
            assertEquals( _listDto.get(0).get_nameCampaign(), ( ( Campaign ) _listEntity.get(0) ).get_nameCampaign() );
            assertEquals( _listDto.get(1).get_nameCampaign(), ( ( Campaign ) _listEntity.get(1) ).get_nameCampaign() );
            //----fin Assert CreateDtoList
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Pruebas unitarias para el Mapper MapperFullCampaign en CampaignMapper
     */
    @Test
    void MapperFullCampaignTest(){
        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        try {
            //-----CreateEntity----
            DTOFullCampaign _DTO = DTOFactory.CreateDtoFullCampaign(1, "Company 1",
                                        "Des",true, sDate , sDate , 1);

            DTOFullCampaign _DTO2 = DTOFactory.CreateDtoFullCampaign(2, "Company 1",
                    "Des",true, sDate , sDate , 2);

            List<DTOFullCampaign> _listDto = new ArrayList<>();

            Entity _entity = _MFullCampaign.CreateEntity( _DTO );
            Entity _entity2 = _MFullCampaign.CreateEntity( _DTO2 );

            Campaign _cam = ( Campaign ) _entity;
            Campaign _cam2 = ( Campaign ) _entity2;
            assertEquals( _cam.get_idCampaign(), _DTO.get_idCampaign() );
            assertEquals( _cam.get_nameCampaign(), _DTO.get_nameCampaign() );
            assertEquals( _cam.get_descCampaign(), _DTO.get_descCampaign() );
            assertEquals( _cam.get_statusCampaign(), _DTO.get_statusCampaign() );
            assertEquals( _cam.get_startCampaign(), _DTO.get_startCampaign() );
            assertEquals( _cam.get_idCompany(), _DTO.get_idCompany() );
            //----fin Asserts Create Entity

            //----Create DTO
            DTOFullCampaign _D2 = _MFullCampaign.CreateDto( _entity );
            assertEquals( _D2.get_idCampaign()     , _cam.get_idCampaign()  );
            assertEquals( _D2.get_nameCampaign()  , _cam.get_nameCampaign() );
            assertEquals( _D2.get_descCampaign()  , _cam.get_descCampaign() );
            assertEquals( _D2.get_statusCampaign(), _cam.get_statusCampaign() );
            assertEquals( _D2.get_idCompany()     , _cam.get_idCompany()  );

            //----fin Asserts CreateDTO

            //----CreateEntityList
            _listDto.add( _DTO );
            _listDto.add( _DTO2 );
            List<Entity> _listEntity = new ArrayList<>();
            _listEntity = _MFullCampaign.CreateEntityList( _listDto );

            assertEquals( ( ( Campaign )_listEntity.get( 0 ) ).get_idCampaign(),
                    _listDto.get( 0 ).get_idCampaign() );

            assertEquals( ( ( Campaign )_listEntity.get( 1 ) ).get_idCampaign(),
                    _listDto.get( 1 ).get_idCampaign() );

            assertEquals( ( ( Campaign )_listEntity.get( 0 ) ).get_nameCampaign(),
                    _listDto.get( 0 ).get_nameCampaign() );

            assertEquals( ( ( Campaign )_listEntity.get( 1 ) ).get_nameCampaign(),
                    _listDto.get( 1 ).get_nameCampaign() );


            //----fin Assert CreateEntityList

            //---CreateDtoList
            _listEntity.add( _entity );
            _listEntity.add( _entity2 );
            _listDto = _MFullCampaign.CreateDtoList( _listEntity );
            assertEquals( _listDto.get(0).get_idCampaign(), ( ( Campaign ) _listEntity.get(0) ).get_idCampaign() );
            assertEquals( _listDto.get(1).get_idCampaign(), ( ( Campaign ) _listEntity.get(1) ).get_idCampaign() );
            assertEquals( _listDto.get(0).get_nameCampaign(), ( ( Campaign ) _listEntity.get(0) ).get_nameCampaign() );
            assertEquals( _listDto.get(1).get_nameCampaign(), ( ( Campaign ) _listEntity.get(1) ).get_nameCampaign() );
            //----fin Assert CreateDtoList
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Pruebas unitarias para el Mapper MapperIdCamapign en CampaignMapper
     */
    @Test
    void MapperIdCamapignTest(){

        try {
            //-----CreateEntity----
            DTOIdCampaign _DTO = DTOFactory.CreateDTOIdCampaign(1);

            DTOIdCampaign _DTO2 = DTOFactory.CreateDTOIdCampaign(2);

            List<DTOIdCampaign> _listDto = new ArrayList<>();

            Entity _entity = _MIdCampaign.CreateEntity( _DTO );
            Entity _entity2 = _MIdCampaign.CreateEntity( _DTO2 );

            Campaign _cam = ( Campaign ) _entity;
            Campaign _cam2 = ( Campaign ) _entity2;
            assertEquals( _cam.get_idCampaign(), _DTO.get_idCampaign() );
            //----fin Asserts Create Entity

            //----Create DTO
            DTOIdCampaign _D2 = _MIdCampaign.CreateDto( _entity );
            assertEquals( _D2.get_idCampaign()     , _cam.get_idCampaign()  );

            //----fin Asserts CreateDTO

            //----CreateEntityList
            _listDto.add( _DTO );
            _listDto.add( _DTO2 );
            List<Entity> _listEntity = new ArrayList<>();
            _listEntity = _MIdCampaign.CreateEntityList( _listDto );

            assertEquals( ( ( Campaign )_listEntity.get( 0 ) ).get_idCampaign(),
                    _listDto.get( 0 ).get_idCampaign() );

            assertEquals( ( ( Campaign )_listEntity.get( 1 ) ).get_idCampaign(),
                    _listDto.get( 1 ).get_idCampaign() );
            //----fin Assert CreateEntityList

            //---CreateDtoList
            _listEntity.add( _entity );
            _listEntity.add( _entity2 );
            _listDto = _MIdCampaign.CreateDtoList( _listEntity );
            assertEquals( _listDto.get(0).get_idCampaign(), ( ( Campaign ) _listEntity.get(0) ).get_idCampaign() );
            assertEquals( _listDto.get(1).get_idCampaign(), ( ( Campaign ) _listEntity.get(1) ).get_idCampaign() );
            //----fin Assert CreateDtoList
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Pruebas unitarias para el Mapper MapperIdStatusCamapign en CampaignMapper
     */
    @Test
    void MapperIdStatusCamapignTest(){

        try {
            //-----CreateEntity----
            DTOIdStatusCampaign _DTO = DTOFactory.CreateDTOIdStatusCampaign(1 , true);

            DTOIdStatusCampaign _DTO2 = DTOFactory.CreateDTOIdStatusCampaign(2 , false);

            List<DTOIdStatusCampaign> _listDto = new ArrayList<>();

            Entity _entity = _MIdStatusCampaign.CreateEntity( _DTO );
            Entity _entity2 = _MIdStatusCampaign.CreateEntity( _DTO2 );

            Campaign _cam = ( Campaign ) _entity;
            Campaign _cam2 = ( Campaign ) _entity2;
            assertEquals( _cam.get_idCampaign(), _DTO.get_idCampaign() );
            assertEquals( _cam.get_statusCampaign(), _DTO.is_status() );
            //----fin Asserts Create Entity

            //----Create DTO
            DTOIdStatusCampaign _D2 = _MIdStatusCampaign.CreateDto( _entity );
            assertEquals( _D2.get_idCampaign()     , _cam.get_idCampaign()  );
            assertEquals( _D2.is_status()          , _cam.get_statusCampaign() );
            //----fin Asserts CreateDTO

            //----CreateEntityList
            _listDto.add( _DTO );
            _listDto.add( _DTO2 );
            List<Entity> _listEntity = new ArrayList<>();
            _listEntity = _MIdStatusCampaign.CreateEntityList( _listDto );

            assertEquals( ( ( Campaign )_listEntity.get( 0 ) ).get_idCampaign(),
                    _listDto.get( 0 ).get_idCampaign() );

            assertEquals( ( ( Campaign )_listEntity.get( 1 ) ).get_idCampaign(),
                    _listDto.get( 1 ).get_idCampaign() );

            assertEquals( ( ( Campaign )_listEntity.get( 0 ) ).get_statusCampaign(),
                    _listDto.get( 0 ).is_status() );

            assertEquals( ( ( Campaign )_listEntity.get( 1 ) ).get_statusCampaign(),
                    _listDto.get( 1 ).is_status() );
            //----fin Assert CreateEntityList

            //---CreateDtoList
            _listEntity.add( _entity );
            _listEntity.add( _entity2 );
            _listDto = _MIdStatusCampaign.CreateDtoList( _listEntity );
            assertEquals( _listDto.get(0).get_idCampaign(), ( ( Campaign ) _listEntity.get(0) ).get_idCampaign() );
            assertEquals( _listDto.get(1).get_idCampaign(), ( ( Campaign ) _listEntity.get(1) ).get_idCampaign() );
            assertEquals( _listDto.get(0).is_status(), ( ( Campaign ) _listEntity.get(0) ).get_statusCampaign() );
            assertEquals( _listDto.get(1).is_status(), ( ( Campaign ) _listEntity.get(1) ).get_statusCampaign() );
            //----fin Assert CreateDtoList
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
