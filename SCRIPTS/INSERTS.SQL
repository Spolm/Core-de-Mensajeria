
--\c CoreMensajeria CoreMensajeria

INSERT INTO public.USER (use_password, use_username, use_type, use_email, use_phone, use_country,
					use_city, use_address, use_date_of_birth, use_gender) values
					('1234', 'Ronnie', 1, 'ronnie@gmail.com', '0414255', 'Ve', 'Mi', 'Caricuao', '04/05/1995', 'F');

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

INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 1', 'Description', true, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 2', 'Description', true, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 3', 'Description', false, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 4', 'Description', true, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 5', 'Description', false, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 6', 'Description', true, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 7', 'Description', true, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 8', 'Description', true, 1);

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

INSERT INTO public.application(app_name,app_description,app_token,app_date,app_status,app_user_creator, app_company) values
('Amazon','Pagina Web','BF5453E0B1BF86A7FA020A4B87D7C0A9B0946ACCEB403E918E352D10BD35007D','04/05/1995',0,1,1),
('Mercantil en linea','Aplicacion movil de mercantil','5E182FC1A42614D37B951A7A38648350D0D166E9D78BA26E59EF7ECE9462BE34','12/13/2017',1,1,2),
('Banesco Online','Aplicacion web de banesco','5DD3CE9EF2B7614FB471B442050DDB24ADDBE88424B3C1382C7DD224A99203BD','01/08/2015',1,1,3);

INSERT INTO public.Template (tem_creation_date, tem_campaign_id, tem_application_id) 
VALUES ('2003-2-1', 1, 1);

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

INSERT INTO public.Template (tem_creation_date, tem_campaign_id, tem_application_id) 
VALUES ('2012-10-11', 4, 2);

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

INSERT INTO public.Template (tem_creation_date, tem_campaign_id, tem_application_id) 
VALUES ('2014-10-11', 8, 3);

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

INSERT INTO public.Template (tem_creation_date, tem_campaign_id, tem_application_id) 
VALUES ('2014-11-11', 10, 1);

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
VALUES (4,1);
INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (4,3);

INSERT INTO public.Template (tem_creation_date, tem_campaign_id, tem_application_id) 
VALUES ('2016-07-04', 13, 2);

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
