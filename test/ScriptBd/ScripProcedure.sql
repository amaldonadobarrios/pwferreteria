DELIMITER $$
CREATE  PROCEDURE `EliminarCompra`(
in id int,
out rpta int
)
BEGIN
DECLARE fin INTEGER DEFAULT 0;
DECLARE v_id_detalle_compra int;
DECLARE id_prod int;
DECLARE cant DOUBLE default 0;
DECLARE v_existencia double default 0;
DECLARE cur1 CURSOR FOR SELECT id_detalle_comprobante_compra,id_producto,cantidad FROM detalle_comprobante_compra where id_comprobante=id and estado='COMPRADO';
DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin=1;
/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLWARNING 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Inicia transaccion*/ 
START TRANSACTION; 
OPEN cur1;
 read_loop: LOOP
    FETCH cur1 INTO v_id_detalle_compra,id_prod, cant;
   IF fin = 1 THEN
       LEAVE read_loop;
    END IF;
	SET v_existencia= (select existencia from producto where id_producto=id_prod);
	UPDATE producto SET existencia =v_existencia-cant  WHERE id_producto=id_prod;
    UPDATE detalle_comprobante_compra SET estado ='ELIMINADO'  WHERE id_detalle_comprobante_compra=v_id_detalle_compra;
  END LOOP;
 CLOSE cur1;
 UPDATE comprobante_compra set estado='ELIMINADO' where id_comprobante=id;
/*Fin de transaccion*/ 
COMMIT; 
/*Mandamos 1 si todo salio bien*/ 
set rpta =1;

END$$
DELIMITER ;

DELIMITER $$
CREATE  PROCEDURE `EliminarProduccion`(
in id int,
out rpta int
)
BEGIN
DECLARE fin INTEGER DEFAULT 0;
DECLARE finx INTEGER DEFAULT 0;
DECLARE v_id_produccion int;
DECLARE id_prod int;
DECLARE cant double default 0;
DECLARE v_id_produ int;
DECLARE id_produc int;
DECLARE canti double default 0;
DECLARE v_existencia double default 0;
DECLARE v_existenciainsumo double default 0;
DECLARE cur_produccion CURSOR FOR SELECT id_produccion ,id_producto,cantidad_produccion FROM detalle_produccion where id_produccion=id and estado=1;
DECLARE cur_descuento CURSOR FOR SELECT id_produccion, id_insumo, requerimiento FROM descuento_produccion where  id_produccion=id;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin=1;
/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLWARNING 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Inicia transaccion*/ 
START TRANSACTION; 
OPEN cur_descuento;
 read_loop: LOOP
    FETCH cur_descuento INTO v_id_produ,id_produc, canti;
   IF fin = 1 THEN
       LEAVE read_loop;
    END IF;
	SET v_existenciainsumo= (select existencia from producto where id_producto=id_produc);
	UPDATE producto SET existencia =v_existenciainsumo+canti  WHERE id_producto=id_produc;
  END LOOP;
 CLOSE cur_descuento;
 
 set fin=0;
OPEN cur_produccion;
 read_loop: LOOP
    FETCH cur_produccion INTO v_id_produccion,id_prod, cant;
   IF fin = 1 THEN
       LEAVE read_loop;
    END IF;
	SET v_existencia= (select existencia from producto where id_producto=id_prod);
	UPDATE producto SET existencia =v_existencia-cant  WHERE id_producto=id_prod;
    UPDATE detalle_produccion SET estado =0  WHERE id_produccion=v_id_produccion;
  END LOOP;
 CLOSE cur_produccion;
 UPDATE produccion set estado=0 where id_produccion=id;
/*Fin de transaccion*/ 
COMMIT; 
/*Mandamos 1 si todo salio bien*/ 
set rpta =1;

END$$
DELIMITER ;

DELIMITER $$
CREATE  PROCEDURE `EliminarRegla`(
in idregla int,
in idproducto int,
out rpta int
)
BEGIN

/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLWARNING 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Inicia transaccion*/ 
START TRANSACTION; 
 UPDATE regla_produccion set estado=0 where id_regla=idregla and id_producto=idproducto;
 UPDATE detalle_regla_produccion set estado=0 where id_regla=idregla and id_producto=idproducto;
/*Fin de transaccion*/ 
COMMIT; 
/*Mandamos 1 si todo salio bien*/ 
set rpta =1;

