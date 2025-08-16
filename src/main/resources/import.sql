INSERT INTO tb_user_app (name, email, password, por_number, birth_date, creat_At) VALUES ('Fábio Costa', 'fabiocosta@mail.com', '$2a$10$T0yoFdiWiA1HH/Oysq/ucOPr0hdu0dvlKk.hm8TH52WNn3l86wE6C', 'PT1234567', '1991-07-24', NOW());
INSERT INTO tb_user_app (name, email, password, por_number, birth_date, creat_At) VALUES ('Rabaioli', 'tfrabaioli@mail.com', '$2a$10$T0yoFdiWiA1HH/Oysq/ucOPr0hdu0dvlKk.hm8TH52WNn3l86wE6C', 'PT7654321', '1987-01-05', NOW());
INSERT INTO tb_user_app (name, email, password, por_number, birth_date, creat_At) VALUES ('David Morgado', 'davidmorgado@mail.com', '$2a$10$T0yoFdiWiA1HH/Oysq/ucOPr0hdu0dvlKk.hm8TH52WNn3l86wE6C', 'PT1122334', '1995-11-30', NOW());
INSERT INTO tb_user_app (name, email, password, por_number, birth_date, creat_At) VALUES ('José Tavares', 'josetavares@mail.com', '$2a$10$T0yoFdiWiA1HH/Oysq/ucOPr0hdu0dvlKk.hm8TH52WNn3l86wE6C', 'PT1719274', '1983-06-22', NOW());
INSERT INTO tb_user_app (name, email, password, por_number, birth_date, creat_At) VALUES ('Tiago Ferreira', 'tiagoferreira@mail.com', '$2a$10$T0yoFdiWiA1HH/Oysq/ucOPr0hdu0dvlKk.hm8TH52WNn3l86wE6C', 'PT3242077', '1993-01-24', NOW());
INSERT INTO tb_user_app (name, email, password, por_number, birth_date, creat_At) VALUES ('Alexandre Silva', 'alexandresilva@mail.com', '$2a$10$T0yoFdiWiA1HH/Oysq/ucOPr0hdu0dvlKk.hm8TH52WNn3l86wE6C', 'PT9199186', '1983-11-06', NOW());



INSERT INTO tb_role (authority) VALUES ('ROLE_VISITANTE');
INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_VIGILANTE');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');




-- 4 VIGILANTES (role_id = 3)

INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 3);
INSERT INTO tb_user_role (user_id, role_id) VALUES (5, 3);
INSERT INTO tb_user_role (user_id, role_id) VALUES (6, 3);

-- 2 ADMINISTRADORES (role_id = 4)
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 4);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 4);







INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Auditório', 'Visitante Mateus Moreira', 'Carteira preta com cartões', '2025-04-08', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Piso 1 Sala de espera', 'Visitante Rodrigo Pinto', 'Auriculares brancos', '2025-02-20', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Recepção', 'Visitante Mariana Fernandes', 'Relógio de pulso prateado', '2025-01-13', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Cantina', 'Visitante Beatriz Lopes', 'Carteira castanha com documentos', '2025-06-13', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Elevador bloco B', 'Visitante Leonor Castro', 'Chaves com porta-chaves azul', '2025-01-25', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Entrada principal', 'Visitante Guilherme Rocha', 'Casaco preto com capuz', '2025-05-11', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Piso 2 WC Masculino', 'Visitante Lara Gomes', 'Óculos de sol Ray-Ban', '2025-06-04', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Biblioteca', 'Visitante Ricardo Figueiredo', 'Telemóvel Samsung azul', '2025-03-05', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Estacionamento', 'Visitante Catarina Ribeiro', 'Carteira preta com cartões', '2025-04-24', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Corredor central', 'Visitante Tomás Almeida', 'Garrafa de água metálica', '2025-01-08', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Entrada principal', 'Visitante Inês Carvalho', 'Chaves com porta-chaves azul', '2025-03-18', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Cantina', 'Visitante João Matos', 'Mochila vermelha com livros', '2025-02-02', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Auditório', 'Visitante Rafael Sousa', 'Relógio de pulso prateado', '2025-07-11', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Elevador bloco B', 'Visitante Sara Marques', 'Carteira castanha com documentos', '2025-06-07', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Corredor central', 'Visitante Filipe Barros', 'Telemóvel Samsung azul', '2025-05-15', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Piso 1 Sala de espera', 'Visitante Sofia Neves', 'Óculos de sol Ray-Ban', '2025-03-28', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Recepção', 'Visitante Diogo Ferreira', 'Auriculares brancos', '2025-01-30', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Biblioteca', 'Visitante Mariana Costa', 'Carteira preta com cartões', '2025-02-17', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Piso 2 WC Masculino', 'Visitante Bruno Martins', 'Garrafa de água metálica', '2025-03-09', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Entrada principal', 'Visitante Carla Lima', 'Casaco preto com capuz', '2025-04-20', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Estacionamento', 'Visitante Hugo Teixeira', 'Mochila vermelha com livros', '2025-06-26', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Cantina', 'Visitante Joana Vieira', 'Carteira castanha com documentos', '2025-05-03', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Biblioteca', 'Visitante Miguel Batista', 'Chaves com porta-chaves azul', '2025-06-18', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Elevador bloco B', 'Visitante Teresa Barata', 'Relógio de pulso prateado', '2025-07-07', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Auditório', 'Visitante Daniel Morais', 'Carteira preta com cartões', '2025-04-27', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Corredor central', 'Visitante Liliana Fonseca', 'Telemóvel Samsung azul', '2025-05-30', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Recepção', 'Visitante Ricardo Dias', 'Óculos de sol Ray-Ban', '2025-02-22', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Piso 1 Sala de espera', 'Visitante Vera Nogueira', 'Auriculares brancos', '2025-01-10', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Cantina', 'Visitante Nuno Pires', 'Carteira castanha com documentos', '2025-06-10', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');
INSERT INTO tb_item_lost (status, location, who_find, description, found_date, img_url) VALUES (true, 'Estacionamento', 'Visitante Andreia Leal', 'Chaves com porta-chaves azul', '2025-03-16', 'https://raw.githubusercontent.com/thiagorabaioli/frontend-requi/refs/heads/main/src/assets/losts/carteiraperdida.jfif');


INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-01', 1, 1, 'Nota do pedido 1 Utilizador 11');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-02', 2, 2, 'Nota do pedido 2 user 6');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-03', 3, 3, 'Nota do pedido 3 user 8');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-04', 4, 4, 'Nota do pedido 4 user 4');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-05', 5, 5, 'Nota do pedido 5 user 28 ');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-06', 6, 6, 'Nota do pedido user 136');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-07', 7, 1, 'Nota do pedido 7 user 7');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-08', 8, 3, 'Nota do pedido 8 user 17');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-09', 9, 2, 'Nota do pedido user 109');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-10', 10, 2, 'Nota do pedido 10 user 5');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-11', 11, 1, 'Nota do pedido 11');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-12', 12, 4, 'Nota do pedido 12');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-13', 13, 2, 'Nota do pedido 13');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-14', 14, 3, 'Nota do pedido 14');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-15', 15, 6, 'Nota do pedido 15');
INSERT INTO ORDER_ITEM (type, interaction_date, item_lost_id, userapp_id, notes) VALUES (1, '2025-07-16', 16, 3, 'Nota do pedido 16');

