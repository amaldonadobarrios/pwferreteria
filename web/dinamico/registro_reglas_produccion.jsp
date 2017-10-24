<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<input type="hidden" id="contexto" value="${context}">
<script>
    function redireccionarPagina() {
        window.location = "SMenu?action=pageRegistroReglasProduccion";
    }
    //------------------------REGLA
    function fn_pintarMostrarInsumos(response) {
        
        if (response === 'NOSESION') {
            mensaje('ERROR', 'SESION EXPIRADA');
            location.href = "login.jsp";
        } else {
            var v_resultado = response + "";
            var respuesta = v_resultado.split('%');
            var tabla = respuesta[0];
            var id_regla = respuesta[1];
           
            if (tabla !== '') {
                $('#nroregla').text('INSUMOS DE LA REGLA N° '+id_regla);
                $('#tablamostrarInsumos').html(tabla);
                $('#dynamic-table3').DataTable({
                    responsive: true
                });
                $('#dynamic-table3').stacktable();
                $('#modal').modal('show');
            }
        }
    }

    function fn_ListarInsumosxReglaxProd(jdatos) {
        var vruta = '/ServProduccion';
        var vevento = 'ListarInsumosxReglaxProd';
        var jqdata = jdatos;
        fnEjecutarPeticion(vruta, jqdata, vevento);
    }
    function fn_mostrarinsumos(idregla, idprod) {
        jdatos = {
            evento: 'ListarInsumosxReglaxProd',
            id_regla: idregla,
            id_producto: idprod
        };
        fn_ListarInsumosxReglaxProd(jdatos);
    }

    function fn_pintarEliminarRegla(response) {
        if (response == 'NOSESION') {
            mensaje('ERROR', 'SESION EXPIRADA');
            location.href = "login.jsp";
        } else {
            var v_resultado = response + "";
            if (v_resultado == 'NOK') {
                mensajeERROR('ERROR', 'REGLA NO PUDO SER ELIMINADA');
            } else if (v_resultado == 'OK') {
                mensajeOK('CORRECTO', 'REGLA ELIMINADA CON EXITO');
                setTimeout("redireccionarPagina()", 1000);
            }
        }
    }

    function fn_EliminarRegla(jdatos) {
        var vruta = '/ServProduccion';
        var vevento = 'EliminarRegla';
        var jqdata = jdatos;
        fnEjecutarPeticion(vruta, jqdata, vevento);
    }
    function fn_eliminarregla(id_regla, id_producto) {
        if (confirm("ESTÁ SEGURO DE ELIMINAR LA REGLA N°" + id_regla)) {
            jdatos = {
                evento: 'EliminarRegla',
                id_regla: id_regla,
                id_producto: id_producto
            };
            fn_EliminarRegla(jdatos);
        }
    }
    function fn_pintarResultadoRegistrarRegla(response) {
        if (response === 'NOSESION') {
            mensaje('ERROR', 'SESION EXPIRADA');
            location.href = "login.jsp";
        } else {
            var v_resultado = response + "";
            var respuesta = v_resultado.split('%');
            var estado = respuesta[0];
            var mensaje = respuesta[1];
            if (estado == 'ERROR') {
                mensajeERROR(estado, mensaje);
            } else if (estado == 'OK') {
                mensajeOK('CORRECTO', mensaje);
                setTimeout("redireccionarPagina()", 1000);
            }
        }
    }
    function fn_EjecutaRegistrarRegla(prod) {
        var vruta = '/ServProduccion';
        var vevento = 'RegistrarRegla';
        var jqdata = {
            evento: 'RegistrarRegla',
            id_producto: prod
        };
        fnEjecutarPeticion(vruta, jqdata, vevento);
    }

    function fn_registrarRegla() {
        var prod = $("#txtidproducto").val();
        if (prod != '') {
            fn_EjecutaRegistrarRegla(prod);
        } else {
            mensajeERROR("FALTA PRODUCTO A FABRICAR", 'No se ha seleccionado el producto a fabricar');
        }
    }








    //-----------------------INSUMO
    function fneliminarItem(item) {
        var it = Number(item - 1);
        var vruta = '/ServProduccion';
        var vevento = 'EliminarInsumoAJAX';
        var jqdata = {
            evento: 'EliminarInsumoAJAX',
            item: it
        };
        fnEjecutarPeticion(vruta, jqdata, vevento);
    }

    function fn_pintarlistaInsumos(response) {
        if (response === 'NOSESION') {
            mensaje('ERROR', 'SESION EXPIRADA');
            location.href = "login.jsp";
        } else {
            var v_resultado = response + "";
            var respuesta = v_resultado.split('%');
            var estado = respuesta[0];
            var tabla = respuesta[1];
            if (estado == 'ERROR') {
                mensajeERROR(estado, tabla);
            } else {
                $('#tablainsumos').html(tabla);
                $('#dynamic-table2').DataTable({
                    responsive: true
                });
                $('#dynamic-table2').stacktable();
            }
        }
    }

    function fn_ejecutarAñadirInsumo(jdatos) {
        var vruta = '/ServProduccion';
        var vevento = 'AñadirInsumo';
        fnEjecutarPeticion(vruta, jdatos, vevento);

    }
    function fn_añadirInsumo(a, b) {
        $('#lblcbxinsumo').css("color", "black");
        $('#lblcantidadinsumo').css("color", "black");
        $('#lblnombre').css("color", "black");
        $('#lblmarca').css("color", "black");
        $('#lblpresentacion').css("color", "black");
        $('#lblmedida').css("color", "black");
        if (a != '' && a != '0' && b != '') {
            var prod = $("#txtidproducto").val();
            if (prod != '') {
                if (b != prod) {
                    var jdatos = {
                        evento: 'AñadirInsumo',
                        id_prod: prod,
                        id_insumo: b,
                        cantidad_insumo: a
                    };
                    fn_ejecutarAñadirInsumo(jdatos);
                } else {
                    mensajeERROR('INSUMO IGUAL AL PRODUCTO FINAL', 'El insumo seleccionado es igual al producto a fabricar');
                }
            } else {
                $('#lblnombre').css("color", "red");
                $('#lblmarca').css("color", "red");
                $('#lblpresentacion').css("color", "red");
                $('#lblmedida').css("color", "red");
            }
        } else {
            $('#lblcbxinsumo').css("color", "red");
            $('#lblcantidadinsumo').css("color", "red");
        }
    }







    ///-----------------------PRODUCTO FINAL
    function limpiarmodalprod() {
        $("#txtparametroProducto").val('');
        $('#tablaProductosFinales').html('');
    }


    function fn_pintar_producto(a, b, c, d, e, f) {
        $("#txtidproducto").val(a);
        $("#txtproducto").val(b);
        $("#txtmarca").val(c);
        $("#txtpresentacion").val(d);
        $("#txtmedida").val(e);
        $('#lblnombre').css("color", "black");
        $('#lblmarca').css("color", "black");
        $('#lblpresentacion').css("color", "black");
        $('#lblmedida').css("color", "black");
        if (f == null || f == 'null' || f == '') {
            var contexto = document.getElementById("contexto").value;
            document.getElementById("image").src = contexto + "/assets/images/sinfoto.png";
        } else {
            document.getElementById("image").src = "data:image/jpg;base64," + f;
        }
        $('#MD_BuscarProducto').modal('hide');
    }


    function fn_buscarProducto(parametro) {
        $('#lblparametro').css("color", "black");
        $('#tablaProductosFinales').html('');
        if (parametro == '') {
            $('#lblparametro').css("color", "red");
        } else {
            var vruta = '/ServProduccion';
            var vevento = 'BuscarProductoFinal';
            var jqdata = {
                evento: 'BuscarProductoFinal',
                parametro: parametro
            };
            fnEjecutarPeticion(vruta, jqdata, vevento);
        }
    }
    function fn_pintalistaProdFinal(response) {
        if (response == 'NOSESION') {
            mensaje('ERROR', 'SESION EXPIRADA');
            location.href = "login.jsp";
        } else {
            $('#tablaProductosFinales').html(response);
            $('#dynamic-table1').DataTable({
                responsive: true
            });
            $('#dynamic-table1').stacktable();
        }
    }
