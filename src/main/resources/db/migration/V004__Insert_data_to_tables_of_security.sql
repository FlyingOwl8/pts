INSERT INTO roles (name, description) VALUES ('USER', 'user');
INSERT INTO users (full_name, email, password, role_id, post_id) VALUES ('Jon Snow', 'jon@snow.com', '$2a$10$jUOwHUDGYBq5H2WyHJXfhu/g4pY.fQb5nNDwDRFaue5yV2c/B9tNa', 1, 1);

INSERT INTO roles (name, description) VALUES ('ADMIN', 'admin');
INSERT INTO users (full_name, email, password, role_id, post_id) VALUES ('ADMIN', 'admin@admin.com', '$2a$10$SZKfR5GAIyJlCihDKIx59.Hp.jLw1psQRmuhQFezN95pjpVqZ5Y56', 2, 2);

INSERT INTO tests (user_id, status_id, result) VALUES (1, 1, 0.0);