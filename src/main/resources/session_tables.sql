create table spring_session (
  primary_id            char(36) not null
    constraint spring_session_pk
    primary key,
  session_id            char(36) not null,
  creation_time         bigint   not null,
  last_access_time      bigint   not null,
  max_inactive_interval integer  not null,
  expiry_time           bigint   not null,
  principal_name        varchar(300) -- <= here was 100
);

create unique INDEX spring_session_ix1
  on spring_session (session_id);

create INDEX spring_session_ix2
  on spring_session (expiry_time);

create INDEX spring_session_ix3
  on spring_session (principal_name);


create table spring_session_attributes (
  session_primary_id char(36)     not null
    constraint spring_session_attributes_fk
    references spring_session
    on delete cascade,
  attribute_name     varchar(200) not null,
  attribute_bytes    BYTEA        not null,
  constraint spring_session_attributes_pk
  primary key (session_primary_id, attribute_name)
);