END$$
DELIMITER ;

DELIMITER $$
CREATE  PROCEDURE `EliminarVenta`(
in id int,
out rpta int
)
BEGIN
DECLARE fin INTEGER DEFAULT 0;
DECLARE v_id_detalle_venta int;
DECLARE id_prod int;
DECLARE cant double default 0;
DECLARE v_existencia double default 0;
DECLARE cur1 CURSOR FOR SELECT id_detalle_comprobante_venta,id_producto,cantidad FROM detalle_comprobante_venta where id_comprobante=id and estado='VENDIDO';
DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin=1;
/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLWARNING 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Inicia transaccion*/ 
START TRANSACTION; 
OPEN cur1;
 read_loop: LOOP
    FETCH cur1 INTO v_id_detalle_venta,id_prod, cant;
   IF fin = 1 THEN
       LEAVE read_loop;
    END IF;
	SET v_existencia= (select existencia from producto where id_producto=id_prod);
	UPDATE producto SET existencia =v_existencia+cant  WHERE id_producto=id_prod;
    UPDATE detalle_comprobante_venta SET estado ='ELIMINADO'  WHERE id_detalle_comprobante_venta=v_id_detalle_venta;
  END LOOP;
 CLOSE cur1;
 UPDATE comprobante_venta set estado='ELIMINADO' where id_comprobante=id;
/*Fin de transaccion*/ 
COMMIT; 
/*Mandamos 1 si todo salio bien*/ 
set rpta =1;

END$$
DELIMITER ;

DELIMITER $$
CREATE  PROCEDURE `ganancia_anual`(
in fecha1 date,
in fecha2 date)
BEGIN
select l.FECHA, (l.totalpormes-m.totalpormes) as ganancia_anual 
from 
(select  DATE_FORMAT(a.fecha, "%Y") AS FECHA, a.estado, SUM(a.total) as totalpormes from comprobante_venta a where  a.fecha BETWEEN fecha1 AND fecha2 and a.estado='VENDIDO' group by DATE_FORMAT(a.fecha, "%Y")) l ,
(select  DATE_FORMAT(b.fecha, "%Y") AS FECHA, b.estado, SUM(b.total) as totalpormes from comprobante_compra b where  b.fecha BETWEEN fecha1 AND fecha2 and b.estado='COMPRADO'group by DATE_FORMAT(b.fecha, "%Y")) m
where l.FECHA=m.FECHA;
END$$
DELIMITER ;

DELIMITER $$
CREATE  PROCEDURE `ganancia_mensual`(
in fecha1 date,
in fecha2 date
)
BEGIN
select l.FECHA, (l.totalpormes-m.totalpormes) as ganancia_mensual from
(select  DATE_FORMAT(z.fecha, "%m-%Y") AS FECHA, z.estado, SUM(z.total) as totalpormes
from 
(select a.fecha, a.total, a.estado from comprobante_venta a where  a.fecha BETWEEN fecha1 AND fecha2 and a.estado='VENDIDO'
union select b.fecha,b.total,b.estado from comprobante_compra b where  b.fecha BETWEEN fecha1 AND fecha2   and b.estado='COMPRADO') z
where z.estado='VENDIDO' group by DATE_FORMAT(z.fecha, "%m-%Y"),z.estado order by z.fecha asc) l ,(select  DATE_FORMAT(z.fecha, "%m-%Y") AS FECHA, z.estado, SUM(z.total) as totalpormes
from 
(select a.fecha, a.total, a.estado from comprobante_venta a where  a.fecha BETWEEN fecha1 AND fecha2 and a.estado='VENDIDO'
union select b.fecha,b.total,b.estado from comprobante_compra b where  b.fecha BETWEEN fecha1 AND fecha2 and b.estado='COMPRADO') z
 where z.estado='COMPRADO' group by DATE_FORMAT(z.fecha, "%m-%Y"),z.estado order by z.fecha asc) m
 where l.FECHA=m.FECHA;
 END$$
DELIMITER ;

DELIMITER $$
CREATE  PROCEDURE `GrabarCompra`(in numero varchar(45),
in productox varchar(2000), 
in cantidadx varchar(2000),
in subtotalX varchar(2000),
in usuario int,
in tipocomprobante varchar(45),
in proveedor int,
in registros int,
in total DOUBLE,
in igv DOUBLE,
in neto DOUBLE,
in fecha date,
out rpta int,
out id_compra int)
BEGIN
DECLARE v1 INT DEFAULT 1;
DECLARE prod varchar(45) DEFAULT 0;
DECLARE sub  double  DEFAULT 0;
DECLARE cant  double DEFAULT 0;
DECLARE v_id_comprobante int;
DECLARE v_existenciax DOUBLE default 0;

