SELECT a.id_regla,a.id_producto,a.cantidad_insumo,b.descripcion, b.marca,b.presentacion,b.medida
FROM regla_produccion a, producto b where a.id_producto=b.id_producto and a.estado=1;
