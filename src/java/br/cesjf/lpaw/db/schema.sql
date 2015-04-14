CREATE TABLE usuario (
    id int not null generated always as identity primary key,
    nome VARCHAR(50),
    email VARCHAR(80),
    senha VARCHAR(20)
);

CREATE TABLE estadia (
    id int not null generated always as identity primary key,
    placa VARCHAR(10),
    entrada timestamp,
    saida timestamp,
    valor_pago double
);

CREATE TABLE evento (
    id int not null generated always as identity primary key,
    acao VARCHAR(20),
    hora timestamp,
    usuario VARCHAR(100),
    estadia VARCHAR(100)
);
