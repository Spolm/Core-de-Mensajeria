-- User: "CoreMensajeria"
-- DROP USER "CoreMensajeria";

DROP USER IF EXISTS "CoreMensajeria";

CREATE USER "CoreMensajeria" WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  ENCRYPTED PASSWORD 'coremensajeria'
  NOREPLICATION;