/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
BEGIN 
set rpta =0;
set id_compra=0;
ROLLBACK; 
END; 

/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLWARNING 
BEGIN 
set rpta =0;
set id_compra=0;
ROLLBACK; 
END; 

/*Inicia transaccion*/ 
START TRANSACTION; 
/*Primer INSERT datos ACTA*/ 
INSERT INTO comprobante_compra (numero_comprobante,tipo,fecha,id_proveedor,estado,id_usuario,fecha_reg,total,igv,neto,items) VALUES(numero,tipocomprobante,fecha,proveedor,'COMPRADO',usuario,now(),total,igv, neto,registros);
SET v_id_comprobante =(SELECT LAST_INSERT_ID());
/*SECOND INSERT datos ACTA*/ 
WHILE v1 <= registros DO
SET prod = ltrim(replace(substring(substring_index(productox, '@', v1), length(substring_index(productox, '@', v1 - 1)) + 1), '@', '')) ; 
SET sub =ltrim(replace(substring(substring_index(subtotalX, '@', v1), length(substring_index(subtotalX, '@', v1 - 1)) + 1), '@', ''))  ;

SET cant =ltrim(replace(substring(substring_index(cantidadx, '@', v1), length(substring_index(cantidadx, '@', v1 - 1)) + 1), '@', '')) ;
SET v_existenciax = (SELECT  existencia FROM producto where  id_producto = prod);
if (v_existenciax is null) then
set v_existenciax=0;
end if;
UPDATE producto SET existencia = (v_existenciax +cant), fecha_mod = now(), usuario_mod = usuario WHERE id_producto = prod;
INSERT INTO detalle_comprobante_compra(numero_detalle,numero_comprobante,id_producto,cantidad,subtotal,id_usuario,fecha_reg,estado,id_comprobante)VALUES(v1,numero,prod,cant, sub,usuario,now(),'COMPRADO',v_id_comprobante);    
    SET v1 = v1+1;
  END WHILE;

/*Fin de transaccion*/ 
COMMIT; 
/*Mandamos 0 si todo salio bien*/ 
set rpta =1;
set id_compra=v_id_comprobante;
END$$
DELIMITER ;

DELIMITER $$
CREATE  PROCEDURE `GrabarProduccion`(
in cantidadreglas int,
in fecha_doc date,
in documento varchar(45),
in numero_doc varchar(45),
in idusuario int, 
in cadena_cant_insumos varchar(2000),
in cadena_id_regla varchar(2000),
in cadena_id_producto varchar(2000),
in cadena_cantidad_produccion varchar(2000),
out rpta int,
out veristock int,
out idproduccion int,
out requerimientos varchar(1000)
)
BEGIN
DECLARE cur_json varchar(1000);
DECLARE cur_json2 varchar(1000) default "";
DECLARE v1 INT DEFAULT 1;
DECLARE idregla int DEFAULT 0;
DECLARE cantinsumo int DEFAULT 0;
DECLARE idproducto int DEFAULT 0;
DECLARE cantproduccion DOUBLE DEFAULT 0;
DECLARE v_id_produccion int;

DECLARE _START INTEGER default 0;
DECLARE _LIMIT INTEGER default 1;
DECLARE _SIZEREQ INTEGER default 0;

