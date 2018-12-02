CREATE OR REPLACE FUNCTION Get_MessageParameter (IN companyIds varchar, IN campaignIds varchar,
  IN channelIds varchar, IN param1 varchar, IN param2 varchar, IN param3 varchar, IN param4 varchar)
 RETURNS TABLE (
 icount BIGINT,
 paramId integer,
 paramName VARCHAR(200)
)
AS $$
BEGIN
	RETURN QUERY EXECUTE('SELECT count(DISTINCT me.*) as msgs, '|| param1 || ', ' || param2 || ' as name ' ||
                  'FROM fact_sent_message me, ' || param3 || ' ' ||
                  'WHERE ' || param1 || ' = ' || param4 || ' ' || companyIds || ' ' || campaignIds || ' ' || channelIds || ' ' ||
                  'GROUP BY ' || param1 || ' , ' || param2);
END; $$
Language 'plpgsql';
