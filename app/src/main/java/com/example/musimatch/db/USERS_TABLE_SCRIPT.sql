create table USERS
(
    ID              NUMBER not null,
    USERNAME        VARCHAR2(20) not null,
    IS_ADMIN        VARCHAR2(1) DEFAULT 'N' not null,
    USER_TYPE       NUMBER not null,
    FIRST_NAME      VARCHAR2(12),
    LAST_NAME       VARCHAR2(12),
    AVERAGE_RATE    NUMBER(2,1) DEFAULT 0 not null,
    SPOTIFY_URL     VARCHAR2(100),
    LAST_UPDATED    TIMESTAMP(6) DEFAULT systimestamp not null
)

alter table USERS add constraint USERS_PK primary key (ID);
alter table USERS add unique (USERNAME);

create sequence SEQ_USERS_ID
minvalue 1
maxvalue 9999999999999999999999999
start with 1
increment by 1
cache 20;