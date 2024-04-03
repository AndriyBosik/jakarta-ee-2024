<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="row">
        <form action="" method="post" class="order-form">
            <input type="hidden" value="${order.id}" name="orderId">
            <div class="s-vflex my20">
                <div class="s-hflex-end">
                    <div>
                        <div class="input-field col s12">
                            <select name="status">
                                <option value="PENDING" ${order.status == 'PENDING' ? 'selected' : ''}>PENDING</option>
                                <option value="APPROVED" ${order.status == 'APPROVED' ? 'selected' : ''}>APPROVED
                                </option>
                                <option value="REJECTED" ${order.status == 'REJECTED' ? 'selected' : ''}>REJECTED
                                </option>
                            </select>
                            <label>Materialize Select</label>
                        </div>
                    </div>
                </div>
                <div class="s-vflex m-hflex personal-info">
                    <div class="equal-flex personal-info-item">
                        <input type="text" name="firstName" placeholder="First Name"
                               value="<c:out value="${order.firstName}" />"/>
                    </div>
                    <div class="equal-flex personal-info-item">
                        <input type="text" name="lastName" placeholder="Last Name"
                               value="<c:out value="${order.lastName}"/>"/>
                    </div>
                    <div class="equal-flex personal-info-item">
                        <input type="text" name="phone" placeholder="Phone" value="<c:out value="${order.phone}"/>"/>
                    </div>
                </div>
                <table class="col s12">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Title</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${order.items}" var="food" varStatus="loop">
                        <tr>
                            <td>
                                    ${loop.index + 1}
                                <input type="hidden" name="id" value="${food.id}"/>
                            </td>
                            <td><c:out value="${food.title}" /></td>
                            <td>
                                <div target="food${food.id}-price" value="${food.price}">
                                    <fmt:formatNumber value="${food.price}" type="number" minFractionDigits="2"
                                                      maxFractionDigits="2"/> UAH
                                </div>
                            </td>
                            <td class="quantity-table-item">
                                <input type="hidden" name="quantity" target="food${food.id}-quantity-value"
                                       value="${food.quantity}"/>
                                <div class="s-hflex">
                                    <div class="decrease quantity-control clickable"
                                         data-target="food${food.id}">
                                        -
                                    </div>
                                    <div class="value s-vflex-center" target="food${food.id}">
                                            ${food.quantity}
                                    </div>
                                    <div class="increase quantity-control clickable"
                                         data-target="food${food.id}">
                                        +
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div target="food${food.id}-total" class="food-total">
                                <span class="value">
                                    <fmt:formatNumber value="${food.price * food.quantity}" type="number"
                                                      minFractionDigits="2" maxFractionDigits="2"/>
                                </span>
                                    UAH
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4" style="text-align: right; font-weight: bold; font-style: italic;">
                            To pay:
                        </td>
                        <td>
                            <span class="to-pay"></span> UAH
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="s-hflex-end my20">
                    <button type="submit" class="teal lighten-2 btn waves-effect waves-light">Confirm Order</button>
                </div>
            </div>
        </form>
    </div>
</div>
