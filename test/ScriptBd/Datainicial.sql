

INSERT INTO `perfil` (`id_perfil`, `codigo`, `tipo`, `descripcion`, `estado`) VALUES
(1, 'ADM', 'ADMIN', 'ADMINISTRADOR', 'A'),
(2, 'SEC', 'SEC', 'SECRETARIA', 'A'),
(3, 'OPE', 'OPE', 'OPERADOR', 'A');

INSERT INTO `usuario` (`usuario`, `password`, `dni`, `apellido_paterno`, `apellido_materno`, `nombres`, `telefono`, `estado`, `fecha_reg`, `fecha_mod`, `usuario_mod`, `usuario_reg`, `perfil_idperfil`) VALUES
( 'master', '120c11210a181006000e', '44263869', 'MASTER', 'MASTER', 'MASTER', '333333333', 'A', '2017-08-15', '2017-08-18', 3, 1, 3);