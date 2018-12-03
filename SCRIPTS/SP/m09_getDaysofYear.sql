CREATE OR REPLACE FUNCTION m09_getDaysofYear()
 RETURNS TABLE (
 dat_dayofyear INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_dayofyear FROM dim_date d ORDER BY d.dat_dayofyear;
END; $$
Language 'plpgsql';
