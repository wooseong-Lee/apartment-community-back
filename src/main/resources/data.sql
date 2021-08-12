--Community 데이터 생성
INSERT INTO community(name, zip_code, notice) VALUES ("해커즈", "01234", "이번주 축구 오전 10시에 있습니다. 빠짐없이 참석해주세요");
INSERT INTO community(name, zip_code, notice) VALUES ("고스락", "34567", "합주 연습하실분");
INSERT INTO community(name, zip_code, notice) VALUES ("학생회", "56789", "제39대 공과대학 학생회");


--Board 데이터 생성
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (1, "해커즈-title-1", "해커즈-content-1", '2017-08-28 17:22:21', '2017-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (1, "해커즈-title-2", "해커즈-content-2", '2018-08-28 17:22:21', '2018-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (1, "해커즈-title-3", "해커즈-content-3", '2014-08-28 17:22:21', '2014-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (1, "해커즈-title-4", "해커즈-content-4", '2018-08-28 17:22:21', '2018-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (1, "해커즈-title-5", "해커즈-content-5", '2017-03-28 17:22:21', '2017-03-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (1, "해커즈-title-6", "해커즈-content-6", '2018-08-28 17:22:21', '2018-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (1, "해커즈-title-7", "해커즈-content-7", '2010-08-28 17:22:21', '2010-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (1, "해커즈-title-8", "해커즈-content-8", '2018-08-28 17:22:21', '2018-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (1, "해커즈-title-9", "해커즈-content-9", '2017-08-20 17:22:21', '2017-08-20 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (1, "해커즈-title-10", "해커즈-content-10", '2018-05-28 17:22:21', '2018-05-28 17:22:21');


INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (2, "고스락-title-1", "고스락-content-1", '2011-08-28 17:22:21', '2011-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (2, "고스락-title-2", "고스락-content-2", '2013-02-28 17:22:21', '2013-02-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (2, "고스락-title-3", "고스락-content-3", '2010-08-21 17:22:21', '2010-08-21 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (2, "고스락-title-4", "고스락-content-4", '2013-04-28 17:22:21', '2013-04-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (2, "고스락-title-5", "고스락-content-5", '2015-08-28 17:22:21', '2015-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (2, "고스락-title-6", "고스락-content-6", '2016-08-28 17:22:21', '2016-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (2, "고스락-title-7", "고스락-content-7", '2010-08-28 17:22:21', '2010-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (2, "고스락-title-8", "고스락-content-8", '2013-08-28 17:22:21', '2013-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (2, "고스락-title-9", "고스락-content-9", '2010-05-23 17:22:21', '2010-05-23 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (2, "고스락-title-10", "고스락-content-10", '2013-08-28 17:22:21', '2013-08-28 17:22:21');


INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (3, "학생회-title-1", "학생회-content-1", '2011-06-18 17:22:21', '2011-06-18 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (3, "학생회-title-2", "학생회-content-2", '2010-08-28 17:22:21', '2010-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (3, "학생회-title-3", "학생회-content-3", '2011-08-28 13:22:21', '2011-08-28 13:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (3, "학생회-title-4", "학생회-content-4", '2010-08-28 17:22:21', '2010-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (3, "학생회-title-5", "학생회-content-5", '2012-08-28 17:22:21', '2012-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (3, "학생회-title-6", "학생회-content-6", '2010-08-28 17:22:21', '2010-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (3, "학생회-title-7", "학생회-content-7", '2012-02-28 17:22:21', '2012-02-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (3, "학생회-title-8", "학생회-content-8", '2013-08-28 17:22:21', '2013-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (3, "학생회-title-9", "학생회-content-9", '2013-08-28 17:22:21', '2013-08-28 17:22:21');
INSERT INTO board(community_id, title, content, created_at, modified_at) VALUES (3, "학생회-title-10", "학생회-content-10", '2010-08-28 17:23:21', '2010-08-28 17:23:21');
