INSERT INTO public.channels(channels_id, channels_username, channels_email, channels_password, channels_creator_id, channels_date_added, channels_oauth_token)
	VALUES (1, 'MountainPierDev', 'mail1@server.com', 'qwerty2018', 'd1098e96-ca3d-11e7-abc4-cec278b6b50a', current_date, 'token');

INSERT INTO public.channels(channels_id, channels_username, channels_password, channels_email, channels_creator_id, channels_date_added, channels_oauth_token)
	VALUES (2, 'MountainPierAlpha', 'mail2@server.com', 'qwerty2018', 'd1098e96-ca3d-11e7-abc4-cec278b6b50a', current_date, 'token');

INSERT INTO public.servers(servers_id, servers_game_id, servers_name, servers_channel_id)
	VALUES (1, 'c3f27041-e605-456b-8676-4739824c3cf1', '[MP|S1] Chess development test', 1);

INSERT INTO public.matches(matches_id, matches_user_id, matches_server_id)
	VALUES (1, 'd1098e96-ca3d-11e7-abc4-cec278b6b50a', 1);
