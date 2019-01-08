
\c CoreMensajeria CoreMensajeria

INSERT INTO public.Privilege (pri_code, pri_action) values ('CUSER','Crear usuario');
INSERT INTO public.Privilege (pri_code, pri_action) values ('RUSER','Ver usuario');
INSERT INTO public.Privilege (pri_code, pri_action) values ('UUSER','Modificar usuario');
INSERT INTO public.Privilege (pri_code, pri_action) values ('DUSER','Des o Habilitar usuario');
INSERT INTO public.Privilege (pri_code, pri_action) values ('CADMIN','Crear un administrador');
INSERT INTO public.Privilege (pri_code, pri_action) values ('CCAMPAIGN','Crear campana');
INSERT INTO public.Privilege (pri_code, pri_action) values ('RCAMPAIGN','Ver campana');
INSERT INTO public.Privilege (pri_code, pri_action) values ('UCAMPAIGN','Modificar campana');
INSERT INTO public.Privilege (pri_code, pri_action) values ('DCAMPAIGN','Des o Habilitar campana');
INSERT INTO public.Privilege (pri_code, pri_action) values ('CCOMPANY','Crear compania');
INSERT INTO public.Privilege (pri_code, pri_action) values ('RCOMPANY','Ver compania');
INSERT INTO public.Privilege (pri_code, pri_action) values ('UCOMPANY','Modificar compania');
INSERT INTO public.Privilege (pri_code, pri_action) values ('DCOMPANY','Des o Habilitar compania');
INSERT INTO public.Privilege (pri_code, pri_action) values ('CINTEGRATOR','Crear integrador');
INSERT INTO public.Privilege (pri_code, pri_action) values ('RINTEGRATOR','Ver integrador');
INSERT INTO public.Privilege (pri_code, pri_action) values ('UINTEGRATOR','Modificar integrador');
INSERT INTO public.Privilege (pri_code, pri_action) values ('DINTEGRATOR','Des o Habilitar integrador');
INSERT INTO public.Privilege (pri_code, pri_action) values ('CCHANNEL','Crear canal');
INSERT INTO public.Privilege (pri_code, pri_action) values ('RCHANNEL','Ver canal');
INSERT INTO public.Privilege (pri_code, pri_action) values ('UCHANNEL','Modificar canal');
INSERT INTO public.Privilege (pri_code, pri_action) values ('DCHANNEL','Des o Habilitar canal');
INSERT INTO public.Privilege (pri_code, pri_action) values ('CAPPLICATION','Crear aplicacion');
INSERT INTO public.Privilege (pri_code, pri_action) values ('RAPPLICATION','Ver aplicacion');
INSERT INTO public.Privilege (pri_code, pri_action) values ('UAPPLICATION','Modificar aplicacion');
INSERT INTO public.Privilege (pri_code, pri_action) values ('DAPPLICATION','Des o Habilitar aplicacion');
INSERT INTO public.Privilege (pri_code, pri_action) values ('CTEMPLATE','Crear plantilla');
INSERT INTO public.Privilege (pri_code, pri_action) values ('RTEMPLATE','Ver plantilla');
INSERT INTO public.Privilege (pri_code, pri_action) values ('UTEMPLATE','Modificar plantilla');
INSERT INTO public.Privilege (pri_code, pri_action) values ('DTEMPLATE','Des o Habilitar plantilla');
INSERT INTO public.Privilege (pri_code, pri_action) values ('ATEMPLATE','Aprobar plantilla');
INSERT INTO public.Privilege (pri_code, pri_action) values ('CMESSAGE','Crear mensaje');
INSERT INTO public.Privilege (pri_code, pri_action) values ('RMESSAGE','Ver mensaje');
INSERT INTO public.Privilege (pri_code, pri_action) values ('UMESSAGE','Modificar mensaje');
INSERT INTO public.Privilege (pri_code, pri_action) values ('DMESSAGE','Des o Habilitar mensaje');
INSERT INTO public.Privilege (pri_code, pri_action) values ('STATISTICS','Ver estadisticas');
INSERT INTO public.Privilege (pri_code, pri_action) values ('SEND','Envia');


INSERT INTO public.USER (use_password, use_username, use_type, use_email, use_phone, use_country,
					use_city, use_address, use_date_of_birth, use_gender) values
					(MD5('1234'), 'Ronnie', 1, 'alexdgn213@gmail.com', '0414255', 'Ve', 'Mi', 'Sta Fe', '04/05/1995', 'F');
