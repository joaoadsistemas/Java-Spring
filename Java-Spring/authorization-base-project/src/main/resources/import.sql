INSERT INTO tb_user (name, email, password) VALUES ('Alex', 'alex@gmail.com', '$2a$10$4tjNMRDx0NxP5DS0hxUtdud8ow31Tvyz5jTDSk2RwalE5tyZwjVHu');
INSERT INTO tb_user (name, email, password) VALUES ('Maria', 'maria@gmail.com', '$2a$10$4tjNMRDx0NxP5DS0hxUtdud8ow31Tvyz5jTDSk2RwalE5tyZwjVHu');

INSERT INTO tb_product (name) VALUES ('TV');
INSERT INTO tb_product (name) VALUES ('Computer');

/* ADICIONANDO ROLES E VINCULANDO COM OS USUÁRIOS */
INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

