create table public.User
(
    use_id serial primary key,
    use_password varchar (18),    
    use_username varchar(20),
    use_type integer,
    use_email varchar(30),
    use_phone varchar(20),
    use_country varchar(20),
    use_city varchar(20),
    use_address varchar(20),
    use_date_of_birth timestamp,
    use_gender char
);

CREATE TABLE public.Company
(
    com_id serial primary key,
    com_name varchar(255) NOT NULL,
    com_description varchar(1000) NOT NULL,
    com_status boolean NOT NULL,
    com_user_id integer NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY ("com_user_id")
    REFERENCES public.User (use_id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
);


CREATE TABLE public.Campaign
(
    cam_id serial PRIMARY KEY,
    cam_name varchar(255) NOT NULL,
    cam_description varchar(1000)NOT NULL,
    cam_status boolean NOT NULL,
    cam_start_date timestamp NOT NULL,
    cam_end_date timestamp NOT NULL,
    cam_company_id integer NOT NULL,
    CONSTRAINT fk_company_id FOREIGN KEY ("cam_company_id")
        REFERENCES public.company (com_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

create table public.Application
(
    app_id serial not null,
    app_name varchar(32) not null,
    app_description varchar(500),
    app_token varchar(64) not null unique,
    app_date timestamp not null,
    app_status integer not null,
    app_user_creator integer not null,
	CONSTRAINT pk_aplication primary key (app_id),
	CONSTRAINT fk_user_aplication foreign key ("app_user_creator") 
	REFERENCES public.User(use_id) 
);

CREATE TABLE integrator(
	int_id serial PRIMARY KEY,	
	int_name varchar(250) not null,
	int_messageCost float not null,
	int_threadCapacity int not null,
	int_tokenApi varchar(250) not null
);

CREATE TABLE channel(
	cha_id serial PRIMARY KEY,	
	cha_name varchar(250) not null,
	cha_description varchar(250) not null
);

CREATE TABLE channel_integrator(
	ci_id serial primary key,
	ci_channel_id int not null,
	ci_integrator_id int not null
);

ALTER TABLE channel_integrator
ADD CONSTRAINT fk_channel_id FOREIGN KEY (ci_channel_id) 
REFERENCES channel (cha_id);

ALTER TABLE channel_integrator
ADD CONSTRAINT fk_integrator_id FOREIGN KEY (ci_integrator_id) 
REFERENCES integrator (int_id);

create table public.Template
(
    tem_id serial primary key,
    tem_creation_date timestamp
);

create table public.Message
(
    mes_id serial PRIMARY KEY,
    mes_text varchar(160) NOT NULL,
    mes_template integer NOT NULL,
    CONSTRAINT fk_template_id FOREIGN KEY ("mes_template") REFERENCES public.Template (tem_id)
);

create table public.Parameter
(
    par_id serial PRIMARY KEY,
    Par_name varchar(15)
);

create table public.Message_Parameter
(
    mp_id serial PRIMARY KEY,
    mp_message integer NOT NULL,
    mp_parameter integer NOT NULL,
    CONSTRAINT fk_message_id FOREIGN KEY ("mp_message") REFERENCES public.message (mes_id),
    CONSTRAINT fk_parameter_id FOREIGN KEY ("mp_parameter") REFERENCES public.parameter (par_id)
);

create table public.Status
(
    sta_id serial PRIMARY KEY,
    sta_name varchar(15) NOT NULL

);

create table public.Template_Status
(
    ts_id serial PRIMARY KEY,
    ts_date timestamp,
    ts_template integer,
    ts_status integer,
    CONSTRAINT fk_template_id FOREIGN KEY ("ts_template") REFERENCES public.Template (tem_id),
    CONSTRAINT fk_Status_id FOREIGN KEY ("ts_status") REFERENCES public.Status (sta_id)
);


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