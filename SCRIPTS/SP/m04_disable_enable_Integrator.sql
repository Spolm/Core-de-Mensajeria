CREATE OR REPLACE FUNCTION m04_enableintegrator(IN _id integer) 
RETURNS void AS
$$
BEGIN
UPDATE integrator SET int_enabled = true WHERE int_id = _id;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION m04_disableintegrator(IN _id integer) 
RETURNS void AS
$$
BEGIN
UPDATE integrator SET int_enabled = false WHERE int_id = _id;
END;
$$
LANGUAGE plpgsql;




	

    