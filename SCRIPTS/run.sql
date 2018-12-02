\ir DROPCASCADE.sql
\ir DROPS.sql
\ir 'CREATE USER.sql'
\ir 'CREATE DB.sql'
\ir CREATES.sql
\ir INSERTS.sql

\c CoreMensajeria CoreMensajeria
\ir SP/m01_users.sql
\ir SP/m02_getcompanies.sql
\ir SP/m02_getcompanybyid.sql
\ir SP/m03_getcampaigns.sql
\ir SP/m04_disable_enable_Integrator.sql
\ir SP/m04_getChannels.sql
\ir SP/m04_getintegrators.sql
\ir SP/m04_getConcreteIntegrator.sql
\ir SP/m04_getIntegrators_by_channel.sql
\ir SP/m06_origin.sql
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
-- \ir StoreProceduresM_09/FuntionsGet.sql
