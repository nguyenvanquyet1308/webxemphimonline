<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<style>
.input input {
	margin-bottom: 10px
}
</style>
<section style="margin-top: 20px;">

	<div class="container">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link active"
				data-bs-toggle="tab" href="#home">Người Dùng </a></li>
			<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
				href="#menu1">Danh sách người dùng</a></li>

		</ul>
		<c:url var="url" value="/user" />
		<!-- Tab panes -->
		<div class="tab-content"
			style="border-top: 1px solid rgb(220, 220, 220);">
			<div class="tab-pane container active mt-4" id="home">

				<form action="/editUsers" method="post">
					<div class="input mb-3">

						<label>Tên:</label> <input type="text" name="fullname"
							value="${form.fullname}" id="name" class="form-control"
							placeholder="Họ và tên"> <label>Tài Khoản:</label> <input
							disabled="true" type="text" name="id" value="${form.id}"
							id="name" class="form-control" placeholder="Tên Tài Khoảng">
						<label>Mật Khẩu:</label> <input type="password" name="password"
							value="${form.password}" id="name" class="form-control"
							placeholder="Mật Khẩu"> <label>Email:</label> <input
							type="email" name="email" value="${form.email}" id="name"
							class="form-control" placeholder="Email">
					</div>

					<button formaction="${url}/update" class="btn btn-primary mx-9px"
						style="margin-left: 20px;">
						<i class="bi bi-pencil-square"> Chỉnh sửa</i>
					</button>
					<button formaction="${url}/delete/${form.id}"
						class="btn btn-primary">
						<i class="bi bi-trash3"> Xóa</i>
					</button>
				</form>
			</div>

			<div class="tab-pane container fade" id="menu1">
				<div class="row col-md-12 col-md-offset-2 custyle">
					<table class="table table-striped custab">
						<thead>

							<tr>
								<th>Tài Khoảng</th>
								<th>Mật Khẩu</th>
								<th>Họ và tên</th>
								<th>Email</th>
								<th>Chứ Vụ</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<c:forEach var="item" items="${userAll}">
							<tr>

								<td>${item.id}</td>
								<td>${item.password}</td>
								<td>${item.fullname}</td>
								<td>${item.email}</td>
								<td>${item.admin?'Admin':'User'}</td>
								<td class="text-center"><a class='btn btn-info btn-xs'
									href="/ASSIGNENMENT/editUsers/${item.id}"><i
										class="bi bi-pencil-square"> </i> </a> <a
									href="/ASSIGNENMENT/user/delete/${item.id}" class="btn btn-danger btn-xs"><i
										class="bi bi-trash3"> </i></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>

			</div>

		</div>
	</div>
	<div style="text-align: center;">
		<c:if test="${not empty message}">
			<div class="alert alert-success d-flex align-items-center"
				role="alert">
				<svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img"
					aria-label="Success:">
							<use xlink:href="#check-circle-fill" /></svg>
				<div>${message}</div>
			</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error }</div>
		</c:if>
	</div>

</section>

