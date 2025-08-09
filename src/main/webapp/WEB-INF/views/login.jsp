<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <div class="d-flex justify-content-center align-items-center vh-100">
        <div class="card shadow-lg" style="width: 100%; max-width: 400px;">
            <div class="card-body">
                <h3 class="card-title text-center mb-4">Đăng nhập</h3>

                <c:if test="${param.incorrectAccount != null}">
                    <div class="alert alert-danger text-center">
                        Username or password incorrect
                    </div>
                </c:if>
                <c:if test="${param.accessDenied != null}">
                    <div class="alert alert-danger text-center">
                        You are not authorized
                    </div>
                </c:if>
                <c:if test="${param.sessionTimeout != null}">
                    <div class="alert alert-danger text-center">
                        Session Timeout
                    </div>
                </c:if>

                <p class="text-center text-muted mb-4">Vui lòng nhập email và mật khẩu của bạn</p>

                <form action="j_spring_security_check" id="formLogin" method="post">
                    <div class="mb-3">
                        <label for="userName" class="form-label">Email</label>
                        <input type="text" class="form-control" id="userName" name="j_username" placeholder="Your Email" required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="j_password" placeholder="Your Password" required>
                    </div>

                    <div class="form-check d-flex justify-content-between mb-3">
                        <input class="form-check-input" type="checkbox" id="rememberMe">
                        <label class="form-check-label" for="rememberMe">Ghi nhớ tài khoản</label>
                        <a href="#" class="text-muted">Quên mật khẩu?</a>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
                </form>

                <div class="text-center mt-4">
                    <p class="text-muted">Hoặc đăng nhập với</p>
                    <div>
                        <a href="#" class="btn btn-outline-primary btn-sm mx-1">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                        <a href="#" class="btn btn-outline-info btn-sm mx-1">
                            <i class="fab fa-twitter"></i>
                        </a>
                        <a href="#" class="btn btn-outline-danger btn-sm mx-1">
                            <i class="fab fa-google"></i>
                        </a>
                    </div>
                </div>

                <p class="text-center mt-4 mb-0">Chưa có tài khoản? <a href="signup" class="fw-bold text-primary">Đăng ký</a></p>
            </div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
