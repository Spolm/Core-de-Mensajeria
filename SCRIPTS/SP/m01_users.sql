--DROP FUNCTION m01_loguser(character varying, character varying);

CREATE OR REPLACE FUNCTION m01_loguser( IN _username character varying, IN _password character varying)
  RETURNS TABLE(use_id integer, use_username character varying, use_type integer, use_email character varying,
                use_phone character varying, use_country character varying, use_city character varying, use_address character varying,
                use_date_of_birth timestamp, use_gender char, use_blocked integer, use_remaining_attempts integer) AS
$BODY$
BEGIN
  RETURN QUERY
  select u.use_id, u.use_username, u.use_type, u.use_email, u.use_phone, u.use_country, u.use_city, u.use_address,
         u.use_date_of_birth, u.use_gender, u.use_blocked, u.use_remaining_attempts from public.user as u
  where (u.use_username=_username or u.use_email=_username) and u.use_password= MD5(_password);
END;
$BODY$
LANGUAGE plpgsql VOLATILE
COST 100
ROWS 1000;

-- Excecute
--select * from m01_loguser('Ronnie','123');