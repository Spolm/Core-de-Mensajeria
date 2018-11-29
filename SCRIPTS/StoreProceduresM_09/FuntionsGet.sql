/*Nombre de las compañias*/
CREATE OR REPLACE FUNCTION Get_CompanyName () 
 RETURNS TABLE (
 companiesName VARCHAR(200)
) 
AS $$
BEGIN
	RETURN QUERY  SELECT com_name from dim_company_campaign group by com_name;
END; $$ 
Language 'plpgsql'

/*
SELECT companiesName as com_name from public.Get_CompanyName();
*/

/*-------------------------------------------------------------------------------*/


/* Nombre de campañas*/
CREATE OR REPLACE FUNCTION Get_CampaignName () 
 RETURNS TABLE (
 campaignName VARCHAR(200)
) 
AS $$
BEGIN
	RETURN QUERY  SELECT cam_name  from dim_company_campaign;
END; $$
Language 'plpgsql'
/*
SELECT campaignName as cam_name from public.Get_CampaignName();
*/

/*-------------------------------------------------------------------------------*/

/*Nombre de Canales*/
CREATE OR REPLACE FUNCTION Get_ChannelName () 
 RETURNS TABLE (
 channelId INT,
 channelName VARCHAR(200)
) 
AS $$
BEGIN
	RETURN QUERY  SELECT DISTINCT cha_id, cha_name FROM dim_channel ORDER BY cha_id;
END; $$
Language 'plpgsql'
/*
SELECT channelId as cha_id, channelName as cha_name  from public.Get_ChannelName();
*/

/*-------------------------------------------------------------------------------*/
/*Cantidad de mensajes enviados por campaña*/
CREATE OR REPLACE FUNCTION Get_CountOfMessagesCampaign (campName VARCHAR(200)) 
 RETURNS TABLE (
 icount bigint
) 
AS $$
BEGIN
	RETURN QUERY SELECT count( M.* ) from fact_sent_message as M, 
	dim_company_campaign as C 
	where C.cam_id = M.sen_cam_id 
	and C.cam_name = campName;
END; $$
Language 'plpgsql'
/*
SELECT * from public.Get_CountOfMessagesCampaign( aux2 );
*/
/*-------------------------------------------------------------------------------*/
/*Cantidad de mensajes enviados por compania*/
CREATE OR REPLACE FUNCTION Get_CountOfMessagesCompany (compName VARCHAR(200)) 
 RETURNS TABLE (
 icount BIGINT
) 
AS $$
BEGIN
	RETURN QUERY SELECT count( M.* ) from fact_sent_message as M, 
	dim_company_campaign as C 
	where C.cam_id = M.sen_cam_id 
	and C.com_name = compName;
END; $$
Language 'plpgsql'
/*
SELECT * from public.Get_CountOfMessagesCompany( aux2 );
*/
/*-------------------------------------------------------------------------------*/
/*Cantidad de mensajes enviados por canal*/
CREATE OR REPLACE FUNCTION Get_CountOfMessagesChannel (chaName VARCHAR(200)) 
 RETURNS TABLE (
 icount BIGINT
) 
AS $$
BEGIN
	RETURN QUERY SELECT count( M.* ) from fact_sent_message as M, 
	dim_channel as C 
	where C.cha_id = M.sen_cha_id 
	and C.cha_name = chaName;
END; $$
Language 'plpgsql'

/*
SELECT * from public.Get_CountOfMessagesChannel( 'aux2' );
*/


