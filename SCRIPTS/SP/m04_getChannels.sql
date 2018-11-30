CREATE OR REPLACE FUNCTION m04_getchannels()
    RETURNS TABLE(cha_id integer, cha_name character varying, cha_description character varying) AS
$BODY$
BEGIN
RETURN QUERY
SELECT * FROM channel;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m04_getchannels
    OWNER TO "CoreMensajeria";