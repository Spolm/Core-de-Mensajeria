package M02_Company;

import DTO.DTOFactory;
import DTO.M02_DTO.*;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.M02_Company.Company;
import Entities.Sql;
import Exceptions.CompanyDoesntExistsException;
import Mappers.CompanyMapper.*;
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

public class MapperCompanyTest {

    private MapperCompanyWithOut_Link _MCompanyWithOutLink ;
    private MapperCompanyWithOutIdAndLink _MCompanyWithOutIdAndLink;
    private MapperFullCompany _MFullCompany;
    private MapperIdCompany _MIdCompany;
    private MapperIdCompUser _MIdUser;
    private MapperIdStatusCompany _MIdStatus;

    @BeforeEach
    void setUp(){

        _MCompanyWithOutLink = MapperFactory.CreateMapperCompanyWithOut_Link();
        _MCompanyWithOutIdAndLink = MapperFactory.CreateMapperCompanyWithOutIdAndLink();
        _MFullCompany = MapperFactory.CreateMapperFullCompany();
        _MIdCompany = MapperFactory.createMapperIdCompany();
        _MIdUser   = MapperFactory.createMapperIdCompUser();
        _MIdStatus = MapperFactory.createMapperIdStatusCompany();

    }

    @AfterEach
    void tearDown() {

        _MCompanyWithOutLink = null ;
        _MCompanyWithOutIdAndLink = null;
        _MFullCompany = null ;
        _MIdCompany = null;
        _MIdUser = null;
        _MIdStatus = null;
    }

