-- \c CoreMensajeria 
-- ALTER SEQUENCE application_app_id_seq RESTART;
-- ALTER SEQUENCE campaign_cam_id_seq RESTART;
-- ALTER SEQUENCE channel_cha_id_seq RESTART;
-- ALTER SEQUENCE channel_integrator_ci_id_seq RESTART;
-- ALTER SEQUENCE company_com_id_seq RESTART;
-- ALTER SEQUENCE integrator_int_id_seq RESTART;
-- ALTER SEQUENCE message_mes_id_seq RESTART;
-- ALTER SEQUENCE message_parameter_mp_id_seq RESTART;
-- ALTER SEQUENCE parameter_par_id_seq RESTART;
-- ALTER SEQUENCE sent_message_sen_id_seq RESTART;
-- ALTER SEQUENCE status_sta_id_seq RESTART;
-- ALTER SEQUENCE template_channel_integrator_tci_id_seq RESTART;
-- ALTER SEQUENCE template_status_ts_id_seq RESTART;
-- ALTER SEQUENCE template_tem_id_seq RESTART;
-- ALTER SEQUENCE user_use_id_seq RESTART;
\c postgres
DROP DATABASE IF EXISTS "CoreMensajeria_StarSchema";
DROP DATABASE IF EXISTS "CoreMensajeria";
