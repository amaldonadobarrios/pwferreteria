<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<input type="hidden" id="contexto" value="${context}">
<script>
    function fn_verImagen(id) {
        var jdata = {evento: 'getFotoProducto',
            id: id};
        var contexto = document.getElementById('contexto').value;
        var vruta = '/ServInventario';
        var vservlet = contexto + vruta;
        var rutafoto;
        $.ajax({
            url: vservlet,
            method: 'POST',
            data: jdata,
            success: function (responseText) {
                if (responseText === '') {
                    rutafoto = 'assets/images/sinfoto.png'
                } else {
                    rutafoto = 'data:image/jpg;base64,' + responseText;
                }
                $.magnificPopup.open({
                    items: {
                        src: rutafoto
                    },
                    type: 'image'
                });
            }
        });
    }
</script>
<div class="page-header">
    <h1>
        INVENTARIO
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Consultar Inventario
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="container">

        <fieldset>
            <legend> Inventario</legend>
            <div class="row">
                <div class="col-xs-12">
                    <div class="clearfix">
                        <div class="pull-right tableTools-container"></div>
                    </div>
                    <div class="table-header">
                        Resultado para "Inventario"
                    </div>

                    <!-- div.table-responsive -->

                    <!-- div.dataTables_borderWrap -->
                    <div>
                        <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th class="center">Nº
                                    </th>
                                    <th>Nombre del Producto/Insumo</th>
                                    <th>Marca</th>
                                    <th>Presentación</th>
                                    <th>Unidad de medida</th>
                                    <th>Tipo</th>
                                    <th>Existencias</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="inventario" items="${listainventario}" varStatus="loop">
                                    <tr>
                                        <td class="center">  ${loop.count} </td>
                                        <td><a class="blue" href="javascript:fn_verImagen(${inventario.id_producto});">${inventario.descripcion}</a></td>
                                        <td> ${inventario.marca} </td>
                                        <td> ${inventario.presentacion} </td>
                                        <td> ${inventario.medida} </td>
                                        <td> ${inventario.prod_insu} </td>
                                        <td>${inventario.existencia}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </fieldset>
    </div> 


