--changeset Tomdok:2
CREATE TABLE note (
   id bigint PRIMARY KEY,
   title varchar(255),
   content text,
   deleted BOOL,
   deleted_date timestamp,
   creation_date timestamp,
   update_date timestamp,
   app_user_id INT REFERENCES app_user(id),
   task_id INT REFERENCES task(id)
);
