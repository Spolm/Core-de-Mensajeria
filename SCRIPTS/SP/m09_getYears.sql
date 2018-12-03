CREATE OR REPLACE FUNCTION m09_getYears()
 RETURNS TABLE (
 dat_year INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_year FROM dim_date d ORDER BY d.dat_yer;
END; $$
Language 'plpgsql';
