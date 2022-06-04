create table TAGS_TO_POST
(
    TAG_ID              NUMBER not null,
    POST_ID             NUMBER not null
)

alter table TAGS_TO_POST add constraint TAGS_TO_POST_UK unique (TAG_ID,POST_ID);
alter table TAGS_TO_POST add constraint TAG_FK foreign key (TAG_ID)
    references TAGS(ID);
alter table TAGS_TO_POST add constraint POST_FK foreign key (POST_ID)
    references POSTS(ID);