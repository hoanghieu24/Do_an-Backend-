<%--
  Created by IntelliJ IDEA.
  User: Nitro 5
  Date: 10/10/2024
  Time: 10:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

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

<body >



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

            <h1 style="text-align: center">Thêm Thông Tin Khách Hàng </h1>
            </div>
            <div >
                <div >
                    <div class="col-xs-12">
                        <form:form method="get" modelAttribute="CustomerEdit" id="form-edit" >

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
                                        Thêm Khách Hàng  </button>
                                    <a href="/admin/staff-list"><button type="button" class="btn btn-default" id="cancelBtn" >Hủy Thao Tác</button></a>
                                    <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                                </div>
                            </div>

                        </form:form>

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
       Thêm khách hàng thành Công !!!
    </div>
</div>

    <script>
        $("#cancelBtn").click(function () {
            showAlertBeforeCancelForm(function () {
                window.location.href = 'admin/staff/edit';
            });
        });

        function infomation() {
            // Hiển thị modal lỗi
            $('#errorModal').modal('show');
        }

        function inFormationSucces() {
            $('#inFormationSucces').modal('show');
            $('#inFormationSucces').on('hidden.bs.modal', function () {
                window.location.href = '/admin/staff-list';
            });
        }

        $('#btnAddorUpdateCustomer').click(function (event) {
            var formData = $('#form-edit').serializeArray(); // Mảng các object
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
            if (!convertJson['status'] || nameField.value.trim() === "" || phoneField.value.trim() === "") {
                event.preventDefault();
                infomation(); // Hiển thị modal lỗi
            } else {
                event.preventDefault(); // Ngăn form tự động submit
                btnAddorUpdateCustomer(convertJson);
            }
        });

        function btnAddorUpdateCustomer(json) {
            $.ajax({
                url: "/api/customers",
                type: "POST",
                data: JSON.stringify(json),
                contentType: "application/json",
                success: function (result) {
                    // Hiển thị thông báo thành công
                    inFormationSucces();
                },
                error: function (xhr, status, error) {
                    console.log("Error: " + error);
                    alert("Đã có lỗi xảy ra: " + xhr.responseText);
                }
            });
        }
    </script>

</body>
</html>
