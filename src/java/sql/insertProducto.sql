INSERT INTO `producto`
(`descripcion`,
`marca`,
`presentacion`,
`medida`,
`foto`,
`estado`,
`fecha_reg`,
`fecha_mod`,
`usuario_reg`,
`usuario_mod`,
`type`,
`producto_insumo`)
VALUES
(?,
?,
?,
?,
?,
'A',
sysdate(),
null,
?,
null,
?,
?);