//CONTROLADOR AJAX

    function fnEjecutarPeticion(ruta, jdata, evento) {
        var contexto = document.getElementById('contexto').value;
        var vservlet = contexto + ruta;
        $.ajax({
            url: vservlet,
            method: 'POST',
            data: jdata,
            success: function (responseText) {
                fnControlEvento(evento, responseText + '');
            }
        });
    }
    function fnControlEvento(vevento, vvrespuesta) {
        if (vevento == 'BuscarProductoFinal') {
            fn_pintalistaProdFinal(vvrespuesta);
        } else if (vevento == 'AñadirInsumo') {
            fn_pintarlistaInsumos(vvrespuesta);
        } else if (vevento == 'EliminarInsumoAJAX') {
            fn_pintarlistaInsumos(vvrespuesta);
        } else if (vevento == 'RegistrarRegla') {
            fn_pintarResultadoRegistrarRegla(vvrespuesta);
        } else if (vevento == 'EliminarRegla') {
            fn_pintarEliminarRegla(vvrespuesta);
        } else if (vevento == 'ListarInsumosxReglaxProd') {
            fn_pintarMostrarInsumos(vvrespuesta);
        }

    }
    //-----------------MENSAJE EMERGENTE
    function mensajeERROR(titulo, mensaje) {
        swal({
            type: 'warning',
            title: titulo,
            text: mensaje,
            timer: 2000,
            showConfirmButton: false
        });
    }
    function mensajeOK(titulo, mensaje) {
        swal({
            type: 'success',
            title: titulo,
            text: mensaje,
            timer: 3000,
            showConfirmButton: false
        });
    }
