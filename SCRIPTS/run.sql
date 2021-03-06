\ir DROPCASCADE.sql
\ir DROPS.sql
\ir 'CREATE USER.sql'
\ir 'CREATE DB.sql'
\ir CREATES.sql
\ir INSERTS.sql

\c CoreMensajeria CoreMensajeria
-- Modulo 1
\ir SP/m01_users.sql
-- Modulo 2
\ir SP/m02_addcompany.sql
\ir SP/m02_getcompanies.sql
\ir SP/m02_getcompaniesall.sql
\ir SP/m02_getcompanybyid.sql
\ir SP/m02_changecompanystatus.sql
\ir SP/m02_getcompaniesbyresponsible.sql
\ir SP/m02_updatecompany.sql
-- Modulo 3
\ir SP/m03_addcampaign.sql
\ir SP/m03_getcampaigns.sql
\ir SP/m03_getcampaignsall.sql
\ir SP/m03_getcampaignbyid.sql
\ir SP/m03_getcampaignsbyuser.sql
\ir SP/m03_changecampaignstatus.sql
\ir SP/m03_getcampaignsbycompany.sql
\ir SP/m03_updatecampaign.sql
-- Modulo 4
\ir SP/m04_channel_integrator.sql
-- Modulo 6
\ir SP/m06_origin.sql
-- Modulo 7 
\ir SP/m07_template.sql
\ir SP/m07_template2.sql
-- Modulo 8
\ir SP/m08_messagecore.sql
-- Modulo 9
\ir SP/m09_coremensajeria.sql
-- Modulo 10
\ir SP/m10_getallcompanies.sql
\ir SP/m10_getallroles.sql
\ir SP/m10_profile.sql

\ir 'CREATE DB_STARSCHEMA.sql'
\ir CREATES_STARSCHEMA.sql

\c CoreMensajeria_StarSchema CoreMensajeria
\ir SP/m09_coremensajeria_starschema.sql
-- \ir StoreProceduresM_09/FuntionsGet.sql
