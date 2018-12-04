--DROP FUNCTION m10_getallroles();

CREATE OR REPLACE FUNCTION m10_getallroles ()
    RETURNS TABLE(rol_id integer, rol_name character varying) AS
$BODY$
BEGIN
RETURN QUERY
SELECT  r.rol_id, r.rol_name FROM role r;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m10_getallroles()
    OWNER TO "CoreMensajeria";