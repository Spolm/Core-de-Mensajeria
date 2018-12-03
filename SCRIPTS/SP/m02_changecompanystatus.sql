-- Function: m02_changecompanystatus(integer, boolean)

-- DROP FUNCTION m02_changecompanystatus(integer, boolean);

CREATE OR REPLACE FUNCTION m02_changecompanystatus ( _company integer, _status boolean)
    RETURNS void AS
$BODY$
BEGIN
    UPDATE public.company set com_status = _status 
    WHERE com_id = _company;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100;
ALTER FUNCTION m02_changecompanystatus(integer, boolean)
    OWNER TO "CoreMensajeria";
