--changeset Tomdok:1
CREATE TABLE app_user (
   id bigint PRIMARY KEY,
   username varchar(255) ,
   password varchar(255) ,
   active BOOL,
   admin_role BOOL,
   deleted BOOL,
   deleted_date DATE,
   creation_date DATE,
   update_date DATE
);
