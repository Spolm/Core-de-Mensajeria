

/*Total de mensajes por canal entre dos fechas*/

CREATE OR REPLACE FUNCTION Get_MessagesByChannel (idate date default null, fdate date default null) 
 RETURNS TABLE (
 Channel VARCHAR(100), 
 Total INT
) 
AS $$
BEGIN
	IF(idate IS NOT NULL AND fdate IS NOT NULL) THEN
		 RETURN QUERY    SELECT  dim_channel.cha_name as Channel,
			count(*) as Total
			FROM public.fact_sent_message
			INNER JOIN public.dim_channel ON (fact_sent_message.sen_cha_id = dim_channel.cha_id)
			WHERE sen_dat_id BETWEEN idate AND fdate
			GROUP BY sen_cha_id,dim_channel.cha_name;
	ELSE
		 RETURN QUERY    SELECT  dim_channel.cha_name as Channel,
			count(*) as Total
			FROM public.fact_sent_message
			INNER JOIN public.dim_channel ON (fact_sent_message.sen_cha_id = dim_channel.cha_id)
			GROUP BY sen_cha_id,dim_channel.cha_name;
	END IF;		
END; $$ 
 
LANGUAGE 'plpgsql';

/*
ASI SE LLAMA DESDE JAVA PARAMETROS DE FECHA OPCIONALES

------------------------------------------
SELECT * from public.get_messagesbychannel(
    '2018-01-01',
    '2020-01-01'
);
------------------------------------------------
*/