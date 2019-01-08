CREATE OR REPLACE FUNCTION m08_insertSentMessage(IN
  _time timestamp,
  _messageID integer,
  _campaignId integer,
  _channelId integer,
  _integratorId integer,
  _applicationId integer)
RETURNS TABLE(
  id INT
) AS $BODY$
BEGIN
RETURN QUERY
INSERT INTO public.sent_message VALUES (nextval('sent_message_sen_id_seq'), _time, _messageID, _campaignId, _channelId, _integratorId, _applicationId)
RETURNING sen_id;
END; $BODY$
LANGUAGE plpgsql VOLATILE
