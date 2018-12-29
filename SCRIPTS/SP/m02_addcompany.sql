-- Function: m02_addcompany(character varying, character varying, boolean, character varying)

--DROP FUNCTION m02_addcompany(character varying, character varying, boolean, character varying);

CREATE OR REPLACE FUNCTION m02_addcompany ( _name character varying, _description character varying, _status boolean, _link character varying)
    RETURNS VOID AS
$BODY$
BEGIN
INSERT INTO public.company (com_name, com_description, com_status, com_route_link)
VALUES (_name, _description, _status, _link);
END;
$BODY$
    LANGUAGE plpgsql VOLATILE
    COST 100;
ALTER FUNCTION m02_addcompany (character varying, character varying, boolean, character varying)
    OWNER TO "CoreMensajeria";