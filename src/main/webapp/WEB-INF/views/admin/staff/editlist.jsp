<%--
  Created by IntelliJ IDEA.
  User: Nitro 5
  Date: 10/10/2024
  Time: 10:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/admin/staff-list"/>
<c:url var="formAjax" value="/api/customers"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Danh Sách Khách Hàng</title>

    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="assets/font-awesome/4.2.0/css/font-awesome.min.css" />

    <!-- text fonts -->
    <link rel="stylesheet" href="assets/fonts/fonts.googleapis.com.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
    <link rel="stylesheet" href="assets/css/building.css">

    <script src="assets/js/ace-extra.min.js"></script>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
    <![endif]-->
    <style>

    </style>
</head>

<body class="no-skin">

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try { ace.settings.check('main-container', 'fixed') } catch (e) { }
    </script>

    <div id="sidebar" class="sidebar responsive" style="display: none" ></div>

    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="#">
                                <i class="fa fa-home"></i> Home
                            </a>
                        </li>
                        <li class="breadcrumb-item">
                            <a href="#">
                                <i class="fa fa-home"></i> building-list
                            </a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">
                            <i class="fa fa-home"></i> edit-building-list
                        </li>
                    </ol>
                </nav>
            </div>
            <div>
            <h1 style="text-align: center">Sửa Thông Tin Khách Hàng </h1>
            </div>
            <div >
                <div >
                    <div class="col-xs-12">
                        <form:form method="get" modelAttribute="CustomerEdit" id="form-edit" >
                            <input type="hidden" name="id" value="${CustomerEdit.id}">

                            <!-- Tên Khách Hàng -->
                            <div class="form-group">
                                <label class="col-xs-3">Tên Khách Hàng</label>
                                <div class="col-xs-9">
                                <form:input type="text" name="name" id="name" class="form-control" path="fullName"/>

                                </div>
                            </div>

                            <!-- Số điện thoại -->
                            <div class="form-group">
                                <label class="col-xs-3">Số điện thoại </label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="phone" id="phone" class="form-control" path="phone"/>
                                </div>
                            </div>

                            <!-- Email -->
                            <div class="form-group">
                                <label class="col-xs-3">Email</label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="email" id="street" class="form-control" path="email"/>
                                </div>
                            </div>

<%--                            <!-- Tên Công ty -->--%>
                            <div class="form-group">
                                <label class="col-xs-3">Tên Công ty </label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="cty" id="structure" class="form-control" path="companyName"/>
                                </div>
                            </div>

                            <!-- Nhu cầu -->
                            <div class="form-group">
                                <label  class="col-xs-3">Nhu cầu</label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="basement" id="basement" class="form-control" path="demand"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Tình Trạng</label>
                                 <form:select path="status" cssStyle="margin-left : 15px ">
                                            <form:options items="${status}"/>
                                        </form:select>
                            </div>

                        </div>


                            <!-- Nút Thêm Tòa Nhà -->
                            <div class="row text-center">
                                <div class="col-xs-9 col-xs-offset-3">
                                    <button type="submit" class="btn btn-primary" id="btnAddorUpdateCustomer">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-save" viewBox="0 0 16 16">
                                            <path
                                                    d="M8 0a2 2 0 0 0-2 2H4a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2h-2a2 2 0 0 0-2-2zM4 3a1 1 0 0 1 1-1h2v4H4V3zm5 0a1 1 0 0 1 1 1v4H9V3h2zM4 8v5h8V8H4z" />
                                        </svg>
                                        Sửa Khách Hàng</button>
                                    <a href="/admin/staff-list"><button type="button" class="btn btn-default" id="cancelBtn" >Hủy Thao Tác</button></a>
                                    <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                                </div>
                            </div>

                        </form:form>



                    </div>
                </div>
            </div>


        <p style="font-size: 20px ; margin-left: 10px">CHĂM SÓC KHÁCH HÀNG </p>
        <hr >
           <div class="row text-left" style="margin-left: 10px">
                                <div class="col-xs-9">
                                    <button type="submit" class="btn btn-primary" onclick="noteUpdate('CSKH')">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-save" viewBox="0 0 16 16">
                                            <path
                                                    d="M8 0a2 2 0 0 0-2 2H4a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2h-2a2 2 0 0 0-2-2zM4 3a1 1 0 0 1 1-1h2v4H4V3zm5 0a1 1 0 0 1 1 1v4H9V3h2zM4 8v5h8V8H4z" />
                                        </svg>
                                       + ADD</button>
                                </div>
                            </div>
            <div class="row" >

    <div class="col-xs-12" >
        <div class="table-responsive">
            <display:table name="CSKH" cellspacing="0" cellpadding="0"
                           requestURI="${formUrl}" partialList="true" sort="external"
                           size="${model.totalItems}" defaultsort="2" defaultorder="ascending"
                           id="item" pagesize="${model.maxPageItems}"
                           export="false"
                           class="table table-striped table-bordered table-hover dataTable no-footer"
                           style="margin: 3em 0 1.5em; "  >
                <display:column title="<fieldset class='form-group'>
                                            <input type='checkbox' id='checkAll' class='check-box-element'>
                                        </fieldset>" class="center select-cell"
                                headerClass="center select-cell">
                    <fieldset>
                        <input type="checkbox" name="checkList" value="${item.id}"
                               id="checkbox_${item.id}" class="check-box-element"/>
                    </fieldset>
                </display:column>
                <display:column headerClass="text-left" property="createdDate" title="Ngày tạo "/>
                <display:column headerClass="text-left" property="createdBy" title="Người tạo  "/>
                <display:column headerClass="text-left" property="modifiedDate" title="Ngày sửa "/>
                <display:column headerClass="text-left" property="modifiedBy" title="Người sửa"/>
                <display:column headerClass="text-left" property="note" title="Chi tiết giao dịch "/>
                                            <display:column title="Thao Tác">
                                                <button
                                                    style="background-color: rgb(218, 218, 124); border-radius: 5px;"
                                                    title="Sửa Ghi Chú"
                                                    onclick="noteUpdates('${item.id}','${item.customerId}', '${item.note}','CSKH')"
                                                    data-id="${item.id}"
                                                    data-customerId="${item.customerId}"
                                                    data-note="${item.note}"
                                                    data-code="CSKH">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                         fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                                        <path
                                                            d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325" />
                                                    </svg>
                                                </button>
                                            </display:column>
            </display:table>
        </div>
    </div>
