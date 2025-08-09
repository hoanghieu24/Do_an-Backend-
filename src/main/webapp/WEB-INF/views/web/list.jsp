<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE >
<html >

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="assets/js/ace-extra.min.js"></script>
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="assets/font-awesome/4.2.0/css/font-awesome.min.css" />

    <title>Sản phẩm</title>

</head>


<body>
<div class="page-wrapper">
    <script type="text/javascript">
        try { ace.settings.check('main-container', 'fixed') } catch (e) { }
    </script>
    <div class="intro text-center">

        <div class="title-page">Tất cả dự án</div>
        <div class="row">
            <div class="col-xs-12 a-left">
                <ul class="desc-intro">
                    <li class="home">
                        <a href="./ViewHome.html"><span style="color:#fff">Trang chủ</span></a>
                        <span class="mx-1" style="color:#fff"> / </span>
                    </li>
                    <li class="intro-item"><span>Tất cả sản phẩm</span></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- SEARCH  -->
    <div class="search">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-3 search-item">
<%--                    <p class="search-text">Chọn tỉnh/thành phố</p>--%>
<%--                    <select class="search-option" name="search-option" id="search-option">--%>
<%--                        <option value>- Tỉnh/thành phố</option>--%>
<%--                        <option value="">TP.Đà Nẵng</option>--%>
<%--                        <option value="">TP.Hồ Chí Minh</option>--%>
<%--                        <option value="">TP.Hà Nội</option>--%>
<%--                        <option value="">TP.Cần Thơ</option>--%>
<%--                    </select>--%>
                </div>
                <div class="col-12 col-md-3 search-item">
<%--                    <p class="search-text">Chọn quận/huyện</p>--%>
<%--                    <select class="search-option" name="search-option" id="search-option">--%>
<%--                        <option value>- Quận/huyện</option>--%>
<%--                    </select>--%>
                </div>

                <div class="col-12 col-md-3 search-item">
<%--                    <p class="search-text">Chọn loại bất động sản</p>--%>
<%--                    <select class="search-option" name="search-option" id="search-option">--%>
<%--                        <option value>- Loại bất động sản</option>--%>
<%--                    </select>--%>
                </div>
                <div class="col-12 col-md-3 search-btn">
<%--                    <button class="search-btn-text pb-0">--%>
<%--                        <i class="fa-solid fa-magnifying-glass search-btn-icon"></i>--%>
<%--                        <span>Tìm kiếm nhanh</span>--%>
<%--                    </button>--%>
                </div>
            </div>
        </div>
    </div>

   <!-- CONTENT  -->
<div class="product mt-5">
    <div class="container">
        <div class="row">
    <c:forEach var="product" items="${buildings}">
        <div class="col-md-4 mb-4">
            <div class="product-item shadow-lg rounded-3 p-4 bg-white" style="border-radius: 15px;">
                <div class="product-image text-center mb-3">
                    <c:if test="${not empty product.image}">
                        <c:set var="imagePath" value="/repository${product.image}" />
                        <img src="${imagePath}" id="viewImage" class="img-fluid rounded-3" alt="${product.nameBuilding}">
                    </c:if>
                    <c:if test="${empty product.image}">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQy1jLLpu9YFLuAcUg6kK-UkzdUfOCzjRv1XQ&s"
                             id="viewImage" class="img-fluid rounded-3" alt="${product.nameBuilding}">
                    </c:if>
                </div>
                <div class="product-info text-center">
                    <h3 class="product-title text-dark font-weight-bold">${product.nameBuilding}</h3>
                    <p class="text-muted">Quản lý: <span class="text-dark font-weight-semibold">${product.managername}</span></p>
                    <p class="text-muted">Địa chỉ: <span class="text-dark">${product.address}</span></p>
                    <p class="price text-primary font-weight-bold">${product.formattedRentPrice} VND</p>

                    <!-- Nút Chi tiết mở modal -->
                    <button class="btn btn-success btn-sm mt-3 rounded-3"
                            style="border-radius: 10px;"
                            onclick="openBuildingModal(
                                '${product.id}',
                                '${product.nameBuilding}',
                                '${product.managername}',
                                '${product.address}',
                                '${product.formattedRentPrice}',
                                '${product.floorarea}',
                                '${product.numberOfBasement}',
                                '${product.staffid.size()}',
                                '${product.servicefee}',
                                '${product.brokerage_fee}',
                                '${product.empty_area}',
                                '${product.rental_area}',
                                '${product.type}',
                                '${product.deposit}',
                                '${product.rentTime}',
                                '${product.decorationTime}',
                                '${product.carfee}',
                                '${product.motofee}',
                                '${product.direction}',
                                '${product.strucTure}',
                                '${product.note}'
                            )">
                        Chi tiết
                    </button>
                </div>
            </div>
        </div>
    </c:forEach>
