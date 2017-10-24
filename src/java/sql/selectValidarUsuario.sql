SELECT * FROM usuario u 
left join perfil p on u.perfil_idperfil=p.id_perfil
WHERE u.usuario=? and u.password=?
limit 1;