</div>
 <p style="font-size: 20px ; margin-left: 10px" >Dẫn Đi Xem  </p>
        <hr >
          <div class="row text-left" style="margin-left: 10px">
                                <div class="col-xs-9">
                                    <button type="submit" class="btn btn-primary" onclick="noteUpdate('DDX')">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-save" viewBox="0 0 16 16">
                                            <path
                                                    d="M8 0a2 2 0 0 0-2 2H4a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2h-2a2 2 0 0 0-2-2zM4 3a1 1 0 0 1 1-1h2v4H4V3zm5 0a1 1 0 0 1 1 1v4H9V3h2zM4 8v5h8V8H4z" />
                                        </svg>
                                       + ADD</button>
                                </div>
                            </div>
            <div class="row">
    <div class="col-xs-12" >
        <div class="table-responsive">
            <display:table name="DDX" cellspacing="0" cellpadding="0"
                           requestURI="${formUrl}" partialList="true" sort="external"
                           size="${model.totalItems}" defaultsort="2" defaultorder="ascending"
                           id="item" pagesize="${model.maxPageItems}"
                           export="false"
                           class="table table-striped table-bordered table-hover dataTable no-footer"
                           style="margin: 3em 0 1.5em; "  >
                <display:column title="<fieldset class='form-group'>
                                            <input type='checkbox' id='checkAll' class='check-box-element'>
                                        </fieldset>" class="center select-cell"
                                headerClass="center select-cell">
                    <fieldset>
                        <input type="checkbox" name="checkList" value="${item.id}"
                               id="checkbox_${item.id}" class="check-box-element"/>
                    </fieldset>
                </display:column>
                <display:column headerClass="text-left" property="createdDate" title="Ngày tạo "/>
                <display:column headerClass="text-left" property="createdBy" title="Người tạo  "/>
                <display:column headerClass="text-left" property="modifiedDate" title="Ngày sửa "/>
                <display:column headerClass="text-left" property="modifiedBy" title="Người sửa"/>
                <display:column headerClass="text-left" property="note" title="Chi tiết giao dịch "/>
                                   <display:column title="Thao Tác">
                                                <button
                                                    style="background-color: rgb(218, 218, 124); border-radius: 5px;"
                                                    title="Sửa Ghi Chú"
                                                    onclick="noteUpdates('${item.id}','${item.customerId}', '${item.note}','DDX')"
                                                    data-id="${item.id}"
                                                    data-customerId="${item.customerId}"
                                                    data-note="${item.note}"
                                                    data-code="DDX">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                         fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                                        <path
                                                            d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325" />
                                                    </svg>
                                                </button>
                                            </display:column>


            </display:table>

        </div>
    </div>
