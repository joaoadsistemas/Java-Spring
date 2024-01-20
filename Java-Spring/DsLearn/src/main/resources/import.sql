INSERT INTO tb_user (name, email, password) VALUES ('Alex Brown', 'alex@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Bob Brown', 'bob@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Maria Green', 'maria@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_STUDENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_INSTRUCTOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);

INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);


INSERT INTO tb_course (name, img_uri, img_gray_uri) VALUES ('Bootcamp HTML', 'https://www.google.com/imgres?imgurl=https%3A%2F%2Fblog.coursify.me%2Fwp-content%2Fuploads%2F2018%2F08%2Fplan-your-online-course.jpg&tbnid=4A8E7jnFToeSoM&vet=12ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ..i&imgrefurl=https%3A%2F%2Fblog.coursify.me%2Fen%2Fhow-to-plan-your-online-course%2F&docid=_7kztPNO6OXWpM&w=800&h=500&q=course&hl=en&ved=2ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ', 'https://www.google.com/imgres?imgurl=https%3A%2F%2Fuxwing.com%2Fwp-content%2Fthemes%2Fuxwing%2Fdownload%2Feducation-school%2Fonline-course-icon.svg&tbnid=tL-3bP85sitPwM&vet=12ahUKEwiuvp38q-yDAxU9OLkGHaDkDBcQMygBegQIARB3..i&imgrefurl=https%3A%2F%2Fuxwing.com%2Fonline-course-icon%2F&docid=pN6l22oUfjgUKM&w=800&h=433&q=course&hl=en&ved=2ahUKEwiuvp38q-yDAxU9OLkGHaDkDBcQMygBegQIARB3');

INSERT INTO tb_offer (edition, start_moment, end_moment, course_id) VALUES ('1.0',TIMESTAMP WITH TIME ZONE '2020-11-20T03:00:00Z',TIMESTAMP WITH TIME ZONE '2021-11-20T03:00:00Z', 1);
INSERT INTO tb_offer (edition, start_moment, end_moment, course_id) VALUES ('2.0',TIMESTAMP WITH TIME ZONE '2020-12-20T03:00:00Z',TIMESTAMP WITH TIME ZONE '2021-12-20T03:00:00Z', 1);