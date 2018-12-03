-- Function: m03_getcampaignbyid(integer)

--DROP FUNCTION m03_getcampaignbyid(integer);

CREATE OR REPLACE FUNCTION m03_getcampaigns ( IN _campaign integer)
    RETURNS TABLE(cam_id integer, cam_name character varying, cam_description character varying, cam_start_date TimeStamp, cam_end_date TimeStamp,
                cam_status boolean) AS
$BODY$
BEGIN
RETURN QUERY
SELECT  c.cam_id, c.cam_name, c.cam_description, c.cam_start_date, c.cam_end_date, c.cam_status
        FROM campaign c 
        WHERE (c.cam_id = _campaign)
        ORDER BY cam_status DESC;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m03_getcampaignbyid(integer)
    OWNER TO "CoreMensajeria";