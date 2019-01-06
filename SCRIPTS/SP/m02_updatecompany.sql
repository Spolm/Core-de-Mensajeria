/*Function: m02_updatecompany ( _name character varying, 
										   _description character varying,
										   _status boolean, 
										   _link character varying,
										    _userId int,
										    _id int
										  )*/

/*DROP FUNCTION m02_updatecompany ( _name character varying, 
										   _description character varying,
										   _status boolean, 
										   _link character varying,
										    _userId int,
										    _id int
										  );*/

CREATE OR REPLACE FUNCTION m02_updatecompany ( _name character varying, 
										   _description character varying,
										   _status boolean, 
										   _link character varying,
										    _userId int,
										    _id int
										  )
    RETURNS VOID AS
$BODY$
BEGIN

IF EXISTS(SELECT 1 FROM public.company WHERE com_id=_id LIMIT 1)
THEN
	BEGIN
	UPDATE public.company
		SET com_name=_name, 
		com_description=_description, 
		com_status=_status, 
		com_user_id=_userId, 
		com_route_link=_link
		WHERE com_id =_id;
		END;
END IF;

END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100;



ALTER FUNCTION m02_updatecompany ( _name character varying, 
										   _description character varying,
										   _status boolean, 
										   _link character varying,
										    _userId int,
										    _id int
										  )
    OWNER TO "CoreMensajeria";
	
	
	