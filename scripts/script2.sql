INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(1, 'Instituto de Antropología', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(2, 'Instituto de Historia', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(3, 'Instituto de Educación', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(4, 'Instituto de Filosofía', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(5, 'Instituto de Letras', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(6, 'Instituto de Lingüística', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(7, 'Área de Estudios Turísticos', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(8, 'Área de Estudios Sordos', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(9, 'Área de Estudios Editoriales', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(10, 'Instituto de Estudios Interdisciplinarios', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(11, 'Centro de Lenguas Extranjeras', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(12, 'Unidad de Apoyo a la Enseñanza', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(13, 'Unidad de Evaluación Institucional y Prospectiva', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(14, 'Unidad de Extensión', 1);
INSERT INTO sgd.unidades_academicas (id, nombreUA, habilitada) VALUES(15, 'Unidad de Egresados', 1);

INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(52, 'Licenciatura en Ciencias Antropológicas', 1, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(53, 'Licenciatura en Educación', 3, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(54, 'Licenciatura en Historia', 2, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(55, 'Licenciatura en Filosofía', 4, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(56, 'Licenciatura en Letras', 5, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(57, 'Licenciatura en Lingüística', 6, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(58, 'Tecnicatura Universitaria en Corrección de Estilo', 9, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(59, 'Tecnólogo en Interpretación y Traducción LSU – Español', 8, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(60, 'Licenciatura en Turismo', 7, 1);

INSERT INTO sgd.areas (id, nombreArea, id_carrera, habilitada) VALUES(52, 'Introducción', 52, 1);
INSERT INTO sgd.areas (id, nombreArea, id_carrera, habilitada) VALUES(53, 'Graduación', 52, 1);
INSERT INTO sgd.areas (id, nombreArea, id_carrera, habilitada) VALUES(102, 'Abordajes interdisciplinarios o vinculados a la docencia', 53, 1);
INSERT INTO sgd.areas (id, nombreArea, id_carrera, habilitada) VALUES(252, 'Pedagogía, Política y Sociedad', 53, 1);

INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(1, 'Admin', 0x000102030405060708090A0B0C0D0E0F10111213141516);
INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(2, 'Docente', 0x02030405080F121314);
INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(52, 'Director de carrera', 0x020304050607080F1011121314);
INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(53, 'Admin de unidades académicas', 0x020305060710111314);
INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(54, 'Admin de Bedelías', 0x09150E13);
INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(55, 'Admin de Comisiones', 0x050A0B1314);
INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(102, 'Admin Gobierno', 0x0C0D131405);

INSERT INTO sgd.usuarios (password, creationDate, id, fullname, username, id_rol, tipoAdscripcion) VALUES('password', '2024-11-13 00:00:00', 1, 'Director Lic Educación', 'director.educacion@fhce.com.uy', 52, 1);
INSERT INTO sgd.usuarios (password, creationDate, id, fullname, username, id_rol, tipoAdscripcion) VALUES('password', '2024-11-13 00:00:00', 2, 'Docente Lic Educacion', 'docente.educacion@fhce.com.uy', 2, 1);
INSERT INTO sgd.usuarios (password, creationDate, id, fullname, username, id_rol, tipoAdscripcion) VALUES('password', '2024-11-13 00:00:00', 3, 'Administrador', 'admin@fhce.com.uy', 1, 2);
INSERT INTO sgd.usuarios (password, creationDate, id, fullname, username, id_rol, tipoAdscripcion) VALUES('password', '2024-11-13 00:00:00', 52, 'Bedelias', 'bedelias@fhce.com.uy', 54, 2);

INSERT INTO sgd.adscripcion_carrera (usuario_id, carrera_id) VALUES(1, 53);
INSERT INTO sgd.adscripcion_carrera (usuario_id, carrera_id) VALUES(2, 53);
INSERT INTO sgd.adscripcion_ua (usuario_id, ua_id) VALUES(3, 2);

INSERT INTO sgd.configuraciones (id, config, value, isDate) VALUES(0, 0, '3', 0);
INSERT INTO sgd.configuraciones (id, config, value, isDate) VALUES(1, 1, '11/11/2024', 1);
INSERT INTO sgd.configuraciones (id, config, value, isDate) VALUES(2, 2, '31/12/2024', 1);
