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

-- drop function m10_select_geographical_region(integer)
CREATE OR REPLACE FUNCTION m10_select_geographical_region (IN geographicalRegionId INTEGER)
RETURNS TABLE (rg_id INTEGER, rg_name VARCHAR, rg_type INTEGER, rg_rg INTEGER) 
AS $$
BEGIN
RETURN QUERY
SELECT rg_id, rg_name, rg_type, rg_rg 
FROM GEOGRAPHICAL_REGION WHERE rg_rg = geographicalRegionId;
END;
$$
LANGUAGE 'plpgsql' VOLATILE;