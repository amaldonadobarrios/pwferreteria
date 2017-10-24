<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<input type="hidden" id="contexto" value="${context}">
<script>
    //BUSCAR VENTA
    function fn_buscar(fecha) {
       if (fecha !== '') {
            location.replace("SMenu?action=pageConsultarProduccion&fecha="+fecha);
        }
    }


    //ELIMINAR VENTA
    function fn_eliminarproduccion(id, estado) {
        var jdatos;
        if (estado === '1') {
            if (confirm("ESTÁ SEGURO DE ELIMINAR LA PRODUCCION")) {
                jdatos = {
                    evento: 'EliminarProduccionAjax',
                    id: id
                };
                fn_EliminarProduccionAjax(jdatos);
            }
        } else if (estado === '0') {
            mensaje('Produccion eliminada', 'La Produccion ya está eliminada,No es posible modificar este registro');
        }
    }
    function fn_EliminarProduccionAjax(jdatos) {
        var vruta = '/ServProduccion';
        var vevento = 'EliminarProduccionAjax';
        var jqdata = jdatos;
        fnEjecutarPeticion(vruta, jqdata, vevento);
    }
    function  fn_pintaEliminarProduccionAjax(response) {
        if (response === 'OK') {
            location.replace("SMenu?action=pageConsultarProduccion");
        }
    }
//MENSAJE SW
    function mensaje(titulo, mensaje) {
        swal({
            type: 'warning',
            title: titulo,
            text: mensaje,
            timer: 2000,
            showConfirmButton: false
        });
    }
//CONTROLADOR
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
        if (vevento == 'EliminarProduccionAjax') {
            fn_pintaEliminarProduccionAjax(vvrespuesta);
        }
    }
</script>

<div class="page-header">
    <h1>
        PRODUCCION
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Consultar Produccion
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="container">

        <fieldset>
            <legend> Buscar Produccion</legend>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Fecha de Produccion </label>
                <div class="col-sm-9">
                    <div class="input-group col-sm-3">
                        <input class="form-control date-picker" id="fechaventa" name="fechaventa" type="text" data-date-format="dd-mm-yyyy"/>
                        <span class="input-group-addon">
                            <i class="fa fa-calendar bigger-110"></i>
                        </span>
                    </div>
                </div>
            </div>  
            <div class="form-group col-sm-12" >
                <input type="button" class="btn-info btn" onclick="fn_buscar(document.getElementById('fechaventa').value);" value="Buscar">
            </div>
        </fieldset>
        <fieldset>
            <legend> Produccion </legend>
            <div class="row">
                <div class="col-xs-12">
                    <div class="clearfix">
                        <div class="pull-right tableTools-container"></div>
                    </div>
                    <div class="table-header">
                        Resultado para "Producciones  Registradas"
                    </div>

                    <!-- div.table-responsive -->
                    <!-- div.dataTables_borderWrap -->
                    <div>
                        <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th class="center">N°
                                    </th>
                                    <th>Fecha Reg</th>
                                    <th>Fecha Doc</th>
                                    <th>Nro Doc</th>
                                    <th>Operador</th>
                                    <th>Nro Reglas</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="prod" items="${lstProduccion}" varStatus="loop">
                                    <tr>
                                        <td class="center">  ${loop.count} </td>
                                        <td> ${prod.fecha_reg}  </td>
                                        <td> ${prod.fecha} </td>
                                        <td> ${prod.num} </td>
                                        <td> ${prod.operador} </td>
                                        <td> ${prod.cant_reglas} </td>
                                        <td>${prod.des_estado}
                                            <div class="hidden-sm hidden-xs action-buttons">
                                                <a class="blue" href="ServReporte?evento=produccion&estado=${prod.estado}&id_produccion=${prod.id_produccion}" target="_blank">
                                                    <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                                </a>
                                                <a class="red" href="javascript:fn_eliminarproduccion('${prod.id_produccion}','${prod.estado}');">
                                                    <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </fieldset>
    </div> 


