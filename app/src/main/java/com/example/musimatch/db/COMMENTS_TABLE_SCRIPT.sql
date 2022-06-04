create table COMMENTS
(
    ID              NUMBER not null,
    USER_ID         NUMBER,
    POST_ID         NUMBER,
    CONTENT         VARCHAR2(20),
    UPLOAD_TIME     TIMESTAMP(6) DEFAULT systimestamp not null
)

alter table COMMENTS add constraint COMMENTS_PK primary key (ID);
alter table COMMENTS add constraint USER_FK foreign key (USER_ID)
    references USERS(ID);
alter table COMMENTS add constraint POST_FK foreign key (POST_ID)
    references POSTS(ID);

create sequence SEQ_COMMENTS_ID
minvalue 1
maxvalue 9999999999999999999999999
start with 1
increment by 1
cache 20;