
CREATE OR REPLACE FUNCTION Get_CampaignMessages () 
 RETURNS TABLE (
 id INT,
 name VARCHAR(200),
 count bigint
) 
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT c.cam_id, c.cam_name, messages from dim_company_campaign c,
                        (select sen_cam_id, count(*) as messages from fact_sent_message 
                        group by sen_cam_id) as m where c.cam_id = m.sen_cam_id ORDER BY c.cam_id ASC;
END; $$ 
Language 'plpgsql';


--SELECT id as cam_id, name as cam_name, count as messages from public.Get_CampaignMessages();
--drop function Get_CompanyMessages(); 
