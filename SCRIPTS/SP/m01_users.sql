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

-- Execute
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

-- Execute
--select m01_isBlocked('Ronnie');

CREATE OR REPLACE FUNCTION m01_getprivileges( IN _user integer)
  RETURNS TABLE(pri_code character varying) AS
$BODY$
BEGIN
  RETURN QUERY
  SELECT DISTINCT(p.pri_code) FROM
                                   public.privilege p, public."user" u, public.rol_pri rp, public.role ro, public.responsability re
  WHERE p.pri_id=rp.rol_pri_pri_id AND ro.rol_id=rp.rol_pri_rol_id AND re.res_rol_id=ro.rol_id
    AND u.use_id=re.res_use_id and u.use_id=_user;
END;
$BODY$
LANGUAGE plpgsql VOLATILE
COST 100
ROWS 1000;

--select * from m01_getprivileges(1);

CREATE OR REPLACE FUNCTION m01_getusers()
  RETURNS TABLE(use_id integer, use_username character varying, use_type integer, use_email character varying,
                use_phone character varying, use_country character varying, use_city character varying, use_address character varying,
                use_date_of_birth timestamp, use_gender char, use_blocked integer, use_remaining_attempts integer) AS
$BODY$
BEGIN
  RETURN QUERY
	select u.use_id, u.use_username, u.use_type, u.use_email, u.use_phone, u.use_country, u.use_city, u.use_address,
		 u.use_date_of_birth, u.use_gender, u.use_blocked, u.use_remaining_attempts from public.user as u;
END;
$BODY$
LANGUAGE plpgsql VOLATILE
COST 100
ROWS 1000;

--select * from m01_getusers();

CREATE OR REPLACE FUNCTION m01_changePassword( IN _username character varying, IN _password character varying)
  RETURNS character varying AS
$BODY$
DECLARE
	_userpassword character varying;
	_userid
BEGIN
	select u.use_id, u.use_password from public.user as u
	where (u.use_username=_username or u.use_email=_username) into _userid, _userpassword;
	if _userid > -1 then
EXECUTE format('UPDATE public.USER SET use_password = %L WHERE use_id= %L', _userpassword, _userid);
	end if;
RETURN _userid;
END;
$BODY$
LANGUAGE plpgsql VOLATILE
COST 100;

--

CREATE OR REPLACE FUNCTION m01_findByUsernameId(IN _userid integer)
  RETURNS TABLE(use_id integer, use_username character varying, use_type integer, use_email character varying,
                use_phone character varying, use_country character varying, use_city character varying, use_address character varying,
                use_date_of_birth timestamp, use_gender char, use_blocked integer, use_remaining_attempts integer) AS
$BODY$
BEGIN
  RETURN QUERY
	select u.use_id, u.use_username, u.use_type, u.use_email, u.use_phone, u.use_country, u.use_city, u.use_address,
		 u.use_date_of_birth, u.use_gender, u.use_blocked, u.use_remaining_attempts from public.user as u
		 where _userid = u.use_id;
END;
$BODY$
LANGUAGE plpgsql VOLATILE
COST 100
ROWS 1000;

--select * from m01_findByUsernameId(2);

CREATE OR REPLACE FUNCTION m01_deleteUser(IN _userid integer) RETURNS void AS $$
DECLARE
BEGIN
	EXECUTE format('DELETE from public.USER WHERE use_id= %L', _userid);
END;
$$ LANGUAGE plpgsql;

--select * from m01_deleteUser(2);

CREATE OR REPLACE FUNCTION m01_insertUser(IN _userpassword character varying, IN _username character varying, IN _usertype integer, IN _useremail character varying,
                IN _userphone character varying, IN _usercountry character varying, IN _usercity character varying, IN _useraddress character varying,
                IN _userdateofbirth timestamp, IN _usergender char, IN _userblocked integer, IN _userremaining integer) RETURNS void AS $$
BEGIN
        INSERT INTO public.user(use_password, use_username, use_type, use_email,
                use_phone, use_country, use_city, use_address,
                use_date_of_birth, use_gender, use_blocked, use_remaining_attempts)
        VALUES(_userpassword, _username, _usertype, _useremail, _userphone, _usercountry, _usercity, _useraddress, _userdateofbirth, _usergender, _userblocked, _userremaining);
      END;
	  $$ LANGUAGE plpgsql;
	  
	  --select * from m01_insertUser('123','pruebainsert', 1, 'alexdgn213@gmail.com', '0414255', 'Ve', 'Mi', 'Sta Fe', '04/05/1995', 'F',0,3);