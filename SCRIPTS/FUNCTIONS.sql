\c CoreMensajeria_StarSchema CoreMensajeria

CREATE FUNCTION update_starschema() RETURNS void AS $$
  BEGIN
    DELETE FROM Fact_Sent_Message;
    DELETE FROM Dim_Date;
    DELETE FROM Dim_Company_Campaign;
    DELETE FROM Dim_Channel;
    DELETE FROM Dim_Integrator;
    DELETE FROM Dim_Application;

    -- Insert data from primary database to StarSchema database.
    INSERT INTO Dim_Company_Campaign SELECT use_id, com_id, cam_id, com_name, cam_name, cam_description, cam_status, cam_start_date, cam_end_date
                                   FROM stats.Company, stats.Campaign, stats.User WHERE com_id = cam_company_id AND com_user_id = use_id;
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

SELECT update_starschema();
