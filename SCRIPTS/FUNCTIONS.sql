\c CoreMensajeria_StarSchema CoreMensajeria

CREATE FUNCTION update_starschema() RETURNS void AS $$
  BEGIN
    DELETE FROM Fact_Message;
    DELETE FROM Dim_Date;
    DELETE FROM Dim_Company_Campaign;
    

    -- Insert data from primary database to StarSchema database.
    INSERT INTO Dim_Company_Campaign SELECT com_id, cam_id, com_name, cam_name, cam_description, cam_status, cam_start_date, cam_end_date
                                   FROM stats.Company, stats.Campaign WHERE com_id = cam_company_id;
    -- Fill time dimension from message created date.
    INSERT INTO Dim_Date SELECT DISTINCT sen_time, EXTRACT(YEAR FROM sen_time), EXTRACT(MONTH FROM sen_time),
                        EXTRACT(ISODOW FROM sen_time), EXTRACT(WEEK FROM sen_time), EXTRACT(DAY FROM sen_time),
                        EXTRACT(DOY FROM sen_time), EXTRACT(HOUR FROM sen_time), EXTRACT(MINUTE FROM sen_time),
                        EXTRACT(SECOND FROM sen_time), EXTRACT(QUARTER FROM sen_time) FROM stats.Sent_Message;
    -- Create facts table
    INSERT INTO Fact_Message SELECT m.sen_id, m.sen_campaign, m.sen_time, c.cam_company_id
                            FROM stats.Sent_Message m, stats.Campaign c WHERE c.cam_id = m.sen_campaign;
  END; $$
  LANGUAGE 'plpgsql';

SELECT update_starschema();
