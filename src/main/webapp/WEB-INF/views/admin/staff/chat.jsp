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
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            text-align: center;
            padding: 50px;
        }
        .container {
            background: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            display: inline-block;
        }
        h1 {
            color: #007BFF;
        }
        p {
            font-size: 18px;
        }
    </style>
</head>

<body>
<img src="https://d34zoy7mey8f6f.cloudfront.net/article_avatar/1577441826.jpg" height="700px" width="1100px" alt="" style="margin-left: 170px ; margin-top: 60px ">


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