</div>

   <p style="font-size: 20px ; margin-left: 10px" >Ký Hợp Đồng  </p>
        <hr >
          <div class="row text-left" style="margin-left: 10px">
                                <div class="col-xs-9">
                                    <button type="submit" class="btn btn-primary" onclick="noteUpdate('DDX')">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-save" viewBox="0 0 16 16">
                                            <path
                                                    d="M8 0a2 2 0 0 0-2 2H4a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2h-2a2 2 0 0 0-2-2zM4 3a1 1 0 0 1 1-1h2v4H4V3zm5 0a1 1 0 0 1 1 1v4H9V3h2zM4 8v5h8V8H4z" />
                                        </svg>
                                       + ADD</button>
                                </div>
                            </div>
            <div class="row">
    <div class="col-xs-12" >
        <div class="table-responsive">
            <display:table name="KHD" cellspacing="0" cellpadding="0"
                           requestURI="${formUrl}" partialList="true" sort="external"
                           size="${model.totalItems}" defaultsort="2" defaultorder="ascending"
                           id="item" pagesize="${model.maxPageItems}"
                           export="false"
                           class="table table-striped table-bordered table-hover dataTable no-footer"
                           style="margin: 3em 0 1.5em; "  >
                <display:column title="<fieldset class='form-group'>
                                            <input type='checkbox' id='checkAll' class='check-box-element'>
                                        </fieldset>" class="center select-cell"
                                headerClass="center select-cell">
                    <fieldset>
                        <input type="checkbox" name="checkList" value="${item.id}"
                               id="checkbox_${item.id}" class="check-box-element"/>
                    </fieldset>
                </display:column>
                <display:column headerClass="text-left" property="customerid" title="Ngày tạo "/>
                <display:column headerClass="text-left" property="staffid" title="Người tạo  "/>
                <display:column headerClass="text-left" property="buildingname" title="Ngày sửa "/>
                <display:column headerClass="text-left" property="rentaldate" title="Người sửa"/>
                <display:column headerClass="text-left" property="rent" title="Chi tiết giao dịch "/>
                <display:column headerClass="text-left" property="leaseterm" title="Chi tiết giao dịch "/>
                <display:column headerClass="text-left" property="customeriDcardnumber" title="Chi tiết giao dịch "/>
                <display:column headerClass="text-left" property="employeeiDcardnumber" title="Chi tiết giao dịch "/>
                <display:column headerClass="text-left" property="signature" title="Chi tiết giao dịch "/>
                                   <display:column title="Thao Tác">
                                                <button
                                                    style="background-color: rgb(218, 218, 124); border-radius: 5px;"
                                                    title="Sửa Ghi Chú"
                                                    onclick="noteUpdates('${item.id}','${item.customerId}', '${item.note}','DDX')"
                                                    data-id="${item.id}"
                                                    data-customerId="${item.customerId}"
                                                    data-note="${item.note}"
                                                    data-code="DDX">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                         fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                                        <path
                                                            d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325" />
                                                    </svg>
                                                </button>
                                            </display:column>


            </display:table>

        </div>
    </div>
</div>

        </div>

  </div>

<!-- basic scripts -->
<script src="assets/js/jquery.2.1.1.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>

            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- Modal Thông báo lỗi -->
<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="errorModalLabel">Lỗi</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Bạn đang bỏ trông trường "Tên Khách Hàng " hoặc "Số Điện thoại  " vui lòng kiểm tra lại !
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<!-- Large modal -->
<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="inFormationSucces">
    <div style="background-color: #0d6efd ; text-align: center ; margin-left: 10px ; font-size: 20px ; color: white"   class="alert alert-primary" role="alert">
       Thêm Yêu cầu thành Công !!!
    </div>
</div>
<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="inFormationSuccesUpdate">
    <div style="background-color: #0d6efd ; text-align: center ; margin-left: 10px ; font-size: 20px ; color: white"   class="alert alert-primary" role="alert">
       Sửa Yêu cầu thành Công !!!
    </div>
</div>
<%--thêm --%>
<div class="modal fade" id="noteModalCSKH" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">New message</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form method="get" action="/admin/staff-edit" id="form-note">
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">Note:</label>
<%--            <input type="hidden" name="code" value="${CSKHS}">--%>
             <input type="hidden" name="customerId" id="customerId" value="${CustomerEdit.id}">
            <input type="text" name= "note" class="form-control" id="recipient-name" placeholder="Chi tiết Giao dịch">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Huỷ</button>
        <button type="button" class="btn btn-primary" id="CreateNote">Cập nhật Giao dịch </button>
      </div>
    </div>
  </div>
</div>
<%--sửa--%>
<div class="modal fade" id="noteModalUpdate" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">New message</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
     <div class="modal-body">
    <form method="get" action="/admin/staff-edit" id="form-note-update">
        <div class="form-group">
            <label for="recipient-name" class="col-form-label">Note:</label>
            <input type="hidden" name="id" id="id" value="">
            <input type="hidden" name="customerId" id="customerId" value="">
            <input type="text" name="note" class="form-control" id="recipient-name" placeholder="Chi tiết Giao dịch">
        </div>
    </form>
</div>

      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Huỷ</button>
        <button type="button" class="btn btn-primary" id="updateNote">Sửa Giao dịch </button>
      </div>
    </div>
  </div>
