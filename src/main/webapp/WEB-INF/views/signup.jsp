<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/api/user" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- Bootstrap and FontAwesome -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="assets/font-awesome/4.2.0/css/font-awesome.min.css" />

    <!-- Page specific plugin styles -->
    <link rel="stylesheet" href="assets/fonts/fonts.googleapis.com.css" />

    <!-- Ace styles -->
    <link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

    <!-- Inline styles related to this page -->
    <link rel="stylesheet" href="assets/css/building.css">

    <!-- Ace settings handler -->
    <script src="assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
    <!--[if lte IE 8]>
    <script src="assets/js/html5shiv.min.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->

    <!-- Custom Styles for Checkmark Animation and Password Toggle -->
    <style>



    </style>

</head>
<body>

<!-- Registration Form Modal -->
<section class="vh-100 d-flex justify-content-center align-items-center" id="signupForm">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-sm border-light">
                    <div class="card-body p-4">
                        <h2 class="fw-bold text-center mb-4" style="color: #333;">Tạo tài khoản</h2>
                        <p class="text-center text-muted mb-4">Vui lòng điền thông tin để đăng ký</p>
                        <form:form id="formsign" class="form-horizontal" modelAttribute="model">
                            <div class="form-group" style="display: none;">
                                <form:select path="roleCode" id="roleCode" class="form-control">
                                    <form:options items="${rolecode}"/>
                                </form:select>
                            </div>
                            <div class="form-group mb-3">
                                <label class="form-label">Tên đăng nhập</label>
                                <c:if test="${not empty model.id}">
                                    <form:input path="userName" id="userName" cssClass="form-control" disabled="true"/>
                                </c:if>
                                <c:if test="${empty model.id}">
                                    <form:input path="userName" id="userName" cssClass="form-control"/>
                                </c:if>
                                <small id="nameuMatchError" style="display: none ; color: red">Tên không được trống!</small>
                            </div>

                            <div class="form-group mb-3">
                                <label class="form-label">Họ và tên</label>
                                <form:input path="fullName" id="fullName" cssClass="form-control"/>
                                  <small id="namefMatchError" style="display: none ; color: red">Tên không được trống!</small>
                            </div>

                            <div class="form-group mb-3 position-relative">
                                <label for="password" class="form-label">Mật khẩu</label>
                                <form:input path="password" id="password" cssClass="form-control" type="password"/>
                            </div>

                            <div class="form-group mb-3 position-relative">
                                <label class="form-label">Nhập lại mật khẩu</label>
                                <form:input path="password" id="passwordConfirm" cssClass="form-control" type="password"/>
                                <small id="passwordMatchError" style="display: none ; color: red">Mật khẩu không trùng khớp!</small>
                            </div>

                            <div class="form-check mb-3">
                                <input class="form-check-input" type="checkbox" id="termsCheck" name="termsCheck" required />
                                <label class="form-check-label" for="termsCheck" style="font-size: 14px;">
                                    Tôi đồng ý với <a href="#!" class="text-primary"><u>Điều khoản sử dụng</u></a><br>
                                      <small id="checkMatchError" style="display: none ; color: red">Bạn hãy chấp nhận điều khoản trước khi đăng ký !!!</small>
                                </label>
                            </div>

                            <c:if test="${not empty model.id}">
                                <input type="button" class="btn btn-primary btn-lg w-100" value="Quay lại đăng nhập" id="btnAddOrUpdateUsers"/>
                            </c:if>
                            <c:if test="${empty model.id}">
                                <input type="button" class="btn btn-primary btn-lg w-100" value="Đăng ký" id="btnAddOrUpdateUsers"/>
                            </c:if>



                            <form:hidden path="id" id="userId"/>
                        </form:form>

                        <p class="text-center text-muted mt-4">Bạn đã có tài khoản? <a href="login" class="fw-bold text-body"><u>Đăng nhập tại đây</u></a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Success Modal -->
<div class="modal fade" tabindex="-1" role="dialog" id="inFormationSucces" aria-labelledby="inFormationSuccesLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content animated bounceInUp">
            <div class="modal-header">
                <h5 class="modal-title" id="inFormationSuccesLabel">Thông báo</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-center">
                <div class="success-message">
                    Đăng ký tài khoản thành công!
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

<script>


    function inFormationSucces() {
        $('#inFormationSucces').modal({
            backdrop: 'static',
            keyboard: false
        });
        $('#inFormationSucces').modal('show');


        $('#inFormationSucces').on('hidden.bs.modal', function () {
            document.getElementById("formsign").reset();
            window.location.href = "/login";
        });
    }


  function validatePasswords() {
    var password = $('#password').val();
    var passwordConfirm = $('#passwordConfirm').val();

    if (password !== passwordConfirm) {
        $('#passwordMatchError').show();
        return false;
    } else {
        $('#passwordMatchError').hide();
        return true;
    }
}

function validateTermCheck() {
    var termCheck = $('#termsCheck').prop('checked');

    if (!termCheck) {
        $('#checkMatchError').show();
        return false;
    } else {
        $('#checkMatchError').hide();
        return true;
    }
}

function validateName() {
    var uname = $('#userName').val().trim();
    var fname = $('#fullName').val().trim();


    if (uname === "" || fname === "") {
        $('#nameuMatchError').show();
        return false;
    } else {
        $('#namefMatchError').hide();
        return true;
    }
}





    $("#btnAddOrUpdateUsers").click(function (event) {
        event.preventDefault();

        if (!validateName()) {
            return;
        }

        if (!validateTermCheck()) {
            return;
        }
        if (!validatePasswords()) {
            return;
        }

        var formData = $("#formsign").serializeArray();
        var dataArray = {};
        $.each(formData, function (i, v) {
            dataArray[v.name] = v.value;
        });

        var userId = $('#userId').val();
        var roleCode = dataArray['roleCode'];
        var userName = dataArray['userName'];

        if (userName && roleCode) {
            $('#loading_image').show();
            addUser(dataArray);
        } else {
            window.location.href = "${pageContext.request.contextPath}/admin/user-edit?message=username_role_require";
        }
    });


    function addUser(data) {
        $.ajax({
            url: '${formUrl}',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                console.log("Đăng ký thành công!");
                inFormationSucces();
                $('#loading_image').hide();
            },
            error: function () {
                $('#loading_image').hide();
                alert('Đã xảy ra lỗi trong quá trình đăng ký. Vui lòng thử lại.');
            }
        });
    }
</script>

</body>
</html>
