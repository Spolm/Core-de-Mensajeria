\c CoreMensajeria_StarSchema CoreMensajeria

create table public.Dim_Date(
	dat_id timestamp not null,
    dat_year int not null,
    dat_month int not null,
    dat_dayofweek int not null,
    dat_weekofyear int not null,
    dat_dayofmonth int not null,
    dat_dayofyear int not null,
    dat_hourofday int not null,
    dat_minuteofhour int not null,
    dat_secondofminute int not null,
    dat_quarterofyear int not null,
	CONSTRAINT pk_Date primary key(dat_id)
);

create table public.Dim_Company_Campaign(
	com_id int not null,
	cam_id int not null,
	com_name varchar(255) not null,
	cam_name varchar(255) NOT NULL,
    cam_description varchar(1000)NOT NULL,
    cam_status boolean NOT NULL,
    cam_start_date timestamp NOT NULL,
    cam_end_date timestamp NOT NULL,
    CONSTRAINT pk_Company_Campaing primary key (com_id,cam_id)
);

create table public.Dim_Channel(
  	cha_id int not null,
	cha_name varchar(250) not null,
  	cha_description varchar(250) not null,
	CONSTRAINT pk_Channel primary key (cha_id)
);

create table public.Dim_Integrator(
  	int_id int not null,
	int_name varchar(250) not null,
  	int_messageCost float not null,
	CONSTRAINT pk_Integrator primary key (int_id)
);

create table public.Dim_Application(
	app_id int not null,
  	app_name varchar(32) not null,
	app_description varchar(500),
	app_date timestamp not null,
	CONSTRAINT pk_Aplication primary key (app_id)
);

create table public.Fact_Sent_Message(
    sen_id int not null ,
	sen_cam_id int not null,
	sen_dat_id timestamp not null,
	sen_com_id int not null,
	sen_cha_id int not null,
	sen_int_id int not null,
	sen_app_id int not null,
    CONSTRAINT pk_Sent_Message primary key("sen_id","sen_cam_id","sen_dat_id","sen_com_id","sen_cha_id","sen_int_id","sen_app_id"),
    CONSTRAINT fk_Company_Campaign foreign key("sen_cam_id","sen_com_id") REFERENCES public.Dim_Company_Campaign("cam_id","com_id"),
    CONSTRAINT fk_Date foreign key("sen_dat_id") REFERENCES public.Dim_Date
);
