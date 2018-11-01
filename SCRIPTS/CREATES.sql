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

