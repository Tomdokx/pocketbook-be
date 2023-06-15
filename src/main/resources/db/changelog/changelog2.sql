--changeset Tomdok:2
CREATE TABLE task (
   id bigint PRIMARY KEY,
   title varchar(255),
   description text,
   done BOOL,
   deleted BOOL,
   deleted_date timestamp,
   creation_date timestamp,
   update_date timestamp,
   app_user_id INT REFERENCES app_user(id)
);
