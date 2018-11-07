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

INSERT INTO public.Template (tem_creation_date) 
values ('2003-2-1');

INSERT INTO public.Message (mes_text,mes_template)
values ('esto es un mensaje de template con un [�$Parametro$�]',1);

insert into public.Parameter (par_name)
values ('parametron');

insert into public.Status (sta_name)
values ('aprobado');

insert into public.template_status (ts_date,ts_template,ts_status)
values ('2003-2-1',1,1);

insert into public.message_parameter (mp_message,mp_parameter)
values (1,1);


/* Template 2 */


INSERT INTO public.Template (tem_creation_date) 
values (CURRENT_DATE);

INSERT INTO public.Message (mes_text,mes_template)
values ('Movistar te invita a una funcion gratuita del Festival de Cine Espanol. Ven el [.fecha.] Plaza Los Palos Grandes',(SELECT MAX(tem_id) FROM public.Template));

insert into public.Parameter (par_name)
values ('fecha');

insert into public.message_parameter (mp_message,mp_parameter)
values ((SELECT MAX(mes_id) FROM public.message),(select max (par_id) from public.Parameter where par_name='fecha'));

insert into public.template_status (ts_date,ts_template,ts_status)
values (CURRENT_TIMESTAMP,(SELECT MAX(tem_id) FROM public.Template),(select sta_id from public.status where sta_name='aprobado'));

/* Template 3 */

INSERT INTO public.Template (tem_creation_date) 
values (CURRENT_DATE);

INSERT INTO public.Message (mes_text,mes_template)
values ('Acabas de escuchar [.cancion.] Envia SI al 86667 si lo quieres como tu Entretono. Renta Bs.S. [.renta.] c/impuestos. Entretono Bs.S. [.entretono.] c/impuestos.',(SELECT MAX(tem_id) FROM public.Template));

insert into public.Parameter (par_name)
values ('cancion');

insert into public.Parameter (par_name)
values ('renta');

insert into public.Parameter (par_name)
values ('entretono');

insert into public.message_parameter (mp_message,mp_parameter)
values ((SELECT MAX(mes_id) FROM public.message),(select max (par_id) from public.Parameter where par_name='fecha'));

insert into public.template_status (ts_date,ts_template,ts_status)
values (CURRENT_TIMESTAMP,(SELECT MAX(tem_id) FROM public.Template),(select sta_id from public.status where sta_name='no aprobado'));