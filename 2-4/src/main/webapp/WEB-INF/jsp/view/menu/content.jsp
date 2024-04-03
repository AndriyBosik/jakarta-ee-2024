<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>

<div class="container">
    <div class="row">
        <c:if test="${errorCode == 'TRANSACTION_ROLLBACK'}">
            <div class="col s12">
                <div class="transaction-alert cart red lighten-2 p10 white-text">
                    <div class="s-hflex weight-strong" style="font-size: 20px">
                        The operation was canceled due to unknown reason
                    </div>
                </div>
            </div>
        </c:if>
        <div class="col s12">
            <div class="col s3">
                <ul class="collection category-list">
                    <c:forEach
                            items="${categories}"
                            var="category"
                    >
                        <li class="collection-item ${category.id == selectedCategoryId ? 'selected' : ''}">
                            <a href="/menu/${category.id}"><c:out value="${category.title}" /></a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col s9">
                <div class="py20">
                    <div class="col s12">

                        <tf:forAdmin>
                            <div class="food-item-wrapper col s6 m6 l4 p5">
                                <div class="food-item add">
                                    <div class="s-vflex-center clickable full-width full-height">
                                        <a href="/admin/food/create">+</a>
                                    </div>
                                </div>
                            </div>
                        </tf:forAdmin>

                        <c:forEach items="${foodPage.content}" var="foodItem">
                            <div class="food-item-wrapper col s6 m6 l4 p5">
                                <div class="food-item">
                                    <div class="full-width s-hflex">
                                        <div class="food-title truncate s-vflex-center">
                                            <c:out value="${foodItem.title}" />
                                        </div>
                                        <tf:forAdmin>
                                            <div class="clickable food-edit food-action s-vflex-center">
                                                <a href="/admin/food/update/${foodItem.id}">
                                                    <i class="material-icons">edit</i>
                                                </a>
                                            </div>
                                            <div style="width: 10px;"></div>
                                            <div class="food-delete food-action s-vflex-center">
                                                <form action="/admin/food/delete/${foodItem.id}" method="post"
                                                      style="margin: 0">
                                                    <button type="submit">
                                                        <i class="clickable material-icons">delete</i>
                                                    </button>
                                                </form>
                                            </div>
                                        </tf:forAdmin>
                                    </div>
                                    <div class="food-image" style="background-image: url(${foodItem.imagePath})"></div>
                                    <div class="equal-flex"></div>
                                    <div class="food-price s-hflex">
                                        <div class="food-price-title">
                                            Price:
                                        </div>
                                        <div class="equal-flex"></div>
                                        <div class="food-price-value">
                                            <fmt:formatNumber value="${foodItem.price}" type="number"
                                                              minFractionDigits="2"
                                                              maxFractionDigits="2"/> UAH
                                        </div>
                                    </div>
                                    <div class="food-actions full-width s-hflex-start">
                                        <div>
                                            <a href="/food/${foodItem.id}"
                                               class="btn btn-small waves-effect waves-light">View</a>
                                        </div>
                                        <div class="equal-flex"></div>
                                        <div class="s-vflex-center">
                                            <c:choose>
                                                <c:when test="${sessionScope.cart.containsKey(foodItem.id)}">
                                                    <div class="s-hflex-center presence-label">
                                                        <div class="s-vflex-center">
                                                            <i class="material-icons">check</i>
                                                        </div>
                                                        <div class="s-vflex-center">
                                                            Added
                                                        </div>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <form action="/cart/add?foodId=${foodItem.id}" method="post">
                                                        <button type="submit"
                                                                class="blue btn btn-small waves-effect waves-light">Add
                                                            to
                                                            cart
                                                        </button>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
