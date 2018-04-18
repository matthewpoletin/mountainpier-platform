INSERT INTO public.channels(channels_id, channels_creator_id, channels_date_added, channels_email, channels_password, channels_oauth_token)
	VALUES (1, 'd1098e96-ca3d-11e7-abc4-cec278b6b50a', current_date, 'mail1@server.com', 'qwerty2018', 'token');

INSERT INTO public.channels(channels_id, channels_creator_id, channels_date_added, channels_email, channels_oauth_token, channels_password)
	VALUES (2, 'd1098e96-ca3d-11e7-abc4-cec278b6b50a', current_date, 'mail2@server.com', 'qwerty2018', 'token');

INSERT INTO public.servers(servers_id, servers_game_id, servers_name, servers_channel_id)
	VALUES (1, 'c3f27041-e605-456b-8676-4739824c3cf1', 'Chess', 1);
