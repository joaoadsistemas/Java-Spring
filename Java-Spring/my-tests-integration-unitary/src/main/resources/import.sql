INSERT INTO tb_movie(movie_name, description) VALUES ('Back To The Future', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');
INSERT INTO tb_movie(movie_name, description) VALUES ('Mercenaries', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');
INSERT INTO tb_movie(movie_name, description) VALUES ('Fast And Furious', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');

INSERT INTO tb_genre(genre_name) VALUES ('Action');
INSERT INTO tb_genre(genre_name) VALUES ('Race');
INSERT INTO tb_genre(genre_name) VALUES ('Science Fiction');

INSERT INTO tb_movie_genre(movie_id, genre_id) VALUES (1, 3);
INSERT INTO tb_movie_genre(movie_id, genre_id) VALUES (2, 1);
INSERT INTO tb_movie_genre(movie_id, genre_id) VALUES (3, 1);
INSERT INTO tb_movie_genre(movie_id, genre_id) VALUES (3, 2);

INSERT INTO tb_user(email, first_name, last_name, password) VALUES ('joao@gmail.com', 'Joao', 'Henrique', '$2a$12$k9u/wTCRBOmxH4/RrP3VE.400yi13gymmqV0z48xtoCKpIzjzqh3m');
INSERT INTO tb_user(email, first_name, last_name, password) VALUES ('carlos@gmail.com', 'Carlos', 'Alberto', '$2a$12$k9u/wTCRBOmxH4/RrP3VE.400yi13gymmqV0z48xtoCKpIzjzqh3m');

INSERT INTO tb_role(authority) VALUES ('CLIENT');
INSERT INTO tb_role(authority) VALUES ('ADMIN');

INSERT INTO tb_user_role(user_id, role_id) VALUES(1,2);
INSERT INTO tb_user_role(user_id, role_id) VALUES(2,1);