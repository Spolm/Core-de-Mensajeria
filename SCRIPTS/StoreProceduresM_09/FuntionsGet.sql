CREATE OR REPLACE FUNCTION Get_CompanyName () 
 RETURNS TABLE (
 icount BIGINT,
 companiesName VARCHAR(200)
 
) 
AS $$
BEGIN
	RETURN QUERY  select count(M.*), C.com_name from fact_sent_message as M, dim_company_campaign as C 
    where C.cam_id = M.sen_cam_id GROUP BY C.com_name  ;
END; $$ 
Language 'plpgsql';

/*
SELECT icount , companiesName as com_name from public.Get_CompanyName();
*/

-- SELECT companiesName as com_name from public.Get_CompanyName();


/* Nombre de campañas*/
CREATE OR REPLACE FUNCTION Get_CampaignName  () 
 RETURNS TABLE (
 icount BIGINT,
 campaignName  VARCHAR(200)
 
) 
AS $$
BEGIN
	RETURN QUERY  select count(M.*), C.cam_name from fact_sent_message as M, dim_company_campaign as C 
    where C.cam_id = M.sen_cam_id GROUP BY C.cam_name  ;
END; $$ 
Language 'plpgsql';

/*
SELECT campaignName as cam_name, icount from public.Get_CampaignName();
*/

/*-------------------------------------------------------------------------------*/
drop function Get_ChannelName()
/*Nombre de Canales*/
CREATE OR REPLACE FUNCTION Get_ChannelName () 
 RETURNS TABLE (
 icount BIGINT,
 channelName VARCHAR(200)
) 
AS $$
BEGIN
	RETURN QUERY  SELECT count(M.*), C.cha_name from fact_sent_message as M, 
                         dim_channel as C where C.cha_id = M.sen_cha_id GROUP BY C.cha_name  ;
END; $$
Language 'plpgsql';
/*
SELECT icount, channelName as cha_name  from public.Get_ChannelName();
*/


