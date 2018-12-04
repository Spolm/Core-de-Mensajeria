CREATE OR REPLACE FUNCTION Get_CampaignMessages () 
 RETURNS TABLE (
 id INT,
 name VARCHAR(200),
 count bigint
) 
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT c.cam_id, c.cam_name, messages from dim_company_campaign c,
                        (select sen_cam_id, count(*) as messages from fact_sent_message 
                        group by sen_cam_id) as m where c.cam_id = m.sen_cam_id ORDER BY c.cam_id ASC;
END; $$ 
Language 'plpgsql';


CREATE OR REPLACE FUNCTION Get_ChannelMessages () 
 RETURNS TABLE (
 id INT,
 name VARCHAR(200),
 count bigint
) 
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT c.cha_id, c.cha_name, messages from dim_channel c, 
                        (select sen_cha_id, count(*) as messages from fact_sent_message 
                        group by sen_cha_id) as m where c.cha_id = m.sen_cha_id ORDER BY c.cha_id ASC;
END; $$ 
Language 'plpgsql';


CREATE OR REPLACE FUNCTION Get_CompanyMessages () 
 RETURNS TABLE (
 id INT,
 name VARCHAR(200),
 count bigint
) 
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT c.com_id, c.com_name, messages from dim_company_campaign c,
                        (select sen_com_id, count(*) as messages from fact_sent_message 
                        group by sen_com_id) as m where c.com_id = m.sen_com_id ORDER BY c.com_id ASC;
