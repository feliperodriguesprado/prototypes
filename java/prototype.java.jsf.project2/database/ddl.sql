DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE SEQUENCE people_id_seq;

CREATE TABLE people (
	id integer NOT NULL DEFAULT nextval ('people_id_seq'),
	name varchar(40) NOT NULL,

	CONSTRAINT pk_people PRIMARY KEY (id)
);

CREATE SEQUENCE user_id_seq;

CREATE TABLE users (
	id integer NOT NULL DEFAULT nextval ('user_id_seq'),
	people_id integer NOT NULL,
	username varchar(40) NOT NULL,
	email varchar(40) NOT NULL,
	password varchar(40) NOT NULL,
	
	CONSTRAINT pk_users PRIMARY KEY (id),
	CONSTRAINT fk_users_people_id FOREIGN KEY (people_id) REFERENCES people (id),
	CONSTRAINT uk_users_people_id UNIQUE (people_id),
	CONSTRAINT uk_users_username UNIQUE (username),
	CONSTRAINT uk_users_email UNIQUE (email)
);