</div>
<script>
    let TypeCode = null;
    $("#cancelBtn").click(function () {
        showAlertBeforeCancelForm(function() {
            window.location.href = 'admin/staff/edit';
        })
    });
    function infomation() {
        // Hiển thị modal lỗi
        $('#errorModal').modal('show');
    }

    function inFormationSucces(){
        $('#inFormationSucces').modal('show');
        $('#inFormationSucces').on('hidden.bs.modal', function () {
            document.getElementById("form-edit").submit(); // Submit form
            window.location.href = '/admin/staff-list';
        });
    }
     function inFormationSuccesUpdate(){
        $('#inFormationSuccesUpdate').modal('show');
        $('#inFormationSuccesUpdate').on('hidden.bs.modal', function () {
            document.getElementById("form-edit").submit(); // Submit form
            window.location.href = '/admin/staff-list';
        });
    }

    function cloneMoldel(){
       $('#noteModalCSKH').modal('hide');
    }
    function cloneMoldels(){
       $('#noteModalUpdate').modal('hide');
    }

    function noteUpdate(typeCode){
    TypeCode = typeCode; // Gán giá trị vào biến toàn cục
    $('#noteModalCSKH').modal('show');
    }
       function noteUpdates(id, customerId, note, code) {
         TypeCode = code;
    // Gán giá trị vào các trường trong modal
    $('#form-note-update input[name="id"]').val(id);
    $('#form-note-update input[name="customerId"]').val(customerId);
    $('#form-note-update input[name="note"]').val(note);
    $('#form-note-update input[name="code"]').val(TypeCode);

    // Hiển thị modal
    $('#noteModalUpdate').modal('show');
}







    $('#CreateNote').click(function (event) {
        var formData = $('#form-note').serializeArray();
        var convertJson = {};
        $.each(formData, function (i, v) {
            convertJson[v.name] = v.value;
        });
        convertJson['code'] = [TypeCode];
            event.preventDefault();
             $('#noteModalCSKH').modal('show');
            btnAddorUpdateNote(convertJson);
    });


    $('#btnAddorUpdateCustomer').click(function (event) {
        var formData = $('#form-edit').serializeArray(); // mảng các object
        var convertJson = {}; // 1 Object

        // Xử lý form và tạo object JSON từ formData
        $.each(formData, function (i, v) {
            if (v.name === 'status') {
                convertJson['status'] = v.value; // Gán trực tiếp giá trị đơn
            } else {
                convertJson[v.name] = v.value;
            }
        });

        const nameField = document.getElementById("name");
        const phoneField = document.getElementById("phone");
        if (!convertJson['status'] || nameField.value.trim() === "" || phoneField.value.trim() === "" ) {
            event.preventDefault();
            infomation(); // Hiển thị modal lỗi
        } else {
            event.preventDefault(); // Ngăn form tự động submit
            btnAddorUpdateCustomer(convertJson);
        }
    });

      function btnAddorUpdateNote(json) {
        $.ajax({
            url: "/api/transaction/note",
            type: "POST",
            data: JSON.stringify(json),
            contentType: "application/json",
            success: function(result) {
                cloneMoldel()
                // Hiển thị thông báo thành công
                inFormationSucces();
            },
            error: function(xhr, status, error) {

                console.log("Error: " + error);
                alert("Đã có lỗi xảy ra: " + xhr.responseText);
            }
        });
    }




      $('#updateNote').click(function (event) {
       var formData = $('#form-note-update').serializeArray();
        var convertJson = {}; // 1 Object


        $.each(formData, function (i, v) {
            convertJson[v.name] = v.value; // Gán các giá trị từ form vào JSON
        });
         convertJson['code'] = [TypeCode]; // Gán giá trị TypeCode
            event.preventDefault(); // Ngăn form tự động submit
             $('#noteModalUpdate').modal('show');
            btnAddorUpdateNotes(convertJson);
    });

     function btnAddorUpdateNotes(json) {
       $.ajax({
        url: "/api/transaction/note/edit" ,
        type: "PUT",
        data: JSON.stringify(json),
        contentType: "application/json",
        success: function (result) {
               cloneMoldels()
           inFormationSuccesUpdate();

        },
        error: function (xhr, status, error) {
            alert("Đã có lỗi xảy ra: " + xhr.responseText);
        }
    });
    }




    function btnAddorUpdateCustomer(json) {
        $.ajax({
            url: "/api/customers",
            type: "PUT",
            data: JSON.stringify(json),
            contentType: "application/json",
            success: function(result) {

                // Hiển thị thông báo thành công
                inFormationSucces();
            },
            error: function(xhr, status, error) {

                console.log("Error: " + error);
                alert("Đã có lỗi xảy ra: " + xhr.responseText);
            }
        });
    }

</script>
</body>
</html>
