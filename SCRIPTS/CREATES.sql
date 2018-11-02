create table public.User(
 userId serial primary key,
 userPassword varchar (18),
 userUsername varchar(20),
 userType integer,
 userEmail varchar(30),
 userPhone varchar(20),
 userCountry varchar(20),
 userCity varchar(20),
 userAddress varchar(20),
 userDateOfBirth timestamp,
 userGender char
);

CREATE TABLE public.company
(
    id_company serial primary key,
    company_name varchar(255) NOT NULL,
    company_desc varchar(1000) NOT NULL,
    company_status boolean NOT NULL,
    user_id integer NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY ("user_id")
        REFERENCES public.User (userId) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE public.campaign
(
    CampaignId serial PRIMARY KEY,
    CampaignName varchar(255) NOT NULL,
    CampaignDescription varchar(1000)NOT NULL,
    CampaignStatus boolean NOT NULL,
    CampaignStartDate date NOT NULL,
    CampaignEndDate date NOT NULL,
    CompanyId integer NOT NULL,
    CONSTRAINT fk_company_id FOREIGN KEY ("CompanyId")
        REFERENCES public.company (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

