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

DELIMITER $$
 CREATE  TRIGGER trgProductoinsert
BEFORE insert ON producto 
FOR EACH ROW
BEGIN
   INSERT INTO producto_historial
    VALUE (
    NEW.id_producto,
    NEW.descripcion,
    NEW.marca,
    NEW.presentacion,
    NEW.medida,
    NEW.producto_insumo,
    NEW.pv1,
    NEW.pv2,
    NEW.pv3,
    NEW.existencia,
    NEW.type,
    NEW.estado,
    NEW.fecha_reg,
    NEW.fecha_mod,
    NEW.usuario_reg,
    NEW.usuario_mod,
    now());
END;
$$

DELIMITER ;

DELIMITER $$
 CREATE  TRIGGER trgProductoupdate
BEFORE update ON producto 
FOR EACH ROW
BEGIN
   INSERT INTO producto_historial
    VALUE (
    NEW.id_producto,
    NEW.descripcion,
    NEW.marca,
    NEW.presentacion,
    NEW.medida,
    NEW.producto_insumo,
    NEW.pv1,
    NEW.pv2,
    NEW.pv3,
    NEW.existencia,
    NEW.type,
    NEW.estado,
    NEW.fecha_reg,
    NEW.fecha_mod,
    NEW.usuario_reg,
    NEW.usuario_mod,
    now());
END;
$$

DELIMITER ;



DELIMITER $$
 CREATE  TRIGGER trgregla_produccioninsert
BEFORE insert ON regla_produccion
FOR EACH ROW
BEGIN
   INSERT INTO regla_produccion_historial
    VALUE (

NEW.id_regla,
    NEW.id_producto,
    NEW.cantidad_insumo,
    NEW.fecha_reg,
    NEW.fecha_mod,
    NEW.usuario_reg,
    NEW.usuario_mod,
    NEW.estado,
    NOW());
    END;
$$

DELIMITER ;



DELIMITER $$
 CREATE  TRIGGER trgregla_produccionupdate
BEFORE update ON regla_produccion
FOR EACH ROW
BEGIN
   INSERT INTO regla_produccion_historial
    VALUE (

NEW.id_regla,
    NEW.id_producto,
    NEW.cantidad_insumo,
    NEW.fecha_reg,
    NEW.fecha_mod,
    NEW.usuario_reg,
    NEW.usuario_mod,
    NEW.estado,
    NOW());
    END;
$$

DELIMITER ;


DELIMITER $$
 CREATE  TRIGGER trgproduccioninsert
BEFORE insert ON produccion
FOR EACH ROW
BEGIN
   INSERT INTO produccion_historial
    VALUE (
	NEW.id_produccion,
    NEW.fecha_reg,
    NEW.fecha,
    NEW.usuario_reg,
    NEW.doc,
    NEW.numero,
    NEW.cantidad_reglas,
    NEW.estado,
   now());
    END;
$$

DELIMITER ;

DELIMITER $$
 CREATE  TRIGGER trgproduccionupdate
BEFORE update ON produccion
FOR EACH ROW
BEGIN
   INSERT INTO produccion_historial
    VALUE (
	NEW.id_produccion,
    NEW.fecha_reg,
    NEW.fecha,
    NEW.usuario_reg,
    NEW.doc,
    NEW.numero,
    NEW.cantidad_reglas,
    NEW.estado,
   now());
    END;
$$

DELIMITER ;


DELIMITER $$
 CREATE  TRIGGER trgdetalle_regla_produccioninsert
BEFORE insert ON detalle_regla_produccion
FOR EACH ROW
BEGIN
   INSERT INTO detalle_regla_produccion_historial
    VALUE (
	NEW.id_detalle_regla,
	NEW.id_producto,
    NEW.id_regla,
    NEW.id_insumo,
    NEW.cantidad,
    NEW.fecha_reg,
    NEW.fecha_mod,
    NEW.usuario_reg,
    NEW.usuario_mod,
    NEW.estado,
	NOW());
    END;