INSERT INTO public.USER (use_password, use_username, use_type, use_email, use_phone, use_country,
					use_city, use_address, use_date_of_birth, use_gender) values
					(MD5('1234'), 'superusuario', 1, 'superusuario@gmail.com', '0414255', 'Ve', 'Mi', 'Caracas', '04/08/1991', 'F');
INSERT INTO public.USER (use_password, use_username, use_type, use_email, use_phone, use_country,
					use_city, use_address, use_date_of_birth, use_gender) values
					(MD5('1234'), 'administrador', 2, 'administrador@gmail.com', '0414255', 'Ve', 'Mi', 'Valencia', '12/08/1987', 'F');
INSERT INTO public.USER (use_password, use_username, use_type, use_email, use_phone, use_country,
					use_city, use_address, use_date_of_birth, use_gender) values
					(MD5('1234'), 'creador', 3, 'creador@gmail.com', '0414255', 'Ve', 'Mi', 'Barquisimeto', '04/08/1991', 'F');
INSERT INTO public.USER (use_password, use_username, use_type, use_email, use_phone, use_country,
					use_city, use_address, use_date_of_birth, use_gender) values
					(MD5('1234'), 'aprobador', 4, 'aprobador@gmail.com', '0414255', 'Ve', 'Mi', 'Margarita', '04/08/1981', 'F');
INSERT INTO public.USER (use_password, use_username, use_type, use_email, use_phone, use_country,
					use_city, use_address, use_date_of_birth, use_gender) values
					(MD5('1234'), 'consultor', 5, 'consultor@gmail.com', '0414255', 'Ve', 'Mi', 'Caracas', '12/03/1985', 'F');

INSERT INTO public.role(rol_name) values('Superusuario');
INSERT INTO public.role(rol_name) values('Administrador');
INSERT INTO public.role(rol_name) values('Creador');
INSERT INTO public.role(rol_name) values('Aprobador');
INSERT INTO public.role(rol_name) values('Consultor');

INSERT INTO public.Rol_pri(rol_pri_rol_id, rol_pri_pri_id) SELECT 1, pri_id FROM public.Privilege;
INSERT INTO public.Rol_pri(rol_pri_rol_id, rol_pri_pri_id) SELECT 2, pri_id FROM public.Privilege 
WHERE pri_code!='CADMIN';
INSERT INTO public.Rol_pri(rol_pri_rol_id, rol_pri_pri_id) SELECT 3, pri_id FROM public.Privilege 
WHERE pri_code = 'RUSER' or pri_code = 'RCAMPAIGN' or pri_code = 'RCOMPANY' or (pri_code like '%TEMPLATE' and pri_code != 'ATEMPLATE');
INSERT INTO public.Rol_pri(rol_pri_rol_id, rol_pri_pri_id) SELECT 4, pri_id FROM public.Privilege 
WHERE pri_code = 'RUSER' or pri_code = 'RCAMPAIGN' or pri_code = 'RCOMPANY' or pri_code = 'ATEMPLATE'or pri_code = 'RTEMPLATE';
INSERT INTO public.Rol_pri(rol_pri_rol_id, rol_pri_pri_id) SELECT 5, pri_id FROM public.Privilege 
WHERE pri_code like 'R%' or pri_code = 'STATISTICS';

INSERT INTO public.Responsability(res_use_id, res_rol_id) values
(1,1),(2,1),(3,2),(4,3),(5,4),(6,5);

INSERT INTO integrator (int_name, int_messageCost, int_threadCapacity,int_tokenApi,int_enabled)
VALUES ('Movistar', 13.4, 25, 'oqiwueyeiu',TRUE);


INSERT INTO integrator (int_name, int_messageCost, int_threadCapacity,int_tokenApi,int_enabled)
VALUES ('Digitel', 10, 20, 'oqiwueyeiu',TRUE);

INSERT INTO integrator (int_name, int_messageCost, int_threadCapacity,int_tokenApi,int_enabled)
VALUES ('Movilnet', 5.5, 2, 'kjdwlkajdjwkw',TRUE);

INSERT INTO integrator (int_name, int_messageCost, int_threadCapacity,int_tokenApi,int_enabled)
VALUES ('MailChimp', 1.5, 100, 'akwjdjkwjjwj',TRUE);

