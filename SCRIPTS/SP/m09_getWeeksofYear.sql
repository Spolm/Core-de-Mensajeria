CREATE OR REPLACE FUNCTION m09_getWeeksofYear()
 RETURNS TABLE (
 dat_weekofyear INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_weekofyear FROM dim_date d ORDER BY d.dat_weekofyear;
END; $$
Language 'plpgsql';
