UPDATE `cliente` 
SET 
`naturaleza_cliente`=?,
`id_tipo_cliente`=?,
`dni_ruc`=?,
`razon_social`=?,
`nombres`=?,
`apellido_paterno`=?,
`apellido_materno`=?,
`telefono`=?,
`direccion`=?,
`email`=?,
`usuario_mod`=?,
`fecha_mod`=sysdate()
 WHERE `id_cliente`=?