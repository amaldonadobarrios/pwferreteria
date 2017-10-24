UPDATE `producto` 
SET 
`descripcion`=?,
`marca`=?,
`presentacion`=?,
`medida`=?,
`producto_insumo`=?,
`fecha_mod`=sysdate(),
`usuario_mod`=?,
`foto`=?,
`type`=?
 WHERE `id_producto`=?