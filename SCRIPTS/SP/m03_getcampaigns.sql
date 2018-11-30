-- Function: m03_getcampaigns(integer)

--DROP FUNCTION m03_getcampaigns(integer);

CREATE OR REPLACE FUNCTION m03_getcampaigns ( IN company integer)
    RETURNS TABLE(cam_id integer, cam_name character varying, cam_description character varying, cam_start_date TimeStamp, cam_end_date TimeStamp,
                cam_status boolean) AS
$BODY$
BEGIN
RETURN QUERY
SELECT  c.cam_id, c.cam_name, c.cam_description, c.cam_start_date, c.cam_end_date, c.cam_status
        FROM campaign c 
        WHERE (c.cam_company_id = company)
        ORDER BY cam_status DESC;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m03_getcampaigns(integer)
    OWNER TO "CoreMensajeria";