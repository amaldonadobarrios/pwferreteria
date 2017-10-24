UPDATE `usuario` SET 
`usuario`=?,
`password`=?,
`dni`=?,
`apellido_paterno`=?,
`apellido_materno`=?,
`nombres`=?,
`telefono`=?,
`estado`=?,
`fecha_mod`=sysdate(),
`usuario_mod`=?,
`perfil_idperfil`=?
 WHERE `id_usuario`=?