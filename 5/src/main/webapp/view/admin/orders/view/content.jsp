<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="https://www.example.com/functions" %>

<div class="container">
    <div class="row">
        <h3>Orders</h3>
        <table class="orders-table">
            <thead>
            <tr>
                <th>#</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Total</th>
                <th>Date</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${orders}" var="order" varStatus="loop">
                <tr>
                    <td>
                            ${loop.index + 1}
                        <input type="hidden" name="id" value="${entry.value.id}"/>
                    </td>
                    <td>${order.firstName}</td>
                    <td>${order.lastName}</td>
                    <td>${order.total} UAH</td>
                    <td>
                            ${f:formatLocalDateTime(order.createdDate, 'HH:mm:ss dd-MM-yyyy')}
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${order.status == 'PENDING'}">
                                <div class="order-status pending">
                                        ${order.status}
                                </div>
                            </c:when>
                            <c:when test="${order.status == 'APPROVED'}">
                                <div class="order-status approved">
                                        ${order.status}
                                </div>
                            </c:when>
                            <c:when test="${order.status == 'REJECTED'}">
                                <div class="order-status rejected">
                                        ${order.status}
                                </div>
                            </c:when>
                        </c:choose>
                    </td>
                    <td>
                        <a href="/admin/orders/view/${order.id}">Edit</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