$$

DELIMITER ;

DELIMITER $$
 CREATE  TRIGGER trgdetalle_regla_produccionupdate
BEFORE update ON detalle_regla_produccion
FOR EACH ROW
BEGIN
   INSERT INTO detalle_regla_produccion_historial
    VALUE (
	NEW.id_detalle_regla,
	NEW.id_producto,
    NEW.id_regla,
    NEW.id_insumo,
    NEW.cantidad,
    NEW.fecha_reg,
    NEW.fecha_mod,
    NEW.usuario_reg,
    NEW.usuario_mod,
    NEW.estado,
	NOW());
    END;
$$

DELIMITER ;



DELIMITER $$
 CREATE  TRIGGER trgdetalle_produccioninsert
BEFORE insert ON detalle_produccion
FOR EACH ROW
BEGIN
   INSERT INTO detalle_produccion_historial
    VALUE (
	NEW.id_detalle_produccion,
    NEW.id_produccion,
    NEW.id_regla,
    NEW.id_producto,
    NEW.cantidad_insumos,
    NEW.cantidad_produccion,
    NEW.fecha_reg,
    NEW.estado,
	NOW());
    END;
$$

DELIMITER ;

DELIMITER $$
 CREATE  TRIGGER trgdetalle_produccionupdate
BEFORE update ON detalle_produccion
FOR EACH ROW
BEGIN
   INSERT INTO detalle_produccion_historial
    VALUE (
	NEW.id_detalle_produccion,
    NEW.id_produccion,
    NEW.id_regla,
    NEW.id_producto,
    NEW.cantidad_insumos,
    NEW.cantidad_produccion,
    NEW.fecha_reg,
    NEW.estado,
	NOW());
    END;
$$

DELIMITER ;


DELIMITER $$
 CREATE  TRIGGER trgdetalle_comprobante_ventainsert
BEFORE insert ON detalle_comprobante_venta
FOR EACH ROW
BEGIN
   INSERT INTO detalle_comprobante_venta_historial
    VALUE (
	NEW.id_detalle_comprobante_venta,
    NEW.numero_detalle,
    NEW.numero_comprobante,
    NEW.id_producto,
    NEW.cantidad,
    NEW.precio,
    NEW.id_usuario,
    NEW.fecha_reg,
    NEW.estado,
    NEW.id_comprobante,
	NOW());
    END;
$$

DELIMITER ;


DELIMITER $$
 CREATE  TRIGGER trgdetalle_comprobante_ventaupdate
BEFORE update ON detalle_comprobante_venta
FOR EACH ROW
BEGIN
   INSERT INTO detalle_comprobante_venta_historial
    VALUE (
	NEW.id_detalle_comprobante_venta,
    NEW.numero_detalle,
    NEW.numero_comprobante,
    NEW.id_producto,
    NEW.cantidad,
    NEW.precio,
    NEW.id_usuario,
    NEW.fecha_reg,
    NEW.estado,
    NEW.id_comprobante,
	NOW());
    END;
$$
DELIMITER ;

DELIMITER $$
 CREATE  TRIGGER trgdescuento_produccionupdate
BEFORE update ON descuento_produccion 
FOR EACH ROW
BEGIN
   INSERT INTO descuento_produccion_historial
    VALUE (
    NEW.id_produccion,
    NEW.id_insumo,
    NEW.requerimiento,
    NEW.existencias,
    NEW.inventario,
    NEW.fecha_reg,
NOW());
END;
$$

DELIMITER ;
DELIMITER $$
 CREATE  TRIGGER trgdescuento_produccioninsert
BEFORE insert ON descuento_produccion 
FOR EACH ROW
BEGIN
   INSERT INTO descuento_produccion_historial
    VALUE (
    NEW.id_produccion,
    NEW.id_insumo,
    NEW.requerimiento,
    NEW.existencias,
    NEW.inventario,
    NEW.fecha_reg,
NOW());
END;
$$

