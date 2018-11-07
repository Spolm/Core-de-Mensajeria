\c CoreMensajeria CoreMensajeria

INSERT INTO public.USER
	(use_password, use_username, use_type, use_email, use_phone, use_country,
	use_city, use_address, use_date_of_birth, use_gender)
values
	('1234', 'Ronnie', 1, 'ronnie@gmail.com', '0414255', 'Ve', 'Mi', 'Caricuao', '04/05/1995', 'F');

INSERT INTO public.USER
	(use_password, use_username, use_type, use_email, use_phone, use_country,
	use_city, use_address, use_date_of_birth, use_gender)
values
	('1234', 'Gregor', 1, 'gregor@gmail.com', '0414255', 'Ve', 'Mi', 'Caricuao', '04/05/1995', 'M');


INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 1', 'Description', true, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 2', 'Description', true, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 3', 'Description', false, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 4', 'Description', true, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 5', 'Description', false, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 6', 'Description', true, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 7', 'Description', true, 1);
INSERT INTO Company(com_name, com_description, com_status, com_user_id) values ('Company 8', 'Description', true, 1);


INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 1', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2018-07-23 15:36:38', 1);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 2', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2018-07-23 15:36:38', 1);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 3', 'Description', false, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 1);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 4', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 1);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 5', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 1);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 6', 'Description', false, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 2);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 7', 'Description', false, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 2);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 8', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 2);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 9', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 2);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 10', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 2);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 11', 'Description', false, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 3);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 12', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 3);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 13', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 4);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 14', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 4);
INSERT INTO Campaign(cam_name, cam_description, cam_status, cam_start_date, cam_end_date, cam_company_id) values ('Campaign 15', 'Description', true, TIMESTAMP '2017-07-23 15:36:38', TIMESTAMP '2017-07-23 15:36:38', 5);

INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2016-07-23', 1);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 1);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 1);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 2);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 2);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 2);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 3);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 3);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 3);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 4);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 4);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 4);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 5);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 5);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 6);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 7);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 8);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 8);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 8);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 8);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 9);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 10);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 11);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 12);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 13);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 14);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 14);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 15);
INSERT INTO Message(mes_date, mes_cam_id) VALUES (TIMESTAMP '2017-07-23', 15);
