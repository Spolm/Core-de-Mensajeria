CREATE OR REPLACE FUNCTION m09_getDaysofMonth()
 RETURNS TABLE (
 dat_dayofmonth INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_dayofmonth FROM dim_date d ORDER BY d.dat_dayofmonth;
END; $$
Language 'plpgsql';
