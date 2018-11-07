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