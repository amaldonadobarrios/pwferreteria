<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-header">
    <h1>
        Participante
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Registro de Participante
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form action="Participante" method="POST" class="form-horizontal" role="form">
            <input name="action" type="hidden" value="registrarParticipante">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Nombre de Participante</label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="nombre" placeholder="Nombre de Participante" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Apellido Paterno de Participante </label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="paterno" placeholder="Apellido Paterno de Participante" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Apellido Materno de Participante </label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="materno" placeholder="Apellido Materno de Participante" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Carnet de Identidad Policial </label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="cip" placeholder="Carnet de Identidad Policial" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> DNI </label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="dni" placeholder="Documento Nacional de Identidad" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Grado </label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="grado" placeholder="Grado PNP" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="space-4"></div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="submit">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        Registrar
                    </button>

                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="reset">
                        <i class="ace-icon fa fa-undo bigger-110"></i>
                        Limpiar
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="hr hr-24"></div>

<div class="row">
    <div class="col-xs-12">
        <h3 class="header smaller lighter blue">Participantes Registrados</h3>

        <div class="clearfix">
            <div class="pull-right tableTools-container"></div>
        </div>
        <div class="table-header">
            Resultado para "Participantes Registrados"
        </div>

        <!-- div.table-responsive -->

        <!-- div.dataTables_borderWrap -->
        <div>
            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th class="center">
                        </th>
                        <th>Nombre</th>
                        <th>Ap Paterno</th>
                        <th>Ap Materno</th>
                        <th>CIP</th>
                        <th>Grado</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="participante" items="${listParticipante}" varStatus="loop">
                        <tr>
                            <td class="center">  ${loop.count} </td>
                            <td> ${participante.nombre} </td>
                            <td> ${participante.paterno} </td>
                            <td> ${participante.materno} </td>
                            <td> ${participante.cip} </td>
                            <td> ${participante.grado} </td>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a class="blue" href="#">
                                        <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                    </a>

                                    <a class="green" href="#">
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>

                                    <a class="red" href="#">
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