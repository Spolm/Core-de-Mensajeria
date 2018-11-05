-- User: "CoreMensajeria"
-- DROP USER "CoreMensajeria";

CREATE USER "CoreMensajeria" WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  ENCRYPTED PASSWORD 'coremensajeria'
  NOREPLICATION;