INSERT INTO integrator (int_name, int_messageCost, int_threadCapacity,int_tokenApi,int_enabled)
VALUES ('Aweber', 2.3, 90, 'ooiiewqoiw',TRUE);

INSERT INTO integrator (int_name, int_messageCost, int_threadCapacity,int_tokenApi,int_enabled)
VALUES ('Infusionsoft', 18, 150, 'odiw24idididmm1',TRUE);

INSERT INTO channel (cha_name, cha_description) VALUES ('SMS', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ae magna ultrices blandit. Nam blandit mi congue, vitae posuere odio blandit.');

INSERT INTO channel (cha_name, cha_description) VALUES ('Email', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ae magna ultrices blandit. Nam blandit mi congue, vitae posuere odio blandit.');

INSERT INTO channel_integrator (ci_channel_id, ci_integrator_id) VALUES
(1,1), (1,2),
(1,3), (2,4),
(2,5), (2,6);

INSERT INTO Company(com_name, com_description, com_status, com_route_link, com_user_id) values ('Company 1', 'Description', true, 'Company1_03122018204456', 1);
INSERT INTO Company(com_name, com_description, com_status, com_route_link, com_user_id) values ('Company 2', 'Description', true, 'Company2_03122018204456', 1);
INSERT INTO Company(com_name, com_description, com_status, com_route_link, com_user_id) values ('Company 3', 'Description', false, 'Company3_03122018204456', 1);
INSERT INTO Company(com_name, com_description, com_status, com_route_link, com_user_id) values ('Company 4', 'Description', true, 'Company4_03122018204456', 1);
INSERT INTO Company(com_name, com_description, com_status, com_route_link, com_user_id) values ('Company 5', 'Description', false, 'Company5_03122018204456', 1);
INSERT INTO Company(com_name, com_description, com_status, com_route_link, com_user_id) values ('Company 6', 'Description', true, 'Company6_03122018204456', 1);
INSERT INTO Company(com_name, com_description, com_status, com_route_link, com_user_id) values ('Company 7', 'Description', true, 'Company7_03122018204456', 1);
INSERT INTO Company(com_name, com_description, com_status, com_route_link, com_user_id) values ('Company 8', 'Description', true, 'Company8_03122018204456', 1);

INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 1', 'Description', true, TIMESTAMP '2017-07-20 15:36:38', TIMESTAMP '2018-07-23 15:36:38', 1);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 2', 'Description', true, TIMESTAMP '2017-07-21 15:36:38', TIMESTAMP '2018-07-23 15:36:38', 1);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 3', 'Description', false, TIMESTAMP '2017-07-22 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 1);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 4', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 1);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 5', 'Description', true, TIMESTAMP '2017-10-01 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 1);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 6', 'Description', false, TIMESTAMP '2017-10-02 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 2);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 7', 'Description', false, TIMESTAMP '2017-10-11 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 2);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 8', 'Description', true, TIMESTAMP '2018-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 2);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 9', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 2);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 10', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 2);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 11', 'Description', false, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 3);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 12', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 3);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 13', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 4);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 14', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 4);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 15', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 5);

INSERT INTO public.Responsability(res_use_id, res_rol_id, res_com_id) values
(3,2,1),(4,3,1),(5,4,1),(6,5,1);
INSERT INTO public.Responsability(res_use_id, res_rol_id, res_com_id) values
(3,2,2),(4,3,2),(5,4,2),(6,5,2);
INSERT INTO public.Responsability(res_use_id, res_rol_id, res_com_id) values
(3,2,3),(4,3,3),(5,4,3),(6,5,3);

INSERT INTO public.application(app_name,app_description,app_token,app_date,app_status,app_user_creator, app_company) values
('Amazon','Pagina Web','BF5453E0B1BF86A7FA020A4B87D7C0A9B0946ACCEB403E918E352D10BD35007D','04/05/1995',0,1,1),
('Mercantil en linea','Aplicacion movil de mercantil','5E182FC1A42614D37B951A7A38648350D0D166E9D78BA26E59EF7ECE9462BE34','07/06/2017',1,1,2),
('Banesco Online','Aplicacion web de banesco','5DD3CE9EF2B7614FB471B442050DDB24ADDBE88424B3C1382C7DD224A99203BD','01/08/2015',1,1,3);

INSERT INTO public.Template (tem_creation_date, tem_campaign_id, tem_application_id, tem_user_id) 
VALUES ('2003-2-1', 1, 1, 3);

