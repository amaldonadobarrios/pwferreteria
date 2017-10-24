<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
      function fn_buscar(fechaini,fechafin) {
      if (fechaini !== '' && fechafin!==''  ) {
            location.replace("SMenu?action=pageReporteVentas&fecha1="+fechaini+"&fecha2="+fechafin);
        }
    }
    </script>

<div class="page-header">
    <h1>
        REPORTES
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Reporte de Ventas
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="container">

        <fieldset>
            <legend> Consultar Ventas por invervalo de fechas</legend>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Fecha de Inicio </label>
                <div class="col-sm-9">
                    <div class="input-group col-sm-3">
                        <input class="form-control date-picker" id="fechaini" name="fechaini" type="text" data-date-format="dd-mm-yyyy"/>
                        <span class="input-group-addon">
                            <i class="fa fa-calendar bigger-110"></i>
                        </span>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Fecha de Fin </label>
                <div class="col-sm-9">
                    <div class="input-group col-sm-3">
                        <input class="form-control date-picker" id="fechafin" name="fechafin" type="text" data-date-format="dd-mm-yyyy"/>
                        <span class="input-group-addon">
                            <i class="fa fa-calendar bigger-110"></i>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group col-sm-12" >
                <input type="button" class="btn-info btn"  onclick="javascript:fn_buscar(document.getElementById('fechaini').value,document.getElementById('fechafin').value);"value="Consultar">
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
                                <c:forEach var="venta" items="${listaventarango}" varStatus="loop">
                                    <tr>
                                        <td class="center">  ${loop.count} </td>
                                        <td> ${venta.numero_comprobante}  ${venta.fecha_reg}</td>
                                        <td> ${venta.cantProductos} </td>
                                        <td> ${venta.neto} </td>
                                        <td> ${venta.igv} </td>
                                        <td> ${venta.total} </td>
                                        <td>${venta.estado}
                                            <div class="hidden-sm hidden-xs action-buttons">
                                                <a class="blue" href="ServReporte?evento=venta&estado=${venta.estado}&num=+${venta.numero_comprobante}" target="_blank">
                                                    <i class="ace-icon fa fa-search-plus bigger-130"></i>
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
            <div class="container"> 
                <div class="col-sm-12">
                    <div class="row">
			<div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
            

                        <div class="row" style="text-align: center;">
                              <div class="col-md-12 sidenav" align="center" id="divfoto" >
                        <c:if test = "${grafico !=null}">

                            <img  src="data:image/png;base64,${grafico}"width="500px" height="500px" 
                                  name="image" id="image" />
                        </c:if>
                        
                    </div>


                            <div class="vspace-12-sm"></div>

                           
                        </div><!-- /.row -->

                     

                        

                        <div class="hr hr32 hr-dotted"></div>

             

                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->						
                    
                    
                    
                    
                    
                    
                    
                    
                </div><!-- /.col -->
            </div>
        </fieldset>
    </div> 


<!-- inline scripts related to this page -->

