CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO public.exercise(
	id, topic, name)
	VALUES (uuid_generate_v4(), 'English', 'Ch');
	
INSERT INTO public.exercise(
	id, topic, name)
	VALUES (uuid_generate_v4(), 'English', 'A');
	
INSERT INTO public.exercise(
	id, topic, name)
	VALUES (uuid_generate_v4(), 'English', 'B');
	
INSERT INTO public.exercise(
	id, topic, name)
	VALUES (uuid_generate_v4(), 'English', 'C');
	
INSERT INTO public.exercise(
	id, topic, name)
	VALUES (uuid_generate_v4(), 'English', 'Q');

INSERT INTO public.exercise(
    id, topic, name)
VALUES (uuid_generate_v4(), 'Ukrainian', 'А');

INSERT INTO public.exercise(
    id, topic, name)
VALUES (uuid_generate_v4(), 'Ukrainian', 'В');

INSERT INTO public.exercise(
    id, topic, name)
VALUES (uuid_generate_v4(), 'Ukrainian', 'Б');

INSERT INTO public.exercise(
    id, topic, name)
VALUES (uuid_generate_v4(), 'Ukrainian', 'С');

INSERT INTO public.exercise(
    id, topic, name)
VALUES (uuid_generate_v4(), 'Ukrainian', 'Д');

INSERT INTO public.exercise(
    id, topic, name)
VALUES (uuid_generate_v4(), 'Ukrainian', 'Ж');