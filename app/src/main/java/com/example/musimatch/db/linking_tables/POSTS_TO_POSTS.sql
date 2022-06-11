create table POSTS_TO_POSTS
(
    SOURCE_POST_ID             NUMBER not null,
    LINKED_POST_ID             NUMBER not null
)

alter table POSTS_TO_POSTS add constraint POSTS_TO_POSTS_UK unique (SOURCE_POST_ID,LINKED_POST_ID);
alter table POSTS_TO_POSTS add constraint SOURCE_POST_FK foreign key (SOURCE_POST_ID)
    references POSTS(ID);
alter table POSTS_TO_POSTS add constraint LINKED_POST_FK foreign key (LINKED_POST_ID)
    references POSTS(ID);