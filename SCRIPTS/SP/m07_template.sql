DROP FUNCTION m07_select_templates_by_campaign(integer);
CREATE OR REPLACE FUNCTION m07_select_templates_by_campaign(IN campaignId integer)
RETURNS TABLE (tem_id integer,tem_creation_date timestamp,tem_campaign_id integer,
		tem_application_id integer, tem_user_id integer, sta_id integer,
		sta_name varchar,ts_user_id integer) AS $gettemplates$
BEGIN
RETURN QUERY
select t.tem_id, t.tem_creation_date, t.tem_campaign_id, t.tem_application_id, t.tem_user_id, s.sta_id, s.sta_name, ts.ts_user_id
          from public.template t
          inner join public.template_status ts
          on ts.ts_template = t.tem_id
            inner join
            (
            select ts_template, max(ts_id) maxID from public.template_status
            group by ts_template
            )ts_ on ts_.ts_template = ts.ts_template
          and ts.ts_id = ts_.maxID
          inner join public.status s
          on ts.ts_status = s.sta_id
          inner join public.campaign c
          on c.cam_id = t.tem_campaign_id
          where c.cam_id = campaignId
          order by t.tem_id;
END;
$gettemplates$
LANGUAGE 'plpgsql' VOLATILE;


CREATE OR REPLACE FUNCTION m07_select_all_templates()
RETURNS TABLE (tem_id integer,tem_creation_date timestamp,sta_id integer,sta_name varchar) 
AS $$
BEGIN
RETURN QUERY
select t.tem_id, t.tem_creation_date, s.sta_id, s.sta_name
                    from template t
                    inner join template_status ts
                    on ts.ts_template = t.tem_id
                    inner join
                    (
                    select ts_template, max(ts_id) maxID from template_status 
                    group by ts_template
                    )ts_ on ts_.ts_template = ts.ts_template
                    and ts.ts_id = ts_.maxID
                    inner join status s
                    on ts.ts_status = s.sta_id
                    order by t.tem_id;
END;
$$
LANGUAGE 'plpgsql' VOLATILE;

DROP FUNCTION m07_select_privileges_by_user_company(integer,integer);
CREATE OR REPLACE FUNCTION m07_select_privileges_by_user_company(
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
	AND SUBSTRING(p.pri_code from 2 for LENGTH(p.pri_code)) IS NOT DISTINCT FROM 'TEMPLATE';
	END;
$$
LANGUAGE 'plpgsql' VOLATILE;
