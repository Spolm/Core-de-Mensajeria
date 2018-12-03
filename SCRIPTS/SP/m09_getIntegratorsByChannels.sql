CREATE OR REPLACE FUNCTION m09_getIntegratorsByChannels(VARIADIC channels_ids NUMERIC[])
    RETURNS TABLE(int_id integer, int_name character varying) AS
$BODY$
BEGIN
RETURN QUERY
SELECT i.int_id, i.int_name FROM INTEGRATOR i, CHANNEL_INTEGRATOR ci WHERE i.INT_ID = ci.CI_INTEGRATOR_ID AND ci.CI_CHANNEL_ID = ANY(channels_ids);
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m09_getIntegratorsByChannels
    OWNER TO "CoreMensajeria";