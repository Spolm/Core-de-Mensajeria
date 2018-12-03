CREATE OR REPLACE FUNCTION m09_getSeconds()
 RETURNS TABLE (
 dat_secondofminute INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_secondofminute FROM dim_date d ORDER BY d.dat_secondofminute;
END; $$
Language 'plpgsql';
