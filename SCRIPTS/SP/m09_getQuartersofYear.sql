CREATE OR REPLACE FUNCTION m09_getQuartersofYear()
 RETURNS TABLE (
 dat_quarterofyear INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_quarterofyear FROM dim_date d ORDER BY d.dat_quarterofyear;
END; $$
Language 'plpgsql';
