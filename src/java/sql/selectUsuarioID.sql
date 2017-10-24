SELECT * FROM usuario u
left join perfil p on u.perfil_idperfil=p.id_perfil
 WHERE id_usuario=?;