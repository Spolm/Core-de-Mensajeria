CREATE DATABASE "CoreMensajeria_StarSchema"
    WITH 
    OWNER = "CoreMensajeria"
    ENCODING = 'UTF8'
    -- LC_COLLATE = 'English_United States.1252'
    -- LC_CTYPE = 'English_United States.1252'
    LC_COLLATE = 'C'
    LC_CTYPE = 'UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

\c CoreMensajeria_StarSchema CoreMensajeria

CREATE EXTENSION postgres_fdw;

CREATE SERVER statistics_server FOREIGN DATA WRAPPER postgres_fdw OPTIONS (host 'localhost', port '5432', dbname 'CoreMensajeria');

CREATE USER MAPPING FOR "CoreMensajeria" SERVER statistics_server OPTIONS (user 'CoreMensajeria', password 'coremensajeria');

CREATE SCHEMA stats;

IMPORT FOREIGN SCHEMA public LIMIT TO (Message, Company, Campaign) FROM SERVER statistics_server INTO stats;
-- CREATE FOREIGN TABLE public.Message(
--     mes_id numeric not null,
--     mes_date timestamp not null,
--     mes_cam_id integer not null,
--     pk_message numeric not null,
--     fk_campaign_message numeric not null
-- ) SERVER statistics_server OPTIONS (schema_name 'public', table_name 'Message');
--
-- CREATE FOREIGN TABLE public.Company(
--     com_id NUMERIC NOT NULL,
--     com_name varchar(255) NOT NULL,
--     com_description varchar(1000) NOT NULL,
--     com_status boolean NOT NULL,
--     com_user_id integer NOT NULL,
--     fk_user_id NUMERIC NOT NULL
-- ) SERVER statistics_server OPTIONS (schema_name 'public', table_name 'Company');
--
-- CREATE FOREIGN TABLE public.Campaign(
--     cam_id NUMERIC NOT NULL,
--     cam_name varchar(255) NOT NULL,
--     cam_description varchar(1000)NOT NULL,
--     cam_status boolean NOT NULL,
--     cam_start_date timestamp NOT NULL,
--     cam_end_date timestamp NOT NULL,
--     cam_company_id integer NOT NULL,
--     fk_company_id NUMERIC NOT NULL
-- ) SERVER statistics_server OPTIONS (schema_name 'public', table_name 'Campaign');
