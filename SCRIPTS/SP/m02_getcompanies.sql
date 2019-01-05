-- Function: m02_getcompanies(integer)

--DROP FUNCTION m02_getcompanies(integer);

CREATE OR REPLACE FUNCTION m02_getcompanies ( IN _user integer)
    RETURNS TABLE(com_id integer, com_name character varying, com_description character varying, com_status boolean, com_route_link character varying) AS
$BODY$
BEGIN
RETURN QUERY
SELECT  c.com_id, c.com_name, c.com_description, c.com_status, c.com_route_link
        FROM company c
        WHERE (c.com_user_id = _user)
        ORDER BY com_status DESC;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m02_getcompanies(integer)
    OWNER TO "CoreMensajeria";