<%--<div class="pagination mt-4">--%>
<%--    <c:if test="${ModelSearch.page > 1}">--%>
<%--        <a href="/san-pham?page=${ModelSearch.page - 1}" class="btn btn-primary">Trước</a>--%>
<%--    </c:if>--%>

<%--    <c:forEach begin="1" end="${ModelSearch.totalPage}" var="i">--%>
<%--        <a href="/san-pham?page=${i}" class="btn btn-outline-primary ${i == ModelSearch.page ? 'active' : ''}">${i}</a>--%>
<%--    </c:forEach>--%>

<%--    <c:if test="${ModelSearch.page < ModelSearch.totalPage}">--%>
<%--        <a href="/san-pham?page=${ModelSearch.page + 1}" class="btn btn-primary">Tiếp theo</a>--%>
<%--    </c:if>--%>
<%--</div>--%>

    <!-- Modal hiển thị chi tiết tòa nhà -->
    <div class="modal fade" id="BuildingDetailModal" style="margin-top: 0px">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalBuildingTitle" style="text-align: center; font-weight: bold;">Chi tiết tòa nhà</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p><strong>Tên tòa nhà:</strong> <span id="buildingName"></span></p>
                    <p><strong>Quản lý:</strong> <span id="buildingManager"></span></p>
                    <p><strong>Địa chỉ:</strong> <span id="buildingAddress"></span></p>
                    <p><strong>Diện tích sàn:</strong> <span id="floorareas"></span> </p>
                    <p><strong>Giá thuê:</strong> <span id="buildingPrice"></span> </p>
                    <p><strong>Số tầng hầm:</strong> <span id="buildingBasement"></span></p>
                    <p><strong>Số lượng nhân viên:</strong> <span id="buildingStaffCount"></span></p>
                    <p><strong>Phí dịch vụ:</strong> <span id="serviceFee"></span> VND</p>
                    <p><strong>Phí môi giới:</strong> <span id="brokerageFee"></span> VND</p>
                    <p><strong>Khu vực trống:</strong> <span id="emptyArea"></span> m²</p>
                    <p><strong>Khu vực cho thuê:</strong> <span id="rentalArea"></span> m²</p>
                    <p><strong>Loại tòa nhà:</strong> <span id="buildingType"></span></p>
                    <p><strong>Phí đặt cọc:</strong> <span id="depositFee"></span> VND</p>
                    <p><strong>Thời gian thuê:</strong> <span id="rentTime"></span> tháng</p>
                    <p><strong>Thời gian trang trí:</strong> <span id="decorationTime"></span> tháng</p>
                    <p><strong>Phí xe ô tô:</strong> <span id="carFee"></span> VND</p>
                    <p><strong>Phí xe máy:</strong> <span id="motoFee"></span> VND</p>
                    <p><strong>Hướng:</strong> <span id="buildingDirection"></span></p>
                    <p><strong>Kết cấu:</strong> <span id="buildingStructure"></span></p>
                    <p><strong>Ghi chú:</strong> <span id="buildingNote"></span></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info rounded-3" data-dismiss="modal" onclick="window.location.href='/lien-he'">Liên Hệ Ngay</button>
                    <button type="button" class="btn btn-secondary rounded-3" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>
</div>


    </div>