INSERT INTO public.Message (mes_text,mes_template)
VALUES ('esto es un mensaje de template con un [.$Parametro$.]',1);

INSERT INTO public.Parameter (par_name,par_company_id)
VALUES ('Parametro',1);

INSERT INTO public.Status (sta_name)
VALUES ('Aprobado');

INSERT INTO public.Template_Status (ts_date,ts_template,ts_status)
VALUES ('2003-2-1',1,1);

INSERT INTO public.Message_Parameter (mp_message,mp_parameter)
VALUES (1,1);

INSERT INTO public.Status (sta_name)
VALUES ('No Aprobado');

INSERT INTO public.Template (tem_creation_date, tem_campaign_id, tem_application_id, tem_user_id) 
VALUES ('2012-10-11', 4, 2,4);

INSERT INTO public.Message(mes_text,mes_template)
VALUES ('BANESCO REGISTRO: CONSUMO EN PUNTO DE VENTA CON TDD [.$Numero tarjeta$.] BS. [.$Monto$.] EL [.$Fecha$.] REF [.$REF$.].',2);

INSERT INTO public.Parameter (par_name,par_company_id)
VALUES ('Numero tarjeta',1);
INSERT INTO public.Parameter (par_name,par_company_id)
VALUES ('Monto',1);
INSERT INTO public.Parameter (par_name,par_company_id) 
VALUES ('Fecha',1);
INSERT INTO public.Parameter (par_name,par_company_id)
VALUES ('REF',1);

INSERT INTO public.Template_Status (ts_date,ts_template,ts_status)
VALUES ('2012-10-11',2,2);

INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (2,2);
INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (2,3);
INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (2,4);
INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (2,5);

INSERT INTO public.Template (tem_creation_date, tem_campaign_id, tem_application_id, tem_user_id) 
VALUES ('2014-10-11', 8, 3, 3);

INSERT INTO public.Message(mes_text,mes_template)
VALUES ('Movistar te invita a una funcion gratuita del [.$Evento$.]. Ven el [.$Fecha$.] [.$Hora$.] [.$Lugar$.].',3);

INSERT INTO public.Parameter (par_name,par_company_id)
VALUES ('Evento',1);
INSERT INTO public.Parameter (par_name,par_company_id) 
VALUES ('Hora',1);
INSERT INTO public.Parameter (par_name,par_company_id) 
VALUES ('Lugar',1);

INSERT INTO public.Template_Status (ts_date,ts_template,ts_status)
VALUES ('2014-10-12',3,1);

INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (3,6);
INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (3,4);
INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (3,7);
INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (3,8);

INSERT INTO public.Template (tem_creation_date, tem_campaign_id, tem_application_id, tem_user_id) 
VALUES ('2014-11-11', 10, 1, 4);

INSERT INTO public.Message(mes_text,mes_template)
VALUES ('Acabas de escuchar [.$Cancion$.] Envia SI al [.$Codigo$.] si lo quieres como tu Entretono. Renta Bs.S. [.$Renta$.] c/impuestos. Entretono Bs.S. [.$Monto$.] c/impuestos.',4);

INSERT INTO public.Parameter (par_name,par_company_id) 
VALUES ('Cancion',1);
INSERT INTO public.Parameter (par_name,par_company_id) 
VALUES ('Codigo',1);
INSERT INTO public.Parameter (par_name,par_company_id)
VALUES ('Renta',1);

INSERT INTO public.Template_Status (ts_date,ts_template,ts_status)
VALUES ('2014-11-11',4,2);

INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (4,9);
INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (4,10);
INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (4,11);
INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (4,3);

INSERT INTO public.Template (tem_creation_date, tem_campaign_id, tem_application_id, tem_user_id) 
VALUES ('2016-07-04', 13, 2, 4);

INSERT INTO public.Message(mes_text,mes_template)
VALUES ('Si tu edad es entre 18 y 30, alistate en la FANB. Prensentate del [.$Fecha inicio$.] al [.$Fecha fin$.] en la unidad militar mas cercana',5);

INSERT INTO public.Parameter (par_name,par_company_id) 
VALUES ('Fecha inicio',1);
INSERT INTO public.Parameter (par_name,par_company_id) 
VALUES ('Fecha fin',1);

INSERT INTO public.Template_Status (ts_date,ts_template,ts_status)
VALUES ('2016-07-04',5,2);

INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (5,12);
INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (5,13);


