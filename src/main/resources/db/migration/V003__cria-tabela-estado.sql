CREATE TABLE  estado (id BIGINT NOT NULL AUTO_INCREMENT,nome VARCHAR(80) NOT NULL,PRIMARY KEY(id))ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO estado (nome) SELECT DISTINCT nome_estado from cidade;


ALTER TABLE cidade ADD COLUMN estado_id BIGINT NOT NULL;


SET sql_safe_updates=0;
UPDATE cidade c SET c.estado_id =  (SELECT e.id FROM estado e  WHERE e.nome = c.nome_estado);


# Altere a tabela cidade e adicione a constraint onde ela é uma chave estrangeira de estado_id, onde ela referencia a coluna id na tabela estado
ALTER TABLE cidade ADD CONSTRAINT fk_cidade_estado FOREIGN KEY (estado_id) REFERENCES estado(id);


ALTER TABLE cidade DROP COLUMN nome_estado;


ALTER TABLE cidade CHANGE nome_cidade nome VARCHAR(80) NOT NULL;
