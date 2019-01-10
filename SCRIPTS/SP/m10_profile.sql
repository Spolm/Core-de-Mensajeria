-- drop function m10_select_privileges_by_user_company(integer,integer);
CREATE OR REPLACE FUNCTION m10_select_privileges_by_user_company(
	IN userId INTEGER, IN companyId INTEGER)
RETURNS TABLE (pri_id integer, pri_code varchar, pri_action varchar) AS $$
BEGIN
RETURN QUERY
	SELECT p.pri_id, p.pri_code, p.pri_action
	FROM public.responsability r
	INNER JOIN public.rol_pri rp
	ON r.res_rol_id = rp.rol_pri_rol_id
	INNER JOIN public.privilege p
	ON rp.rol_pri_pri_id = p.pri_id
	WHERE r.res_use_id = userId AND r.res_com_id = companyId
	AND SUBSTRING(p.pri_code from 2 for LENGTH(p.pri_code)) IS NOT DISTINCT FROM 'USER';
	END;
$$
LANGUAGE 'plpgsql' VOLATILE;

-- drop function m10_select_geographical_region(integer);
CREATE OR REPLACE FUNCTION m10_select_geographical_region (IN geographicalRegionId INTEGER)
RETURNS TABLE (rg_id_ INTEGER, rg_name_ VARCHAR, rg_type_ INTEGER, rg_rg_ INTEGER) 
AS $$
BEGIN
RETURN QUERY
SELECT rg_id, rg_name, rg_type, rg_rg 
FROM GEOGRAPHICAL_REGION WHERE rg_rg = geographicalRegionId;
END;
$$
LANGUAGE 'plpgsql' VOLATILE;

-- DROP FUNCTION m10_edit_user_by_profile(integer,varchar, varchar, integer, integer, varchar, varchar, varchar, varchar, varchar);
CREATE OR REPLACE FUNCTION m10_edit_user_by_profile(
	IN userId INTEGER, IN name VARCHAR, IN lastname VARCHAR,
	IN ci INTEGER, IN gr INTEGER, IN address VARCHAR, IN birthdate VARCHAR,
	IN gender CHAR, IN email VARCHAR, IN phone VARCHAR)
RETURNS VOID AS $$
BEGIN
	UPDATE PUBLIC.User SET
	use_name = name,
	use_lastname = lastname,
	use_identification_number = ci,
	use_rg_id = gr,
	use_address = address,
	use_date_of_birth = TO_TIMESTAMP(birthdate,'YYYY-MM-DD'),
	use_gender = gender,
	use_email = email,
	use_phone = phone
	WHERE use_id = userId;
	END;
$$
LANGUAGE 'plpgsql' VOLATILE;

-- drop function m10_select_responsabilities_by_company
CREATE OR REPLACE FUNCTION m10_select_responsabilities_by_company (IN companyId INTEGER)
RETURNS TABLE (userid_ INTEGER, username_ VARCHAR, name_ VARCHAR, lastname_ VARCHAR, identification_ INTEGER,
	birth_ TIMESTAMP, phone_ VARCHAR, rg_ INTEGER, email_ VARCHAR, rol_id_ INTEGER, rol_name_ VARCHAR)
AS $$
BEGIN
RETURN QUERY  
SELECT u.use_id, u.use_username, u.use_name, u.use_lastname, u .use_identification_number, u.use_date_of_birth, u.use_phone, u.use_rg_id, u.use_email,
	rol.rol_id, rol.rol_name
FROM PUBLIC.RESPONSABILITY r
INNER JOIN PUBLIC.COMPANY c
on r.res_com_id = c.com_id
INNER JOIN PUBLIC.USER u
on r.res_use_id = u.use_id
INNER JOIN PUBLIC.ROLE rol
on rol.rol_id = r.res_rol_id
WHERE c.com_id = companyId;
END;
$$
LANGUAGE 'plpgsql' VOLATILE;