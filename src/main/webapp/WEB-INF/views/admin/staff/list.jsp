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
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Danh Sách Toà Nhà</title>

    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="assets/font-awesome/4.2.0/css/font-awesome.min.css" />

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="assets/fonts/fonts.googleapis.com.css" />


    <!-- ace styles -->
    <link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />


    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
    <![endif]-->

    <!-- inline styles related to this page -->
    <link rel="stylesheet" href="assets/css/building.css">
    <!-- ace settings handler -->
    <script src="assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="assets/js/html5shiv.min.js"></script>
    <script src="assets/js/respond.min.js"></script>


    <![endif]-->
    <style>


        .search-box {
            border: 2px solid #007bff; /* Màu viền */
            padding: 20px;
            border-radius: 10px; /* Góc bo tròn */
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); /* Bóng đổ */
            background-color: #f9f9f9; /* Màu nền nhẹ */
        }

        h3 {
            text-align: center;
            margin-bottom: 20px;
        }

        .btn-search {
            display: block;
            width: 100%;
            margin-top: 20px;
        }

    </style>
</head>

<body>


    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
                </script>

                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Customer-list</li>
                    </ol>
                </nav><!-- /.breadcrumb -->

                <div class="nav-search" id="nav-search">
                    <form class="form-search">
							<span class="input-icon">


							</span>
                    </form>
                </div><!-- /.nav-search -->
            </div>

            <div class="page-content">
                <div class="ace-settings-container" id="ace-settings-container">
                    <div class="ace-settings-box clearfix" id="ace-settings-box">
                        <div class="pull-left width-50">
                            <div class="ace-settings-item">
                                <div class="pull-left">
                                    <select id="skin-colorpicker" class="hide">
                                        <option data-skin="no-skin" value="#438EB9">#438EB9</option>
                                        <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                                        <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                                        <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                                    </select>
                                </div>
                                <span>&nbsp; Choose Skin</span>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
                                <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
                                <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
                                <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
                                <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
                                <label class="lbl" for="ace-settings-add-container">
                                    Inside
                                    <b>.container</b>
                                </label>
                            </div>
                        </div><!-- /.pull-left -->

                        <div class="pull-left width-50">
                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" />
                                <label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" />
                                <label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" />
                                <label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
                            </div>
                        </div><!-- /.pull-left -->
                    </div><!-- /.ace-settings-box -->
                </div><!-- /.ace-settings-container -->

                <div class="page-header">
                    <h1>
                        Dashboard
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div><!-- /.page-header -->
            </div><!-- /.page-content -->

            <div style="background-color: #f5f5f5; padding: 20px; border-radius: 10px;">
                <div class="container">
                    <h3 class="text-center" style="color: #333; margin-bottom: 20px;">Tìm Kiếm Khách Hàng </h3>
                    <div class="widget-body">
                        <div class="widget-main">
                            <form:form modelAttribute="ModelSearchs" action="/admin/staff-list" method="GET" id="listform">

                                <div class="form-group row">
                                    <div class="col-xs-4">
                                        <label for="">Tên Khách Hàng</label>
                                            <%--                                        <input type="text" name="name" class="form-control" style="border-radius: 5px;" value="${ModelSearch.name}" placeholder="Mời nhập tên toà nhà !!">--%>
                                        <form:input class="form-control"   style="border-radius: 5px;"  placeholder="Mời nhập tên khách hàng !!" path="fullname"/>
                                    </div>

                                    <div class="col-xs-4">
                                        <label for="">SDT Di Động</label>
                                        <form:input class="form-control"   style="border-radius: 5px;"  placeholder="0" path="phone"/>
                                            <%--                                        <input type="number" name="flootArea" class="form-control" style="border-radius: 5px;" value="${ModelSearch.flootArea}" placeholder="0">--%>
                                    </div>

                                    <div class="col-xs-4">
                                        <label for="">Email</label>
                                        <form:input class="form-control"   style="border-radius: 5px;"  placeholder="0" path="email"/>
                                            <%--                                        <input type="number" name="flootArea" class="form-control" style="border-radius: 5px;" value="${ModelSearch.flootArea}" placeholder="0">--%>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <div class="col-xs-4">
                                        <label for="">Tình Trạng</label>
                                        <form:select path="status" class="form-control">
                                            <form:option value="" label="--- Chọn Trạng Thái  ---"/>
                                            <form:options items="${status}"/>
                                        </form:select>
                                    </div>
                                     <security:authorize access="hasRole('MANAGER')">
                                     <div class="col-xs-4">
                                        <label for="">Nhân Viên Phụ Trách</label>
                                            <%--                                        <select name="staffId" class="form-control" style="border-radius: 5px;">--%>
                                            <%--                                            <option value="">---Chọn Nhân Viên---</option>--%>
                                            <%--                                            <option value="1">Nhân viên 1</option>--%>
                                            <%--                                        </select>--%>
                                        <form:select path="staffId" class="form-control" style="border-radius: 5px;">
                                            <form:option value="" label="---Chọn Nhân Viên---"/>
                                            <form:options items="${staffs}"/>
                                        </form:select>
                                    </div>
                                    </security:authorize>
                                    <div class="col-xs-4">
                                        <label for="">Người Thêm </label>
                                            <%--                                        <input type="text" name="name" class="form-control" style="border-radius: 5px;" value="${ModelSearch.name}" placeholder="Mời nhập tên toà nhà !!">--%>
                                        <form:input class="form-control"   style="border-radius: 5px;"  placeholder="Mời nhập tên người thêm !!" path="createdBy"/>
                                    </div>
                                    </div>




                                <div class="form-group row">
                                    <div class="col-xs-6">
                                        <button type="submit" class="btn btn-sm btn-primary" style="border-radius: 5px; padding: 5px 15px;" id="btnSearchcustomer">Tìm Kiếm</button>
                                    </div>
                                </div>

                            </form:form>

                        </div>
                    </div>
                </div>
            </div>


            <div class="pull-right" id="hovers">
                <security:authorize access="hasRole('MANAGER')">
                    <button class="btn btn-app btn-danger btn-sm" id="btnDeleteCustomers">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-dash" viewBox="0 0 16 16">
  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1m0-7a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
  <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
