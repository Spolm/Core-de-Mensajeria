-- DROP FUNCTION m10_select_privileges_by_user_company(integer,integer);
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