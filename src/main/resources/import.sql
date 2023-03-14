insert into cozinha (id,nome) values (1,'Australiana');
insert into cozinha (id,nome) values (2,'Estadunidense');
insert into cozinha (id,nome) values (3,'Brasileira');


insert into restaurante (nome,taxa_frete,cozinha_id) values ('Outback',15.0, 1);
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Bobs',5.0, 2);
insert into restaurante (nome,taxa_frete,cozinha_id) values ('Parme',10.0, 3);

insert into estado (nome) values ('rio de janeiro');
insert into estado (nome) values ('minas gerais');
insert into estado (nome) values ('sao paulo');

insert into forma_pagamento (descricao) values ('Crédito');
insert into forma_pagamento (descricao) values ('Débito');
insert into forma_pagamento (descricao) values ('PIX');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);