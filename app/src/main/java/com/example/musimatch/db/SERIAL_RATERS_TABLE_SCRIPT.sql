create table SERIAL_RATERS
(
    ID                      NUMBER not null,
    POEM_RATE_SECTION       NUMBER not null,
    MELODY_RATE_SECTION     NUMBER not null,
    RATE_VALUE              NUMBER not null,
    POST_ID                 NUMBER not null,
    USER_ID                 NUMBER not null
)

alter table SERIAL_RATERS add constraint SERIAL_RATERS_PK primary key (ID);
alter table SERIAL_RATERS add constraint POST_FK foreign key (POST_ID)
    references USERS(ID);
alter table SERIAL_RATERS add constraint USER_FK foreign key (USER_ID)
    references USERS(ID);

create sequence SEQ_SERIAL_RATERS_ID
minvalue 1
maxvalue 9999999999999999999999999
start with 1
increment by 1
cache 20;