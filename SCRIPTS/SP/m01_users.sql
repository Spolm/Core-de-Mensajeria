--DROP FUNCTION m01_loguser(character varying, character varying);

CREATE OR REPLACE FUNCTION m01_loguser( IN _username character varying, IN _password character varying)
  RETURNS TABLE(use_id integer, use_username character varying, use_type integer, use_email character varying,
                use_phone character varying, use_country character varying, use_city character varying, use_address character varying,
                use_date_of_birth timestamp, use_gender char, use_blocked integer, use_remaining_attempts integer) AS
$BODY$
DECLARE
	_userid integer;
	_userpassword character varying;
	_userremaining integer;
BEGIN
	_userid:=-1;
	select u.use_id, u.use_password, u.use_remaining_attempts from public.user as u 
		where (u.use_username=_username or u.use_email=_username) into _userid, _userpassword, _userremaining;
	if _userid > -1 and _userremaining >0 then
		if _userpassword = MD5(_password) then
			EXECUTE format('UPDATE public.USER SET use_remaining_attempts = %L WHERE use_id= %L',
						   3, _userid);
			RETURN QUERY
			select u.use_id, u.use_username, u.use_type, u.use_email, u.use_phone, u.use_country, u.use_city, u.use_address,
				 u.use_date_of_birth, u.use_gender, u.use_blocked, u.use_remaining_attempts from public.user as u
			where (u.use_username=_username or u.use_email=_username) and u.use_password= MD5(_password);
		else
			EXECUTE format('UPDATE public.USER SET use_remaining_attempts = %L WHERE use_id= %L',
						   _userremaining-1, _userid);
		end if;
	end if;
	if _userremaining = 0 then
		EXECUTE format('UPDATE public.USER SET use_blocked = %L WHERE use_id= %L',
						   1, _userid);
	end if;
END;
$BODY$
LANGUAGE plpgsql VOLATILE
COST 100
ROWS 1000;

-- Excecute
--select * from m01_loguser('Ronnie','123');

CREATE OR REPLACE FUNCTION m01_isBlocked( IN _username character varying)
  RETURNS integer AS
$BODY$
DECLARE
	_userblocked integer;
BEGIN
	_userblocked:=0;
	select  u.use_blocked from public.user as u 
		where (u.use_username=_username or u.use_email=_username) into _userblocked;
	RETURN _userblocked;
END;
$BODY$
LANGUAGE plpgsql VOLATILE
COST 100;

-- Excecute
--select m01_isBlocked('Ronnie');