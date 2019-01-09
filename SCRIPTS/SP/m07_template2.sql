--Funcion para el metodo postMessage
CREATE OR REPLACE FUNCTION m07_insertMessage(_text character varying, _templateid integer) returns integer
	language plpgsql
as $$
DECLARE id INTEGER;
BEGIN
  INSERT INTO public.message(mes_text,mes_template)
  VALUES(_text,_templateID) returning mes_id into id;
  return id;
END;
$$;

alter function m07_insertmessage(varchar, integer) owner to "CoreMensajeria";



--Funcion para el metodo postParameterOfMessage
CREATE OR REPLACE FUNCTION m07_insertParameterOfMessage(IN _messageID integer, IN _parameterID integer)
RETURNS VOID AS
$BODY$
BEGIN
INSERT INTO public.message_parameter(mp_message,mp_parameter)
VALUES(_messageID,_parameterID);
END;
$BODY$
LANGUAGE plpgsql VOLATILE;
--Select m07_insertParameterOfMessage(1,3);

--Funcion para el metodo updateMessage
--ESTOY EN DESACUERDO CON ESTE
CREATE OR REPLACE FUNCTION m07_updatemessage(_text character varying, _parameterid integer) returns integer
	language plpgsql
as $$
DECLARE
  _messageID integer;
BEGIN

  select m.mes_id from public.message as m
  where (m.mes_template = _parameterID) into _messageID;

  if _messageID > -1 then
    EXECUTE format('UPDATE public.MESSAGE set mes_text = %L WHERE mes_template = %L', _text, _parameterID);
  end if;
  RETURN _messageID;
END;
$$;

alter function m07_updatemessage(varchar, integer) owner to "CoreMensajeria";


-- Select * from m07_updateMessage('test1',1);

--Funcion para el metodo UpdateParameterOfMessage
CREATE OR REPLACE FUNCTION m07_deleteMessage(_messageid integer) returns void
	language plpgsql
as $$
DECLARE
BEGIN
  EXECUTE format('DELETE from public.message_parameter WHERE mp_message = %L', _messageID);
END;
$$;

alter function m07_deleteMessage(integer) owner to "CoreMensajeria";



------------------------------------------ MessageHandler ---------------------------------------------------

--Funcion para el metodo post parameter PARTE I 
CREATE OR REPLACE FUNCTION m07_findParameterByPar_Com_IDAndByParameterName(IN _companyId integer, IN _name character varying)
 RETURNS TABLE(par_id integer,par_name character varying,par_company_id integer) AS
 $BODY$
BEGIN
RETURN QUERY
	select p.par_id, p.par_name,p.par_company_id FROM public.parameter p where _companyId = p.par_company_id and _name = p.par_name;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;
-- Select * from m07_findParameterByPar_Com_IDAndByParameterName('Parametro',1);

--Funcion para el metodo post parameter PARTE II
CREATE OR REPLACE FUNCTION m07_findParameterByPar_Com_IDAndByParameterNameInsert(IN _name character varying, IN _par_com_ID integer)
RETURNS VOID AS
$BODY$
BEGIN
INSERT INTO public.parameter(par_name,par_company_id)
VALUES(_name,_par_com_ID);
END;
$BODY$
LANGUAGE plpgsql VOLATILE;
-- Select m07_findParameterByPar_Com_IDAndByParameterNameInsert('test',2);

--Funcion para el metodo getParameters
CREATE OR REPLACE FUNCTION m07_getParameters(IN _companyId integer)
RETURNS TABLE(par_id integer, par_name character varying, par_company_id integer) AS
$BODY$
BEGIN
RETURN QUERY
	select p.par_id,p.par_name,p.par_company_id from public.parameter as p where _companyId = p.par_company_id;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;
-- select * from m07_getParameters(1);

--Funcion para el metodo getParametersByMessage
CREATE OR REPLACE FUNCTION m07_getParametersByMessage(IN _messageID integer)
RETURNS TABLE(par_id integer, par_name character varying) AS
$BODY$
BEGIN
RETURN QUERY
	select p.par_id, p.par_name from public.parameter as p, public.message_parameter where p.par_id = mp_parameter and mp_message = _messageID order by p.par_name;
END;
 $BODY$
LANGUAGE plpgsql VOLATILE;
-- select * from m07_getParametersByMessage(1);

---------------------------------------------------ParameterHandler-----------------------------------------------------

