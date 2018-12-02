CREATE OR REPLACE FUNCTION m09_getAllChannel() 
 RETURNS TABLE (
 chanId   INT,
 chanName VARCHAR(200)
) 
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT cha_id, cha_name FROM dim_channel ORDER BY cha_id;
END; $$ 
Language 'plpgsql';

/*SELECT chanId AS cha_id
,chanName AS cha_name
 FROM Get_All_Channel()*/