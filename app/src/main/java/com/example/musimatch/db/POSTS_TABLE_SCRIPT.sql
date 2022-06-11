create table POSTS
(
    ID                  NUMBER not null,
    TITLE               VARCHAR2(15) DEFAULT 'No Title' not null,
    POEM_LYRICS         VARCHAR2(200),
    MELODY_FILE_PATH    VARCHAR2(100),
    CREATOR_ID          NUMBER not null,
    POST_TYPE           NUMBER not null,
    AVERAGE_RATER_ID    NUMBER not null,
    AVERAGE_POST_RATE   NUMBER(2,1) not null
)

alter table POSTS add constraint POSTS_PK primary key (ID);
alter table POSTS add constraint CREATOR_FK foreign key (CREATOR_ID)
    references USERS(ID);
alter table POSTS add constraint AVERAGE_RATER_FK foreign key (AVERAGE_RATER_ID)
    references AVERAGE_RATERS(ID);

create sequence SEQ_POSTS_ID
minvalue 1
maxvalue 9999999999999999999999999
start with 1
increment by 1
cache 20;