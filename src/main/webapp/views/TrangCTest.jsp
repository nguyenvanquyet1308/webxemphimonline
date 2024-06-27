<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<title>Title</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.0.11/css/all.css"
	rel="stylesheet">
<!-- Bootstrap CSS v5.2.1 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.4/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">
<link rel="stylesheet" href="/ASSIGNENMENT/css/Trangchu.css">

<style type="text/css">
.slide img {
	height: 450px;
	object-fit: fill;
}

section {
	margin-top:20px;
	margin-bottom: 20px;
}

h3 {
	font-weight: bold;
	font-family: Roboto, sans-serif;
}

.textCard {
	padding: 7px;
	font-family: Roboto, Arial, sans-serif;
	font-size: 16px;
	line-height: 20px;
	text-align: start;
}

.textCard a {
	text-decoration: none;
	color: black;
}

.col-md-3 {
	margin-bottom: 20px;
}

footer {
	background-color: #2e2e2e;
}

.ts {
	margin-top: 10px;
}

.fonFooter {
	font-size: 20px;
	font-family: Roboto, Arial, sans-serif;
	font-weight: bold;
	color: rgb(190, 190, 190);
}

.Template {
	font-size: 15px;
	font-family: Roboto, Arial, sans-serif;
	font-weight: bold;
	color: rgb(190, 190, 190);
}
.bi {
	font-size: 20px;
}
</style>
</head>

<body>
<c:url var="home" value="/views/Information" />

	<header class="topbar">
		<div class="container">
			<div class="row">
				<!-- social icon-->
				<div class="col-sm-12">
					<ul class="social-network">
						<li><a class="waves-effect waves-dark" href="#"><i
								class="bi bi-facebook"></i></a></li>
						<li><a class="waves-effect waves-dark" href="#"><i
								class="bi bi-twitter"></i></a></li>
						<li><a class="waves-effect waves-dark" href="#"><i
								class="bi bi-linkedin"></i></a></li>
						<li><a class="waves-effect waves-dark" href="#"><i
								class="bi bi-pinterest"></i></a></li>
						<li><a class="waves-effect waves-dark" href="#"><i
								class="bi bi-google"></i></a></li>
					</ul>
				</div>

			</div>
		</div>
	</header>
	<nav
		class="navbar navbar-expand-lg navbar-dark mx-background-top-linear">
		<div class="container">
			<a class="navbar-brand" rel="nofollow" target="_blank" href="/ASSIGNENMENT/user/TrangChu"
				style="text-transform: uppercase;"> <img
				src="/ASSIGNENMENT/img/logo.png" style="width: 90px;"
				alt=""> PhimMoi.com
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">

				<ul class="navbar-nav ml-auto">

					<li class="nav-item active"><a class="nav-link" href="/ASSIGNENMENT/user/TrangChu">Trang
							Chủ <span class="sr-only">(current)</span>
					</a></li>

					<li class="nav-item"><a class="nav-link" href="${home}/lienhe">Liên Hệ</a></li>

					<li class="nav-item"><a class="nav-link" href="${home}/hoidap">Hỏi Đáp</a></li>

					<li class="nav-item"><a class="nav-link" href="${home}/chungtoi">Chúng
							Tôi</a></li>

				</ul>


				<c:choose>

					<c:when test="${sessionScope.user.admin == false}">
						<ul class="navbar-nav ">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false"> <img
									src="/ASSIGNENMENT/img/logo.png" alt="Avatar Logo"
									style="width: 40px;" class="rounded-pill">
							</a>
								<ul class="dropdown-menu dropdown-menu-end">

									<li><a class="dropdown-item" href="/ASSIGNENMENT/user/videoLike">Video Đã Thích</a></li>
									<li>
										<hr class="dropdown-divider">
									</li>
									<li><a class="dropdown-item" href="/ASSIGNENMENT/user/historyWatch">Lịch sử xem</a></li>
									<li>
										<hr class="dropdown-divider">
									</li>
									<li><a class="dropdown-item"
										href="/ASSIGNENMENT/user/change">Chỉnh sửa </a></li>
									<li>
										<hr class="dropdown-divider">
									</li>

									<li><a class="dropdown-item"
										href="/ASSIGNENMENT/sign-out">Đăng Xuất</a></li>
								</ul></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="navbar-nav ">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false"> <img
									src="/ASSIGNENMENT/img/-.jpg" alt="Avatar Logo"
									style="width: 40px;" class="rounded-pill">
							</a>
								<ul class="dropdown-menu dropdown-menu-end">
									<li><a class="dropdown-item" href="/ASSIGNENMENT/user/change">Đổi mật Khẩu</a></li>
									<li>
										<hr class="dropdown-divider">
									</li>
									<li><a class="dropdown-item"
										href="/ASSIGNENMENT/manager/QLvideo">Quản lý
											Video</a></li>
									<li>
										<hr class="dropdown-divider">
									</li>
									<li><a class="dropdown-item"
										href="/ASSIGNENMENT/manager/QLnguoiDung">Người
											Dùng</a></li>
									<li>
										<hr class="dropdown-divider">
									</li>
									<li><a class="dropdown-item" href="/ASSIGNENMENT/manager/thongKe">Thống Kê</a></li>
									<li>
										<hr class="dropdown-divider">
									</li>
									<li><a class="dropdown-item"
										href="/ASSIGNENMENT/sign-out">Đăng Xuất</a></li>
								</ul></li>
						</ul>
					</c:otherwise>
				</c:choose>



			</div>
		</div>
	</nav>

	<main>

	
		<jsp:include page="${views}" />
		<jsp:include page="${viewInformation}" />
		
		


		<!-- phim -->

		<section >		
		<jsp:include page="${view}" />
		<br>
			<jsp:include page="/views/footer/footer.jsp" />

		</section>

		
	</main>








	<!-- Bootstrap JavaScript Libraries -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
		integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
		crossorigin="anonymous">
		
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
		integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
		crossorigin="anonymous">
		
	</script>
	  <script
            src="https://kit.fontawesome.com/bff65d775c.js"
            crossorigin="anonymous"
        ></script>
</body>

</html>