UPDATE `producto` 
SET 
`descripcion`=?,
`marca`=?,
`presentacion`=?,
`medida`=?,
`producto_insumo`=?,
`fecha_mod`=sysdate(),
`usuario_mod`=?
 WHERE `id_producto`=?