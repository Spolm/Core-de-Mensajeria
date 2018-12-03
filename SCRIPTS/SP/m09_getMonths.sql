CREATE OR REPLACE FUNCTION m09_getMonths()
 RETURNS TABLE (
 dat_month INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_month FROM dim_date d ORDER BY d.dat_month;
END; $$
Language 'plpgsql';
