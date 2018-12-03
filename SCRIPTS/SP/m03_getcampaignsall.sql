-- Function: m03_getcampaignsall()

--DROP FUNCTION m03_getcampaignsall();

CREATE OR REPLACE FUNCTION m03_getcampaignsall ()
    RETURNS TABLE(cam_id integer, cam_name character varying, cam_description character varying, cam_start_date TimeStamp, cam_end_date TimeStamp,
                cam_status boolean) AS
$BODY$
BEGIN
RETURN QUERY
SELECT  c.cam_id, c.cam_name, c.cam_description, c.cam_start_date, c.cam_end_date, c.cam_status
        FROM campaign c 
        ORDER BY cam_id DESC;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m03_getcampaignsall()
    OWNER TO "CoreMensajeria";