</script>







<style>
    fieldset { 
        display: block;
        margin-left: 2px;
        margin-right: 2px;
        padding-top: 0.35em;
        padding-bottom: 0.625em;
        padding-left: 0.75em;
        padding-right: 0.75em;
        border: 2px groove (internal value);
    }
</style>
<div class="page-header">
    <h1>
        REGLAS DE PRODUCCIÒN
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Registro de Reglas de Producción
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="container-fluid">
        <div class="col-sm-12">
            <div class="col-sm-8">
                <fieldset>
                    <legend>Producto a Fabricar</legend>   
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblnombre"> Nombre Producto</label>
                        <div class="col-sm-9">
                            <input type="text" id="txtproducto" name="txtproducto"  class="col-xs-10 col-sm-9"  disabled/>
                            <input type="hidden" id="txtidproducto" name="txtidproducto"  class="col-xs-10 col-sm-9"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblmarca"> Marca del Producto</label>
                        <div class="col-sm-9">
                            <input type="text" id="txtmarca" name="txtmarca"  class="col-xs-10 col-sm-9" disabled />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblpresentacion"> Presentación</label>
                        <div class="col-sm-9">
                            <input type="text" id="txtpresentacion" name="txtpresentacion"  class="col-xs-10 col-sm-9" disabled/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblmedida"> Unidad de medida</label>
                        <div class="col-sm-9">
                            <input type="text" id="txtmedida" name="txtmedida"  class="col-xs-10 col-sm-9" disabled/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Cantidad minimo</label>
                        <div class="col-sm-9">
                            <input type="text" id="txtminimo" name="txtminimo"  value="1" class="col-xs-10 col-sm-9" disabled/>
                        </div>
                    </div>
                    <div  class="col-sm-12" align="center"> <input class="btn btn-default" type="button" onclick="limpiarmodalprod();
                            $('#MD_BuscarProducto').modal('show');" value="Seleccionar Producto"></input>   </div>
                </fieldset>
            </div>
            <div class="col-sm-4">
                <div  align="center" id="divfoto" >
                    <img src="assets/images/sinfoto.png" width="200px"  height="200px" class="img-thumbnail"
                         name="image" id="image" />
                </div>
            </div>
        </div>    

        <div class="col-sm-12 col-md-12 col-xs-12">
            <div class="col-sm-6 col-md-6 col-xs-6">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4 class="widget-title">Seleccionar Insumos</h4>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main no-padding">
                            <form>
                                <!-- <legend>Form</legend> -->
                                <fieldset>
                                    <div class="form-group">
                                        <label for="form-field-select-3" class="col-sm-3 col-md-3 col-xs-3 control-label no-padding-right" id="lblcbxinsumo">Seleccione Insumo</label>
                                        <div class="col-sm-9 col-md-9 col-xs-9" >
                                            <select class="chosen-select form-control" id="cbxinsumo" data-placeholder="Choose a State..." class="col-xs-10 col-sm-5" onchange="document.getElementById('txtcantidad_insumo').value = '';" >
                                                <option value=""selected>Seleccione</option>
                                                <c:forEach var="insumo" items="${lista_insumos}" varStatus="loop">
                                                    <option value="${insumo.id_producto}">${insumo.descripcion} ${insumo.marca} ${insumo.presentacion}  en : ${insumo.medida}</option>
                                                </c:forEach>
                                            </select>

                                        </div>
                                    </div>
                                    <br>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" id="lblcantidadinsumo" for="form-field-1"> Cantidad</label>
                                        <div class="col-sm-9">
                                            <input type="number" id="txtcantidad_insumo" name="txtcantidad_insumo" min="0"  value="" step="any"class="col-xs-10 col-sm-5"/>
                                        </div>
                                    </div>

                                </fieldset>

                                <div class="form-actions center">
                                    <button type="button" class="btn btn-sm btn-success" onclick="fn_añadirInsumo(document.getElementById('txtcantidad_insumo').value, document.getElementById('cbxinsumo').value, );">
                                        Añadir
                                        <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4 class="widget-title">Insumos Añadidos</h4>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main no-padding">
                            <form>
                                <!-- <legend>Form</legend> -->
                                <fieldset>
                                    <div class="table-responsive">
                                        <div id="tablainsumos"></div> 

                                    </div>
                                </fieldset>


                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="space-4"></div>

        <div class="clearfix form-actions col-md-12 " align="center" >
            <div class="col-md-offset-3 col-md-6" >
                <button class="btn btn-info" type="button" onclick="fn_registrarRegla();">
                    <i class="ace-icon fa fa-check bigger-110"></i>
                    Registrar
                </button>

                &nbsp; &nbsp; &nbsp;
                <a  href="SMenu?action=pageRegistroReglasProduccion">  <button class="btn" type="button" id="limpiar">
                        <i class="ace-icon fa fa-undo bigger-110"></i>
                        Limpiar
                    </button></a>
            </div>
        </div>
    </div></div>