    /**
     * Pruebas unitarias para el Mapper MapperCompanyWithOutLink en CompanyMapper
     */
    @Test
    void MapperCompanyWithOutLinkTest(){

        try {
            //-----CreateEntity----
            DTOCompanyWithOut_Link _DTO = DTOFactory.CreateDtoCompanyWithOut_Link(1, "Company 1", "Des"
                    , true);

            DTOCompanyWithOut_Link _DTO2 = DTOFactory.CreateDtoCompanyWithOut_Link(2, "Company 2", "Des1"
                    , true);

            List<DTOCompanyWithOut_Link> _listDto = new ArrayList<>();

            Entity _entity = _MCompanyWithOutLink.CreateEntity(_DTO);
            Entity _entity2 = _MCompanyWithOutLink.CreateEntity(_DTO2);

            Company _com = (Company) _entity;
            Company _com2 = (Company) _entity2;

            assertEquals(_com.get_idCompany(), _DTO.get_idCompany());
            assertEquals(_com.get_name(), _DTO.get_name());
            assertEquals(_com.get_desc(), _DTO.get_desc());
            assertEquals(_com.get_status(), _DTO.get_status());
            //----fin Asserts Create Entity

            //----Create DTO
            DTOCompanyWithOut_Link _D2 = _MCompanyWithOutLink.CreateDto(_entity);

            assertEquals(_D2.get_idCompany(), _com.get_idCompany());
            assertEquals(_D2.get_name(), _com.get_name());
            assertEquals(_D2.get_desc(), _com.get_desc());
            assertEquals(_D2.get_status(), _com.get_status());
            //----fin Asserts CreateDTO

            //----CreateEntityList
            _listDto.add( _DTO );
            _listDto.add( _DTO2 );
            List<Entity> _listEntity = new ArrayList<>();
            _listEntity = _MCompanyWithOutLink.CreateEntityList( _listDto );

            assertEquals( ( ( Company )_listEntity.get( 0 ) ).get_name(), _listDto.get( 0 ).get_name() );
            assertEquals( ( ( Company )_listEntity.get( 1 ) ).get_name(), _listDto.get( 1 ).get_name() );
            assertEquals( ( ( Company )_listEntity.get( 0 ) ).get_idCompany(), _listDto.get( 0 ).get_idCompany() );
            assertEquals( ( ( Company )_listEntity.get( 1 ) ).get_idCompany(), _listDto.get( 1 ).get_idCompany() );
            //----fin Assert CreateEntityList


            //---CreateDtoList
            _listEntity.add( _entity );
            _listEntity.add( _entity2 );
            _listDto = _MCompanyWithOutLink.CreateDtoList( _listEntity );
            assertEquals( _listDto.get(0).get_idCompany() , ( ( Company ) _listEntity.get(0) ).get_idCompany() );
            assertEquals( _listDto.get(0).get_name()      , ( ( Company ) _listEntity.get(0) ).get_name() );
            assertEquals( _listDto.get(1).get_idCompany() , ( ( Company ) _listEntity.get(1) ).get_idCompany() );
            assertEquals( _listDto.get(1).get_name()      , ( ( Company ) _listEntity.get(1) ).get_name() );
            //----fin Assert CreateDtoList

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Pruebas unitarias para el Mapper MapperCompanyWithOutIdAndLink en CompanyMapper
     */
    @Test
    void MapperCompanyWithOutIdAndLinkTest(){
        try {
            //-----CreateEntity----
            DTOCompanyWithOutIdAndLink _DTO = DTOFactory.CreateDtoCompanyWithOut_idAndlink("Company 1", "Des"
                    , true);

            DTOCompanyWithOutIdAndLink _DTO2 = DTOFactory.CreateDtoCompanyWithOut_idAndlink("Company 2", "Des1"
                    , true);

            List<DTOCompanyWithOutIdAndLink> _listDto = new ArrayList<>();

            Entity _entity = _MCompanyWithOutIdAndLink.CreateEntity( _DTO );
            Entity _entity2 = _MCompanyWithOutIdAndLink.CreateEntity( _DTO2 );

            Company _com = (Company) _entity;
            Company _com2 = (Company) _entity2;

            assertEquals(_com.get_name(), _DTO.get_name());
            assertEquals(_com.get_desc(), _DTO.get_desc());
            assertEquals(_com.get_status(), _DTO.get_status());
            //----fin Asserts Create Entity

            //----Create DTO
            DTOCompanyWithOutIdAndLink _D2 = _MCompanyWithOutIdAndLink.CreateDto(_entity);

            assertEquals(_D2.get_name(), _com.get_name());
            assertEquals(_D2.get_desc(), _com.get_desc());
            assertEquals(_D2.get_status(), _com.get_status());
            //----fin Asserts CreateDTO

            //----CreateEntityList
            _listDto.add( _DTO );
            _listDto.add( _DTO2 );
            List<Entity> _listEntity = new ArrayList<>();
            _listEntity = _MCompanyWithOutIdAndLink.CreateEntityList( _listDto );

            assertEquals( ( ( Company )_listEntity.get( 0 ) ).get_name(), _listDto.get( 0 ).get_name() );
            assertEquals( ( ( Company )_listEntity.get( 1 ) ).get_name(), _listDto.get( 1 ).get_name() );
            //----fin Assert CreateEntityList


            //---CreateDtoList
            _listEntity.add( _entity );
            _listEntity.add( _entity2 );
            _listDto = _MCompanyWithOutIdAndLink.CreateDtoList( _listEntity );

            assertEquals( _listDto.get(0).get_name()      , ( ( Company ) _listEntity.get(0) ).get_name() );
            assertEquals( _listDto.get(1).get_name()      , ( ( Company ) _listEntity.get(1) ).get_name() );
            //----fin Assert CreateDtoList

        }
        catch (Exception e){
            e.printStackTrace();

        }

    }

    /**
     * Pruebas unitarias para el Mapper MapperFullCompany en CompanyMapper
     */
    @Test
    void MapperFullCompanyTest(){
        try {
            //-----CreateEntity----
            DTOFullCompany _DTO = DTOFactory.CreateDtoFullCompany(1,"Company 1", "Des"
                    , true , "D" , 1);

            DTOFullCompany _DTO2 = DTOFactory.CreateDtoFullCompany(2,"Company 2", "Des1"
                    , true , "E",2);

            List<DTOFullCompany> _listDto = new ArrayList<>();

            Entity _entity = _MFullCompany.CreateEntity( _DTO );
            Entity _entity2 = _MFullCompany.CreateEntity( _DTO2 );

            Company _com = (Company) _entity;
            Company _com2 = (Company) _entity2;

            assertEquals( _com.get_idCompany() , _DTO.get_idCompany());
            assertEquals( _com.get_name()      , _DTO.get_name());
            assertEquals( _com.get_desc()      , _DTO.get_desc());
            assertEquals( _com.get_status()    , _DTO.get_status());
            //----fin Asserts Create Entity

            //----Create DTO
            DTOFullCompany _D2 = _MFullCompany.CreateDto(_entity);

            assertEquals(_D2.get_idCompany() , _com.get_idCompany());
            assertEquals(_D2.get_name()      , _com.get_name());
            assertEquals(_D2.get_desc()      , _com.get_desc());
            assertEquals(_D2.get_status()    , _com.get_status());
            //----fin Asserts CreateDTO

            //----CreateEntityList
            _listDto.add( _DTO );
            _listDto.add( _DTO2 );
            List<Entity> _listEntity = new ArrayList<>();
            _listEntity = _MFullCompany.CreateEntityList( _listDto );
            assertEquals( ( ( Company )_listEntity.get( 0 ) ).get_name(), _listDto.get( 0 ).get_name() );
            assertEquals( ( ( Company )_listEntity.get( 1 ) ).get_name(), _listDto.get( 1 ).get_name() );
            assertEquals( ( ( Company )_listEntity.get( 0 ) ).get_idCompany(), _listDto.get( 0 ).get_idCompany() );
            assertEquals( ( ( Company )_listEntity.get( 1 ) ).get_idCompany(), _listDto.get( 1 ).get_idCompany() );
            //----fin Assert CreateEntityList

            //---CreateDtoList
            _listEntity.add( _entity );
            _listEntity.add( _entity2 );
            _listDto = _MFullCompany.CreateDtoList( _listEntity );
            assertEquals( _listDto.get(0).get_idCompany() , ( ( Company ) _listEntity.get(0) ).get_idCompany() );
            assertEquals( _listDto.get(0).get_name()      , ( ( Company ) _listEntity.get(0) ).get_name() );
            assertEquals( _listDto.get(1).get_idCompany() , ( ( Company ) _listEntity.get(1) ).get_idCompany() );
            assertEquals( _listDto.get(1).get_name()      , ( ( Company ) _listEntity.get(1) ).get_name() );
            //----fin Assert CreateDtoList

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Pruebas unitarias para el Mapper MapperIdCompany en CompanyMapper
     */
    @Test
    void MapperIdCompanyTest(){
        try {
            //-----CreateEntity----
            DTOIdCompany _DTO = DTOFactory.CreateDTOIdCompany(1);

            DTOIdCompany _DTO2 = DTOFactory.CreateDTOIdCompany(2);

            List<DTOIdCompany> _listDto = new ArrayList<>();

            Entity _entity = _MIdCompany.CreateEntity( _DTO );
            Entity _entity2 = _MIdCompany.CreateEntity( _DTO2 );

            Company _com = (Company) _entity;
            Company _com2 = (Company) _entity2;

            assertEquals( _com.get_idCompany() , _DTO.get_idCompany());
            //----fin Asserts Create Entity

            //----Create DTO
            DTOIdCompany _D2 = _MIdCompany.CreateDto(_entity);

            assertEquals(_D2.get_idCompany() , _com.get_idCompany());
            //----fin Asserts CreateDTO

            //----CreateEntityList
            _listDto.add( _DTO );
            _listDto.add( _DTO2 );
            List<Entity> _listEntity = new ArrayList<>();
            _listEntity = _MIdCompany.CreateEntityList( _listDto );
            assertEquals( ( ( Company )_listEntity.get( 0 ) ).get_idCompany(), _listDto.get( 0 ).get_idCompany() );
            assertEquals( ( ( Company )_listEntity.get( 1 ) ).get_idCompany(), _listDto.get( 1 ).get_idCompany() );
            //----fin Assert CreateEntityList

            //---CreateDtoList
            _listEntity.add( _entity );
            _listEntity.add( _entity2 );
            _listDto = _MIdCompany.CreateDtoList( _listEntity );
            assertEquals( _listDto.get(0).get_idCompany() , ( ( Company ) _listEntity.get(0) ).get_idCompany() );
            assertEquals( _listDto.get(1).get_idCompany() , ( ( Company ) _listEntity.get(1) ).get_idCompany() );
            //----fin Assert CreateDtoList

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void MapperIdCompanyUserTest(){
        try {
            //-----CreateEntity----
            DTOIdCompUser _DTO = DTOFactory.createDTOIdCompUser(1,1);

            DTOIdCompUser _DTO2 = DTOFactory.createDTOIdCompUser(2,2);

            List<DTOIdCompUser> _listDto = new ArrayList<>();

            Entity _entity = _MIdUser.CreateEntity( _DTO );
            Entity _entity2 = _MIdUser.CreateEntity( _DTO2 );

            Company _com = (Company) _entity;
            Company _com2 = (Company) _entity2;

            assertEquals( _com.get_idCompany() , _DTO.get_idCompany() );
            assertEquals( _com.get_idUser()    , _DTO.get_idUser() );
            //----fin Asserts Create Entity

            //----Create DTO
            DTOIdCompUser _D2 = _MIdUser.CreateDto(_entity);

            assertEquals( _D2.get_idCompany() , _com.get_idCompany() );
            assertEquals( _D2.get_idUser()    , _com.get_idUser() );
            //----fin Asserts CreateDTO

            //----CreateEntityList
            _listDto.add( _DTO );
            _listDto.add( _DTO2 );
            List<Entity> _listEntity = new ArrayList<>();
            _listEntity = _MIdUser.CreateEntityList( _listDto );
            assertEquals( ( ( Company )_listEntity.get( 0 ) ).get_idCompany(), _listDto.get( 0 ).get_idCompany() );
            assertEquals( ( ( Company )_listEntity.get( 1 ) ).get_idCompany(), _listDto.get( 1 ).get_idCompany() );
            assertEquals( ( ( Company )_listEntity.get( 0 ) ).get_idUser(), _listDto.get( 0 ).get_idUser() );
            assertEquals( ( ( Company )_listEntity.get( 1 ) ).get_idUser(), _listDto.get( 1 ).get_idUser() );

            //----fin Assert CreateEntityList


            //---CreateDtoList
            _listEntity.add( _entity );
            _listEntity.add( _entity2 );
            _listDto = _MIdUser.CreateDtoList( _listEntity );
            assertEquals( _listDto.get(0).get_idCompany() , ( ( Company ) _listEntity.get(0) ).get_idCompany() );
            assertEquals( _listDto.get(1).get_idCompany() , ( ( Company ) _listEntity.get(1) ).get_idCompany() );
            assertEquals( _listDto.get(0).get_idUser() , ( ( Company ) _listEntity.get(0) ).get_idUser() );
            assertEquals( _listDto.get(1).get_idUser() , ( ( Company ) _listEntity.get(1) ).get_idUser() );

            //----fin Assert CreateDtoList

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Pruebas unitarias para el Mapper MapperIdStatus en CompanyMapper
     */
    @Test
    void MapperIdStatusTest(){
        try {
            //-----CreateEntity----
            DTOIdStatusCompany _DTO = DTOFactory.CreateDTOIdStatusCompany(1 , true);

            DTOIdStatusCompany _DTO2 = DTOFactory.CreateDTOIdStatusCompany(2 , false);

            List<DTOIdStatusCompany> _listDto = new ArrayList<>();

            Entity _entity = _MIdStatus.CreateEntity( _DTO );
            Entity _entity2 = _MIdStatus.CreateEntity( _DTO2 );

            Company _com = (Company) _entity;
            Company _com2 = (Company) _entity2;

            assertEquals( _com.get_idCompany() , _DTO.get_idCompany());
            assertEquals( _com.get_status()    , _DTO.is_status() );
            //----fin Asserts Create Entity

            //----Create DTO
            DTOIdStatusCompany _D2 = _MIdStatus.CreateDto(_entity);

            assertEquals(_D2.get_idCompany() , _com.get_idCompany());
            assertEquals(_D2.is_status() , _com.get_status());
            //----fin Asserts CreateDTO

            //----CreateEntityList
            _listDto.add( _DTO );
            _listDto.add( _DTO2 );
            List<Entity> _listEntity = new ArrayList<>();
            _listEntity = _MIdStatus.CreateEntityList( _listDto );
            assertEquals( ( ( Company )_listEntity.get( 0 ) ).get_idCompany(), _listDto.get( 0 ).get_idCompany() );
            assertEquals( ( ( Company )_listEntity.get( 1 ) ).get_idCompany(), _listDto.get( 1 ).get_idCompany() );
            assertEquals( ( ( Company )_listEntity.get( 0 ) ).get_status()   , _listDto.get( 0 ).is_status() );
            assertEquals( ( ( Company )_listEntity.get( 1 ) ).get_status()   , _listDto.get( 1 ).is_status() );
            //----fin Assert CreateEntityList

            //---CreateDtoList
            _listEntity.add( _entity );
            _listEntity.add( _entity2 );
            _listDto = _MIdStatus.CreateDtoList( _listEntity );
            assertEquals( _listDto.get(0).get_idCompany() , ( ( Company ) _listEntity.get(0) ).get_idCompany() );
            assertEquals( _listDto.get(1).get_idCompany() , ( ( Company ) _listEntity.get(1) ).get_idCompany() );
            assertEquals( _listDto.get(0).is_status()     , ( ( Company ) _listEntity.get(0) ).get_status() );
            assertEquals( _listDto.get(1).is_status()     , ( ( Company ) _listEntity.get(1) ).get_status() );
            //----fin Assert CreateDtoList
        }
        catch (Exception e){
            e.printStackTrace();

        }
    }
}
