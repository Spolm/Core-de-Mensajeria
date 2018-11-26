--\c CoreMensajeria CoreMensajeria

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

create table Application(
    app_id serial not null,
    app_name varchar(32) not null,
    app_description varchar(500),
    app_token varchar(64) not null unique,
    app_date timestamp not null,
    app_status integer not null,
    app_user_creator integer not null,
    app_company integer not null,
    CONSTRAINT pk_aplication primary key (app_id),
    CONSTRAINT fk_user_aplication foreign key ("app_user_creator")
    REFERENCES User(use_id),
    CONSTRAINT fk_company_aplication foreign key ("app_company")
    REFERENCES Company(com_id)
);

CREATE TABLE integrator(
	int_id serial PRIMARY KEY,
	int_name varchar(250) not null,
	int_messageCost float not null,
	int_threadCapacity int not null,
	int_tokenApi varchar(250) not null,
	int_enabled boolean not null
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
    mes_text varchar(360) NOT NULL,
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

create table public.Sent_Message
(
    sen_id serial PRIMARY KEY,
    sen_time timestamp,
    sen_message integer NOT NULL,
    sen_campaign integer NOT NULL,
    sen_channel integer NOT NULL,
    sen_integrator integer NOT NULL,
    sen_application integer NOT NULL,
    CONSTRAINT fk_message_id FOREIGN KEY ("sen_message") REFERENCES public.Message (mes_id),
    CONSTRAINT fk_campaign_id FOREIGN KEY ("sen_campaign") REFERENCES public.Campaign (cam_id),
    CONSTRAINT fk_channel_id FOREIGN KEY ("sen_channel") REFERENCES channel (cha_id),
    CONSTRAINT fk_integrator_id FOREIGN KEY ("sen_integrator") REFERENCES integrator (int_id),
    CONSTRAINT fk_application_id FOREIGN KEY ("sen_application") REFERENCES public.Application (app_id)
);

create table public.TEMPLATE_CHANNEL_INTEGRATOR
 (
 	tci_id serial PRIMARY KEY,
 	tci_template_id integer NOT NULL,
 	tci_ci_id integer NOT NULL,
 	CONSTRAINT fk_template_id FOREIGN KEY("tci_template_id") REFERENCES public.Template(tem_id),
 	CONSTRAINT fk_ci_id FOREIGN KEY("tci_ci_id") REFERENCES public.CHANNEL_INTEGRATOR(ci_id)
 );

ALTER TABLE Template
ADD COLUMN tem_campaign_id integer NOT NULL,
ADD CONSTRAINT fk_campaign_id FOREIGN KEY ("tem_campaign_id") 
REFERENCES Campaign (cam_id);

ALTER TABLE Template
ADD COLUMN tem_application_id integer NOT NULL,
ADD CONSTRAINT fk_application_id FOREIGN KEY ("tem_application_id")
REFERENCES Application (app_id);