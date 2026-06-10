-- ============================================================
-- data.sql - Seed data para o projeto Apoio Pet
--
-- COMO RESEEDAR (após DROP/CREATE do banco):
-- 1. Pare a aplicação.
-- 2. No MySQL: DROP DATABASE db_animal; CREATE DATABASE db_animal;
-- 3. Edite application.properties (temporariamente):
--       spring.jpa.hibernate.ddl-auto=create
--       spring.sql.init.mode=always
--    → Sem comentários na mesma linha do valor!
-- 4. Rode: ./mvnw spring-boot:run
-- 5. Após subir com dados, REVERTA IMEDIATAMENTE para:
--       spring.jpa.hibernate.ddl-auto=update
--       spring.sql.init.mode=never
-- 6. Reinicie a aplicação 2x e valide os dados no MySQL.
--
-- Observação: O modo 'never' protege os dados em desenvolvimento.
-- ============================================================

-- Administrador (login)
INSERT INTO tb_administrador (email_adm, senha) VALUES
('apoiopet.joinville@gmail.com', 'apoipet123');

-- Pelagens
INSERT INTO tb_pelagem (pelagem) VALUES
('Branco'), ('Preto'), ('Cinza'), ('Marrom'), ('Laranja'),
('Caramelo'), ('Frajola'), ('Rajado'), ('Siames'), ('Dourado'),
('Bicolor'), ('Tricolor');

-- Sexos
INSERT INTO tb_sexo (sexo) VALUES ('M'), ('F');

-- Espécies
INSERT INTO tb_especie (especie) VALUES ('Cachorro'), ('Gato');

-- Portes
INSERT INTO tb_porte (porte) VALUES
('Mini'), ('Pequeno'), ('Médio'), ('Grande'), ('Gigante');

-- Emails de Voluntários
INSERT INTO tb_email_voluntario (email) VALUES
('carlos.voluntario@email.com'),
('ana.santos@email.com');

-- Animais (usando os IDs gerados acima: especie 1=Cachorro, 2=Gato; porte 3=Médio, etc.)
INSERT INTO tb_animal (nome_animal, idade, status, imagem, tb_especie_id_especie, tb_porte_id_porte, tb_pelagem_id_pelagem, tb_sexo_id_sexo, tb_administrador_id_administrador) VALUES
('Pop', '12', true, 'https://drive.google.com/file/d/1k5-ZaG2pIJFwlN0-U7ey3_F5JN2uRrw8/view?usp=drivesdk', 1, 3, 2, 2, 1),
('Mel', '3', true, 'https://example.com/mel.jpg', 2, 1, 1, 1, 1),
('Thor', '5', true, 'https://example.com/thor.jpg', 1, 4, 7, 1, 1),
('Luna', '1', false, 'https://example.com/luna.jpg', 2, 2, 5, 2, 1);

-- Voluntários
INSERT INTO tb_voluntarios (nome, idade, tb_email_voluntario_id_email_voluntario) VALUES
('Carlos Mendes', 28, 1),
('Ana Paula Santos', 34, 2);

-- Mensagens / Relatos de resgate
INSERT INTO tb_mensagem (descricao, status, imagem_antes, imagem_depois) VALUES
('Encontrei este cachorro machucado na rua das Palmeiras.', true, 'https://example.com/antes1.jpg', 'https://example.com/depois1.jpg'),
('Gatinha com filhotes abandonada no terreno baldio.', true, 'https://example.com/antes2.jpg', 'https://example.com/depois2.jpg');
