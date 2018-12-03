\c CoreMensajeria
ALTER SEQUENCE IF EXISTS application_app_id_seq RESTART;
ALTER SEQUENCE IF EXISTS campaign_cam_id_seq RESTART;
ALTER SEQUENCE IF EXISTS channel_cha_id_seq RESTART;
ALTER SEQUENCE IF EXISTS channel_integrator_ci_id_seq RESTART;
ALTER SEQUENCE IF EXISTS company_com_id_seq RESTART;
ALTER SEQUENCE IF EXISTS integrator_int_id_seq RESTART;
ALTER SEQUENCE IF EXISTS message_mes_id_seq RESTART;
ALTER SEQUENCE IF EXISTS message_parameter_mp_id_seq RESTART;
ALTER SEQUENCE IF EXISTS parameter_par_id_seq RESTART;
ALTER SEQUENCE IF EXISTS privilege_pri_id_seq RESTART;
ALTER SEQUENCE IF EXISTS responsability_res_id_seq RESTART;
ALTER SEQUENCE IF EXISTS rol_pri_rol_pri__id_seq RESTART;
ALTER SEQUENCE IF EXISTS role_rol_id_seq RESTART;
ALTER SEQUENCE IF EXISTS sent_message_sen_id_seq RESTART;
ALTER SEQUENCE IF EXISTS status_sta_id_seq RESTART;
ALTER SEQUENCE IF EXISTS template_channel_integrator_tci_id_seq RESTART;
ALTER SEQUENCE IF EXISTS template_status_ts_id_seq RESTART;
ALTER SEQUENCE IF EXISTS template_tem_id_seq RESTART;
ALTER SEQUENCE IF EXISTS user_use_id_seq RESTART;
\c postgres
DROP DATABASE IF EXISTS "CoreMensajeria_StarSchema";
DROP DATABASE IF EXISTS "CoreMensajeria";
