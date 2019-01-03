-- Function: m02_addcompany(character varying, character varying, boolean, character varying)

/*DROP FUNCTION FUNCTION mo2_addcampaign ( _name character varying, 
										   _description character varying,
										   _status boolean, 
										   _startDate date,
                                           _endDate date,
										    _companyId int
										  ) */

CREATE OR REPLACE FUNCTION m02_addcampaign ( _name character varying, 
										   _description character varying,
										   _status boolean, 
										   _startDate date,
                                           _endDate date,
										    _companyId int
										  )
    RETURNS VOID AS
$BODY$
BEGIN
INSERT INTO public.campaign(
     cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id)
	VALUES (_name, _description, true, _startDate, _endDate, _companyId);
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100;
	
ALTER FUNCTION  m02_addcampaign (  _name character varying, 
										   _description character varying,
										   _status boolean, 
										   _startDate date,
                                           _endDate date,
										   _companyId int
										)
    OWNER TO "CoreMensajeria";