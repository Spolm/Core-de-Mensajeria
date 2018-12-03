-- Function: m03_changecampaignstatus(integer, boolean)

-- DROP FUNCTION m03_changecampaignstatus(integer, boolean);

CREATE OR REPLACE FUNCTION m03_changecampaignstatus ( _campaign integer, _status boolean)
    RETURNS void AS
$BODY$
BEGIN
    UPDATE public.campaign set cam_status = _status 
    WHERE cam_id = _campaign;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100;
ALTER FUNCTION m03_changecampaignstatus(integer, boolean)
    OWNER TO "CoreMensajeria";
