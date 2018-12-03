CREATE OR REPLACE FUNCTION m09_getMinutes()
 RETURNS TABLE (
 dat_minuteofhour INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_minuteofhour FROM dim_date d ORDER BY d.dat_minuteofhour;
END; $$
Language 'plpgsql';
