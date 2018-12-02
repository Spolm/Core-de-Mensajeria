--ADD APPLICATION
CREATE FUNCTION m06_add_application(IN app_name_v character varying, IN app_description_v character varying, 
			   IN app_token_v character varying,IN app_user_creator_v integer,IN app_company_v integer)
			   
RETURNS void AS $addapplication$
BEGIN
	INSERT INTO public.application(app_name,app_description,app_token,app_user_creator,app_company,app_status,app_date)
                                   values(app_name_v, app_description_v, app_token_v, app_user_creator_v,app_company_v, 1, now() );
  END; 
  $addapplication$
  LANGUAGE 'plpgsql' VOLATILE;

--SELECT APPLICATIONS
CREATE FUNCTION m06_select_all_application() 
RETURNS table (app_id integer, app_name character varying, app_description character varying, 
			   app_token character varying, app_date timestamp without time zone,
			   app_status integer, app_user_creator integer, app_company integer) AS $selectapplication$
BEGIN
	RETURN QUERY
	SELECT app.app_id , app.app_name, app.app_description, 
	app.app_token, app.app_date , app.app_status, app.app_user_creator , app.app_company 
	FROM public.application as app
	ORDER BY app.app_name ;
  END; 
  $selectapplication$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100
  ROWS 1000;

CREATE FUNCTION m06_select_by_token_application(IN app_token_v character varying) 
RETURNS table (app_id integer, app_name character varying, app_description character varying, 
			   app_token character varying, app_date timestamp without time zone,
			   app_status integer, app_user_creator integer, app_company integer) AS $selectapplicationbytoken$
BEGIN
	RETURN QUERY
	SELECT app.app_id , app.app_name, app.app_description, 
	app.app_token, app.app_date , app.app_status, app.app_user_creator , app.app_company  
	FROM public.application as app
	WHERE app.app_token = app_token_v;
  END; 
  $selectapplicationbytoken$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100
  ROWS 1000;

--UPDATE APPLICATION
CREATE OR REPLACE FUNCTION m06_update_application_status(IN app_status_v integer, IN app_id_v integer ) 
RETURNS void AS
$updateapplication$
BEGIN
	  UPDATE public.application SET app_status = app_status_v WHERE app_id = app_id_v ;
END;
$updateapplication$
LANGUAGE plpgsql VOLATILE;