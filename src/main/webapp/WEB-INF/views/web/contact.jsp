<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="assets/js/ace-extra.min.js"></script>
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="assets/font-awesome/4.2.0/css/font-awesome.min.css" />
    <title>Liên hệ</title>


</head>
<body>
<div class="page-wrapper">
    <script type="text/javascript">
        try { ace.settings.check('main-container', 'fixed') } catch (e) { }
    </script>
    <!-- INTRO  -->
    <div class="intro text-center mb-5">
        <div class="title-page">Liên hệ</div>
        <div class="row">
            <div class="col-xs-12 a-left">
                <ul class="desc-intro">
                    <li class="home">
                        <a href="./ViewHome.html"><span style="color:#fff">Trang chủ</span></a>
                        <span class="mx-1" style="color:#fff"> / </span>
                    </li>
                    <li class="intro-item"><span>Liên hệ</span></li>
                </ul>
            </div>
        </div>
    </div>

    <!-- CONTENT  -->
    <div class="map">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="google-map margin-bottom-30">
                        <div class="maps_iframe">
                            <iframe style="width: 100%;"
                                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.577731862711!2d106.7695372142139!3d10.850261760047032!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752f8d0e993b05%3A0x8abf4c480f8b822b!2zOTcgTWFuIFRoacOqbiwgUGjDuiBIaeG7h3AgSMaw4budYywgbmjhuq10IHPDumMgVHJ1w6JuIFbEg25nLCBWaeG7h3QgTmFt!5e0!3m2!1sen!2s!4v1652520257601!5m2!1sen!2s"
                                    width="600" height="450" style="border:0;" allowfullscreen=""
                                    loading="lazy"></iframe>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="box-lienhe mt-5">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-6">
                    <h2 class="title-lienhe"><strong>Công ty cổ phần bất động sản HODHouse</strong></h2>
                    <div class="desc-lienhe">
                        <p>Được thành lập vào ngày 10/12/2024 với niềm đam mê và khát vọng thành công trong lĩnh vực
                            bất động sản. Nhờ chiến lược rõ ràng và hướng đi đúng, HODHouse đã nhanh chóng phát triển
                            và đạt được những thành công nhất định.</p>
                        <ul class="margin-bottom-15 link" style="list-style-type: none;">
                            <li>
                                <span class="block_fonticon"><i class="fa fa-map-marker icon-lienhe"></i></span>
                                <p class="desc-footer">Số 26 , Nguyễn Thái Học , Phường Đội Cung </p> Thành Phố Vinh , Nghệ An
                            </li>
                            <li>
                                <span class="block_fonticon"><i class="fa fa-mobile icon-lienhe"></i></span>
                                <span class="title-li">
                                        Hotline: <a style="color: #434a6e;" class="fone" href="">0846014482</a>

                                    </span>
                            </li>
                            <li>
                                <span class="block_fonticon"><i class="fa fa-envelope icon-lienhe"></i></span>
                                <span class="title-li">Email: <a style="color: #434a6e;"
                                                                 href="">hvan8677@gmail.com</a></span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-12 col-md-6">
                    <h2 class="title-lienhe"><strong>Liên hệ với chúng tôi</strong></h2>
                    <form method="get" action="/web/contact" id="form-edit">
                        <div class="row">
                            <div class="col">
                                <input type="text" name="fullName" id="name" class="form-control" placeholder="Tên đầy đủ" >
                            </div>
                            <div class="col">
                                <input type="text" name="email" id="email" class="form-control" placeholder="Email" >
                            </div>
                        </div>
                        <input type="text" name="phone" id="phone" class="form-control" placeholder="Số điện thoại">
                        <input type="text" name="demand" id="demand" class="form-control" placeholder="Ghi chú" >
                        <div class="row text-center">
                            <div class="col-xs-9 col-xs-offset-3">
                                <button type="submit" class="btn btn-primary" id="btnAddorUpdateCustomer" style="margin-left: 10px ; margin-top: 5px ; background: #35bf76;">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                         fill="currentColor" class="bi bi-save" viewBox="0 0 16 16">
                                        <path
                                                d="M8 0a2 2 0 0 0-2 2H4a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2h-2a2 2 0 0 0-2-2zM4 3a1 1 0 0 1 1-1h2v4H4V3zm5 0a1 1 0 0 1 1 1v4H9V3h2zM4 8v5h8V8H4z" />
                                    </svg>
                                    Gửi liên hệ
                                </button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>

    </div>
    <!-- FOOTER  -->
    <footer class="footer">
        <div class="container">
            <div class="top-footer text-center mt-0">
                <div class="logo logo-footer pt-5">
                    <a href="./ViewHome.html"><img src="https://scontent.xx.fbcdn.net/v/t1.15752-9/462564059_568102029320883_3253807442937868109_n.png?stp=dst-png_s480x480&_nc_cat=103&ccb=1-7&_nc_sid=0024fc&_nc_ohc=xS-ovLIsd6YQ7kNvgE4dsGF&_nc_ad=z-m&_nc_cid=0&_nc_zt=23&_nc_ht=scontent.xx&oh=03_Q7cD1gHTEVDHcUth5_fttvo71RUO-VExv1ori4vW1iQNhjEK9A&oe=678A6A76"
                                                   width="100px" height="100px"  alt="logo-footer"></a>
                    <p class="desc-logo-footer mt-3">Với hơn 10 năm kinh nghiệm, HODHOUSE tự hào là sàn
                        mua
                        bán, giao dịch và quảng cáo
                        bất động sản hàng đầu tại Việt Nam</p>
                    <div class="item-footer mt-5">
                        <div class="row">
                            <div class="col-12 col-md-4 text-center">
                                <div class="icon-footer">
                                    <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_maps.png?1676257083798" alt="">
                                </div>
                                <div class="content-center-footer">
                                    <p class="mb-1 mt-3">Trụ sở chính</p>
                                    <p class="desc-footer">Số 26 , Nguyễn Thái Học , Phường Đội Cung </p> Thành Phố Vinh , Nghệ An
                                </div>
                            </div>
                            <div class="col-12 col-md-4 text-center">
                                <div class="icon-footer">
                                    <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_phone.png?1676257083798 alt=">
                                </div>
                                <div class="content-center-footer">
                                    <p class="mb-1 mt-3">Hotline</p>
                                    <p class="desc-footer"><a class="a-text" href="#">0846014482</a></p>
                                </div>
                            </div>
                            <div class="col-12 col-md-4 text-center">
                                <div class="icon-footer">
                                    <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_email.png?1676257083798g" alt="">
                                </div>
                                <div class="content-center-footer">
                                    <p class="mb-1 mt-3">Email</p>
                                    <p class="desc-footer"><a class="a-text" href="#">hvan8677@gmail.com</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="text-center">
                    <div class="border-bottom mb-5 mt-4"></div>
                </div>
            </div>
            <div class="bottom-footer">
                <div class="row">
                    <div class="col-12 col-md-3">
                        <h4 class="title-item-bottom-footer">Thông tin công ty</h4>
                        <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                            chủ</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                            sản</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                    </div>
                    <div class="col-12 col-md-3">
                        <h4 class="title-item-bottom-footer">Chính sách hoạt động</h4>
                        <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                            chủ</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                            sản</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                    </div>
                    <div class="col-12 col-md-3">
                        <h4 class="title-item-bottom-footer">Hỗ trợ khách hàng</h4>
                        <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                            chủ</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                            sản</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                    </div>
                    <div class="col-12 col-md-3">
                        <h4 class="title-item-bottom-footer">Kết nối với chúng tôi</h4>
                        <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                            chủ</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                            sản</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                        <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="bottom-footer-2">
            <div class="text-center desc-bottom-footer-2">@ Bản quyền thuộc về Happy Team |
                Cung cấp bởi <a class="a-text group-name" href="#">HappyTeam</a></div>
        </div>

    </footer>
