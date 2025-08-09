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
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Danh Sách Toà Nhà</title>

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

    <div id="sidebar" class="sidebar responsive" ></div>

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
            <h1 style="text-align: center">Thêm Tòa Nhà</h1>
            </div>
            <div >
                <div >
                    <div class="col-xs-12">
                        <form:form method="get" modelAttribute="buildingEdit" id="form-edit" >
                            <input type="hidden" name="id" value="${buildingEdit.id}">

                            <!-- Tên Tòa Nhà -->
                            <div class="form-group">
                                <label class="col-xs-3">Tên Toà Nhà</label>
                                <div class="col-xs-9">
                                <form:input type="text" name="name" id="name" class="form-control" path="name"/>

                                </div>
                            </div>

                            <!-- Quận -->
                            <div class="form-group">
                                <label class="col-xs-3">Quận</label>
                                 <form:select path="district" cssStyle="margin-left : 15px ">
<%--                                            <form:option value="" label="--- Chọn Quận ---"/>--%>
                                            <form:options items="${district}"/>
                                        </form:select>
                            </div>

                            <!-- Phường -->
                            <div class="form-group">
                                <label for="ward" class="col-xs-3">Phường</label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="ward" id="ward" class="form-control" path="street"/>
                                </div>
                            </div>

                            <!-- Đường -->
                            <div class="form-group">
                                <label for="street" class="col-xs-3">Đường</label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="street" id="street" class="form-control" path="ward"/>
                                </div>
                            </div>

