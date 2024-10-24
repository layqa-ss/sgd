INSERT INTO roles (id, nombre) VALUES(1, 'Admin');
UPDATE usuarios SET id_rol=1 WHERE username='usuario.prueba';

INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(1, 'Instituto de Antropología');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(2, 'Instituto de Historia');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(3, 'Instituto de Educación');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(4, 'Instituto de Filosofía');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(5, 'Instituto de Letras');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(6, 'Instituto de Lingüística');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(7, 'Área de Estudios Turísticos');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(8, 'Área de Estudios Sordos');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(9, 'Área de Estudios Editoriales');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(10, 'Instituto de Estudios Interdisciplinarios');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(11, 'Centro de Lenguas Extranjeras');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(12, 'Unidad de Apoyo a la Enseñanza');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(13, 'Unidad de Evaluación Institucional y Prospectiva');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(14, 'Unidad de Extensión');
INSERT INTO sgd.unidades_academicas (id, nombreUA) VALUES(15, 'Unidad de Egresados');

INSERT INTO sgd.carreras (id, nombreCarrera, id_ua) VALUES(52, 'Licenciatura en Ciencias Antropológicas', 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua) VALUES(53, 'Licenciatura en Educación', 3);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua) VALUES(54, 'Licenciatura en Historia', 2);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua) VALUES(55, 'Licenciatura en Filosofía', 4);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua) VALUES(56, 'Licenciatura en Letras', 5);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua) VALUES(57, 'Licenciatura en Lingüística', 6);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua) VALUES(58, 'Tecnicatura Universitaria en Corrección de Estilo', 9);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua) VALUES(59, 'Tecnólogo en Interpretación y Traducción LSU – Español', 8);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua) VALUES(60, 'Licenciatura en Turismo', 7);

INSERT INTO sgd.areas (id, nombreArea, id_carrera) VALUES(52, 'Introducción', 52);
INSERT INTO sgd.areas (id, nombreArea, id_carrera) VALUES(53, 'Graduación', 52);
INSERT INTO sgd.areas (id, nombreArea, id_carrera) VALUES(102, 'Abordajes interdisciplinarios o vinculados a la docencia', 53);
INSERT INTO sgd.areas (id, nombreArea, id_carrera) VALUES(252, 'Pedagogía, Política y Sociedad', 53);
