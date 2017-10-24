SELECT `producto`.`id_producto`,
    `producto`.`descripcion`,
    `producto`.`marca`,
    `producto`.`presentacion`,
    `producto`.`medida`,
    `producto`.`producto_insumo`,
    `producto`.`existencia`
FROM `producto` where estado='A' and producto_insumo='INSUMO' OR producto_insumo='PRODUCTO E INSUMO' ;