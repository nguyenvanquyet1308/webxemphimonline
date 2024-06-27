<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
	<div class="container">
				<div class="row">
					<div class="col-md-9">
						<h3>Tất Cả Phim</h3>
					</div>
					<div class="col-md-3">
			   <form class="d-flex" role="search" action="/ASSIGNENMENT/selectVideoKey/*">
	        <input name="title" class="form-control me-2"  placeholder="Tìm Kiếm" aria-label="Search"> 
			        <button class="btn btn-outline-success" type="submit">Search as</button>
			      </form>
					</div>
				</div>

				<div class="row">
				
				<c:forEach var="item" items="${VideoKey}">
					<div class="col-md-3">
						<div class="card h-100">
							<img src="/ASSIGNENMENT/img/${item.poster}"
								class="card-img-top " alt="..." style="height:180px ;object-fit: fill;">
							<div class="card-body textCard">
								<a href="/ASSIGNENMENT/ChiTiet">
									<p class="card-title">${item.title}</p>
								</a>
							</div>
							<div class="card-footer">
								<small class="text-muted">Last updated 3 mins ago</small>
							</div>
						</div>
					</div>
					</c:forEach>
				</div>



			</div>
			<nav aria-label="Page navigation example ">
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link" href="#">Previous</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">Next</a></li>
				</ul>
			</nav>