INSERT INTO PUBLIC.TEMPLATE_CHANNEL_INTEGRATOR (TCI_TEMPLATE_ID,TCI_CI_ID) VALUES (1,1);
INSERT INTO PUBLIC.TEMPLATE_CHANNEL_INTEGRATOR (TCI_TEMPLATE_ID,TCI_CI_ID) VALUES (2,2);
INSERT INTO PUBLIC.TEMPLATE_CHANNEL_INTEGRATOR (TCI_TEMPLATE_ID,TCI_CI_ID) VALUES (2,4);
INSERT INTO PUBLIC.TEMPLATE_CHANNEL_INTEGRATOR (TCI_TEMPLATE_ID,TCI_CI_ID) VALUES (3,2);
INSERT INTO PUBLIC.TEMPLATE_CHANNEL_INTEGRATOR (TCI_TEMPLATE_ID,TCI_CI_ID) VALUES (4,5);
INSERT INTO PUBLIC.TEMPLATE_CHANNEL_INTEGRATOR (TCI_TEMPLATE_ID,TCI_CI_ID) VALUES (4,3);
INSERT INTO PUBLIC.TEMPLATE_CHANNEL_INTEGRATOR (TCI_TEMPLATE_ID,TCI_CI_ID) VALUES (5,6);

INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2017-03-15', 1, 1, 1, 1, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2016-01-01', 1, 1, 2, 2, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2016-06-23', 1, 1, 1, 3, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2018-06-30', 1, 1, 2, 4, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2016-11-25', 1, 1, 1, 5, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2017-11-29', 1, 1, 2, 6, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2018-04-12', 1, 2, 1, 1, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2017-04-13', 1, 2, 2, 2, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2017-07-23', 1, 2, 1, 3, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2017-07-23', 1, 3, 2, 4, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2016-09-09', 1, 3, 1, 5, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2018-09-11', 1, 3, 2, 6, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2016-12-24', 1, 3, 1, 1, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2017-12-25', 1, 4, 2, 2, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2017-12-31', 1, 4, 1, 3, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2018-02-05', 2, 5, 2, 4, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2017-02-10', 2, 5, 1, 5, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2016-03-07', 2, 5, 2, 6, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2018-03-17', 2, 6, 1, 1, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2018-04-19', 2, 6, 2, 2, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2016-05-11', 2, 6, 1, 3, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2018-08-28', 2, 7, 2, 4, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2018-08-31', 2, 8, 1, 5, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2017-10-12', 2, 9, 2, 6, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2017-10-23', 2, 10, 1, 1, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2018-10-23', 2, 11, 2, 2, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2016-11-09', 2, 12, 1, 3, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2018-04-16', 2, 13, 2, 4, 1);
INSERT INTO Sent_Message(sen_time, sen_message, sen_campaign, sen_channel, sen_integrator, sen_application) VALUES (TIMESTAMP '2017-07-23', 2, 14, 1, 5, 1);

INSERT INTO public.Planning(pla_start_date, pla_end_date, pla_start_time, pla_end_time, pla_template_id)
VALUES ('2003-02-01','2022-04-04', '08:30', '18:30', 1);
INSERT INTO public.Planning(pla_start_date, pla_end_date, pla_start_time, pla_end_time, pla_template_id)
VALUES ('2014-10-11','2014-12-05', '07:00', '17:00', 2);
INSERT INTO public.Planning(pla_start_date, pla_end_date, pla_start_time, pla_end_time, pla_template_id)
VALUES ('2014-10-12','2015-01-29', '10:45', '20:00', 3);
INSERT INTO public.Planning(pla_start_date, pla_end_date, pla_start_time, pla_end_time, pla_template_id)
VALUES ('2014-11-11','2020-04-11', '9:00', '17:30', 4);
INSERT INTO public.Planning(pla_start_date, pla_end_date, pla_start_time, pla_end_time, pla_template_id)
VALUES ('2016-07-04','2019-12-01', '12:00', '23:15', 5);

