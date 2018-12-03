CREATE OR REPLACE FUNCTION Get_CompanyMessages () 
 RETURNS TABLE (
 id INT,
 name VARCHAR(200),
 count bigint
) 
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT c.com_id, c.com_name, messages from dim_company_campaign c,
                        (select sen_com_id, count(*) as messages from fact_sent_message 
                        group by sen_com_id) as m where c.com_id = m.sen_com_id ORDER BY c.com_id ASC;
END; $$ 
Language 'plpgsql';


--SELECT id as com_id, name as com_name, count as messages from public.Get_CompanyMessages();
--drop function Get_CompanyMessages(); 


