CREATE OR REPLACE FUNCTION m04_getIntegratorsByChannel(IN _id integer)
    RETURNS TABLE(int_id integer, int_name character varying, int_messagecost float, int_threadcapacity integer, int_tokenapi character varying, int_enabled boolean) AS
$BODY$
BEGIN
RETURN QUERY
SELECT i.int_id, i.int_name, i.int_messageCost, i.int_threadCapacity, i.int_tokenApi, i.int_enabled FROM INTEGRATOR i, CHANNEL_INTEGRATOR ci , CHANNEL c WHERE i.INT_ID = ci.CI_INTEGRATOR_ID AND ci.CI_CHANNEL_ID = c.CHA_ID AND c.CHA_ID = _id;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m04_getIntegratorsByChannel
    OWNER TO "CoreMensajeria";