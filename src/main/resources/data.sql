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
('Amora', '12', disponivel, 'https://drive.google.com/file/d/1ffi355zDDI62aOjVCDDbui1pMS22zlvX/view?usp=drive_link', 1, 3, 11, 2, 1),
('Bento', '3', disponivel, 'https://drive.google.com/file/d/129eG3XdFt51WllJ9SOS5ZpUR6tbxQFcy/view?usp=drive_link', 1, 2, 12, 1, 1),
('Blanco', '5', disponivel, 'https://drive.google.com/file/d/1z4s01-MgvCKu49QIAqw2HbjnW4L47WTU/view?usp=drive_link', 1, 4, 6, 2, 1),
('Cacau', '1', indisponivel, 'https://drive.google.com/file/d/1US8qXFUdrhf03X_pZ-yv-UmJQ3ic0xwb/view?usp=drive_link', 1, 3, 6, 1, 1),
('Dani', '1', indisponivel, 'https://drive.google.com/file/d/1NEitIdmtDIsPMoZZvrv2y54_kmEOIl1W/view?usp=drive_link', 1, 1, 2, 1, 1),
('Fred', '9', disponivel, 'https://drive.google.com/file/d/1N63wggY8sic4fX18cSX1XEhNsMUep4Ub/view?usp=drive_link', 2, 3, 11 ,1, 1),
('Frida', '1', disponivel, 'https://drive.google.com/file/d/1WVNUa8tkiKrCciWhODpRQuKpCfKH3zLu/view?usp=drive_link', 1, 2, 12, 2, 1),
('Simon', '8', disponivel, 'https://drive.google.com/file/d/1MLUDQjhw9vTWuqyyOklNpduP_bikcbhx/view?usp=drive_link', 2, 3, 11, 1, 1),
('Linda', '7', disponivel, 'https://drive.google.com/file/d/19WVAzvtBd0dzxRVoWpkOFaBYYSYa73Ys/view?usp=drive_link', 2, 3, 2, 2, 1),
('Lola', '4', disponivel, 'https://drive.google.com/file/d/1C6rz-8wtSVZvZ0mJC9kNl-K37n3Ig8lk/view?usp=drive_link', 2, 3, 11, 2 ,1);

-- Voluntários (Usando INSERT IGNORE para evitar falhas de constraint única)
INSERT IGNORE INTO tb_voluntarios (nome, idade, tb_email_voluntario_id_email_voluntario) VALUES
('Carlos Mendes', 28, 1),
('Ana Paula Santos', 34, 2);

-- Mensagens / Relatos de resgate
INSERT INTO tb_mensagem (descricao, status, imagem_antes, imagem_depois, nome_animal, idade_animal) VALUES
('Thor foi encontrado nas ruas, assustado e muito magro. Com cuidados, alimentação e carinho, se recuperou e hoje vive com mais segurança.', true, 'https://drive.google.com/file/d/1EsNZshv-1SnaHlASy9e3mICbaj5HUKX6/view?usp=drive_link', 'https://drive.google.com/file/d/1sTAlxKvyqXzAsqs8xH9JQr0IlHuNWr31/view?usp=drive_link', 'Thor', 4),
('Bob vivia em situação de abandono. Após receber acompanhamento e cuidados, melhorou sua saúde e passou a viver com mais tranquilidade.    ', true, 'https://drive.google.com/file/d/169qwZcdZ1K88dhbO63F_UER_t6u1uYQu/view?usp=drive_link','https://drive.google.com/file/d/1D089uqQpH8naVEet2Y9PpA1PrpRXl0UK/view?usp=drive_link', 'Bob', 5),
('Max passou por momentos difíceis antes do resgate. Depois de ser acolhido, ganhou energia, saúde e voltou a brincar.', true, 'https://drive.google.com/file/d/1-q91JMoBc1y2PxfArSZGO4nzFMIgFU5D/view?usp=drive_link', 'https://drive.google.com/file/d/1VcY940b2CPMWCRltrnnAKD-0uyxMPJlg/view?usp=drive_link', 'Max', 6),
('Luna foi resgatada precisando de cuidados urgentes. Com tratamento, alimentação e carinho, conseguiu se recuperar muito bem.', true, 'https://drive.google.com/file/d/1ocNJsp98SLJrbpeGQ3spGWGhcQ9pXBYQ/view?usp=drive_link', 'https://drive.google.com/file/d/1I43JTmtaCVm1fyuKN6wp2QIW3FIPE8wn/view?usp=drive_link', 'Luna', 3),
('Mel chegou debilitada e com medo. Depois do resgate, recebeu tratamento, voltou a confiar nas pessoas e ganhou uma nova chance.', false, 'https://drive.google.com/file/d/1s3RvxbPfdqi86QPvHOxhpNtGr1MotO0z/view?usp=drive_link', 'https://drive.google.com/file/d/17JEhAvrC5N_cfEPrHdO96gC-8IBC4RYx/view?usp=drive_link', 'Mel', 2),
('Nina foi encontrada ainda jovem, frágil e precisando de proteção. Após o resgate, ficou saudável e cheia de vida.', true, 'https://drive.google.com/file/d/1jlyjhJkI9fgvdWKnjWPxcixr1AaHHQV_/view?usp=drive_link', 'https://drive.google.com/file/d/1moQDcvXUPj7z-4PxOj4uWyAexou2NzF3/view?usp=drive_link', 'Nina', 1);
