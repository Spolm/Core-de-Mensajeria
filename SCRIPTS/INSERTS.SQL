
\c CoreMensajeria CoreMensajeria

INSERT INTO public.USER (use_password, use_username, use_type, use_email, use_phone, use_country,
					use_city, use_address, use_date_of_birth, use_gender) values
					('1234', 'Ronnie', 1, 'ronnie@gmail.com', '0414255', 'Ve', 'Mi', 'Caricuao', '04/05/1995', 'F');


INSERT INTO integrator (int_name, int_messageCost, int_threadCapacity,int_tokenApi)
VALUES ('Movistar', 13.4, 25, 'oqiwueyeiu');


INSERT INTO integrator (int_name, int_messageCost, int_threadCapacity,int_tokenApi)
VALUES ('Digitel', 10, 20, 'oqiwueyeiu');

INSERT INTO integrator (int_name, int_messageCost, int_threadCapacity,int_tokenApi)
VALUES ('Movilnet', 5.5, 2, 'kjdwlkajdjwkw');

INSERT INTO integrator (int_name, int_messageCost, int_threadCapacity,int_tokenApi)
VALUES ('MailChimp', 1.5, 100, 'akwjdjkwjjwj');

INSERT INTO integrator (int_name, int_messageCost, int_threadCapacity,int_tokenApi)
VALUES ('Aweber', 2.3, 90, 'ooiiewqoiw');

INSERT INTO integrator (int_name, int_messageCost, int_threadCapacity,int_tokenApi)
VALUES ('Infusionsoft', 18, 150, 'odiw24idididmm1');

insert into channel (cha_name, cha_description) values ('SMS', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ae magna ultrices blandit. Nam blandit mi congue, vitae posuere odio blandit.');

insert into channel (cha_name, cha_description) values ('Email', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ae magna ultrices blandit. Nam blandit mi congue, vitae posuere odio blandit.');

insert into channel_integrator (ci_channel_id, ci_integrator_id) values
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

INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2016-01-01', 1);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-03-15', 1);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2016-06-23', 1);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2018-06-30', 2);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2016-11-25', 2);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-11-29', 2);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2018-04-12', 3);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-04-13', 3);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 3);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 4);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2016-09-09', 4);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2018-09-11', 4);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2016-12-24', 5);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-12-25', 5);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-12-31', 6);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2018-02-05', 7);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-02-10', 8);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2016-03-07', 8);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2018-03-17', 8);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2018-04-19', 8);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2016-05-11', 9);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2018-08-28', 10);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2018-08-31', 11);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-10-12', 12);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-10-23', 13);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2018-10-23', 14);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2016-11-09', 14);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2018-04-16', 15);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 15);

INSERT INTO public.application(app_name,app_description,app_token,app_date,app_status,app_user_creator) values 
('Amazon','Pagina Web','BF5453E0B1BF86A7FA020A4B87D7C0A9B0946ACCEB403E918E352D10BD35007D','04/05/1995',0,1), 
('Mercantil en linea','Aplicacion movil de mercantil','5E182FC1A42614D37B951A7A38648350D0D166E9D78BA26E59EF7ECE9462BE34','12/13/2017',1,1), 
('Banesco Online','Aplicacion web de banesco','5DD3CE9EF2B7614FB471B442050DDB24ADDBE88424B3C1382C7DD224A99203BD','01/08/2015',1,1);