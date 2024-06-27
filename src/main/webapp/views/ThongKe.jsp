<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
 <section style="margin-top: 20px;">

        <div class="container">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a class="nav-link active" data-bs-toggle="tab" href="#home">Favorites</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="tab" href="#menu1">Favorites User</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-bs-toggle="tab" href="#menu2">Share Friends</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content" style="border-top:  1px solid rgb(220, 220, 220);">
                <div class="tab-pane container active mt-4" id="home">
                    <div class="row col-md-12 col-md-offset-2 custyle">
                        <table class="table table-striped custab">
                            <thead>

                                <tr>
                                    <th>Video Title</th>
                                    <th>Favorites Count</th>
                                    <th>Latest Date</th>
                                    <th>Oldest  Date</th>
                                
                                    
                                </tr>
                            </thead>
                           <c:forEach var="item" items="${like}"> 
                         	<tr>

							<th>${item.group}</th>
							<td>${item.like}</td>
							<td>${item.newest}</td>
							<td>${item.oldest}</td>
								</tr>
							</c:forEach>
                      </table>
                    </div>

                </div>

                <div class="tab-pane container fade" id="menu1">
                    <div class="row col-md-12 col-md-offset-2 custyle">
                        <div class="form-floating mt-4 mb-2">
                            <select class="form-select" id="floatingSelect" aria-label="Floating label select example">
                              <c:forEach var="item" items="${titleVideo}">
                              <option >${item.title}</option>
                        
                              </c:forEach>
                            </select>
                            <label for="floatingSelect" class=" ms-3">Video Title</label>
                          </div>
                        <table class="table table-striped custab">
                            <thead>

                                <tr>
                                    <th>User Name</th>
                                    <th>Full Name</th>
                                  
                                    <th>Email</th>
                                    <th>Favorite Date</th>
                                  
                                </tr>
                            </thead>
                            <tr>
                                <td>teolv</td>
                                <td>123123</td>
                                <td>Ngô Nhiên</td>
                                <td>01/01/2020</td>
                               
                            </tr>
                       </table>
                    </div>

                </div>


                    
                <div class="tab-pane container fade" id="menu2">
                    <div class="row col-md-12 col-md-offset-2 custyle">
                        <div class="form-floating mt-4 mb-2">
                            <select class="form-select" id="floatingSelect" aria-label="Floating label select example">
                           <c:forEach var="item" items="${titleVideo}">
                              <option >${item.title}</option>
                        
                              </c:forEach>
                            </select>
                            <label for="floatingSelect" class=" ms-3">Video Title</label>
                          </div>
                        <table class="table table-striped custab">
                            <thead>

                                <tr>
                                    <th>Sender Name</th>
                                    <th>Sender Email </th>
                                  
                                    <th>Receiver Email </th>
                                    <th>Send Date</th>
                                  
                                </tr>
                            </thead>
                            <tr>
                                <td>Nguyễn Văn Tèo</td>
                                <td>teo@gmail.com</td>
                                <td>poly</td>
                                <td>01/01/2020</td>
                               
                            </tr>
                      </table>
                    </div>

                </div>
            </div>
        </div>

    </section>