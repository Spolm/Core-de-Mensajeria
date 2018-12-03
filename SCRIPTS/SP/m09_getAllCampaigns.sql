CREATE OR REPLACE FUNCTION m09_getAllCampaigns(VARIADIC companies_id NUMERIC[]) 
 RETURNS TABLE (
 cam_id   INT,
 cam_name VARCHAR(200)
 
) 
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT c.cam_id, c.cam_name FROM dim_company_campaign c WHERE c.com_id = ANY(companies_id) ORDER BY c.cam_id;
END; $$ 
Language 'plpgsql';


/*SELECT campId AS cam_id,
campName AS cam_name
FROM Get_All_Campaign()*/
