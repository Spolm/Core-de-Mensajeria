CREATE OR REPLACE FUNCTION Get_All_Company () 
 RETURNS TABLE (
 companieId   INT,
 companieName VARCHAR(200)
 
) 
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT com_id, com_name FROM dim_company_campaign ORDER BY com_id;
END; $$ 
Language 'plpgsql';


/*SELECT  companieId AS com_id
	,companieName AS com_name
 FROM Get_All_Company()*/
