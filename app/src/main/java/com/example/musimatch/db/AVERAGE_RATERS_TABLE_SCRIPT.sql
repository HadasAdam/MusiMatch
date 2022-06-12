create table AVERAGE_RATERS
(
    ID                  NUMBER not null,
    RATER_SECTION_1     NUMBER(2,1) DEFAULT 0.0 not null,
    RATER_SECTION_2     NUMBER(2,1) DEFAULT 0.0 not null,
    RATER_SECTION_3     NUMBER(2,1) DEFAULT 0.0 not null
)

alter table AVERAGE_RATERS add constraint AVERAGE_RATERS_PK primary key (ID);

create sequence SEQ_AVERAGE_RATERS_ID
minvalue 1
maxvalue 9999999999999999999999999
start with 1
increment by 1
cache 20;