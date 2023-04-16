--changeset Tomdok:2
CREATE TABLE task (
   id bigint PRIMARY KEY,
   title varchar(255),
   description text,
   done BOOL,
   deleted BOOL,
   deleted_date DATE,
   creation_date DATE,
   update_date DATE,
   app_user_id INT REFERENCES app_user(id)
);
