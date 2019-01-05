/*Function: FUNCTION m03_updatecampaign ( _name character varying, 
										   _description character varying,
										   _status boolean, 
										   _startDate date,
                                           _endDate date,
										    _companyId int,
                                            _id int
										  )*/

/*DROP FUNCTION FUNCTION m03_updatecampaign ( _name character varying, 
										   _description character varying,
										   _status boolean, 
										   _startDate date,
                                           _endDate date,
										    _companyId int,
                                            _id int
										  );*/

CREATE OR REPLACE FUNCTION m03_updatecampaign ( _name character varying, 
										   _description character varying,
										   _status boolean, 
										   _startDate date,
                                           _endDate date,
										    _companyId int,
                                            _id int
										  )
    RETURNS VOID AS
$BODY$
BEGIN

IF EXISTS(SELECT 1 FROM public.campaign WHERE cam_id=_id LIMIT 1)
THEN
	BEGIN
UPDATE public.campaign
        SET cam_name=_name, 
        cam_description=_description, 
        cam_status=_status,
        cam_start_date=_startDate, 
        cam_end_date=_endDate, 
        cam_company_id=_companyId
		WHERE cam_id =_id;
		END;
END IF;

END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100;



ALTER FUNCTION  m03_updatecampaign ( _name character varying, 
										   _description character varying,
										   _status boolean, 
										   _startDate date,
                                           _endDate date,
										    _companyId int,
                                            _id int
										  )
    OWNER TO "CoreMensajeria";
	
	
	