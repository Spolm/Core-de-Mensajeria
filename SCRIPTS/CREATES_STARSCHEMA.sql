create table public.Dim_Date(
	dat_id timestamp not null,
	CONSTRAINT pk_Date primary key("dat_id")
);
create table public.Dim_Company_Campaing(
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
create table public.Fact_Application(
	app_cam_id int not null,
	app_dat_id timestamp not null,
	app_com_id serial not null,
    app_status integer not null,
    CONSTRAINT pk_Application primary key("app_cam_id","app_dat_id","app_com_id"),
    CONSTRAINT fk_Application_Company_Campaign foreign key("app_cam_id","app_com_id")
    REFERENCES public.Dim_Company_Campaing("cam_id","com_id"),
    CONSTRAINT fk_Application_Date foreign key("app_dat_id")
    REFERENCES public.Dim_Date
);
