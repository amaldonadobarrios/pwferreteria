SELECT *
FROM producto
where existencia is not null and existencia>0 and pv1 is not null and pv2 is not null and pv3 is not null
and pv1>0 and pv2>0 and pv3>0 and estado='A';