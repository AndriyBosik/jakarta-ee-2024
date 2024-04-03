<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="https://www.example.com/functions" %>

<div class="container">
    <div class="row">
        <div class="s-vflex">
            <div class="panel-option clickable">
                <div class="s-hflex">
                    <div class="title s-vflex-center">
                        Orders
                    </div>
                    <div class="equal-flex"></div>
                    <div class="arrow s-vflex-center">
                        <a href="/admin/orders/view">
                            <i class="material-icons">arrow_forward</i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="history s-vflex">
                <h3>History</h3>
                <table class="orders-table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Date</th>
                        <th>Description</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${audit.content}" var="entry" varStatus="loop">
                        <tr>
                            <td>
                                    ${loop.index + 1}
                                <input type="hidden" name="id" value="${entry.id}"/>
                            </td>
                            <td>
                                    ${f:formatLocalDateTime(entry.createdDate, 'HH:mm:ss dd-MM-yyyy')}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${entry.userType == 'ADMIN' && entry.entityType == 'FOOD' && entry.actionType == 'CREATE'}">
                                        The food was created by admin. ID is: ${entry.entityId}
                                    </c:when>
                                    <c:when test="${entry.userType == 'ADMIN' && entry.entityType == 'FOOD' && entry.actionType == 'UPDATE'}">
                                        The food was updated by admin. ID is: ${entry.entityId}
                                    </c:when>
                                    <c:when test="${entry.userType == 'ADMIN' && entry.entityType == 'FOOD' && entry.actionType == 'DELETE'}">
                                        The food was deleted by admin. ID is: ${entry.entityId}
                                    </c:when>
                                    <c:when test="${entry.userType == 'ADMIN' && entry.entityType == 'ORDER' && entry.actionType == 'UPDATE'}">
                                        The order was updated by admin. ID is: ${entry.entityId}
                                    </c:when>
                                    <c:when test="${entry.userType == 'USER' && entry.entityType == 'ORDER' && entry.actionType == 'CREATE'}">
                                        The order was created by user. ID is: ${entry.entityId}
                                    </c:when>
                                    <c:otherwise>
                                        User type: ${entry.userType}; Entity type: ${entry.entityType}; Action type: ${entry.actionType}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="s-hflex" style="margin-top: 10px;">
                    <c:if test="${audit.pageNumber > 0}">
                        <div>
                            <a href="?page=${audit.pageNumber - 1}" class="btn waves-effect waves-light">
                                <i class="material-icons left">arrow_back</i>
                                Back
                            </a>
                        </div>
                    </c:if>
                    <div class="equal-flex"></div>
                    <c:if test="${audit.pageNumber + 1 < audit.totalPages}">
                        <div>
                            <a href="?page=${audit.pageNumber + 1}" class="btn waves-effect waves-light">
                                <i class="material-icons right">arrow_forward</i>
                                Next
                            </a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
