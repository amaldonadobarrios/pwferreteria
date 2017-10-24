<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<input type="hidden" id="contexto" value="${context}">
<script>
    //BUSCAR VENTA
    function fn_buscar(fecha) {
       if (fecha !== '') {
            location.replace("SMenu?action=pageConsultarVenta&fecha="+fecha);
        }
    }


    //ELIMINAR VENTA
    function fn_eliminarventa(id, estado, numero) {
        var jdatos;
        if (estado === 'VENDIDO') {
            if (confirm("ESTÁ SEGURO DE ELIMINAR LA VENTA")) {
                jdatos = {
                    evento: 'EliminarVentaAjax',
                    id: id,
                    num: numero
                };
                fn_EliminarVentaAjax(jdatos);
            }
        } else if (estado === 'ELIMINADO') {
            mensaje('Venta eliminada', 'La venta ya está eliminada,No es posible modificar esta venta');
        }
    }
    function fn_EliminarVentaAjax(jdatos) {
        var vruta = '/ServVenta';
        var vevento = 'EliminarVentaAjax';
        var jqdata = jdatos;
        fnEjecutarPeticion(vruta, jqdata, vevento);
    }
    function  fn_pintaEliminarVentaAjax(response) {
        if (response === 'OK') {
            location.replace("SMenu?action=pageConsultarVenta");
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
        if (vevento == 'EliminarVentaAjax') {
            fn_pintaEliminarVentaAjax(vvrespuesta);
        }
    }
</script>

<div class="page-header">
    <h1>
        VENTAS
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Consultar una  Venta
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="container">

        <fieldset>
            <legend> Buscar Venta</legend>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Fecha de Venta </label>
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
            <legend> Ventas </legend>
            <div class="row">
                <div class="col-xs-12">
                    <div class="clearfix">
                        <div class="pull-right tableTools-container"></div>
                    </div>
                    <div class="table-header">
                        Resultado para "Ventas Registradas"
                    </div>

                    <!-- div.table-responsive -->
                    <!-- div.dataTables_borderWrap -->
                    <div>
                        <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th class="center">N°
                                    </th>
                                    <th>Comprobante</th>
                                    <th>Items</th>
                                    <th>Neto</th>
                                    <th>IGV</th>
                                    <th>Total</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="venta" items="${listaVenta}" varStatus="loop">
                                    <tr>
                                        <td class="center">  ${loop.count} </td>
                                        <td> ${venta.numero_comprobante}  ${venta.fecha_reg}</td>
                                        <td> ${venta.cantProductos} </td>
                                        <td> ${venta.neto} </td>
                                        <td> ${venta.igv} </td>
                                        <td> ${venta.total} </td>
                                        <td>${venta.estado}
                                            <div class="hidden-sm hidden-xs action-buttons">
                                                <a class="blue" href="ServReporte?evento=venta&estado=${venta.estado}&num=${venta.numero_comprobante}" target="_blank">
                                                    <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                                </a>
                                                <a class="red" href="javascript:fn_eliminarventa('${venta.id_comprobante}','${venta.estado}','${venta.numero_comprobante}');">
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


