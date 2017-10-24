select * from 
(SELECT `producto`.`id_producto`,
    `producto`.`descripcion`,
    `producto`.`marca`,
    `producto`.`presentacion`,
    `producto`.`medida`,
    `producto`.`producto_insumo`,
    `producto`.`foto`
FROM `producto` where   producto_insumo='PRODUCTO' OR producto_insumo='PRODUCTO E INSUMO' and estado='A') a
where  a.descripcion like concat('%', ?, '%')
or a.marca like concat('%', ?, '%')
or a.presentacion like concat('%', ?, '%')
or a.medida like concat('%', ?, '%');