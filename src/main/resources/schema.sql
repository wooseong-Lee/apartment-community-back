alter table board drop foreign key FK2ghtq9w3v151obbc55p0vpc0v
drop table if exists board
drop table if exists community
create table board (id bigint not null auto_increment, created_at datetime(6), modified_at datetime(6), content varchar(255), title varchar(255) not null, community_id bigint, primary key (id)) engine=InnoDB
create table community (id bigint not null auto_increment, name varchar(255) not null, notice varchar(255), zip_code varchar(255) not null, primary key (id)) engine=InnoDB
alter table community add constraint UK6exqlu53bmqrhurithxg2r6uo unique (name, zip_code)
alter table board add constraint FK2ghtq9w3v151obbc55p0vpc0v foreign key (community_id) references community (id)