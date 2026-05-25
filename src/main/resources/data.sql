INSERT INTO tb_administrador (emailAdm, senha) VALUES ('apoiopet.joinville@gmail.com', 'apoipet123');

INSERT INTO tb_pelagem (pelagem) 
VALUES (
    'Branco';
    'Preto';
    'Cinza';
    'Marrom';
    'Laranja';
    'Caramelo';
    'Frajola';
    'Rajado';
    'Siâmes';
    'Dourado';
    'Bicolor';
    'Tricolor'
);

INSERT INTO tb_sexo (sexo) 
VALUES (
    'M';
    'F'
);

INSERT INTO tb_especie (especie)
VALUES (
    'Cachorro';
    'Gato'
);

INSERT INTO tb_porte (porte)
VALUES (
    'Mini';
    'Pequeno';
    'Médio';
    'Grande';
    'Gigante'
);

INSERT INTO tb_animal (nomeAnimal, idade, status, imagem, tb_especie_idEspecie, tb_porte_idPorte, tb_pelagem_idPelagem, tb_sexo_idSexo, tb_administrador_idAdministrador)
VALUES (
    'Pop', '12', true, 'https://drive.google.com/file/d/1k5-ZaG2pIJFwlN0-U7ey3_F5JN2uRrw8/view?usp=drivesdk', 1, 3, 2, 2, 1
);