INSERT INTO public.application(app_name,app_description,app_token,app_date,app_status,app_user_creator, app_company) values
('Facebook','Red Social','BF5453E0B1BF86A7FA020A4B87D7C0A9B0946ACCEB403E918E352D10BD35007D','04/05/1995',1,1,1);
INSERT INTO public.Parameter (par_name,par_company_id) 
VALUES ('Numero Tarjeta',2);
INSERT INTO public.Parameter (par_name,par_company_id) 
VALUES ('Fecha',2);
INSERT INTO public.Parameter (par_name,par_company_id) 
VALUES ('Renta',2);
INSERT INTO public.Parameter (par_name,par_company_id) 
VALUES ('Codigo',2);
INSERT INTO public.Parameter (par_name,par_company_id) 
VALUES ('Cancion',2);
INSERT INTO public.Parameter (par_name,par_company_id) 
VALUES ('Monto',2);

INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('1','Venezuela',NULL);
--2s
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Amazonas',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Anzoátegui',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Apure',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Aragua',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Barinas',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Bolívar',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Carabobo',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Cojedes',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Delta Amacuro',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Distrito Capital',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Falcón',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Guárico',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Lara',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Mérida',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Miranda',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Monagas',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Nueva Esparta',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Portuguesa',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Sucre',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Tachira',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Trujillo',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Vargas',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Yaracuy',1);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('2','Zulia',1);
--3s de Amazonas
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Alto Orinoco',2);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Atabapo',2);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Atures',2);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Autana',2);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Manapiare',2);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Maroa',2);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Carlos de Río Negro',2);
--3s de Anzoategui
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Anaco',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Aragua',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bolívar',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bruzual',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Cajigal',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Carvajal',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Diego Bautista Urbaneja',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Freites',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Guanipa',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Guanta',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Independencia',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Libertad',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','McGregor',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Miranda',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Monagas',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Peñalver',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Píritu',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Juan de Capistrano',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Santa Ana',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Simón Rodríguez',3);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sotillo',3);
--3s de Apure
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Achaguas',4);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Biruaca',4);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Muñoz',4);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Páez',4);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Pedro Camejo',4);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Rómulo Gallegos',4);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Fernando',4);
--3s de Aragua
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bolívar',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Camatagua',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Francisco Linares Alcántara',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Girardot',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','José Ángel Lamas',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','José Félix Ribas',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','José Rafael Revenga',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Libertador',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Mario Briceño Iragorry',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Ocumare de La Costa de Oro',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Casimiro',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Sebastián',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Santiago Mariño',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Santos Michelena',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sucre',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Tovar',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Urdaneta',5);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Zamora',5);
--3s de Barinas
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Alberto Arvelo Torrealba',6);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Antonio José de Sucre',6);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Arismendi',6);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Andrés Eloy Blanco',6);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Barinas',6);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bolívar',6);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Cruz Paredes',6);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Ezequiel Zamora',6);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Obispos',6);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Pedraza',6);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Rojas',6);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sosa',6);
--3s de Bolivar
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Angostura',7);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Caroní',7);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Cedeño',7);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','El Callao',7);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Gran Sabana',7);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Heres',7);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Padre Pedro Chien',7);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Piar',7);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Roscio',7);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sifontes',7);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sucre',7);
--3s de Carabobo
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bejuma',8);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Carlos Arvelo',8);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Diego Ibarra',8);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Guacara',8);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Juan José Mora',8);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Libertador',8);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Los Guayos',8);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Miranda',8);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Montalbán',8);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Naguanagua',8);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Puerto Cabello',8);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Diego',8);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Joaquín',8);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Valencia',8);
--3s de Cojedes
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Anzoátegui',9);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Ezequiel Zamora',9);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Falcon',9);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Girardot',9);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Lima Blanco',9);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Pao de San Juan Bautista',9);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Ricaurte',9);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Rómulo Gallegos',9);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Tinaco',9);
--3s de Delta Amacuro
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Antonio Díaz',10);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Casacoima',10);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Pedernales',10);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Tucupita',10);
--3s de Distrito Capital
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Libertador',11);
--3s del 2 Falcon
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Acosta',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bolívar',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Buchivacoa',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Carirubana',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Colina',12);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Dabajuro',12);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Democracia',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Falcón',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Federación',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Iturriza',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Jacura',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Los Taques',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Mauroa',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Manaure',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Miranda',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Palma Sola',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Petit',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Píritu',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Francisco',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Silva',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sucre',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Tocópero',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Unión',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Urumaco',12);	
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Zamora',12);
--3s de Guarico
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Camaguán',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Chaguaramas',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','El Socorro',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Infante',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Las Mercedes',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Mellado',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Miranda',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Monagas',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Ortiz',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Ribas',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Roscio',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Jerónimo de Guayabal',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San José de Guaribe',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Santa María de Ipire',13);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Zaraza',13);
--3s de Lara
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Andrés Eloy Blanco',14);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Crespo',14);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Iribarren',14);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Jímenez',14);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Morán',14);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Palavecino',14);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Simón Planas',14);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Torres',14);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Urdaneta',14);
--3s de Merida
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Alberto Adriani',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Andrés Bello',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Antonio Pinto Salinas',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Aricagua',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Arzobispo Chacón',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Campo Elías',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Caracciolo Parra Olmedo',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Cardenal Quintero',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Guaraque',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Julio César Salas',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Justo Briceño',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Libertador',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Miranda',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Obispo Ramos de Lora',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Padre Noguera',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Pueblo Llano',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Rangel',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Rivas Dávila',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Santos Marquina',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sucre',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Tovar',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Tulio Febres Cordero',15);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Zea',15);
--3s de Miranda
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Acevedo',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Andrés Bello',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Baruta',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Brión',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bolívar',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Buroz',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Carrizal',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Chacao',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','El Hatillo',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Guaicaipuro',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Gual',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Independencia',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Lander',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Los Salias',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Páez',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Paz Castillo',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Plaza',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Rojas',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Urdaneta',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sucre',17);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Zamora',17);
--3s de Monagas
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Acosta',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Aguasay',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bolívar',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Caripe',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Cedeño',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Libertador',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Maturín',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Piar',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Punceres',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Santa Bárbara',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sotillo',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Uracoa',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Zamora',19);
--3 de Nueva Esparta
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Antolín del Campo',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Arismendi',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Díaz',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','García',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Gómez',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Maneiro',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Marcano',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Mariño',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Península de Macanao',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Tubores',19);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Villalba',19);
--3s de Portuguesa
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Agua Blanca',110);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Araure',110);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Esteller',110);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Guanare',110);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Guanarito',110);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','José Vicente De Unda',110);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Ospino',110);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Páez',110);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Papelón',110);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Genaro de Boconoíto',110);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Rafael de Onoto',110);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Santa Rosalía',110);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sucre',110);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Turén',110);
--3s de Sucre
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Arismendi',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Andrés Eloy Blanco',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Benítez',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bermúdez',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bolívar',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Cajigal',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Cruz Salmerón Acosta',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Libertador',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Andrés Mata',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Mariño',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Mejía',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Montes',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Ribero',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sucre',20);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Valdez',20);
--3s de Tachira
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Andrés Bello',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Antonio Rómulo Costa',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Ayacucho',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bolívar',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Cárdenas',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Córdoba',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Fernández Feo',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Francisco de Miranda',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','García de Hevia',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Guásimos',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Independencia',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Jáuregui',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','José María Vargas',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Junín',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Judas Tadeo',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Libertad',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Libertador',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Lobatera',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Michelena',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Panamericano',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Pedro María Ureña',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Rafael Urdaneta',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Samuel Dario Maldonado',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Cristóbal',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Seboruco',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Simón Rodríguez',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sucre',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Torbes',21);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Uribante',21);
--3s de Trujillo
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Andrés Bello',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Boconó',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bolívar',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Candelaria',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Carache',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Carvajal',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Campo Elías',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Escuque',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','La Ceiba',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Márquez Cañizalez',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Miranda',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Monte Carmelo',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Motatán',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Pampán',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Pampanito',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Rangel',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sucre',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Trujillo',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Urdaneta',22);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Valera',22);
--3s de Vargas
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Vargas',23);
--3s de Yaracuy
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bastidas',24);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bolívar',24);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Bruzual',24);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Cocorote',24);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Independencia',24);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','La Trinidad',24);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Monge',24);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Nirgua',24);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Páez',24);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Peña',24);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Felipe',24);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sucre',24);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Urachiche',24);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Veroes',24);
--3s de Zulia
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Almirante Padilla',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Baralt',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Cabimas',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Catatumbo',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Colón',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Francisco Javier Pulgar',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Jesús Enrique Lossada',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Jesús María Semprún',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','La Cañada de Urdaneta',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Lagunillas',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Machiques de Perijá',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Mara',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Maracaibo',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Miranda',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Guajira',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Rosario de Perijá',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','San Francisco',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Santa Rita',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Simón Bolívar',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Sucre',25);
INSERT INTO GEOGRAPHICAL_REGION(rg_type, rg_name, rg_rg) VALUES ('3','Valmore Rodríguez',25);