create table TAGS
(
    ID          NUMBER not null,
    TAG_GROUP   NUMBER DEFAULT 0 not null,
    NAME        VARCHAR2(12) not null
)

alter table TAGS add constraint TAGS_PK primary key (ID);
alter table TAGS add unique (NAME);

create sequence SEQ_TAGS_ID
minvalue 1
maxvalue 9999999999999999999999999
start with 1
increment by 1
cache 20;