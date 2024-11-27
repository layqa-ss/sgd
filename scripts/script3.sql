alter table sgd.configuraciones drop check configuraciones_chk_1;
INSERT INTO sgd.configuraciones (id, config, value, isDate) VALUES(3, 3, 'www.google.com', 0);

UPDATE sgd.roles set operaciones = 0x02030405080F12131416 where id=2;
UPDATE sgd.roles set operaciones = 0x020304050607080F101112131416 where id=3;
