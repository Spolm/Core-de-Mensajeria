-- Function: m03_getcampaignsbyuser(integer)

--DROP FUNCTION m03_getcampaignsbyuser(integer);

CREATE OR REPLACE FUNCTION m03_getcampaignsbyuser ( IN _user integer)
    RETURNS TABLE(cam_id integer, cam_name character varying, cam_description character varying, cam_start_date TimeStamp, cam_end_date TimeStamp,
                cam_status boolean) AS
$BODY$
BEGIN
RETURN QUERY
SELECT DISTINCT  ca.cam_id, ca.cam_name, ca.cam_description, ca.cam_start_date, ca.cam_end_date, ca.cam_status from campaign ca INNER JOIN company co ON ca.cam_company_id = co.com_id 
INNER JOIN "user" u ON co.com_user_id = _user
        ORDER BY cam_status DESC;
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100
    ROWS 1000;
ALTER FUNCTION m03_getcampaignsbyuser(integer)
    OWNER TO "CoreMensajeria";