<div class="hr hr-24"></div>

<div class="row">
    <div class="col-xs-12">
        <h3 class="header smaller lighter blue">Reglas de Productos fabricados</h3>

        <div class="clearfix">
            <div class="pull-right tableTools-container"></div>
        </div>
        <div class="table-header">
            Resultado de busqueda "Reglas de fabricaciòn Activas"
        </div>

        <!-- div.table-responsive -->

        <!-- div.dataTables_borderWrap -->
        <div>
            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th class="center">Nº Regla
                        </th>
                        <th>Nombre Producto </th>
                        <th>Marca</th>
                        <th>Presentación</th>
                        <th>Unidad de medida</th>
                        <th>Nro de insumos</th>
                        <th>Opciones</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="regla" items="${lista_reglasActivas}" varStatus="loop">
                        <tr>
                            <td class="center">  ${regla.id_regla} </td>
                            <td> ${regla.descripcion}  </td>
                            <td> ${regla.marca} </td>
                            <td> ${regla.presentacion} </td>
                            <td> ${regla.medida} </td>
                            <td> ${regla.nro_insumos} </td>
                            <td><div class="hidden-sm hidden-xs action-buttons">
                                    <a class="blue" href="javascript:fn_mostrarinsumos('${regla.id_regla}','${regla.id_producto}');">
                                        <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                    </a>
                                    <a  class="red" href="javascript:fn_eliminarregla('${regla.id_regla}','${regla.id_producto}');">
                                        <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                    </a>
                                </div></td>   
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>







<div class="modal fade bs-example-modal-lg" id="MD_BuscarProducto" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div  class="panel panel-success" id="panel-tipoMsj">
            <div class="panel-heading"><h3>BUSCAR PRODUCTO</h3></div>
            <div class="panel-body">
                <div class="row">
                    <div class="container">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblparametro"> Nombre del Producto </label>
                            <div class="col-sm-9 col-md-6" >
                                <input type="text" id="txtparametroProducto" name="txtparametroProducto" placeholder="producto" class="col-xs-10 col-sm-5 form-control" />
                                <span class="input-group-btn">
                                    <input class="btn btn-default" type="button" onclick="fn_buscarProducto($('#txtparametroProducto').val());" value="Buscar"></input>
                                </span>
                            </div>
                        </div>   
                    </div>
                </div>
                <div id="tablaProductosFinales">
                </div>
            </div>
            <div class="modal-footer">

                <!-- 								<input type="button" id="btnClear" class="btn btn-default" -->
                <!-- 									value="LIMPIAR" > -->
                <button type="button" id="btnclose" class="btn btn-warning"
                        data-dismiss="modal">Cerrar</button>

            </div>
        </div>
    </div>

</div>  
 <div class="modal fade bs-example-modal-lg" id="modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div  class="panel panel-success" id="panel-tipoMsj">
            <div class="panel-heading"><h4 class="modal-title" id="nroregla"> 
            </h4></div>
            <div class="panel-body">
                 <div  align="center" id="tablamostrarInsumos"/> 
            </div>
            <div class="modal-footer">

                <!-- 								<input type="button" id="btnClear" class="btn btn-default" -->
                <!-- 									value="LIMPIAR" > -->
                <button type="button" id="btnclose" class="btn btn-warning"
                        data-dismiss="modal">Cerrar</button>

            </div>
        </div>
    </div>

</div>