DELIMITER ;


DELIMITER $$
 CREATE  TRIGGER trgcomprobante_ventaupdate
BEFORE update ON comprobante_venta 
FOR EACH ROW
BEGIN
   INSERT INTO comprobante_venta_historial
    VALUE (
    NEW.id_comprobante,
    NEW.numero_comprobante,
    NEW.tipo,
    NEW.fecha,
    NEW.id_cliente,
    NEW.estado,
    NEW.id_usuario,
    NEW.fecha_reg,
    NEW.total,
    NEW.igv,
    NEW.neto,
    NEW.items,
NOW());
END;
$$

DELIMITER ;

DELIMITER $$
 CREATE  TRIGGER trgcomprobante_ventainsert
BEFORE insert ON comprobante_venta 
FOR EACH ROW
BEGIN
   INSERT INTO comprobante_venta_historial
    VALUE (
    NEW.id_comprobante,
    NEW.numero_comprobante,
    NEW.tipo,
    NEW.fecha,
    NEW.id_cliente,
    NEW.estado,
    NEW.id_usuario,
    NEW.fecha_reg,
    NEW.total,
    NEW.igv,
    NEW.neto,
    NEW.items,
NOW());
END;
$$

DELIMITER ;


DELIMITER $$
 CREATE  TRIGGER trgdetalle_comprobante_compraupdate
BEFORE update ON detalle_comprobante_compra 
FOR EACH ROW
BEGIN
   INSERT INTO detalle_comprobante_compra_historial
    VALUE (
NEW.id_detalle_comprobante_compra,
    NEW.numero_detalle,
    NEW.numero_comprobante,
    NEW.id_producto,
    NEW.cantidad,
    NEW.subtotal,
    NEW.id_usuario,
    NEW.fecha_reg,
    NEW.estado,
    NEW.id_comprobante,
    NOW());
END;
$$

DELIMITER ;

DELIMITER $$
 CREATE  TRIGGER trgdetalle_comprobante_comprainsert
BEFORE insert ON detalle_comprobante_compra 
FOR EACH ROW
BEGIN
   INSERT INTO detalle_comprobante_compra_historial
    VALUE (
NEW.id_detalle_comprobante_compra,
    NEW.numero_detalle,
    NEW.numero_comprobante,
    NEW.id_producto,
    NEW.cantidad,
    NEW.subtotal,
    NEW.id_usuario,
    NEW.fecha_reg,
    NEW.estado,
    NEW.id_comprobante,
    NOW());
END;
$$

DELIMITER ;



DELIMITER $$
 CREATE  TRIGGER trgcomprobante_compraupdate
BEFORE update ON comprobante_compra 
FOR EACH ROW
BEGIN
   INSERT INTO comprobante_compra_historial
    VALUE (
    NEW.id_comprobante,
    NEW.numero_comprobante,
    NEW.tipo,
    NEW.fecha,
    NEW.id_proveedor,
    NEW.estado,
    NEW.id_usuario,
    NEW.fecha_reg,
    NEW.total,
    NEW.igv,
    NEW.neto,
    NEW.items,
NOW());
END;
$$

DELIMITER ;

DELIMITER $$
 CREATE  TRIGGER trgcomprobante_comprainsert
BEFORE insert ON comprobante_compra 
FOR EACH ROW
BEGIN
   INSERT INTO comprobante_compra_historial
    VALUE (
    NEW.id_comprobante,
    NEW.numero_comprobante,
    NEW.tipo,
    NEW.fecha,
    NEW.id_proveedor,
    NEW.estado,
    NEW.id_usuario,
    NEW.fecha_reg,
    NEW.total,
    NEW.igv,
    NEW.neto,
    NEW.items,
NOW());
END;
$$

DELIMITER ;