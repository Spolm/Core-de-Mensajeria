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
\ir SP/m02_getcompanies.sql
\ir SP/m02_getcompaniesall.sql
\ir SP/m02_getcompanybyid.sql
\ir SP/m02_changecompanystatus.sql
-- Modulo 3
\ir SP/m03_getcampaigns.sql
\ir SP/m03_getcampaignsall.sql
\ir SP/m03_getcampaignbyid.sql
\ir SP/m03_getcampaignsbyuser.sql
\ir SP/m03_changecampaignstatus.sql
-- Modulo 4
\ir SP/m04_channel_integrator.sql
-- Modulo 6
\ir SP/m06_origin.sql
-- Modulo 7 
\ir SP/m07_template.sql
\ir SP/m09_getIntegratorsByChannels.sql

\ir 'CREATE DB_STARSCHEMA.sql'
\ir CREATES_STARSCHEMA.sql

\c CoreMensajeria_StarSchema CoreMensajeria
\ir SP/m09_update_starschema.sql
\ir SP/m09_Get_CampaignMessages.sql
\ir SP/m09_Get_ChannelMessages.sql
\ir SP/m09_Get_CompanyMessages.sql
\ir SP/m09_getAllCampaigns.sql
\ir SP/m09_getAllChannels.sql
\ir SP/m09_getMessagesParameter.sql
\ir SP/m09_getDaysofMonth.sql
\ir SP/m09_getDaysofWeek.sql
\ir SP/m09_getDaysofYear.sql
\ir SP/m09_getHours.sql
\ir SP/m09_getMinutes.sql
\ir SP/m09_getMonths.sql
\ir SP/m09_getQuartersofYear.sql
\ir SP/m09_getSeconds.sql
\ir SP/m09_getWeeksofYear.sql
\ir SP/m09_getYears.sql
-- \ir StoreProceduresM_09/FuntionsGet.sql