<%--                            <!-- Kết cấu -->--%>
                            <div class="form-group">
                                <label for="structure" class="col-xs-3">Kết cấu</label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="structure" id="structure" class="form-control" path="brokerageFee"/>
                                </div>
                            </div>

                            <!-- Số Tầng Hầm -->
                            <div class="form-group">
                                <label for="basement" class="col-xs-3">Số Tầng Hầm</label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="basement" id="basement" class="form-control" path="numberOfBasement"/>
                                </div>
                            </div>

                            <!-- Diện tích sàn -->
                            <div class="form-group">
                                <label for="area" class="col-xs-3">Diện tích sàn</label>
                                <div class="col-xs-9">
                                    <form:input type="number" name="area" id="area" class="form-control" path="floorArea"/>
                                </div>
                            </div>

                            <!-- Hướng -->
                            <div class="form-group">
                                <label for="direction" class="col-xs-3">Hướng</label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="direction" id="direction" class="form-control" path="direction"/>
                                </div>
                            </div>

                            <!-- Hạng -->
                            <div class="form-group">
                                <label for="rank" class="col-xs-3">Hạng</label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="rank" id="rank" class="form-control" path="level"/>
                                </div>
                            </div>

                            <!-- Diện tích thuê -->
                            <div class="form-group">
                                <label for="rentArea" class="col-xs-3">Diện tích thuê</label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="rentArea" id="rentArea" class="form-control" path="rentareaEntity_List"/>
                                </div>
                            </div>

                            <!-- Giá thuê -->
                            <div class="form-group">
                                <label for="rentPrice" class="col-xs-3">Giá thuê</label>
                                <div class="col-xs-9">
                                    <form:input type="number" name="rentPrice" id="rentPrice" class="form-control" path="rentPrice"/>
                                </div>
                            </div>

                            <!-- Mô tả Giá -->
                            <div class="form-group">
                                <label for="priceDescription" class="col-xs-3">Mô tả Giá</label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="priceDescription" id="priceDescription" class="form-control" path="rentPriceDescription"/>
                                </div>
                            </div>

                            <!-- Phí dịch vụ -->
                            <div class="form-group">
                                <label for="serviceFee" class="col-xs-3">Phí dịch vụ</label>
                                <div class="col-xs-9">
                                    <form:input type="number" name="serviceFee" id="serviceFee" class="form-control" path="serviceFee"/>
                                </div>
                            </div>

                            <!-- Phí ô tô -->
                            <div class="form-group">
                                <label for="carFee" class="col-xs-3">Phí ô tô</label>
                                <div class="col-xs-9">
                                    <form:input type="number" name="carFee" id="carFee" class="form-control" path="carFee"/>
                                </div>
                            </div>

                            <!-- Phí mô tô -->
                            <div class="form-group">
                                <label for="motorFee" class="col-xs-3">Phí mô tô</label>
                                <div class="col-xs-9">
                                    <form:input type="number" name="motorFee" id="motorFee" class="form-control" path="motoFee"/>
                                </div>
                            </div>

                            <!-- Phí ngoài giờ -->
                            <div class="form-group">
                                <label for="overtimeFee" class="col-xs-3">Phí ngoài giờ</label>
                                <div class="col-xs-9">
                                    <form:input type="number" name="overtimeFee" id="overtimeFee" class="form-control" path="overTimeFee"/>
                                </div>
                            </div>

                            <!-- Tiền điện -->
                            <div class="form-group">
                                <label for="electricityFee" class="col-xs-3">Tiền điện</label>
                                <div class="col-xs-9">
                                    <form:input type="number" name="electricityFee" id="electricityFee" class="form-control" path="electricityFee"/>
                                </div>
                            </div>

                            <!-- Đặt cọc -->
                            <div class="form-group">
                                <label for="deposit" class="col-xs-3">Đặt cọc</label>
                                <div class="col-xs-9">
                                    <form:input type="number" name="deposit" id="deposit" class="form-control" path="deposit" />
                                </div>
                            </div>

                            <!-- Thanh Toán -->
                            <div class="form-group">
                                <label for="payment" class="col-xs-3">Thanh Toán</label>
                                <div class="col-xs-9">
                                    <form:input  type="text" name="payment" id="payment" class="form-control" path="payment"/>
                                </div>
                            </div>

                            <!-- Thời gian thuê -->
                            <div class="form-group">
                                <label for="rentTime" class="col-xs-3">Thời gian thuê</label>
                                <div class="col-xs-9">
                                    <form:input type="date" name="rentTime" id="rentTime" class="form-control" path="rentTime"/>
                                </div>
                            </div>

                            <!-- Thời gian trang trí -->
                            <div class="form-group" >
                                <label for="decorationTime" class="col-xs-3" >Thời gian trang trí</label>
                                <div class="col-xs-9">
                                    <form:input type="date" name="decorationTime" id="decorationTime" class="form-control" placeholder="dd/mm/yyyy"  path="decorationTime"/>
                                </div>
                            </div>

                            <!-- Tên Quản lý -->
                            <div class="form-group">
                                <label for="managerName" class="col-xs-3">Tên Quản lý</label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="managerName" id="managerName" class="form-control" path="managername"/>
                                </div>
                            </div>

                            <!-- SĐT Quản lý -->
                            <div class="form-group">
                                <label for="managerPhone" class="col-xs-3">SĐT quản lý</label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="managerPhone" id="managerPhone" class="form-control" path="managerPhoneNumber"/>
                                </div>
                            </div>

                             <!-- SĐT Quản lý -->
                            <div class="form-group">
                                <label for="managerPhone" class="col-xs-3">Phi Moi Gioi </label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="managerPhone" id="managerPhone" class="form-control" path="brokerageFee"/>
                                </div>
                            </div>

                            <!-- Loại tòa nhà -->
                            <div class="form-group" id = "TypeCode">
                                <label class="col-xs-3">Loại tòa nhà</label>
                                  <form:checkboxes cssStyle="margin-left : 15px " items="${rentype}" path="type" />

                            </div>
                            <div class="form-group">
                            <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                            <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                            <div class="col-sm-9">
                                <c:if test="${not empty buildingEdit.image}">
                                   <c:set var="imagePath" value="/repository${buildingEdit.image}"/>
                                    <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px" >
                                    </c:if>
                                    <c:if test="${empty buildingEdit.image}">
                                         <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                    </c:if>

                            </div>
                        </div>


                            <!-- Ghi chú -->
                            <div class="form-group">
                                <label for="note" class="col-xs-3">Ghi chú</label>
                                <div class="col-xs-9">
                                    <form:input type="text" name="note" id="note" class="form-control" path="note"/>
                                </div>
                            </div>

                            <!-- Nút Thêm Tòa Nhà -->
                            <div class="row text-center">
                                <div class="col-xs-9 col-xs-offset-3">
                                    <button type="submit" class="btn btn-primary" id="btnAddorUpdateBuilding">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-save" viewBox="0 0 16 16">
                                            <path
                                                    d="M8 0a2 2 0 0 0-2 2H4a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2h-2a2 2 0 0 0-2-2zM4 3a1 1 0 0 1 1-1h2v4H4V3zm5 0a1 1 0 0 1 1 1v4H9V3h2zM4 8v5h8V8H4z" />
                                        </svg>
                                        Thêm Toà Nhà</button>
                                    <a href="/admin/building-list"><button type="button" class="btn btn-default" id="cancelBtn" >Hủy Thao Tác</button></a>
                                    <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                                </div>
                            </div>

                        </form:form>

                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- basic scripts -->