/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLWARNING 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Inicia transaccion*/ 
START TRANSACTION; 
/*Primer INSERT datos ACTA*/ 
CREATE TEMPORARY TABLE REQ_INSUMOS (id_insumo int, requerimiento double,existencias double);
CREATE TEMPORARY TABLE RESUMEN_REQ_INSUMOS (id_insumo int, requerimiento double,existencias double);
INSERT INTO produccion (fecha_reg,fecha,usuario_reg,doc,numero,cantidad_reglas,estado)VALUES(now(),fecha_doc,idusuario,documento,numero_doc,cantidadreglas,1);
SET v_id_produccion =(SELECT LAST_INSERT_ID());
set idproduccion=v_id_produccion;
WHILE v1 <= cantidadreglas DO
SET idregla =ltrim(replace(substring(substring_index(cadena_id_regla, '@', v1), length(substring_index(cadena_id_regla, '@', v1 - 1)) + 1), '@', '')) ;
SET idproducto =ltrim(replace(substring(substring_index(cadena_id_producto, '@', v1), length(substring_index(cadena_id_producto, '@', v1 - 1)) + 1), '@', '')) ;
SET cantproduccion =ltrim(replace(substring(substring_index(cadena_cantidad_produccion, '@', v1), length(substring_index(cadena_cantidad_produccion, '@', v1 - 1)) + 1), '@', '')) ;
SET cantinsumo =ltrim(replace(substring(substring_index(cadena_cant_insumos, '@', v1), length(substring_index(cadena_cant_insumos, '@', v1 - 1)) + 1), '@', '')) ;
INSERT INTO  REQ_INSUMOS (select a.id_insumo,(a.cantidad *cantproduccion),b.existencia from detalle_regla_produccion a, producto b where a.id_regla=idregla and a.id_producto=idproducto and a.estado=1 and a.id_insumo=b.id_producto);
SET v1 = v1+1;
END WHILE;
INSERT INTO  RESUMEN_REQ_INSUMOS (SELECT id_insumo,sum(requerimiento), existencias from REQ_INSUMOS group by id_insumo);
set veristock=(select count(id_insumo) from RESUMEN_REQ_INSUMOS where existencias<requerimiento);
set _SIZEREQ=(select count(id_insumo) from RESUMEN_REQ_INSUMOS);
SET _LIMIT=1;
WHILE _START < veristock DO
Set cur_json=(select concat('El insumo : ',b.descripcion,' ',b.marca,' requiere de : ',a.requerimiento,' ',b.medida,' Pero existe en inventario: ',a.existencias,' ',b.medida,'\n') from RESUMEN_REQ_INSUMOS a, producto b where a.existencias<a.requerimiento and a.id_insumo=b.id_producto limit _START,_LIMIT);
set cur_json2= concat(cur_json2,cur_json);
SET _START = _START+1;
SET _LIMIT=_LIMIT+1;
END WHILE;
set requerimientos=cur_json2;
if(veristock>0)
then rollback;
end if;

UPDATE producto INNER JOIN RESUMEN_REQ_INSUMOS on producto.id_producto=RESUMEN_REQ_INSUMOS.id_insumo SET producto.existencia = producto.existencia - RESUMEN_REQ_INSUMOS.requerimiento, producto.fecha_mod = now(), producto.usuario_mod = idusuario;

SET v1 = 1;
WHILE v1 <= cantidadreglas DO
SET idregla =ltrim(replace(substring(substring_index(cadena_id_regla, '@', v1), length(substring_index(cadena_id_regla, '@', v1 - 1)) + 1), '@', '')) ;
SET cantinsumo =ltrim(replace(substring(substring_index(cadena_cant_insumos, '@', v1), length(substring_index(cadena_cant_insumos, '@', v1 - 1)) + 1), '@', '')) ;
SET idproducto =ltrim(replace(substring(substring_index(cadena_id_producto, '@', v1), length(substring_index(cadena_id_producto, '@', v1 - 1)) + 1), '@', '')) ;
SET cantproduccion =ltrim(replace(substring(substring_index(cadena_cantidad_produccion, '@', v1), length(substring_index(cadena_cantidad_produccion, '@', v1 - 1)) + 1), '@', '')) ;
UPDATE producto set existencia = existencia + cantproduccion, fecha_mod = now(), usuario_mod = idusuario WHERE id_producto=idproducto;
INSERT INTO detalle_produccion (id_produccion,id_regla,id_producto,cantidad_insumos,cantidad_produccion,fecha_reg,estado)
VALUES (v_id_produccion,idregla,idproducto,cantinsumo,cantproduccion,now(),1);
SET v1 = v1+1;
END WHILE;
INSERT INTO descuento_produccion (SELECT v_id_produccion as id_produccion ,id_insumo,requerimiento, existencias  ,(existencias-requerimiento) as inventario,now() as fecha_reg from RESUMEN_REQ_INSUMOS);
/*Fin de transaccion*/ 
COMMIT; 
/*Mandamos 0 si todo salio bien*/ 
set rpta =1;
END$$
DELIMITER ;

