<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<input type="hidden" id="contexto" value="${context}">

<script>
    function redireccionarPagina() {
        window.location = "SMenu?action=pagRegistroProduccion";
    }


    //-------------------REGISTRAR PRODUCCION
    function fn_pintar_RegitrarProduccion(response) {
        //alert(response);
        $('#modalLoaging').modal('hide');
        if (response === 'NOSESION') {
            mensaje('ERROR', 'SESION EXPIRADA');
            location.href = "login.jsp";
        } else {
            var v_resultado = response + "";
            var respuesta = v_resultado.split('%');
            var estado = respuesta[0];
            var validacion = respuesta[1];
            var detalleerror = respuesta[2];
            var id = respuesta[3];
            if (detalleerror == 'null') {
                detalleerror = 'Verificar cantidad de insumos';
            }
            if (estado == 'NOK') {
                mensajeERROR('ERROR' + '\n' + validacion, detalleerror);
            } else if (estado == 'OK') {
                mensajeOK("CORRECTO", "SE REGISTRO LA PRODUCCION EXISTOSAMENTE");
                var popUp = window.open('ServReporte?evento=produccion&estado=1&id_produccion=' + id, 'ventana1', "width=700,height=500,scrollbars=SI");
                if (popUp == null || typeof (popUp) == 'undefined') {
                    $('#modalLoaging').modal('show');
                    setTimeout("redireccionarPagina()", 20000);
                } else {
                    $('#modalLoaging').modal('hide');
                    setTimeout("redireccionarPagina()", 5000);
                }
            }

        }


    }

    function fn_ejecutarRegistrarProduccion(jdatos) {
        var vruta = '/ServProduccion';
        var vevento = 'RegitrarProduccion';
        fnEjecutarPeticion(vruta, jdatos, vevento);
    }

    function fn_RegistrarProduccion() {
        var numero = $('#numero').val();
        var fecha = $('#fecha').val();
        var doc = $('#cbxdoc').val();
        $('#lblnumero').css("color", "black");
        $('#lblfecha').css("color", "black");
        $('#lbldoc').css("color", "black");
        if (numero != '' && fecha != '' && doc != '') {
            var jdatos = {
                evento: 'RegitrarProduccion',
                numero: numero,
                fecha: fecha,
                documento: doc
            };
            $('#modalLoaging').modal('show');
            fn_ejecutarRegistrarProduccion(jdatos);
        } else {
            $('#lblnumero').css("color", "red");
            $('#lblfecha').css("color", "red");
            $('#lbldoc').css("color", "red");
        }
    }
    //---------------------------------------verificar beta
    
    function fn_EjecutarMostarErrorProduccion() {
        var jqdata = {
            evento: 'verificarErrorProduccion'
        };
        var contexto = document.getElementById('contexto').value;
        var vruta = '/ServProduccion';
        var vservlet = contexto + vruta;
        $.ajax({
            url: vservlet,
            method: 'POST',
            data: jqdata,
            success: function (responseText) {
                mensajeERROR('ERROR', responseText);
            }
        });
    }
    function fn_mostarerror() {
        fn_EjecutarMostarErrorProduccion();
    }
    function fn_grabar(estado) {
        $('#msg').html(estado);
    }
    function fn_EjecutarVerificarProduccion() {
        var jqdata = {
            evento: 'verificarProduccion'
        };
        var contexto = document.getElementById('contexto').value;
        var vruta = '/ServProduccion';
        var vservlet = contexto + vruta;
        $.ajax({
            url: vservlet,
            method: 'POST',
            data: jqdata,
            success: function (responseText) {
                var v_resultado = responseText + "";
                var respuesta = v_resultado.split('%');
                var estado = respuesta[0];
                var mensaje = respuesta[1];
                if (estado === 'NOK') {
                    fn_mostarerror();
                    //mensajeERROR('ERROR', mensaje);
                } else if (estado === 'OK') {
                    fn_grabar(estado);
                }
            }
        });
    }

    function fn_VerificarProduccion() {
        waitingDialog.show('Verificando Existencias . . .');
        setTimeout("waitingDialog.hide();fn_EjecutarVerificarProduccion();", 3000);
        setTimeout("fn_EjecutarVerificarProduccion();", 4000);
    }
    //---------------------Poductos finales a fabricar
    function fneliminarItem(item) {
        var it = Number(item - 1);
        var vruta = '/ServProduccion';
        var vevento = 'EliminarProductofinal';
        var jqdata = {
            evento: 'EliminarProductofinal',
            item: it
        };
        fnEjecutarPeticion(vruta, jqdata, vevento);
    }

    function fn_pintarlistaProduccion(response) {
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
                $('#tablaProductosFinales').html(tabla);
                $('#dynamic-table2').DataTable({
                    responsive: true
                });
                $('#dynamic-table2').stacktable();
            }
        }
    }

    function fn_ejecutarAñadirProduccion(jdatos) {
        var vruta = '/ServProduccion';
        var vevento = 'AñadirProduccion';
        fnEjecutarPeticion(vruta, jdatos, vevento);

    }

    function fn_añadir() {
        var id_regla = document.getElementById('id_regla').value;
        var cantidad = document.getElementById('cantidad').value;
        $('#lblregla').css("color", "black");
        $('#lblcantidad').css("color", "black");
        if (id_regla != '' && cantidad != '' && cantidad != '0') {
            var jdatos = {
                evento: 'AñadirProduccion',
                id_regla: id_regla,
                cantidad: cantidad
            };
            fn_ejecutarAñadirProduccion(jdatos);
        } else {
            $('#lblregla').css("color", "red");
            $('#lblcantidad').css("color", "red");
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
        if (vevento == 'AñadirProduccion') {
            fn_pintarlistaProduccion(vvrespuesta);
        } else if (vevento == 'EliminarProductofinal') {
            fn_pintarlistaProduccion(vvrespuesta);
        } else if (vevento == 'RegitrarProduccion') {
            fn_pintar_RegitrarProduccion(vvrespuesta);

        }

    }
    //-----------------MENSAJE EMERGENTE
    function mensajeERROR(titulo, mensaje) {
        swal({
            type: 'warning',
            title: titulo,
            text: mensaje,
            timer: 3000,
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

<div class="page-header">
    <h1>
        PRODUCCION
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Registro de Producción 
        </small>
    </h1><div id="msg"></div>
</div><!-- /.page-header -->

<div class="row">
    <div class="container">
        <div class="col-sm-12">
            <div class="col-sm-6">
                <fieldset>
                    <legend>OPERADOR</legend>   
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> OPERADOR</label>
                        <div class="col-sm-9">
                            <input type="text" id="form-field-1" name="nombre" placeholder="OPERADOR" class="col-xs-10 col-sm-9"  value="${sessionScope.usuario.apellidoPaterno} ${sessionScope.usuario.apellidoMaterno} ${sessionScope.usuario.nombres}" disabled/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> DNI</label>
                        <div class="col-sm-9">
                            <input type="text" id="form-field-1" name="nombre" placeholder="DNI" class="col-xs-10 col-sm-9" value="${sessionScope.usuario.dni}"disabled/>
                        </div>
                    </div>
                </fieldset>
            </div>
            <div class="col-sm-6">             
                <fieldset>
                    <legend>Documento</legend>   
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lbldoc"> Documento</label>
                        <div class="col-sm-9">
                            <select class="chosen-select form-control col-xs-10 col-sm-8" id="cbxdoc" name="cbxdoc" data-placeholder="Documento">
                                <option value="">Seleccione</option>
                                <option value="FProduccion" selected="">Formato de Produccion</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblnumero"> Número</label>
                        <div class="col-sm-9">
                            <input type="text" id="numero" name="numero" placeholder="Número" onkeypress="return solo_numeros(event);"class="col-xs-10 col-sm-9" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblfecha"> Fecha</label>
                        <div class="input-group col-sm-5" >
                            <input class="form-control date-picker" id="fecha" name="fecha" type="text" data-date-format="yyyy-mm-dd"/>
                            <span class="input-group-addon">
                                <i class="fa fa-calendar bigger-110"></i>
                            </span>
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>    

        <div class="col-sm-12">
            <fieldset>
                <legend>Producto</legend>   
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblregla"> Producto</label>
                    <div class="col-sm-9">
                        <select class="chosen-select form-control col-xs-10 col-sm-8" id="id_regla" name="unidad"   data-placeholder="Producto" onchange="document.getElementById('cantidad').value = '';">
                            <option value=""selected>Seleccione</option>
                            <c:forEach var="reglas" items="${comboreglas}" varStatus="loop">
                                <option value="${reglas.id_regla}">${reglas.descripcion} ${reglas.marca} ${reglas.presentacion} EN: ${reglas.medida}</option>
                            </c:forEach>
                        </select> 
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblcantidad"> Cantidad</label>
                    <div class="col-sm-9">
                        <input type="number" id="cantidad" name="cantidad" placeholder="Cantidad" class="col-xs-10 col-sm-9" step="any"/>
                    </div>
                </div>
                <div  class="col-sm-12" align="center"> <input type="button"  class="buttons bigger-130 colorpicker-with-alpha" value="Añadir Producto" onclick="fn_añadir()"></div>
            </fieldset>   
        </div>
        <div class="col-sm-12">
            <fieldset>
                <legend>PRODUCCION</legend>   
                <div class="row">
                    <div class="col-xs-12">
                        <div class="clearfix">
                            <div class="pull-right tableTools-container"></div>
                        </div>
                        <div class="table-header">
                            Resultado para "Producción Registrada"
                        </div>

                        <!-- div.table-responsive -->
                        <!-- div.dataTables_borderWrap -->
                        <div id="tablaProductosFinales">
                            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>

                                        <th>Código</th>
                                        <th>Cantidad</th>
                                        <th>Detalle de Producto</th>
                                        <th>Cantidad</th>
                                        <th></th>
                                        <th>Eliminar</th>
                                        <th></th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <tr>
                                        <td class="center">  </td>
                                        <td>  </td>
                                        <td>  </td>
                                        <td>  </td>
                                        <td>  </td>
                                        <td> 

                                        </td>
                                        <td></td>

                                    </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
        </div>
        </fieldset>   
    </div>  
</div></div>

<!-- PAGE CONTENT BEGINS -->








<div class="clearfix form-actions">
    <div class="col-md-12" align="center">
        <button class="btn btn-info" type="button" onclick="fn_RegistrarProduccion();">
            <i class="ace-icon fa fa-check bigger-110"></i>
            Registrar
        </button>

        &nbsp; &nbsp; &nbsp;
        <button class="btn" type="button" onclick="redireccionarPagina();">
            <i class="ace-icon fa fa-undo bigger-110"></i>
            Limpiar
        </button>
    </div>
</div>

<div class="modal fade container-fluid" id="modalLoaging" role="dialog" data-backdrop="static" data-keyboard="false" >
    <div class="modal-dialog modal-sm container-fluid">
        <br><br><br><br><br><br><br><br><br><br><br><br><br>
        <div class="loader  container-fluid">
        </div>
    </div>
</div>

