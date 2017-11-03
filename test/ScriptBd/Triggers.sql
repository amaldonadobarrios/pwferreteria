CREATE TABLE `usuario_historial` (
  `id_usuario` int(11) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `dni` varchar(45) NOT NULL,
  `apellido_paterno` varchar(45) NOT NULL,
  `apellido_materno` varchar(45) DEFAULT NULL,
  `nombres` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `estado` varchar(2) NOT NULL,
  `fecha_reg` date DEFAULT NULL,
  `fecha_mod` date DEFAULT NULL,
  `usuario_mod` int(11) DEFAULT NULL,
  `usuario_reg` int(11) DEFAULT NULL,
  `perfil_idperfil` int(11) NOT NULL,
  `fecha_reg_trigger` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cliente_historial` (
  `id_cliente` int(11) NOT NULL,
  `naturaleza_cliente` varchar(45) CHARACTER SET utf8 NOT NULL,
  `id_tipo_cliente` int(11) NOT NULL,
  `dni_ruc` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `razon_social` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `nombre` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `apellido_paterno` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `apellido_materno` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `telefono` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `direccion` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `usuario_reg` int(11) DEFAULT NULL,
  `usuario_mod` int(11) DEFAULT NULL,
  `fecha_reg` date DEFAULT NULL,
  `fecha_mod` date DEFAULT NULL,
  `fecha_reg_trigger` datetime DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='tabla de clientes';










DELIMITER $$
 CREATE  TRIGGER trgClienteinsert
BEFORE insert ON cliente 
FOR EACH ROW
BEGIN
   INSERT INTO cliente_historial
    VALUE (NEW.id_cliente,
    NEW.naturaleza_cliente,
    NEW.id_tipo_cliente,
    NEW.dni_ruc,
   NEW.razon_social,
    NEW.nombres,
    NEW.apellido_paterno,
    NEW.apellido_materno,
    NEW.telefono,
    NEW.direccion,
    NEW.email,
    NEW.usuario_reg,
    NEW.usuario_mod,
    NEW.fecha_reg,
    NEW.fecha_mod, now());
END;
$$

DELIMITER ;
DELIMITER $$
 CREATE  TRIGGER trgClienteupdate
BEFORE UPDATE ON cliente 
FOR EACH ROW
BEGIN
   INSERT INTO cliente_historial
    VALUE (NEW.id_cliente,
    NEW.naturaleza_cliente,
    NEW.id_tipo_cliente,
    NEW.dni_ruc,
   NEW.razon_social,
    NEW.nombres,
    NEW.apellido_paterno,
    NEW.apellido_materno,
    NEW.telefono,
    NEW.direccion,
    NEW.email,
    NEW.usuario_reg,
    NEW.usuario_mod,
    NEW.fecha_reg,
    NEW.fecha_mod, now());
END;
$$

DELIMITER ;




DELIMITER $$
 CREATE  TRIGGER trgUsuarioinsert
BEFORE insert ON usuario 
FOR EACH ROW
BEGIN
   INSERT INTO usuario_historial
    VALUE (NEW.id_usuario,
    NEW.usuario,
    NEW.password,
    NEW.dni,
    NEW.apellido_paterno,
    NEW.apellido_materno,
    NEW.nombres,
    NEW.telefono,
    NEW.estado,
    NEW.fecha_reg,
    NEW.fecha_mod,
    NEW.usuario_mod,
    NEW.usuario_reg,
    NEW.perfil_idperfil,
    now());
END;
$$

DELIMITER ;
DELIMITER $$
 CREATE  TRIGGER trgUsuarioupdate
BEFORE UPDATE ON usuario 
FOR EACH ROW
BEGIN
   INSERT INTO usuario_historial
    VALUE (NEW.id_usuario,
    NEW.usuario,
    NEW.password,
    NEW.dni,
    NEW.apellido_paterno,
    NEW.apellido_materno,
    NEW.nombres,
    NEW.telefono,
    NEW.estado,
    NEW.fecha_reg,
    NEW.fecha_mod,
    NEW.usuario_mod,
    NEW.usuario_reg,
    NEW.perfil_idperfil,
    now());
END;
$$

DELIMITER ;