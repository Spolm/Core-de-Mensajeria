CREATE OR REPLACE FUNCTION Get_All_Campaign () 
 RETURNS TABLE (
 campId   INT,
 campName VARCHAR(200)
 
) 
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT cam_id, cam_name FROM dim_company_campaign ORDER BY cam_id;
END; $$ 
Language 'plpgsql';


/*SELECT campId AS cam_id,
campName AS cam_name
FROM Get_All_Campaign()*/
