<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>

<div class="container">
    <div class="row py20 food-container">
        <div class="col s4">
            <div class="s-vflex">
                <div class="image" style="background-image: url('${food.imagePath}')"></div>
                <div class="price s-hflex">
                    <div class="price-title">
                        Price:
                    </div>
                    <div class="equal-flex"></div>
                    <div class="price-value">
                        <fmt:formatNumber value="${food.price}" type="number" minFractionDigits="2" maxFractionDigits="2" /> UAH
                    </div>
                </div>
                <div class="cart">
                    <c:choose>
                        <c:when test="${sessionScope.cart.containsKey(food.id)}">
                            <div class="s-hflex-end presence-label">
                                <div class="s-vflex-center">
                                    <i class="material-icons">check</i>
                                </div>
                                <div class="s-vflex-center">
                                    Added
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <form action="/cart/add?foodId=${food.id}" method="post">
                                <button type="submit" class="blue btn btn-small waves-effect waves-light">Add to cart</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="col s8 content px2">
            <div class="s-hflex">
                <div class="title truncate">${food.title}</div>
                <div class="equal-flex"></div>
                <tf:forAdmin>
                    <div class="action-icon s-vflex-center clickable">
                        <i class="material-icons small update">edit</i>
                    </div>
                    <div class="action-icon s-vflex-center clickable">
                        <i class="material-icons small delete">delete_forever</i>
                    </div>
                </tf:forAdmin>
            </div>
            <div class="category">${food.category.title}</div>
            <div class="description">${food.description}</div>
        </div>
    </div>
</div>
