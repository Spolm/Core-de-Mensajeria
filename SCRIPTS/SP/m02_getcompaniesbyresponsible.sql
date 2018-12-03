-- Function: m02_getcompaniesbyresponsible(integer)

--DROP FUNCTION m02_getcompaniesbyresponsible(integer);

CREATE OR REPLACE FUNCTION m02_getcompaniesbyresponsible ( IN _user integer)
    RETURNS TABLE(com_id integer, com_name character varying, com_description character varying, com_status boolean, com_route_link character varying, res_use_id integer) AS
$BODY$
BEGIN
RETURN QUERY
SELECT DISTINCT co.com_id, co.com_name, co.com_description, co.com_status, co.com_route_link, r.res_use_id FROM company co INNER JOIN responsability r
    on co.com_id = r.res_com_id INNER JOIN "user" u
    on r.res_use_id = _user
        ORDER BY com_status DESC;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m02_getcompaniesbyresponsible (integer)
    OWNER TO "CoreMensajeria";