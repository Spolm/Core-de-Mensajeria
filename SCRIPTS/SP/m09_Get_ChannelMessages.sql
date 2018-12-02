
CREATE OR REPLACE FUNCTION Get_ChannelMessages () 
 RETURNS TABLE (
 id INT,
 name VARCHAR(200),
 count bigint
) 
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT c.cha_id, c.cha_name, messages from dim_channel c, 
                        (select sen_cha_id, count(*) as messages from fact_sent_message 
                        group by sen_cha_id) as m where c.cha_id = m.sen_cha_id ORDER BY c.cha_id ASC;
END; $$ 
Language 'plpgsql';


--SELECT id as cha_id, name as cha_name, count as messages from public.Get_ChannelMessages();
--drop function Get_ChannelMessages(); 