DROP TABLE application CASCADE;
DROP TABLE campaign CASCADE;
DROP TABLE channel  CASCADE;
DROP TABLE channel_integrator CASCADE;
DROP TABLE responsability CASCADE;
DROP TABLE company CASCADE;
DROP TABLE integrator CASCADE;
DROP TABLE message CASCADE;
DROP TABLE message_parameter CASCADE;
DROP TABLE parameter CASCADE;
DROP TABLE sent_message CASCADE;
DROP TABLE status CASCADE;
DROP TABLE template_channel_integrator CASCADE;
DROP TABLE template CASCADE;
DROP TABLE template_status CASCADE;
DROP TABLE rol_pri CASCADE;
DROP TABLE privilege CASCADE;
DROP TABLE public.role CASCADE;
DROP TABLE public.user CASCADE;
DROP FUNCTION m02_getcompanies(integer);
DROP FUNCTION m03_getcampaigns(integer);
drop function m01_loguser(varchar, varchar);

drop function m01_isblocked(varchar);

drop function m01_getpriviledges(integer);

drop function m01_getusers();

drop function m02_getcompanybyid(integer);

drop function m04_enableintegrator(integer);

drop function m04_disableintegrator(integer);

drop function m04_getchannels();

drop function m04_getconcreteintegrator(integer);

drop function m04_getintegrators();

drop function m04_getintegratorsbychannel(integer);

drop function m06_add_application(varchar, varchar, varchar, integer, integer);

drop function m06_select_all_application();

drop function m06_select_by_token_application(varchar);

drop function m06_update_application_status(integer, integer);

drop function get_campaignmessages();

drop function get_channelmessages();

drop function get_companymessages();

drop function m09_getallcampaigns();

drop function m09_getallchannel();

drop function m09_update_starschema();

drop function m07_select_templates_by_campaign(integer);

drop function m07_select_all_templates();

drop function m07_select_privileges_by_user_company(integer,integer);

drop function m07_select_campaign_by_user_company(integer,integer,integer);

drop function m07_select_channels_by_template(integer);

drop function m07_select_messages(integer);

drop function m07_select_message(integer);

drop function m07_select_Planning(integer);

drop function m07_select_applicantion_by_template(integer);

drop function m01_getprivileges(integer);

drop function m01_changepassword(varchar, varchar);

drop function m01_findbyusernameid(integer);

drop function m01_deleteuser(integer);