DELIMITER $$
CREATE  PROCEDURE `GrabarRegla`(
in idproducto int,
in idusuario int, 
in nroInsumos int,
in cadena_id_insumo varchar(1000),
in cadena_cantidad varchar(2000),
out rpta int)
BEGIN
DECLARE v1 INT DEFAULT 1;
DECLARE insu int DEFAULT 0;
DECLARE cant VARCHAR(45) DEFAULT 0;
DECLARE v_id_regla int;

/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLWARNING 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Inicia transaccion*/ 
START TRANSACTION; 
/*Primer INSERT datos ACTA*/ 
INSERT INTO regla_produccion (id_producto,cantidad_insumo,fecha_reg,usuario_reg,estado)
VALUES
(idproducto,nroInsumos,now(),idusuario,1);
SET v_id_regla =(SELECT LAST_INSERT_ID());
/*SECOND INSERT datos ACTA*/ 
WHILE v1 <= nroInsumos DO
SET insu =ltrim(replace(substring(substring_index(cadena_id_insumo, '@', v1), length(substring_index(cadena_id_insumo, '@', v1 - 1)) + 1), '@', '')) ;
SET cant =ltrim(replace(substring(substring_index(cadena_cantidad, '@', v1), length(substring_index(cadena_cantidad, '@', v1 - 1)) + 1), '@', '')) ;
INSERT INTO detalle_regla_produccion(id_producto,id_regla,id_insumo,cantidad,fecha_reg,usuario_reg,estado)
VALUES
(idproducto,v_id_regla,insu,cant,now(),idusuario,1);
SET v1 = v1+1;
END WHILE;
/*Fin de transaccion*/ 
COMMIT; 
/*Mandamos 0 si todo salio bien*/ 
set rpta =1;
END$$
DELIMITER ;

DELIMITER $$
CREATE  PROCEDURE `GrabarVenta`(
in numero varchar(45),
in productox varchar(2000), 
in cantidadx varchar(2000),
in preciox varchar(2000),
in usuario int,
in tipocomprobante varchar(45),
in cliente int,
in registros int,
in total DOUBLE,
in igv DOUBLE,
in neto DOUBLE,
out rpta int)
BEGIN
DECLARE v1 INT DEFAULT 1;
DECLARE prod int DEFAULT 0;
DECLARE prec float DEFAULT 0;
DECLARE cant double DEFAULT 0;
DECLARE v_id_comprobante int;

/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Handler para error SQL*/ 
DECLARE EXIT HANDLER FOR SQLWARNING 
BEGIN 
set rpta =0;
ROLLBACK; 
END; 

/*Inicia transaccion*/ 
START TRANSACTION; 
/*Primer INSERT datos ACTA*/ 
INSERT INTO comprobante_venta (numero_comprobante,tipo,fecha,id_cliente,estado,id_usuario,fecha_reg,total,igv,neto,items) VALUES(numero,tipocomprobante,now(),cliente,'VENDIDO',usuario,now(),total,igv, neto,registros);
SET v_id_comprobante =(SELECT LAST_INSERT_ID());
/*SECOND INSERT datos ACTA*/ 
WHILE v1 <= registros DO
set prod= ltrim(replace(substring(substring_index(productox, '@', v1), length(substring_index(productox, '@', v1 - 1)) + 1), '@', '')) ;
SET prec =ltrim(replace(substring(substring_index(preciox, '@', v1), length(substring_index(preciox, '@', v1 - 1)) + 1), '@', '')) ;
SET cant =ltrim(replace(substring(substring_index(cantidadx, '@', v1), length(substring_index(cantidadx, '@', v1 - 1)) + 1), '@', '')) ;
UPDATE producto SET existencia = (existencia -cant), fecha_mod = now(), usuario_mod = usuario WHERE id_producto = prod;
INSERT INTO detalle_comprobante_venta(numero_detalle,numero_comprobante,id_producto,cantidad,precio,id_usuario,fecha_reg,estado,id_comprobante)VALUES(v1,numero,prod,cant, prec,usuario,now(),'VENDIDO',v_id_comprobante);    
    SET v1 = v1+1;
  END WHILE;
/*Fin de transaccion*/ 
COMMIT; 
/*Mandamos 0 si todo salio bien*/ 
set rpta =1;
END$$
DELIMITER ;

DELIMITER $$
CREATE  PROCEDURE `select_producto`()
BEGIN
SELECT * FROM producto;
END$$
DELIMITER ;
