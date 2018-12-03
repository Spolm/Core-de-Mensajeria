CREATE OR REPLACE FUNCTION m09_getDaysofWeek()
 RETURNS TABLE (
 dat_dayofweek INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_dayofweek FROM dim_date d ORDER BY d.dat_dayofweek;
END; $$
Language 'plpgsql';
