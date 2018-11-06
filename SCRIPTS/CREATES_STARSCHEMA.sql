create table public.Dim_Date(
	dat_id timestamp not null,
    dat_year int not null,
    dat_month int not null,
    dat_weekofyear int not null,
    dat_dayofmonth int not null,
    dat_dayofyear int not null,
    dat_hourofday int not null,
    dat_minuteofhour int not null,
    dat_secondofminute int not null,
    dat_quarterofyear int not null,
	CONSTRAINT pk_Date primary key("dat_id")
);

create table public.Dim_Company_Campaign(
	com_id serial not null,
	cam_id serial not null,
	com_name varchar(255) not null,
	cam_name varchar(255) NOT NULL,
    cam_description varchar(1000)NOT NULL,
    cam_status boolean NOT NULL,
    cam_start_date timestamp NOT NULL,
    cam_end_date timestamp NOT NULL,
    CONSTRAINT pk_Company_Campaing primary key (com_id,cam_id)
);

create table public.Fact_Message(
    mes_id int not null ,
	mes_cam_id int not null,
	mes_dat_id timestamp not null,
	mes_com_id serial not null,
    CONSTRAINT pk_Application primary key("mes_id","mes_cam_id","mes_dat_id","mes_com_id"),
    CONSTRAINT fk_Application_Company_Campaign foreign key("mes_cam_id","mes_com_id")
    REFERENCES public.Dim_Company_Campaing("cam_id","com_id"),
    CONSTRAINT fk_Application_Date foreign key("mes_dat_id")
    REFERENCES public.Dim_Date
);
