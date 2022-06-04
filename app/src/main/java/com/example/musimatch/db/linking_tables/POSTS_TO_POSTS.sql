create table POSTS_TO_POSTS
(
    SOURCE_POST             NUMBER not null,
    LINKED_POST             NUMBER not null
)

alter table POSTS_TO_POSTS add constraint POSTS_TO_POSTS_UK unique (SOURCE_POST,LINKED_POST);
alter table POSTS_TO_POSTS add constraint SOURCE_POST_FK foreign key (POST_ID)
    references POSTS(ID);
alter table POSTS_TO_POSTS add constraint LINKED_POST_FK foreign key (POST_ID)
    references POSTS(ID);