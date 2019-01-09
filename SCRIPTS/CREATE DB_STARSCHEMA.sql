CREATE DATABASE "CoreMensajeria_StarSchema"
    WITH
    OWNER = "CoreMensajeria"
    ENCODING = 'UTF8'
    -- LC_COLLATE = 'Spanish_Venezuela.1252'
    -- LC_CTYPE = 'Spanish_Venezuela.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

\c CoreMensajeria_StarSchema CoreMensajeria

CREATE EXTENSION postgres_fdw;

CREATE SERVER statistics_server FOREIGN DATA WRAPPER postgres_fdw OPTIONS (host 'localhost', port '5432', dbname 'CoreMensajeria');

CREATE USER MAPPING FOR "CoreMensajeria" SERVER statistics_server OPTIONS (user 'CoreMensajeria', password 'coremensajeria');

CREATE SCHEMA stats;

IMPORT FOREIGN SCHEMA public LIMIT TO (Sent_Message, Company, Campaign, Channel, Integrator, Application) FROM SERVER statistics_server INTO stats;
