<%@ page language="java" contentType="text/html; charset= utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<style>
</style>

<section style="margin-top: 20px;">
	<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
  <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
    <path
			d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z" />
  </symbol>
  <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
    <path
			d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z" />
  </symbol>
  <symbol id="exclamation-triangle-fill" fill="currentColor"
			viewBox="0 0 16 16">
    <path
			d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
  </symbol>
</svg>


	<div class="row">
		<div class="col">

			<c:if test="${not empty message}">
				<div class="alert alert-success d-flex align-items-center"
					role="alert">
					<svg class="bi flex-shrink-0 me-2" width="24" height="24"
						role="img" aria-label="Success:">
							<use xlink:href="#check-circle-fill" /></svg>
					<div>${message}</div>
				</div>
			</c:if>
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error }</div>
			</c:if>
		</div>
	</div>

	<div class="container">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link active"
				data-bs-toggle="tab" href="#home">Video Edition</a></li>
			<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
				href="#menu1">Video List</a></li>

		</ul>
		<c:url var="url" value="/user" />
		<!-- Tab panes -->
		<div class="tab-content"
			style="border-top: 1px solid rgb(220, 220, 220);">
			<div class="tab-pane container active mt-4" id="home">

				<form action="/editVideo" method="post"
					enctype="multipart/form-data">
					<div class="row">
						<div class="col-sm-4">
							<div class="card text-bg-dark">
								<img src="/ASSIGNENMENT/${form.poster}" class="card-img"
									alt="...">
								<div class="card-img-overlay"></div>
							</div>

							<div class="mb-3">
								<label for="formFile" class="form-label">Chọn Poster</label> <input
									class="form-control" value="${form.poster}" name="cover"
									type="file" id="formFile">
							</div>
						</div>

						<div class="col-sm-8">
							<label for="text">ID</label> <input name="id" value="${form.id}"
								id="text" class="form-control" aria-label="Search"> <label
								for="text">Tiêu Đề</label> <input name="title"
								value="${form.title}" id="text" class="form-control"
								aria-label="Search"> <label for="text">Lượt xem
							</label><input name="views" value="${form.views}" id="text"
								class="form-control" aria-label="Search">
							<div class="d-flex mt-2 ">
								<div class="form-check mx-3">
									<input class="form-check-input" type="radio"
										name="flexRadioDefault" id="flexRadioDefault1"
										${form.active?'checked':''}> <label
										class="form-check-label" for="flexRadioDefault1">
										Active </label>
								</div>
								<div class="form-check ">
									<input class="form-check-input" type="radio"
										name="flexRadioDefault" id="flexRadioDefault2"
										${form.active?'':'checked'}> <label
										class="form-check-label" for="flexRadioDefault2">
										InActive </label>
								</div>
							</div>
							<div class="mb-3">
								<label for="text">Miêu Tả</label> <label for=""
									class="form-label"></label>
								<textarea class="form-control" name="description" id="" rows="3">${form.description} </textarea>
							</div>


							<button formaction="${url}/createVideo" class="btn btn-danger">
								<i class="bi bi-node-plus-fill" style="color: white;"> Tạo</i>
							</button>
							<button formaction="${url}/updateVideo" class="btn btn-danger">
								<i class="bi bi-pencil-square" style="color: white;"> Chỉnh
									sửa</i>
							</button>
							<button formaction="${url}/deleteVideo/${form.id}"
								class="btn btn-danger">
								<i class="bi bi-trash3" style="color: white;"> Xóa</i>
							</button>
							<button formaction="/ASSIGNENMENT/manager/QLvideo"
								class="btn btn-danger">
								<i class="bi bi-lightning" style="color: white;"> Tải Lại</i>
							</button>
						</div>
					</div>
				</form>
			</div>
			<div class="tab-pane container fade" id="menu1">
				<div class="row col-md-12 col-md-offset-2 custyle">

					<table class="table table-striped custab">
						<thead>

							<tr>
								<th>ID</th>
								<th>Tiêu Đề</th>
								<th>Lượt xem</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<c:forEach var="item" items="${videoALL}">
							<tr>
								<td>${item.id}</td>
								<td>${item.title}</td>
								<td>${item.views}</td>
								<td class="text-center"><a class='btn btn-info btn-xs'
									href="/ASSIGNENMENT/editVideo/${item.id}"><i
										class="bi bi-pencil-square"> </i></a>
										<a
									href="/ASSIGNENMENT/user/deleteVideo/${item.id}" class="btn btn-danger btn-xs"><i
										class="bi bi-trash3"> </i></a>
							</tr>

						</c:forEach>

					</table>
				</div>

			</div>

		</div>
	</div>

</section>