</svg>
                    </button>
                </security:authorize>



                <a href="/admin/staff-edit">
                    <button class="btn btn-app btn-primary btn-sm">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
  <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
</svg>

                    </button>
                </a>

            </div>

            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        </div>
        <div class="hr hr-200 dotted hr-double"></div>
       <div class="row">
    <div class="col-xs-12">
        <div class="table-responsive">
            <display:table name="customs" cellspacing="0" cellpadding="0"
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
                <display:column headerClass="text-left" property="fullName" title="Tên khách hàng"/>
                <display:column headerClass="text-left" property="phone" title="Số di động "/>
                <display:column headerClass="text-left" property="email" title="Email "/>
                <display:column headerClass="text-left" property="demand" title="Nhu cầu"/>
                <display:column headerClass="text-left" property="createdBy" title="Người thêm"/>
                <display:column headerClass="text-left" property="createdDate" title="Ngày thêm"/>
                <display:column headerClass="text-left" property="status" title="Tình trạng "/>
                <display:column title="Thao Tác">
                     <a href="/admin/staff-edit-${item.id}">
                                            <button
                                                    style="background-color: rgb(218, 218, 124); border-radius: 5px;  "
                                                    title="sửa toà nhà ">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                     fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                                    <path
                                                            d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325" />
                                                </svg>

                                            </button>
                                        </a>

                                    <security:authorize access="hasRole('MANAGER')">
                                      <button
                                                style="background-color: rgb(240, 123, 123); border-radius: 5px;"
                                                title="xoá toà nhà "
                                                onclick="DeleteCustomer(${item.id})">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                 fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
                                                <path
                                                        d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5M11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47M8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5" />
                                            </svg>

                                        </button>


                                        <button style="background-color: lightblue; border-radius: 5px;"
                                                title="Giao Toà Nhà " onclick="AssỉngmentCustomer(${item.id})" id="btnAssigmentCustomer">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                 fill="currentColor" class="bi bi-card-list" viewBox="0 0 16 16">
                                                <path
                                                        d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2z" />
                                                <path
                                                        d="M5 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 5 8m0-2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5m0 5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5m-1-5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0M4 8a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0m0 2.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0" />
                                            </svg>

                                        </button>
                                    </security:authorize>

                </display:column>
            </display:table>
        </div>
    </div>
</div>

        <script src="assets/js/jquery.2.1.1.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>



</div>
    <div class="modal fade" id="AssỉngmentCustomerModdal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Danh Sách Nhân Viên </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <table id="staff-list" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                Chọn
                            </th>
                            <th class="center">
                                Tên Nhân Viên
                            </th>
                        </tr>
                        </thead>

                        <tbody>

                        </tbody>
                    </table>
                    <input type="hidden" id="customerId" value="">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="save" data-dismiss="modal">Save changes</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

                </div>
            </div>
        </div>
    </div>
    <div class="modal modal-sheet position-static d-block bg-body-secondary p-4 py-md-5" id="modalChoice">
    <div class="modal-dialog" role="document">
        <div class="modal-content rounded-3 shadow-lg" style="border: none; background-color: #f8f9fa;">
            <div class="modal-body p-4 text-center">
                <h5 class="mb-3" style="font-weight: bold; color: #333;">Bạn có chắc chắn muốn xoá toà nhà này?</h5>
                <p class="mb-0" style="font-size: 16px; color: #555;">Toà nhà này có thể mất vĩnh viễn. Bạn có chắc
                    chắn không?</p>
            </div>
            <div class="modal-footer flex-nowrap p-0">

                <button type="button"
                        class="btn btn-lg btn-danger fs-6 text-white col-6 py-3 m-0 rounded-0 border-end"
                        style="background-color: #dc3545;" id="btn-Confirm" onclick="ConfirmDeleteCustomer()" >
                    <strong>Đúng vậy, tôi chắc chắn!</strong>
                </button>

                <button type="button" class="btn btn-lg btn-secondary fs-6 text-dark col-6 py-3 m-0 rounded-0"
                        data-bs-dismiss="modal" style="background-color: #6c757d;" id="btn-Cancel" onclick="CancelDeleteCustomer()">
                    Không chắc
                </button>
            </div>
        </div>
    </div>
    </div>
