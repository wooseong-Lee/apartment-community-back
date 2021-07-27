drop table if exists post

drop table if exists board
drop table if exists community
drop table if exists test_user

create table board (id bigint not null auto_increment, created_at datetime(6), modified_at datetime(6), content varchar(255), title varchar(255) not null, community_id bigint, primary key (id)) engine=InnoDB
create table community (id bigint not null auto_increment, name varchar(255) not null, notice varchar(255), primary key (id)) engine=InnoDB
create table test_user (test_user_id bigint not null auto_increment, age integer not null, name varchar(255), primary key (test_user_id)) engine=InnoDB

alter table board add constraint UK_mph7qe4yv41dlsoap3i3nojto unique (title)
alter table community add constraint UK_ggi0mfnbrejia9lxku7voffc9 unique (name)
alter table board add constraint FK2ghtq9w3v151obbc55p0vpc0v foreign key (community_id) references community (id)

