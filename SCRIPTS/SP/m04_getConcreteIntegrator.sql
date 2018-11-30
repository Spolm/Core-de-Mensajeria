CREATE OR REPLACE FUNCTION m04_getConcreteIntegrator(IN _id integer)
    RETURNS TABLE(int_id integer, int_name character varying, int_messagecost float, int_threadcapacity integer, int_tokenapi character varying, int_enabled boolean) AS
$BODY$
BEGIN
RETURN QUERY
SELECT * FROM integrator i WHERE i.int_id = _id;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m04_getintegrators
    OWNER TO "CoreMensajeria";