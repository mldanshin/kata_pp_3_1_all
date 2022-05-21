INSERT INTO roles (id, name) VALUES (1, "ROLE_ADMIN");
INSERT INTO roles (id, name) VALUES (2, "ROLE_USER");
INSERT INTO users (id, first_name, last_name, username, password) VALUES (1, "admin", "admin", "admin", "$2a$12$CoaeXo3G4V5q49dowlVrcufA3XpLybr5XVcnsNvYkILoa/1ZviyK2");
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users (id, first_name, last_name, username, password) VALUES (2, "user", "user", "user", "$2a$12$nIFj8DJX7Gdmng0p4jCM7.15BJPVbZ7X7lUcVVzFEqRnBujJ60sEi");
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);