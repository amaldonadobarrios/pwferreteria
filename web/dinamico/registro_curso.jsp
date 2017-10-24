<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-header">
    <h1>
        CURSOS
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Registro de Cursos
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form action="Curso" method="POST" class="form-horizontal" role="form">
            <input name="action" type="hidden" value="registrarCurso">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Nombre de Curso</label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="nombre" placeholder="Nombre de Curso" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Tipo de Curso </label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="tipo" placeholder="Tipo de Curso" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Descripcion </label>
                <div class="col-sm-9">
                    <textarea id="form-field-1" name="descripcion" class="autosize-transition col-xs-10 col-sm-5"></textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Fecha de Inicio </label>
                <div class="col-sm-9">
                    <div class="input-group col-sm-3">
                        <input class="form-control date-picker" id="id-date-picker-1" name="fechaini" type="text" data-date-format="dd-mm-yyyy"/>
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
                        <input class="form-control date-picker" id="id-date-picker-1" name="fechafin" type="text" data-date-format="dd-mm-yyyy"/>
                        <span class="input-group-addon">
                            <i class="fa fa-calendar bigger-110"></i>
                        </span>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Requisitos </label>
                <div class="col-sm-9">
                    <textarea id="form-field-1" name="requisitos" class="autosize-transition col-xs-10 col-sm-5"></textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Vacantes </label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="vacantes" placeholder="Cantidades de Vacantes" class="col-xs-10 col-sm-5" />
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
        <h3 class="header smaller lighter blue">Cursos Registrados</h3>

        <div class="clearfix">
            <div class="pull-right tableTools-container"></div>
        </div>
        <div class="table-header">
            Resultado para "Cursos Registrados"
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
                        <th>Tipo</th>
                        <th>Fecha Inicio</th>
                        <th>Fecha Fin</th>
                        <th>Vacantes</th>
                        <th></th>
                    </tr>
                </thead>
                
                <tbody>
                    <c:forEach var="curso" items="${listCurso}" varStatus="loop">
                        <tr>
                            <td class="center">  ${loop.count} </td>
                            <td> ${curso.nombre} </td>
                            <td> ${curso.tipo} </td>
                            <td> ${curso.fechaini} </td>
                            <td> ${curso.fechafin} </td>
                            <td> 
                                <span class="label label-sm label-info">${curso.vacante}</span>
                            </td>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    <a class="blue" href="Curso?action=verCurso">
                                        <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                    </a>

                                    <a class="green" href="Curso?action=formActualizarCurso&idcurso=${curso.idcurso}" >
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>

                                    <a class="red" href="Curso?action=eliminarCurso&idcurso=${curso.idcurso}">
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
