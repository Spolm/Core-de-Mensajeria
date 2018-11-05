INSERT INTO public.USER (use_password, use_username, use_type, use_email, use_phone, use_country, 
					use_city, use_address, use_date_of_birth, use_gender) values
					('1234', 'Ronnie', 1, 'ronnie@gmail.com', '0414255', 'Ve', 'Mi', 'Caricuao', '04/05/1995', 'F');

INSERT INTO public.Template (tem_creation_date) 
values ('2003-2-1');

INSERT INTO public.Message (mes_text,mes_template)
values ('esto es un mensaje de template con un [·$Parametro$·]',1);

insert into public.Parameter (par_name)
values ('parametron');

insert into public.Status (sta_name)
values ('aprobado');

insert into public.template_status (ts_date,ts_template,ts_status)
values ('2003-2-1',1,2);

insert into public.message_parameter (mp_message,mp_parameter)
values (2,1);