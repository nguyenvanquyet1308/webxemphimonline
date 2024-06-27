<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <div class="container rounded bg-white mt-5 mb-5">
        <div class="row">
            <div class="col-md-3 border-right">
                <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">${sessionScope.user.fullname}</span><span class="text-black-50">${sessionScope.user.email}</span><span> </span></div>
            </div>
            <div class="col-md-5 border-right">
                <div class="p-3 py-5">
                		<div class="alert alert-success" role="alert">
					${message}
</div>
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right">Chỉnh Sửa</h4>
                    </div>
                <form action="/ASSIGNENMENT/user/profile" method="post">
                    <div class="row mt-3">
                        <div class="col-md-12"><label class="labels">Tài Khoảng</label><input name="id" type="text" class="form-control" placeholder="Tài Khoảng" value="${sessionScope.user.id}"></div>
                        <div class="col-md-12"><label class="labels">Mật Khẩu</label><input name="password" type="text" class="form-control" placeholder="Mật Khẩu" value="${sessionScope.user.password}"></div>
                        <div class="col-md-12"><label class="labels">Tên Đầy Đủ</label><input name="fullname" type="text" class="form-control" placeholder="Tên Đầy Đủ" value="${sessionScope.user.fullname}"></div>                     
                        <div class="col-md-12"><label class="labels">Email </label><input name="email" type="text" class="form-control" placeholder="Email" value="${sessionScope.user.email}"></div>
                    </div>
                    
                    <div class="mt-5 text-center"><button  class="btn btn-primary profile-button" type="submit">Lưu Thông Tin</button></div>
                </form>
                </div>
            </div>
            <div class="col-md-4">
                <div class="p-3 py-5">
                  <form action="/ASSIGNENMENT/change/passNew" method="post">
                    <div class="d-flex justify-content-between align-items-center experience"><span>Chỉnh Sửa Mật Khẩu</span><span class="border px-3 p-1 add-experience">&nbsp;<button  class="btn btn-primary profile-button" type="submit"> <i class="fa fa-plus"> Lưu</i></button></span></div><br>
                    <div class="col-md-12"><label class="labels">Mật Khẩu Cũ</label><input name="passcu" type="text" class="form-control" placeholder="Mật Khẩu Cũ" value=""></div> <br>
                    <div class="col-md-12"><label class="labels">Mật Khẩu Mới</label><input name="passmoi" type="text" class="form-control" placeholder="Mật Khẩu Mới" value=""></div>
                 </form>
                </div>
            </div>
        </div>
    </div>