</div>



    <footer class="footer">
        <div class="container-fluid">
            <div class="top-footer text-center mt-0">
                <div class="logo logo-footer pt-5">
                    <a href="./ViewHome.html"><img src="https://scontent.xx.fbcdn.net/v/t1.15752-9/462564059_568102029320883_3253807442937868109_n.png?stp=dst-png_s480x480&_nc_cat=103&ccb=1-7&_nc_sid=0024fc&_nc_ohc=xS-ovLIsd6YQ7kNvgE4dsGF&_nc_ad=z-m&_nc_cid=0&_nc_zt=23&_nc_ht=scontent.xx&oh=03_Q7cD1gHTEVDHcUth5_fttvo71RUO-VExv1ori4vW1iQNhjEK9A&oe=678A6A76"
                                                   width="100px" height="100px"  alt="logo-footer"></a>
                    <p class="desc-logo-footer mt-3">Với hơn 10 năm kinh nghiệm, SkyLand tự hào là sàn
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
                                    <p class="desc-footer">Số 46 Man Thiện, TP Thủ Đức, TP HCM</p>
                                </div>
                            </div>
                            <div class="col-12 col-md-4 text-center">
                                <div class="icon-footer">
                                    <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_phone.png?1676257083798" alt="">
                                </div>
                                <div class="content-center-footer">
                                    <p class="mb-1 mt-3">Hotline</p>
                                    <p class="desc-footer"><a class="a-text" href="#">098828</a></p>
                                </div>
                            </div>
                            <div class="col-12 col-md-4 text-center">
                                <div class="icon-footer">
                                    <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_email.png?1676257083798" alt="">
                                </div>
                                <div class="content-center-footer">
                                    <p class="mb-1 mt-3">Email</p>
                                    <p class="desc-footer"><a class="a-text" href="#">vsh@gmail.com</a>
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
            <div class="bottom-footer container">
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
    </footer>
</div>

<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



<script>
    // function escapeHtml(str) {
    //     var element = document.createElement('div');
    //     if (str) {
    //         element.innerText = str;
    //         element.textContent = str;
    //     }
    //     return element.innerHTML;
    // }

    function openBuildingModal(id, name, manager, address, price, floorarea, basement, staffCount, serviceFee, brokerageFee, emptyArea, rentalArea, type, deposit, rentTime, decorationTime, carFee, motoFee, direction, structure, note) {
        // Cập nhật nội dung modal
        document.getElementById('modalBuildingTitle').innerText =  name;
        document.getElementById('buildingName').innerText = name;
        document.getElementById('buildingManager').innerText = manager;
        document.getElementById('buildingAddress').innerText = address;
        document.getElementById('floorareas').innerText = floorarea + " m²";
        document.getElementById('buildingPrice').innerText = price + " VND";
        document.getElementById('buildingBasement').innerText = basement;
        document.getElementById('buildingStaffCount').innerText = staffCount;
        document.getElementById('serviceFee').innerText = serviceFee;
        document.getElementById('brokerageFee').innerText = brokerageFee;
        document.getElementById('emptyArea').innerText = emptyArea;
        document.getElementById('rentalArea').innerText = rentalArea;
        document.getElementById('buildingType').innerText = type;
        document.getElementById('depositFee').innerText = deposit;
        document.getElementById('rentTime').innerText = rentTime;
        document.getElementById('decorationTime').innerText = decorationTime;
        document.getElementById('carFee').innerText = carFee;
        document.getElementById('motoFee').innerText = motoFee;
        document.getElementById('buildingDirection').innerText = direction;
        document.getElementById('buildingStructure').innerText = structure;
        document.getElementById('buildingNote').innerText = note;

        // Hiển thị modal
        $('#BuildingDetailModal').modal('show');
    }

    function openModal(productid) {
        $('#AssỉngmentBuildingModdal').modal();
        $('#productId').val(productid);
        loadProduct(productid);
    }

    function loadProduct(productid) {
        $.ajax({
            url: "/api/product/" + productid,
            type: "GET",
            dataType: 'json',
            success: function(reponse) {
                console.log("Thanh Cong ");
            },
            error: function(error) {
                console.log("Error: " + error);

            }
        });
    }
</script>

</body>



</html>