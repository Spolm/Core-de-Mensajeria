-- Function: m02_getcompanies(integer)

--DROP FUNCTION m03_getcompanies(integer);

CREATE OR REPLACE FUNCTION m03_getcompanies ( IN _user integer)
    RETURNS TABLE(com_id integer, com_name character varying, com_description character varying, com_status boolean) AS
$BODY$
BEGIN
RETURN QUERY
SELECT  c.com_id, c.com_name, c.com_description, c.com_status
        FROM company c 
        WHERE (c.com_user_id = _user)
        ORDER BY com_status DESC;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m03_getcompanies(integer)
    OWNER TO "CoreMensajeria";