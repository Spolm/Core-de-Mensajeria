-- Function: m02_getcompaniesall()

--DROP FUNCTION m02_getcompaniesall();

CREATE OR REPLACE FUNCTION m02_getcompaniesall ()
    RETURNS TABLE(com_id integer, com_name character varying, com_description character varying, com_status boolean, com_route_link character varying,com_user_id integer) AS
$BODY$
BEGIN
RETURN QUERY
SELECT  c.com_id, c.com_name, c.com_description, c.com_status, c.com_route_link, c.com_user_id
        FROM company c 
        ORDER BY com_id DESC;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m02_getcompaniesall()
    OWNER TO "CoreMensajeria";