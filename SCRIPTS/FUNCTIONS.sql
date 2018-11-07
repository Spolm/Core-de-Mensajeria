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
    INSERT INTO Dim_Date SELECT DISTINCT mes_date, EXTRACT(YEAR FROM mes_date), EXTRACT(MONTH FROM mes_date),
                        EXTRACT(ISODOW FROM mes_date), EXTRACT(WEEK FROM mes_date), EXTRACT(DAY FROM mes_date),
                        EXTRACT(DOY FROM mes_date), EXTRACT(HOUR FROM mes_date), EXTRACT(MINUTE FROM mes_date),
                        EXTRACT(SECOND FROM mes_date), EXTRACT(QUARTER FROM mes_date) FROM stats.Message;
    -- Create facts table
    INSERT INTO Fact_Message SELECT m.mes_id, m.mes_cam_id, m.mes_date, c.cam_company_id
                            FROM stats.Message m, stats.Campaign c WHERE c.cam_id = m.mes_cam_id;
  END; $$
  LANGUAGE 'plpgsql';
