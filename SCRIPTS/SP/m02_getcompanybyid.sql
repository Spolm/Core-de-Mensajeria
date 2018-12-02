-- Function: m02_getcompanybyid(integer)

--DROP FUNCTION m02_getcompanybyid(integer);

CREATE OR REPLACE FUNCTION m02_getcompanybyid ( IN _cmpny integer)
    RETURNS TABLE(com_id integer, com_name character varying, com_description character varying, com_status boolean) AS
$BODY$
BEGIN
RETURN QUERY
SELECT  c.com_id, c.com_name, c.com_description, c.com_status
        FROM company c 
        WHERE (c.com_id = _cmpny)
        ORDER BY com_status DESC;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m02_getcompanybyid(integer)
    OWNER TO "CoreMensajeria";