<script src="assets/js/jquery.2.1.1.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
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
                Bạn đang bỏ trông trường "Loại toà nhà " hoặc "Tên toà nhà " vui lòng kiểm tra lại !
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
       Thêm toà nhà thành Công !!!
    </div>
</div>

<script>
    var imageBase64 = '';
    var imageName = '';




    $("#cancelBtn").click(function () {
        showAlertBeforeCancelForm(function() {
            window.location.href = 'admin/building/edit';
        })
    });

    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. vd: a-b-c
            console.log(imageName)
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    function infomation() {
        // Hiển thị modal lỗi
        $('#errorModal').modal('show');
    }

    function inFormationSucces(){
        $('#inFormationSucces').modal('show');
        $('#inFormationSucces').on('hidden.bs.modal', function () {
            document.getElementById("form-edit").submit(); // Submit form
        });
    }

    $('#btnAddorUpdateBuilding').click(function (event) {

        var formData = $('#form-edit').serializeArray(); // mảng các object
        var convertJson = {}; // 1 Object
        var typeCode = [];
        // Xử lý form và tạo object JSON từ formData
        $.each(formData, function (i, v) {
            if (v.name === 'type') {
                typeCode.push(v.value);
            } else if (v.name === 'district') {

                convertJson[v.name] = v.value ? v.value : null;
            } else {
                convertJson[v.name] = v.value;
            }
            // if ('' !== e.value && null != e.value) {
            //     formData['' + e.name + ''] = e.value;
            // }

            if ('' !== imageBase64) {
                convertJson['imageBase64'] = imageBase64;
                convertJson['imageName'] = imageName;
            }
        });
        $('#loading_image').show();

        const nameField = document.getElementById("name");
        if (typeCode.length === 0 || nameField.value.trim() === "" ) {
            event.preventDefault();
            infomation();
        } else {
            event.preventDefault(); // Ngăn form tự động submit
            convertJson['type'] = typeCode;
            btnAddorUpdateBuilding(convertJson);
        }
    });



    function btnAddorUpdateBuilding(json) {
        $.ajax({
            url: "/api/buildings",
            type: "POST",
            data: JSON.stringify(json),
            contentType: "application/json",
            success: function(result) {
                $('#loading_image').hide();
                // Hiển thị thông báo thành công
                inFormationSucces();
            },
            error: function(xhr, status, error) {
                $('#loading_image').hide();
                console.log("Error: " + error);
                alert("Đã có lỗi xảy ra: " + xhr.responseText);
            }
        });
    }

</script>
</body>
</html>
