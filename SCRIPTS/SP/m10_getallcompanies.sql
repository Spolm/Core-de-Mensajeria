--DROP FUNCTION m10_getcompanies(integer);

CREATE OR REPLACE FUNCTION m10_getallcompanies ()
    RETURNS TABLE(com_id integer, com_name character varying, com_description character varying, com_status boolean) AS
$BODY$
BEGIN
RETURN QUERY
SELECT  c.com_id, c.com_name, c.com_description, c.com_status
        FROM company c;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m10_getallcompanies()
    OWNER TO "CoreMensajeria";