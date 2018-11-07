INSERT INTO public.USER (use_password, use_username, use_type, use_email, use_phone, use_country, 
					use_city, use_address, use_date_of_birth, use_gender) VALUES
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

INSERT INTO channel (cha_name, cha_description) VALUES ('SMS', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ae magna ultrices blandit. Nam blandit mi congue, vitae posuere odio blandit.');

INSERT INTO channel (cha_name, cha_description) VALUES ('Email', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ae magna ultrices blandit. Nam blandit mi congue, vitae posuere odio blandit.');

INSERT INTO channel_integrator (ci_channel_id, ci_integrator_id) VALUES
(1,1), (1,2),
(1,3), (2,4),
(2,5), (2,6);

INSERT INTO public.Template (tem_creation_date) 
VALUES ('2003-2-1');

INSERT INTO public.Message (mes_text,mes_template)
VALUES ('esto es un mensaje de template con un [.$Parametro$.]',1);

INSERT INTO public.Parameter (par_name)
VALUES ('Parametro');

INSERT INTO public.Status (sta_name)
VALUES ('Aprobado');

INSERT INTO public.Template_Status (ts_date,ts_template,ts_status)
VALUES ('2003-2-1',1,1);

INSERT INTO public.Message_Parameter (mp_message,mp_parameter)
VALUES (1,1);

INSERT INTO public.Status (sta_name)
VALUES ('No Aprobado');

INSERT INTO public.Template (tem_creation_date) 
VALUES ('2012-10-11');

INSERT INTO public.Message(mes_text,mes_template)
VALUES ('BANESCO REGISTRO: CONSUMO EN PUNTO DE VENTA CON TDD [.$Numero tarjeta$.] BS. [.$Monto$.] EL [.$Fecha$.] REF [.$REF$.].',2);

INSERT INTO public.Parameter (par_name) 
VALUES ('Numero tarjeta');
INSERT INTO public.Parameter (par_name) 
VALUES ('Monto');
INSERT INTO public.Parameter (par_name) 
VALUES ('Fecha');
INSERT INTO public.Parameter (par_name) 
VALUES ('REF');

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

INSERT INTO public.Template (tem_creation_date) 
VALUES ('2014-10-11');

INSERT INTO public.Message(mes_text,mes_template)
VALUES ('Movistar te invita a una funcion gratuita del [.$Evento$.]. Ven el [.$Fecha$.] [.$Hora$.] [.$Lugar$.].',3);

INSERT INTO public.Parameter (par_name) 
VALUES ('Evento');
INSERT INTO public.Parameter (par_name) 
VALUES ('Hora');
INSERT INTO public.Parameter (par_name) 
VALUES ('Lugar');

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

INSERT INTO public.Template (tem_creation_date) 
VALUES ('2014-11-11');

INSERT INTO public.Message(mes_text,mes_template)
VALUES ('Acabas de escuchar [.$Cancion$.] Envia SI al [.$Codigo$.] si lo quieres como tu Entretono. Renta Bs.S. [.$Renta$.] c/impuestos. Entretono Bs.S. [.$Monto$.] c/impuestos.',4);

INSERT INTO public.Parameter (par_name) 
VALUES ('Cancion');
INSERT INTO public.Parameter (par_name) 
VALUES ('Codigo');
INSERT INTO public.Parameter (par_name) 
VALUES ('Renta');

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

INSERT INTO public.Template (tem_creation_date) 
VALUES ('2016-07-04');

INSERT INTO public.Message(mes_text,mes_template)
VALUES ('Si tu edad es entre 18 y 30, alistate en la FANB. Prensentate del [.$Fecha inicio$.] al [.$Fecha fin$.] en la unidad militar mas cercana',5);

INSERT INTO public.Parameter (par_name) 
VALUES ('Fecha inicio');
INSERT INTO public.Parameter (par_name) 
VALUES ('Fecha fin');

INSERT INTO public.Template_Status (ts_date,ts_template,ts_status)
VALUES ('2016-07-04',5,2);

INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (5,12);
INSERT INTO public.Message_Parameter (mp_message,mp_parameter) 
VALUES (5,13);