</div>

<!-- basic scripts -->
<!-- Đảm bảo jQuery được tải trước -->
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
<script>
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
        $('#successModal').modal('show');
        $('#successModal').on('hidden.bs.modal', function () {
            document.getElementById("form-edit").submit(); // Submit form
        });
    }

    $('#btnAddorUpdateCustomer').click(function (event) {
        var formData = $('#form-edit').serializeArray(); // mảng các object
        var convertJson = {}; // 1 Object


        $.each(formData, function (i, v) {
            if (v.name === 'status') {
                convertJson['status'] = "chưa xử lý";
            } else {
                convertJson[v.name] = v.value;
            }
        });

        const nameField = document.getElementById("name");
        const phoneField = document.getElementById("phone");
        if ( nameField.value.trim() === "" || phoneField.value.trim() === "" ) {
            event.preventDefault();
            infomation(); // Hiển thị modal lỗi
        }
        else {
            event.preventDefault(); // Ngăn form tự động submit
            btnAddorUpdateCustomer(convertJson);
        }
    });




    function btnAddorUpdateCustomer(json) {
        $.ajax({
            url: "/api/customers/nologin",
            type: "POST",
            data: JSON.stringify(json),
            contentType: "application/json",
            success: function(result) {
                alert("Thêm Thành Công , Xin Cảm Ơn !!! ")

                // inFormationSucces();
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
