<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<input type="hidden" id="contexto" value="${context}">
<script>
    //BUSCAR COMPRA
    function fn_buscar(fecha) {
       if (fecha !== '') {
            location.replace("SMenu?action=pageConsultarCompra&fecha="+fecha);
        }
    }


    //ELIMINAR VENTA
    function fn_eliminarcompra(id, estado, numero) {
        var jdatos;
        if (estado === 'COMPRADO') {
                if (confirm("ESTÁ SEGURO DE ELIMINAR LA COMPRA")) {
                jdatos = {
                    evento: 'EliminarCompraAjax',
                    id: id,
                    num: numero
                };
                fn_EliminarCompraAjax(jdatos);
            }
        } else if (estado === 'ELIMINADO') {
            mensaje('Compra eliminada', 'La compra ya está eliminada,No es posible modificar esta compra');
        }
    }
    function fn_EliminarCompraAjax(jdatos) {
        var vruta = '/ServCompra';
        var vevento = 'EliminarCompraAjax';
        var jqdata = jdatos;
        fnEjecutarPeticion(vruta, jqdata, vevento);
    }
    function  fn_pintaEliminarCompraAjax(response) {
        if (response === 'OK') {
            location.replace("SMenu?action=pageConsultarCompra");
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
        if (vevento == 'EliminarCompraAjax') {
            fn_pintaEliminarCompraAjax(vvrespuesta);
        }
    }
</script>

<div class="page-header">
    <h1>
        COMPRAS
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Consultar una  Compra
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="container">

        <fieldset>
            <legend> Buscar Compra</legend>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Fecha de Compra </label>
                <div class="col-sm-9">
                    <div class="input-group col-sm-3">
                        <input class="form-control date-picker" id="fechacompra" name="fechacompra" type="text" data-date-format="dd-mm-yyyy"/>
                        <span class="input-group-addon">
                            <i class="fa fa-calendar bigger-110"></i>
                        </span>
                    </div>
                </div>
            </div>  
            <div class="form-group col-sm-12" >
                <input type="button" class="btn-info btn" onclick="fn_buscar(document.getElementById('fechacompra').value);" value="Buscar">
            </div>
        </fieldset>
        <fieldset>
            <legend> Compras </legend>
            <div class="row">
                <div class="col-xs-12">
                    <div class="clearfix">
                        <div class="pull-right tableTools-container"></div>
                    </div>
                    <div class="table-header">
                        Resultado para "Compras Registradas"
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
                                <c:forEach var="compra" items="${listaCompra}" varStatus="loop">
                                    <tr>
                                        <td class="center">  ${loop.count} </td>
                                        <td> ${compra.numero_comprobante}  ${compra.fecha}</td>
                                        <td> ${compra.cantProductos} </td>
                                        <td> ${compra.neto} </td>
                                        <td> ${compra.igv} </td>
                                        <td> ${compra.total} </td>
                                        <td>${compra.estado}
                                            <div class="hidden-sm hidden-xs action-buttons">
                                                <a class="blue" href="ServReporte?evento=compra&estado=${compra.estado}&num=+${compra.numero_comprobante}+&id_compra=${compra.id_comprobante}" target="_blank">
                                                    <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                                </a>
                                                <a class="red" href="javascript:fn_eliminarcompra('${compra.id_comprobante}','${compra.estado}','${compra.numero_comprobante}');">
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


