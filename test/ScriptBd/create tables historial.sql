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


CREATE TABLE `comprobante_compra_historial` (
  `id_comprobante` int(11) NOT NULL,
  `numero_comprobante` varchar(45) NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `id_proveedor` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_reg` datetime NOT NULL,
  `total` double NOT NULL,
  `igv` double NOT NULL,
  `neto` double NOT NULL,
  `items` int(11) NOT NULL,
  `fecha_reg_trigger` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `comprobante_venta_historial` (
  `id_comprobante` int(11) NOT NULL,
  `numero_comprobante` varchar(45) NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_reg` datetime NOT NULL,
  `total` double NOT NULL,
  `igv` double NOT NULL,
  `neto` double NOT NULL,
  `items` int(11) NOT NULL,
  `fecha_reg_trigger` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `descuento_produccion_historial` (
  `id_produccion` int(11) DEFAULT NULL,
  `id_insumo` int(11) DEFAULT NULL,
  `requerimiento` double DEFAULT NULL,
  `existencias` double DEFAULT '0',
  `inventario` double DEFAULT NULL,
  `fecha_reg` datetime DEFAULT NULL,
  `fecha_reg_trigger` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `detalle_comprobante_compra_historial` (
  `id_detalle_comprobante_compra` int(11) NOT NULL,
  `numero_detalle` int(11) NOT NULL,
  `numero_comprobante` varchar(45) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` double NOT NULL,
  `subtotal` double NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `fecha_reg` datetime DEFAULT NULL,
  `estado` varchar(45) NOT NULL DEFAULT 'VENDIDO',
  `id_comprobante` int(11) NOT NULL,
  `fecha_reg_trigger` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `detalle_comprobante_venta_historial` (
  `id_detalle_comprobante_venta` int(11) NOT NULL,
  `numero_detalle` int(11) NOT NULL,
  `numero_comprobante` varchar(45) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` double NOT NULL,
  `precio` double NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `fecha_reg` datetime DEFAULT NULL,
  `estado` varchar(45) NOT NULL DEFAULT 'VENDIDO',
  `id_comprobante` int(11) NOT NULL,
  `fecha_reg_trigger` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `detalle_produccion_historial` (
  `id_detalle_produccion` int(11) NOT NULL,
  `id_produccion` int(11) NOT NULL,
  `id_regla` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad_insumos` int(11) NOT NULL,
  `cantidad_produccion` varchar(45) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `fecha_reg` datetime NOT NULL,
  `estado` int(11) NOT NULL,
  `fecha_reg_trigger` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `detalle_regla_produccion_historial` (
  `id_detalle_regla` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_regla` int(11) NOT NULL,
  `id_insumo` int(11) NOT NULL,
  `cantidad` double NOT NULL,
  `fecha_reg` datetime NOT NULL,
  `fecha_mod` datetime DEFAULT NULL,
  `usuario_reg` int(11) NOT NULL,
  `usuario_mod` int(11) DEFAULT NULL,
  `estado` int(11) NOT NULL COMMENT '1 = activo\n0= desactivado',
  `fecha_reg_trigger` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `produccion_historial` (
  `id_produccion` int(11) NOT NULL,
  `fecha_reg` datetime NOT NULL,
  `fecha` date DEFAULT NULL,
  `usuario_reg` int(11) DEFAULT NULL,
  `doc` varchar(45) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `numero` varchar(45) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `cantidad_reglas` int(11) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fecha_reg_trigger` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `producto_historial` (
  `id_producto` int(11) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `presentacion` varchar(45) NOT NULL,
  `medida` varchar(45) NOT NULL,
  `producto_insumo` varchar(45) NOT NULL,
  `pv1` double DEFAULT NULL,
  `pv2` double DEFAULT NULL,
  `pv3` double DEFAULT NULL,
  `existencia` double DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `fecha_reg` datetime DEFAULT NULL,
  `fecha_mod` datetime DEFAULT NULL,
  `usuario_reg` int(11) DEFAULT NULL,
  `usuario_mod` int(11) DEFAULT NULL,
  `fecha_reg_trigger` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `regla_produccion_historial` (
  `id_regla` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad_insumo` int(11) NOT NULL,
  `fecha_reg` datetime NOT NULL,
  `fecha_mod` datetime DEFAULT NULL,
  `usuario_reg` int(11) NOT NULL,
  `usuario_mod` int(11) DEFAULT NULL,
  `estado` int(11) NOT NULL COMMENT '1= activo\n0= desactivado',
  `fecha_reg_trigger` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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