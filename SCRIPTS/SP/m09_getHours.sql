CREATE OR REPLACE FUNCTION m09_getHours()
 RETURNS TABLE (
 dat_hourofday INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_hourofday FROM dim_date d ORDER BY d.dat_hourofday;
END; $$
Language 'plpgsql';