<%--    // thong bao--%>
    <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="inFormationSucces">
        <div style="background-color: #0d6efd ; text-align: center ; margin-left: 10px ; font-size: 20px ; color: white"   class="alert alert-primary" role="alert">
            Giao  thành Công !!!
        </div>
    </div>
<%--    thông báo xoà--%>
    <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="inFormationDeleteSucces">
        <div style="background-color: #0d6efd ; text-align: center ; margin-left: 10px ; font-size: 20px ; color: white"   class="alert alert-primary" role="alert">
            Xoá toà nhà thành Công !!!
        </div>
    </div>
    <script>
        let customerIdToDelete = null;




        // xử lý hiển thị thông báo bị null
        function AssỉngmentCustomer(customerId) {
            $('#AssỉngmentCustomerModdal').modal();
            $('#customerId').val(customerId);
            loadStaff(customerId);
        }

        // xử lý hiển thị thông báo trước khi xoá
        function AssỉngmentDeleteCustomer() {
            $('#modalChoice').modal();
        }



        function CancelDeleteCustomer() {
            customerIdToDelete = null; // Đặt lại biến
            $('#modalChoice').modal('hide'); // Đóng modal
        }


        // xử lý tích tất cả các ô


        // Xử lý xóa nhiều tòa nhà cùng một lúc
        $('#btnDeleteCustomers').click(function (e) {
            e.preventDefault();
            var customerIds = $('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();

            if (customerIds.length <= 0) {
                alert("Hiện đang không có tòa nhà nào được chọn để xóa!!");
            } else {

                customerIdToDelete = customerIds;
                $('#modalChoice').modal('show');
            }
        });

        // Hàm xử lý hiển thị modal xác nhận trước khi xóa một tòa nhà
        function DeleteCustomer(customerId) {
            customerIdToDelete = customerId;
            $('#modalChoice').modal('show');
        }

        // Hàm xác nhận xóa tòa nhà
        function ConfirmDeleteCustomer() {
            if (customerIdToDelete !== null) {
                console.log("Xóa khach hang với ID: " + customerIdToDelete);
                btnDeleteCustomers(customerIdToDelete);
            }
            $('#modalChoice').modal('hide');
        }

        // Hàm xử lý xóa tòa nhà
        function btnDeleteCustomers(data) {
            $.ajax({
                url: "/api/customers/" + data,
                type: "DELETE",
                dataType: 'text',
                success: function(result) {
                    console.log("Khach Hang  đã được xóa thành công");
                    inFormationDeleteSucces(); // Gọi hiển thị thông báo
                },
                error: function( error) {
                    console.log("Error: " + error);

                }
            });
        }


        function loadStaff(customerId) {
            $.ajax({
                url: "/api/customers/" + customerId,
                type: "GET",
                dataType: 'json',
                success: function(reponse) {
                    var row = '';
                    $.each(reponse.data,function (index,item){
                        row += '<tr class="center">';
                        row +=  '<td> <input type="checkbox" value=' + item.staffId + ' id=checkbox_'+ item.staffId + ' ' + item.checked + '/></td>';
                        row += '<td>' + item.userName + '</td>';
                        row += '</tr>';
                    })
                    $('#staff-list tbody').html(row);

                },
                error: function(error) {
                    console.log("Error: " + error);
                    alert("Đã có lỗi xảy ra: " + error);
                }
            });
        }
        function inFormationSucces(){
            $('#inFormationSucces').modal('show');
            $('#inFormationSucces').on('hidden.bs.modal', function () {
                document.getElementById("AssỉngmentCustomerModdal").submit(); // Submit form
            });
        }
        function inFormationDeleteSucces() {
            $('#inFormationDeleteSucces').modal('show');

            $('#inFormationDeleteSucces').on('hidden.bs.modal', function () {
                location.reload(); // Tải lại trang sau khi đóng thông báo
            });
        }

            $('#save').click(function (e){
            e.preventDefault()
            console.log(json)
            var json = {};
            console.log(json)
            json['customerId'] = $('#customerId').val();
            var staffs = $('#staff-list').find('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            json['staffs'] = staffs;
            UpdateAssigmentCustomer(json);

        });



        function UpdateAssigmentCustomer(json){
            $.ajax({
                url: "/api/customers/staffs",
                type: "PUT",
                data: JSON.stringify(json),
                contentType: "application/json",
                dataType: 'text',
                success: function(result) {
                    inFormationSucces();
                    // location.reload(); // Tải lại trang sau khi xóa
                },
                error: function(error) {
                    console.log("Error: " + error);
                    alert(error)
                }
            });
        }







        $('#btnSearchcustomer').click(function (e){
            e.preventDefault();
            $('#listform').submit();
        })





    </script>
</body>


</html>
