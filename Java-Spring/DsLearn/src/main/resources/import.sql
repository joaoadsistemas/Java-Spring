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

INSERT INTO tb_notification (text, moment, read, route, user_id) VALUES ('Aula começando', TIMESTAMP WITH TIME ZONE '2020-11-20T03:00:00Z', false, '?', 1);
INSERT INTO tb_notification (text, moment, read, route, user_id) VALUES ('Promoção especial', TIMESTAMP WITH TIME ZONE '2020-11-20T03:00:00Z', false, '?', 2);
INSERT INTO tb_notification (text, moment, read, route, user_id) VALUES ('Palestra', TIMESTAMP WITH TIME ZONE '2020-11-20T03:00:00Z', false, '?', 2);

INSERT INTO tb_resource (title, description, position, img_uri, type, offer_id) VALUES ('Trilha HTML', 'Trilha principal', 1, 'https://www.google.com/imgres?imgurl=https%3A%2F%2Fblog.coursify.me%2Fwp-content%2Fuploads%2F2018%2F08%2Fplan-your-online-course.jpg&tbnid=4A8E7jnFToeSoM&vet=12ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ..i&imgrefurl=https%3A%2F%2Fblog.coursify.me%2Fen%2Fhow-to-plan-your-online-course%2F&docid=_7kztPNO6OXWpM&w=800&h=500&q=course&hl=en&ved=2ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ', 1, 1);
INSERT INTO tb_resource (title, description, position, img_uri, type, offer_id) VALUES ('Fórum', 'Tire suas duvidas',2, 'https://www.google.com/imgres?imgurl=https%3A%2F%2Fblog.coursify.me%2Fwp-content%2Fuploads%2F2018%2F08%2Fplan-your-online-course.jpg&tbnid=4A8E7jnFToeSoM&vet=12ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ..i&imgrefurl=https%3A%2F%2Fblog.coursify.me%2Fen%2Fhow-to-plan-your-online-course%2F&docid=_7kztPNO6OXWpM&w=800&h=500&q=course&hl=en&ved=2ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ', 2, 1);
INSERT INTO tb_resource (title, description, position, img_uri, type, offer_id) VALUES ('Lives', 'Lives exclusivas para a turma', 3,'https://www.google.com/imgres?imgurl=https%3A%2F%2Fblog.coursify.me%2Fwp-content%2Fuploads%2F2018%2F08%2Fplan-your-online-course.jpg&tbnid=4A8E7jnFToeSoM&vet=12ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ..i&imgrefurl=https%3A%2F%2Fblog.coursify.me%2Fen%2Fhow-to-plan-your-online-course%2F&docid=_7kztPNO6OXWpM&w=800&h=500&q=course&hl=en&ved=2ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ', 0, 1);

INSERT INTO tb_section (title, description, position, img_uri, resource_id, prerequisite_id) VALUES ('Capitulo 1', 'Neste capitulovamos começar', 1, 'https://www.google.com/imgres?imgurl=https%3A%2F%2Fblog.coursify.me%2Fwp-content%2Fuploads%2F2018%2F08%2Fplan-your-online-course.jpg&tbnid=4A8E7jnFToeSoM&vet=12ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ..i&imgrefurl=https%3A%2F%2Fblog.coursify.me%2Fen%2Fhow-to-plan-your-online-course%2F&docid=_7kztPNO6OXWpM&w=800&h=500&q=course&hl=en&ved=2ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ', 1, null);
INSERT INTO tb_section (title, description, position, img_uri, resource_id, prerequisite_id) VALUES ('Capitulo 2','Nestecapitulovamos continuar', 2, 'https://www.google.com/imgres?imgurl=https%3A%2F%2Fblog.coursify.me%2Fwp-content%2Fuploads%2F2018%2F08%2Fplan-your-online-course.jpg&tbnid=4A8E7jnFToeSoM&vet=12ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ..i&imgrefurl=https%3A%2F%2Fblog.coursify.me%2Fen%2Fhow-to-plan-your-online-course%2F&docid=_7kztPNO6OXWpM&w=800&h=500&q=course&hl=en&ved=2ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ', 1, 1);
INSERT INTO tb_section (title, description, position, img_uri, resource_id, prerequisite_id) VALUES ('Capitulo 3','Nestecapitulovamos finalizar', 3, 'https://www.google.com/imgres?imgurl=https%3A%2F%2Fblog.coursify.me%2Fwp-content%2Fuploads%2F2018%2F08%2Fplan-your-online-course.jpg&tbnid=4A8E7jnFToeSoM&vet=12ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ..i&imgrefurl=https%3A%2F%2Fblog.coursify.me%2Fen%2Fhow-to-plan-your-online-course%2F&docid=_7kztPNO6OXWpM&w=800&h=500&q=course&hl=en&ved=2ahUKEwiXkdDrq-yDAxX8NbkGHSULBGcQMygKegUIARCIAQ', 1, 2);

INSERT INTO tb_enrollment (user_id, offer_id, enroll_moment, refund_moment, available, only_update) VALUES (1, 1,TIMESTAMP  WITH TIME ZONE '2020-11-20T13:00:00Z', null, true, false);
INSERT INTO tb_enrollment (user_id, offer_id, enroll_moment, refund_moment, available, only_update) VALUES (2, 1, TIMESTAMP  WITH TIME ZONE '2020-11-20T13:00:00Z', null, true, false);

INSERT INTO tb_lesson (title, position, section_id) VALUES ('Aula 1 do capítulo 1', 1, 1);
INSERT INTO tb_content (id, text_content, video_uri) VALUES (1, 'Material de apoio: abc', 'https://www.youtube.com');

INSERT INTO tb_lesson (title, position, section_id) VALUES ('Aula 2 do capítulo 1', 2, 1);
INSERT INTO tb_content (id, text_content, video_uri) VALUES (2, '', 'https://www.youtube.com');

INSERT INTO tb_lesson (title, position, section_id) VALUES ('Aula 3 do capítulo 1', 3, 1);
INSERT INTO tb_content (id, text_content, video_uri) VALUES (3, '', 'https://www.youtube.com');

INSERT INTO tb_lesson (title, position, section_id) VALUES ('Tarefa do capítulo 1', 4, 1);
INSERT INTO tb_task (id, description, question_count, approval_count, weight, due_date) VALUES (4, 'Fazer um trabalholegal', 5, 4, 1.0, TIMESTAMP  WITH TIME ZONE '2020-11-25T13:00:00Z');

INSERT INTO tb_lessons_done (lesson_id, user_id, offer_id) VALUES (1, 1, 1);
INSERT INTO tb_lessons_done (lesson_id, user_id, offer_id) VALUES (2, 1, 1);