END; $$ 
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_getAllCampaigns(VARIADIC companies_id NUMERIC[]) 
 RETURNS TABLE (
 cam_id   INT,
 cam_name VARCHAR(200)
 
) 
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT c.cam_id, c.cam_name FROM dim_company_campaign c WHERE c.com_id = ANY(companies_id) ORDER BY c.cam_id;
END; $$ 
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_getAllChannel() 
 RETURNS TABLE (
 chanId   INT,
 chanName VARCHAR(200)
) 
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT cha_id, cha_name FROM dim_channel ORDER BY cha_id;
END; $$ 
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_getDaysofMonth()
 RETURNS TABLE (
 dat_dayofmonth INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_dayofmonth FROM dim_date d ORDER BY d.dat_dayofmonth;
END; $$
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_getDaysofWeek()
 RETURNS TABLE (
 dat_dayofweek INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_dayofweek FROM dim_date d ORDER BY d.dat_dayofweek;
END; $$
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_getDaysofYear()
 RETURNS TABLE (
 dat_dayofyear INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_dayofyear FROM dim_date d ORDER BY d.dat_dayofyear;
END; $$
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_getHours()
 RETURNS TABLE (
 dat_hourofday INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_hourofday FROM dim_date d ORDER BY d.dat_hourofday;
END; $$
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_getMinutes()
 RETURNS TABLE (
 dat_minuteofhour INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_minuteofhour FROM dim_date d ORDER BY d.dat_minuteofhour;
END; $$
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_getQuartersofYear()
 RETURNS TABLE (
 dat_quarterofyear INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_quarterofyear FROM dim_date d ORDER BY d.dat_quarterofyear;
END; $$
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_getSeconds()
 RETURNS TABLE (
 dat_secondofminute INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_secondofminute FROM dim_date d ORDER BY d.dat_secondofminute;
END; $$
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_getWeeksofYear()
 RETURNS TABLE (
 dat_weekofyear INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_weekofyear FROM dim_date d ORDER BY d.dat_weekofyear;
END; $$
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_getMonths()
 RETURNS TABLE (
 dat_month INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_month FROM dim_date d ORDER BY d.dat_month;
END; $$
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_getYears()
 RETURNS TABLE (
 dat_year INT
)
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT d.dat_year FROM dim_date d ORDER BY d.dat_year;
END; $$
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_get_MessageParameter (IN companyIds varchar, IN campaignIds varchar,
  IN channelIds varchar, IN integratorIds varchar, IN yearsIds varchar, IN monthsIds varchar,
  IN daysofweekIds varchar, IN weeksofyearIds varchar, IN daysofmonthIds varchar, IN daysofyearIds varchar,
  IN hourIds varchar, IN minutesIds varchar, IN secondsIds varchar, IN quartersofyearIds varchar,
  IN param1 varchar, IN param2 varchar, IN param3 varchar, IN param4 varchar, IN param5 varchar, IN param6 varchar)
 RETURNS TABLE (
 icount BIGINT,
 paramId int,
 paramName VARCHAR(200)
)
AS $$
BEGIN
	RETURN QUERY EXECUTE('SELECT count(DISTINCT me.*) as msgs, ' || param1 || ', ' || param2 || ' as name ' ||
                  'FROM fact_sent_message me ' || param3 ||  param5 || ' ' ||
                  'WHERE ' || param1 || ' = ' || param4  || param6 ||  companyIds || campaignIds || channelIds ||
                  integratorIds ||  yearsIds || monthsIds || daysofweekIds || weeksofyearIds ||
                  daysofmonthIds || daysofyearIds || hourIds || minutesIds || secondsIds ||
                  quartersofyearIds ||
                  ' GROUP BY ' || param1 || ', '|| param2);
END; $$
Language 'plpgsql';


CREATE OR REPLACE FUNCTION m09_get_MessagesTime (IN companyIds varchar, IN campaignIds varchar,
  IN channelIds varchar, IN integratorIds varchar, IN yearsIds varchar, IN monthsIds varchar,
  IN daysofweekIds varchar, IN weeksofyearIds varchar, IN daysofmonthIds varchar, IN daysofyearIds varchar,
  IN hourIds varchar, IN minutesIds varchar, IN secondsIds varchar, IN quartersofyearIds varchar,
  IN param1 varchar, IN param2 varchar, IN param3 varchar, IN param4 varchar)
 RETURNS TABLE (
 icount BIGINT,
 paramName int
)
AS $$
BEGIN
	RETURN QUERY EXECUTE('SELECT count(DISTINCT me.*) as msgs, ' || param1 || ', ' || param2 || ' as name ' ||
                  'FROM fact_sent_message me ' || param3 || ' ' ||
                  'WHERE ' || param1 || ' = ' || param4  || companyIds || campaignIds || channelIds ||
                  integratorIds ||  yearsIds || monthsIds || daysofweekIds || weeksofyearIds ||
                  daysofmonthIds || daysofyearIds || hourIds || minutesIds || secondsIds ||
                  quartersofyearIds ||
                  ' GROUP BY ' || param1 || ', '|| param2);
END; $$
Language 'plpgsql';


CREATE FUNCTION m09_update_starschema() RETURNS void AS $$
  BEGIN
    DELETE FROM Fact_Sent_Message;
    DELETE FROM Dim_Date;
    DELETE FROM Dim_Company_Campaign;
    DELETE FROM Dim_Channel;
    DELETE FROM Dim_Integrator;
    DELETE FROM Dim_Application;

    -- Insert data from primary database to StarSchema database.
    INSERT INTO Dim_Company_Campaign SELECT com_id, cam_id, com_name, cam_name, cam_description, cam_status, cam_start_date, cam_end_date
                                   FROM stats.Company, stats.Campaign WHERE com_id = cam_company_id;
    INSERT INTO Dim_Channel SELECT cha_id, cha_name, cha_description FROM stats.Channel;
    INSERT INTO Dim_Integrator SELECT int_id, int_name, int_messageCost FROM stats.Integrator;
    INSERT INTO Dim_Application SELECT app_id, app_name, app_description, app_date FROM stats.Application;
    -- Fill time dimension from message created date.
    INSERT INTO Dim_Date SELECT DISTINCT sen_time, EXTRACT(YEAR FROM sen_time), EXTRACT(MONTH FROM sen_time),
                        EXTRACT(ISODOW FROM sen_time), EXTRACT(WEEK FROM sen_time), EXTRACT(DAY FROM sen_time),
                        EXTRACT(DOY FROM sen_time), EXTRACT(HOUR FROM sen_time), EXTRACT(MINUTE FROM sen_time),
                        EXTRACT(SECOND FROM sen_time), EXTRACT(QUARTER FROM sen_time) FROM stats.Sent_Message;
    -- Create facts table
    INSERT INTO Fact_Sent_Message SELECT m.sen_id, m.sen_campaign, m.sen_time, c.cam_company_id, cha.cha_id,
                            i.int_id, app.app_id FROM stats.Sent_Message m, stats.Campaign c, stats.Channel cha, 
                            stats.Integrator i, stats.Application app WHERE c.cam_id = m.sen_campaign AND 
                            cha.cha_id = m.sen_channel AND i.int_id = m.sen_integrator AND app.app_id = m.sen_application;
  END; $$
  LANGUAGE 'plpgsql';

SELECT m09_update_starschema();


