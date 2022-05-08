
INSERT INTO TB_ROLE (NAME) VALUES('WORKER');
INSERT INTO TB_ROLE (NAME) VALUES('CAMPER');
INSERT INTO TB_ROLE (NAME) VALUES('HIVE');
INSERT INTO TB_ROLE (NAME) VALUES('GARDENER');
INSERT INTO TB_ROLE (NAME) VALUES('FARMER');
INSERT INTO TB_ROLE (NAME) VALUES('MANAGER');


INSERT INTO tb_csa (NAME) VALUES('CSA 1');
INSERT INTO tb_csa (NAME) VALUES('CSA 2');

INSERT INTO tb_user (email, password, role_id) VALUES('email@email.com', '$2a$10$5e5VlXrubyiSk5567yHbkubH/EUlsXCbT.Cd1CbjVlhbUqv0qXO9a', 6);

INSERT INTO tb_participant (name, email, position, id_user) VALUES('Manager', 'email@email.com', 'MANAGER', 1);

INSERT INTO tb_csa_participant (start_date, csa_id, participant_id) VALUES(PARSEDATETIME('2021-12-12','yyyy-MM-dd'), 1, 1);