--Funcion para el metodo PostPlanning
CREATE OR REPLACE FUNCTION m07_postPlanning(_templateId integer,_start_date character varying,
_start_time character varying,_end_date character varying,_end_time character varying)
RETURNS VOID AS
$BODY$
BEGIN
EXECUTE format('INSERT INTO public.Planning(pla_start_date, pla_start_time, pla_end_date, pla_end_time, pla_template_id)
VALUES (%L , %L , %L , %L, %L)',_start_date,_start_time,_end_date,_end_time,_templateId);
END;
$BODY$
LANGUAGE plpgsql VOLATILE;	
--VER DESPUES

--Funcion para el metodo updatePlanning 
CREATE OR REPLACE FUNCTION m07_updatePlanning(_templateId integer,_start_date character varying,
_start_time character varying,_end_date character varying,_end_time character varying )
RETURNS void AS $$
DECLARE
BEGIN
	EXECUTE format('update public.Planning set pla_start_date = %L ,
					pla_start_time = %L , pla_end_date = %L ,
					pla_end_time = %L
                	where pla_template_id = %L',_start_date,_start_time,_end_date,_end_time,_templateId);
END;
$$ LANGUAGE plpgsql;
--VER DESPUES

--Funcion para el metodo postTemplateStatusAprobado
CREATE OR REPLACE FUNCTION m07_postTemplateStatusApproved(_templateID integer, _userID integer)
RETURNS VOID AS
$BODY$
BEGIN
INSERT INTO public.template_status(ts_date, ts_template, ts_user_id, ts_status) 
VALUES(CURRENT_TIMESTAMP, _templateID, _userID, (select sta_id from public.status where sta_name='Aprobado'));
END;
$BODY$
LANGUAGE plpgsql VOLATILE;
-- Select m07_postTemplateStatusApproved(1,1);

--Funcion para el metodo postTemplateStatusAprobado
CREATE OR REPLACE FUNCTION m07_postTemplateStatusNotApproved(_templateID integer)
RETURNS VOID AS
$BODY$
BEGIN
INSERT INTO public.template_status(ts_date, ts_template, ts_status) 
VALUES(CURRENT_TIMESTAMP, _templateID, (select sta_id from public.status where sta_name='No Aprobado'));
END;
$BODY$
LANGUAGE plpgsql VOLATILE;
-- Select m07_postTemplateStatusNotApproved(1);

-----------------------------------------------------STATUSHANDLER----------------------------------------------------------

CREATE OR REPLACE FUNCTION m07_getTemplate( _id integer)
RETURNS TABLE(tem_id integer, ts_id integer, tem_user_id integer, tem_application_id integer, tem_campaign_id integer, tem_creation_date timestamp, sta_name character varying) AS
$BODY$
BEGIN
RETURN QUERY
	select t.tem_id, x.ts_id, t.tem_user_id, t.tem_application_id, t.tem_campaign_id, t.tem_creation_date, s.sta_name from public.template_status as x,public.template as t,public.status as s
	where t.tem_id = _id and t.tem_id = x.ts_template and s.sta_id = x.ts_status
	order by ts_id desc limit 1;
END;
 $BODY$
LANGUAGE plpgsql VOLATILE;
-- select * from m07_getTemplate(1);

CREATE OR REPLACE FUNCTION m07_getCampaignById( _id integer)
RETURNS TABLE(cam_id integer, cam_name character varying, cam_description character varying, cam_status boolean, cam_start_date timestamp, cam_end_date timestamp, cam_company_id integer) AS
$BODY$
BEGIN
RETURN QUERY
	select c.cam_id, c.cam_name, c.cam_description, c.cam_status, c.cam_start_date, c.cam_end_date, c.cam_company_id from public.campaign as c where c.cam_id = _id ;
END;
 $BODY$
LANGUAGE plpgsql VOLATILE;
-- select * from m07_getCampaignById(1);

CREATE OR REPLACE FUNCTION m07_getTemplate( _id integer)
RETURNS TABLE(tem_id integer, ts_id integer,tem_user_id integer, tem_application_id integer,tem_campaign_id integer,tem_creation_date timestamp,sta_name character varying) AS
$BODY$
BEGIN
RETURN QUERY
	select t.tem_id, x.ts_id, t.tem_user_id, t.tem_application_id, t.tem_campaign_id, t.tem_creation_date, s.sta_name from public.template_status as x,public.template as t,public.status as s
	where t.tem_id = _id and t.tem_id = x.ts_template and s.sta_id = x.ts_status 
	order by x.ts_id desc limit 1;
END;
 $BODY$
LANGUAGE plpgsql VOLATILE;	
-- select * from m07_getTemplate(1);

CREATE OR REPLACE FUNCTION m07_getCampaignByTemplate( _id integer)
RETURNS TABLE(tem_campaign_id integer) AS
$BODY$
BEGIN
RETURN QUERY
	select t.tem_campaign_id from public.template as t where t.tem_id = _id;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;
-- select * from m07_getCampaignByTemplate(1);

create function m07_postchannelintegrator(_templateId integer, _channel integer, _integrator integer) returns void
  language plpgsql
as
$$
BEGIN
  insert into public.template_channel_integrator (tci_template_id,tci_ci_id)
                        values (_templateId ,(select ci_id from public.channel_integrator
                        where ci_channel_id = _channel  and ci_integrator_id =  _integrator ));
END;
$$;

alter function m07_postchannelintegrator(integer, integer, integer) owner to "CoreMensajeria";

		
--DROP FUNCTION m07_postchannelintegrator(integer,integer,integer,integer);
-- select * from m07_postChannelIntegrator(1,1,1,1);

--Funcion para el metodo postTemplate parte 1 
CREATE OR REPLACE FUNCTION m07_postTemplate(_campaignid integer, _appid integer, _userid integer) returns integer
	language plpgsql
as $$
DECLARE id integer;
BEGIN
  INSERT INTO public.template(tem_creation_date, tem_campaign_id, tem_application_id, tem_user_id)
  VALUES(CURRENT_TIMESTAMP,_campaignId,_appId,_userId) returning tem_id INTO id;
  
  RETURN id;
END;
$$;

alter function m07_posttemplate(integer, integer, integer) owner to "CoreMensajeria";



--Funcion para el metodo postTemplate parte 2
CREATE OR REPLACE FUNCTION m07_posttemplate2(_campaignid integer, _userid integer) returns int
  language plpgsql
as
$$
DECLARE id INTEGER;
BEGIN
  INSERT INTO public.template(tem_creation_date, tem_campaign_id, tem_user_id)
  VALUES(CURRENT_TIMESTAMP,_campaignId,_userId) returning tem_id INTO id;
  RETURN id;
END;
$$;

alter function m07_posttemplate2(integer, integer) owner to "CoreMensajeria";





--Funcion para el metodo updateChannelIntegrator
CREATE OR REPLACE FUNCTION m07_updateChannelIntegrator(_templateID integer)
RETURNS void AS $$
DECLARE
BEGIN
	EXECUTE format('DELETE from public.template_channel_integrator WHERE tci_template_id= %L', _templateID);
END;
$$ LANGUAGE plpgsql;
-- select * from m07_updateChannelIntegrator();

--Funcion para el metodo updateTemplate1
CREATE OR REPLACE FUNCTION m07_updateTemplate(_campaignid integer, _appid integer, _templateid integer) returns void
	language plpgsql
as $$
BEGIN
  select t.tem_id,t.tem_campaign_id,t.tem_application_id from public.template as t
  where (t.tem_id = _templateID) into _templateID;
  if _templateID > -1 then
    EXECUTE format('UPDATE public.template set tem_campaign_id = %L, tem_application_id = %L WHERE tem_id = %L',_campaignId,_appId,_templateID );
  end if;
END;
$$;

alter function m07_updatetemplate(integer, integer, integer) owner to "CoreMensajeria";


--Funcion para el metodo updateTemplate2
CREATE OR REPLACE FUNCTION m07_updatetemplate2(_campaignid integer, _templateid integer) returns void
	language plpgsql
as $$
BEGIN
  select t.tem_id,t.tem_campaign_id,t.tem_application_id from public.template as t
  where (t.tem_id = _templateID) into _templateID;
  if _templateID > -1 then
    EXECUTE format('UPDATE public.template set tem_campaign_id = %L, tem_application_id = null WHERE tem_id = %L',_campaignId,_templateID );
  end if;
END;
$$;

alter function m07_updatetemplate2(integer, integer) owner to "CoreMensajeria";

--Fucion para el metodo deleteTemplate
CREATE OR REPLACE FUNCTION m07_deletetemplate(_id integer) returns void
	language plpgsql
as $$
BEGIN
  DELETE FROM public.message_parameter mp
  WHERE mp.mp_message = ( SELECT m.mes_id FROM public.message m
                          WHERE m.mes_template = _id );
  DELETE FROM public.message m
  WHERE m.mes_template = _id;

  DELETE FROM public.template_channel_integrator tci
  WHERE tci.tci_template_id = _id;

  DELETE FROM public.template_status ts
  WHERE ts.ts_template = _id;

  DELETE FROM public.planning p
  WHERE p.pla_template_id = _id;

  DELETE FROM public.template t
  WHERE t.tem_id = _id;


END;
$$;

alter function m07_deletetemplate(integer) owner to "CoreMensajeria";

--Fucion para el metodo channelintegrator
CREATE OR REPLACE FUNCTION m07_deletechannelintegrator(_templateid integer) returns void
	language plpgsql
as $$
BEGIN
  DELETE FROM public.template_channel_integrator
  WHERE tci_template_id =  _templateId;

END;
$$;

alter function m07_deletechannelintegrator(integer) owner to "CoreMensajeria";

--FUNCION POST PARAMETERS OF MESSAGE
CREATE OR REPLACE FUNCTION m07_postparameterofmessage(_messageId integer,_companyId integer, _parameter character varying)
returns void
  language plpgsql
as
$$
BEGIN
    insert into public.message_parameter(mp_message,mp_parameter)
    values (_messageId ,(select par_id
    from public.parameter
    where par_company_id = _companyId and par_name = _parameter));
END;
$$;

alter function m07_postparameterofmessage(integer,integer, varchar) owner to "CoreMensajeria";








		
