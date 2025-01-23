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

INSERT INTO sgd.unidades_academicas_SEQ (next_val) VALUES (16);

INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(52, 'Licenciatura en Ciencias Antropológicas', 1, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(53, 'Licenciatura en Educación', 3, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(54, 'Licenciatura en Historia', 2, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(55, 'Licenciatura en Filosofía', 4, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(56, 'Licenciatura en Letras', 5, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(57, 'Licenciatura en Lingüística', 6, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(58, 'Tecnicatura Universitaria en Corrección de Estilo', 9, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(59, 'Tecnólogo en Interpretación y Traducción LSU – Español', 8, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(60, 'Licenciatura en Turismo', 7, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(61, 'Licenciatura en Biología Humana', 10, 1);
INSERT INTO sgd.carreras (id, nombreCarrera, id_ua, habilitada) VALUES(62, 'Tecnicatura Universitaria en Dramaturgia', 5, 1);

INSERT INTO sgd.carreras_SEQ (next_val) VALUES (63);

insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(1, 'Formación general', 52, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(2, 'Formación general – Formación y práctica en extensión', 52, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(3, 'Formación general – Lenguas extranjeras', 52, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(4, 'Formación específica I – Antropología social', 52, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(5, 'Formación específica I – Arqueología', 52, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(6, 'Formación específica I –  Antropología biológica', 52, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(7, 'Formación específica II – Antropología social', 52, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(8, 'Formación específica II – Arqueología', 52, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(9, 'Formación específica II –  Antropología biológica', 52, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(10, 'Contenidos optativos específicos – Antropología social', 52, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(11, 'Contenidos optativos específicos – Arqueología', 52, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(12, 'Contenidos optativos específicos – Antropología biológica', 52, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(13, 'Electivas', 52, 1);

insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(14, 'Módulo introductorio', 53, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(15, 'Módulo pedagogía, política y sociedad', 53, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(16, 'Módulo enseñanza y aprendizaje', 53, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(17, 'Módulo historia y filosofía de la educación', 53, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(18, 'Módulo de formación en lengua extranjera', 53, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(19, 'Actividades integrales', 53, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(20, 'Abordajes interdisciplinarios o vinculados a la docencia', 53, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(21, 'Estudios electivos', 53, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(22, 'Formación específica y profundización metodológica – Pedagogía, política y sociedad', 53, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(23, 'Formación específica y profundización metodológica – Enseñanza y aprendizaje', 53, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(24, 'Formación específica y profundización metodológica – Historia y filosofía de la educación', 53, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(25, 'Integración interdisciplinaria', 53, 1);

insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(26, 'Obligatorias filosóficas – Historia de la filosofía', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(27, 'Obligatorias filosóficas – Lógica y metodología', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(28, 'Obligatorias filosóficas – Filosofía teórica', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(29, 'Obligatorias filosóficas – Filosofía de la práctica', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(30, 'Obligatorias filosóficas – Estética', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(31, 'Obligatorias filosóficas – Historia y filosofía de la ciencia', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(32, 'Obligatorias filosóficas – Filosofía latinoamericana y uruguaya', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(33, 'Obligatorias complementarias', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(34, 'Electivas filosóficas – Lógica y metodología', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(35, 'Electivas filosóficas – Filosofía teórica', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(36, 'Electivas filosófica – Filosofía de la práctica', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(37, 'Electivas filosófica – Estética', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(38, 'Electivas filosófica – Historia y filosofía de la ciencia', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(39, 'Electivas filosófica – Filosofía latinoamericana y uruguaya', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(40, 'Electivas filosófica – Formación complementaria', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(41, 'Electivas filosófica – Tópicos especiales – Historia de la filosofía', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(42, 'Electivas filosófica – Tópicos especiales – Lógica y metodología', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(43, 'Electivas filosófica – Tópicos especiales – Filosofía teórica', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(44, 'Electivas filosófica – Tópicos especiales – Filosofía de la práctica', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(45, 'Electivas filosófica – Tópicos especiales – Estética', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(46, 'Electivas filosófica – Tópicos especiales – Historia y filosofía de la ciencia', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(47, 'Electivas filosófica – Tópicos especiales – Filosofía latinoamericana y uruguaya', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(48, 'Electivas universitarias', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(49, 'Taller integral', 55, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(50, 'Seminario de tesina', 55, 1);

insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(51, 'Introductoria', 54, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(52, 'Teórico – metodológica', 54, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(53, 'Europa y el mundo', 54, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(54, 'Uruguay y América', 54, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(55, 'Actividades integradas', 54, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(56, 'Optativas', 54, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(57, 'Electivas', 54, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(58, 'Seminario de tesis', 54, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(59, 'Lengua moderna', 54, 1);

insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(60, 'Formación general', 56, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(61, 'Formación específica – Literaturas uruguaya y de América Latina', 56, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(62, 'Formación específica – Filología clásica', 56, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(63, 'Formación específica – Teórico - metodológica', 56, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(64, 'Formación específica – Literaturas en lenguas europeas', 56, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(65, 'Formación específica – Seminarios – Literaturas uruguaya y de América Latina', 56, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(66, 'Formación específica – Seminarios – Filología clásica', 56, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(67, 'Formación específica – Seminarios – Teórico - metodológica', 56, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(68, 'Formación específica – Seminarios –  Literaturas en lenguas europeas', 56, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(69, 'Formación específica – Taller de tesina ', 56, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(70, 'Formación específica – Optativas', 56, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(71, 'Formación específica – Electivas', 56, 1);

insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(72, 'Formación fundamental – Descripción y análisis lingüístico', 57, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(73, 'Formación fundamental – Teorías lingüísticas', 57, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(74, 'Formación fundamental – Las lenguas en su perspectiva histórica y sincrónica', 57, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(75, 'Formación fundamental – Prácticas integradoras académico-profesionales', 57, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(76, 'Recorridos interdisciplinarios optativos', 57, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(77, 'Formación electiva ', 57, 1);

insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(78, 'Formación general y académica', 58, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(79, 'Técnico - instrumental ', 58, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(80, 'Lingüística', 58, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(81, 'Literaria', 58, 1);

insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(82, 'Técnico - instrumental', 59, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(83, 'Lingüística', 59, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(84, 'Lengua ', 59, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(85, 'Actividades Optativas', 59, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(86, 'Actividades Electivas', 59, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(87, 'Actividades de EFIs', 59, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(88, 'Cursos universitarios de la FHCE', 59, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(89, 'Actividades integradoras y de formación general', 59, 1);

insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(90, 'Práctica de la escritura – Talleres de dramaturgia', 62, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(91, 'Práctica de la escritura – Talleres optativos de escritura para formatos específicos', 62, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(92, 'Práctica de la escritura – Pasantías', 62, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(93, 'Teórico - práctica – Obligatorias', 62, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(94, 'Teórico - práctica – Contenidos optativos', 62, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(95, 'Actividades complementarias', 62, 1);

insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(96, 'Introductoria – Obligatorias', 60, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(97, 'Introductoria – Optativas', 60, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(98, 'Instrumental – Obligatorias', 60, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(99, 'Instrumental – Optativas', 60, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(100, 'Conceptualizaciones y enfoques del turismo – Obligatorias', 60, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(101, 'Conceptualizaciones y enfoques del turismo – Optativas', 60, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(102, 'Operativa – Obligatorias', 60, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(103, 'Operativa – Optativas', 60, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(104, 'Espacio de aplicación turística – Obligatorias', 60, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(105, 'Espacio de aplicación turística – Optativas', 60, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(106, 'Electivas', 60, 1);

insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(107, 'Áreas comunes – Ciencias básicas', 61, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(108, 'Áreas comunes – Ciencias biológicas', 61, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(109, 'Áreas comunes – Ciencias sociales y humanísticas', 61, 1);
insert into sgd.areas (id, nombrearea, id_carrera, habilitada) values(110, 'Orientaciones específicas', 61, 1);

INSERT INTO sgd.areas_SEQ (next_val) VALUES (111);

INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(1, 'Admin', 0x000102030405060708090A0B0C0D0E0F10111213141516);
INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(2, 'Docente', 0x02030405080F12131416);
INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(3, 'Director de carrera', 0x020304050607080F101112131416);
INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(4, 'Admin de unidades académicas', 0x020305060710111314);
INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(5, 'Admin de Bedelías', 0x09150E13);
INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(6, 'Admin de Comisiones', 0x050A0B1314);
INSERT INTO sgd.roles (id, nombre, operaciones) VALUES(7, 'Admin Gobierno', 0x0C0D131405);

INSERT INTO sgd.roles_SEQ (next_val) VALUES (8);

INSERT INTO sgd.usuarios (password, creationDate, id, fullname, username, id_rol, tipoAdscripcion) VALUES('password', '2024-11-13 00:00:00', 1, 'Director Lic Educación', 'director.educacion', 3, 1);
INSERT INTO sgd.usuarios (password, creationDate, id, fullname, username, id_rol, tipoAdscripcion) VALUES('password', '2024-11-13 00:00:00', 2, 'Docente Lic Educacion', 'docente.educacion', 2, 1);
INSERT INTO sgd.usuarios (password, creationDate, id, fullname, username, id_rol, tipoAdscripcion) VALUES('password', '2024-11-13 00:00:00', 3, 'Administrador', 'admin', 1, 2);
INSERT INTO sgd.usuarios (password, creationDate, id, fullname, username, id_rol, tipoAdscripcion) VALUES('password', '2024-11-13 00:00:00', 4, 'Bedelias', 'bedelias', 5, 2);
INSERT INTO sgd.usuarios (id, creationDate, fullname, password, tipoAdscripcion, username, id_rol) VALUES(5, '2024-11-18 13:56:00.198000', 'Admin Inst Educación', 'password', 0, 'admin.educacion', 4);
INSERT INTO sgd.usuarios (id, creationDate, fullname, password, tipoAdscripcion, username, id_rol) VALUES(6, '2024-11-18 13:56:27.574000', 'Comisiones', 'password', 2, 'comisiones', 6);
INSERT INTO sgd.usuarios (id, creationDate, fullname, password, tipoAdscripcion, username, id_rol) VALUES(7, '2024-11-18 13:56:49.111000', 'Gobierno', 'password', 2, 'gobierno', 7);
INSERT INTO sgd.usuarios (password, creationDate, id, fullname, username, id_rol, tipoAdscripcion) VALUES('', '2025-01-13 00:00:00', 9, 'Test User', 'test.user', 1, 2);
INSERT INTO sgd.usuarios_SEQ (next_val) VALUES (10);

INSERT INTO sgd.adscripcion_carrera (usuario_id, carrera_id) VALUES(1, 53);
INSERT INTO sgd.adscripcion_carrera (usuario_id, carrera_id) VALUES(2, 53);

INSERT INTO sgd.configuraciones (id, config, value, isDate) VALUES(0, 0, '3', 0);
INSERT INTO sgd.configuraciones (id, config, value, isDate) VALUES(1, 1, '11/11/2024', 1);
INSERT INTO sgd.configuraciones (id, config, value, isDate) VALUES(2, 2, '31/12/2024', 1);
alter table sgd.configuraciones drop check configuraciones_chk_1;
INSERT INTO sgd.configuraciones (id, config, value, isDate) VALUES(3, 3, 'https://fhce.edu.uy/wp-content/uploads/2024/12/Formulario-para-autorizacion.doc', 0);

insert into sgd.configuraciones_SEQ(next_val) values (3);

INSERT INTO sgd.unidades_curriculares (id, nombreUC) VALUES(1, 'Investigación Educativa I');
insert into sgd.unidades_curriculares_SEQ(next_val) values (2);
insert into sgd.revisiones_SEQ(next_val) values (1);

INSERT INTO sgd.programas (id, adecuaciones, contenidos, creditos, descrEvaluacion, descrMetodologia, descripcionAdecuaciones, duracion, duracionOtro, estado, fecha, formato, horasAula, horasLecturas, horasOtrosConAcompa, horasOtrosSinAcompa, horasTareas, horasTotales, horasTrabajosConAcompa, horasTrabajosFinales, horasTrabajosSinAcompa, ingreso, modalidad, modoAprobacion, objetivos, otrasAclaracionesCarrera, otrosConAcompa, otrosServicios, otrosSinAcompa, recomNoCorresponde, recomendaciones, regimen, requisitos, requisitosCuales, semestre, tareas75obligatoria, `year`, id_uc, id_usuario) VALUES(1, 0, '<p><strong>Módulo 1 - Teorías y metodologías de investigación</strong></p><p>Especificidad de la investigación educativa. Campo conceptual y problemas de investigación.</p><p>Todo es teoría en investigación. Decisiones metodológicas en investigación educativa.</p><p>Referencias éticas en la investigación social.</p><p>Implicación. Análisis de implicación y extrañamiento.</p><p><br /></p><p><strong>Módulo 2 - Perspectivas, métodos y técnicas</strong></p><p>Abordajes cualitativos. Principales características y potencialidades en el campo de la investigación educativa.</p><p>Análisis de discurso. Análisis político del discurso.</p><p>Etnografía y reflexividad. Proceso de investigación etnográfica en prácticas educativas.</p><p>La entrevista como invención dialógica.</p><p>Observación y observación participante.</p><p>Registro, formas y estrategias de registro.</p><p>Grupos de discusión e intercambio.</p><p><br /></p><p><strong>Módulo 3 – Lectura activa de investigaciones</strong></p><p>En este módulo los y las estudiantes desarrollarán en dinámica de seminario, la lectura y análisis de un conjunto de investigaciones en el campo de la educación desde diferentes perspectivas metodológicas.</p>', 13, '<p>Se desarrollará mediante la combinación de cuatro modalidades: a) dos pruebas parciales; b) el desarrollo de dos trabajos prácticos; c) el análisis y presentación grupal de una investigación (tesis de maestría y doctorado) desarrollando una lectura metodológica de estas producciones; d) la participación activa durante el curso.</p><p><strong>Según el artículo n.º 7 y n.º 8 del Reglamento de estudios de grado de la Facultad de Humanidades y Ciencias de la Educación:</strong></p><p><strong>Art. 7. Sobre la obtención del derecho a aprobar.</strong> Para la obtención del derecho a aprobarla unidad curricular se requerirá una calificación final de Aceptable o superior para el conjunto de las actividades de evaluación realizadas durante el curso (a modo de ejemplo: pruebas parciales, informes, actividades prácticas, ejercicios presenciales o domiciliarios). Una calificación final inferior a Aceptable conducirá a la necesidad de repetir el curso.</p><p><br /></p><p><strong>Art. 8. Sobre la aprobación.</strong> Los estudiantes que obtengan el derecho a aprobar una unidad curricular podrán hacerlo mediante alguna de las siguientes modalidades:</p><p><strong>a) Aprobación directa.</strong> Aquellos estudiantes que en las actividades de evaluación referidas en el artículo anterior obtuvieran una calificación final de Bueno o superior, estarán exonerados de cualquier otro tipo de evaluación y se considerarán aprobados con dicha calificación. El docente explicitará esta posibilidad en el Programa correspondiente.</p><p><strong>b) Aprobación por evaluación final.</strong> El estudiante que no logre la aprobación directa o en el caso en que el programa establezca esta modalidad como obligatoria, podrá aprobar la unidad mediante una evaluación final o examen. Para ello, deberá alcanzar una calificación mínima de Aceptable. En cualquier caso, el estudiante que no alcanzare la calificación mínima requerida (Aceptable) en una sola de las evaluaciones durante el curso -cualquiera que esta evaluación fuere-, ya sea para su aprobación directa o para ganar el derecho a aprobar mediante evaluación final, tendrá derecho a la realización de una prueba de recuperación, que sustituirá a la referida instancia de evaluación.</p><p><strong>c) Examen libre.</strong> Para la aprobación de las unidades curriculares de carácter teórico, podrán rendir un examen libre aquellos estudiantes inscriptos al mismo. El examen versará sobre la totalidad del Programa del último curso impartido.</p><p><br /></p><p>El Consejo de Facultad -con el asesoramiento de la Comisión Académica de Grado- podráconsiderar y aprobar otras formas de aprobación.</p><p><br /></p>', '<p>Descripción propuesta metodológica</p>', NULL, 0, NULL, 0, '2024-11-18 13:43:36.501000', 2, 60, 0, 0, 0, 120, 195, 0, 0, 15, 0, 0, 2, '<ol><li>Propiciar el abordaje de la educación como un campo con problemáticas de investigación específicas en el marco de las ciencias sociales y humanas.</li><li>Presentar y analizar un panorama amplio de la investigación educativa.</li><li>En forma colectiva desarrollar una lectura activa de investigaciones en el campo de la educación.</li></ol>', NULL, NULL, 1, NULL, 0, 'Conocimientos básicos acerca de la constitución del campo de la educación y elaboración de informes académicos.', 1, 0, NULL, 0, 0, 2024, 1, 2);
insert into sgd.programas_SEQ(next_val) values (2);
INSERT into sgd.marcos(id, id_programa) values(1,1);
insert into sgd.marcos_SEQ(next_val) values (2);
INSERT INTO sgd.marco_area (marco_id, area_id) VALUES(1, 14);
INSERT INTO sgd.marco_carrera (marco_id, carrera_id) VALUES(1, 53);
INSERT INTO sgd.marco_ua (marco_id, ua_id) VALUES(1, 4);

INSERT INTO sgd.integrantes (id, cargo, cargoOtro, rol, subunidad_academica, unidad_academica, id_docente, id_programa) VALUES(1, 2, NULL, 0, NULL, 'Instituto de Educación', 2, 1);
INSERT INTO sgd.integrantes (id, cargo, cargoOtro, rol, subunidad_academica, unidad_academica, id_docente, id_programa) VALUES(2, 2, NULL, 1, NULL, 'Instituto de Educación', 2, 1);
insert into sgd.integrantes_SEQ(next_val) values (3);
INSERT INTO sgd.bibliografia (id, esTitulo, orden, titulo, id_programa) VALUES(1, 0, 3, 'BECKER, Howard (2018) Datos, pruebas e ideas. Buenos Aires, Siglo XXI.', 1);
INSERT INTO sgd.bibliografia (id, esTitulo, orden, titulo, id_programa) VALUES(2, 0, 2, 'BUENFIL, Rosa Nidia (1991). Análisis de discurso y educación. Estudios Interculturales y Educación Bases Teóricas. DIE-CINVESTAV-IPN, 179-198.', 1);
INSERT INTO sgd.bibliografia (id, esTitulo, orden, titulo, id_programa) VALUES(3, 0, 4, 'DIKER, Gabriela (2007) Autoridad, poder y saber en el campo de la pedagogía, Revista Colombiana de Educación, núm. 52, enero-junio, 2007, pp. 150-171 - Universidad Pedagógica Nacional, Bogotá, Colombia', 1);
INSERT INTO sgd.bibliografia (id, esTitulo, orden, titulo, id_programa) VALUES(4, 0, 1, 'ARFUCH, Leonor, (1995) La entrevista, una invención dialógica, Barcelona, Ed. Paidós.', 1);
insert into sgd.bibliografia_SEQ(next_val) values (5);
insert into sgd.aprobaciones_SEQ(next_val) values (1);
