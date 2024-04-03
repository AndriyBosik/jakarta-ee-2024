<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="row">
        <form action="" method="post" class="order-form">
            <div class="s-vflex my20">
                <div class="s-vflex m-hflex personal-info">
                    <div class="col s12 m6 equal-flex personal-info-item">
                        <input type="text" name="firstName" placeholder="First Name" />
                    </div>
                    <div class="col s12 m6 equal-flex personal-info-item">
                        <input type="text" name="lastName" placeholder="Last Name" />
                    </div>
                    <div class="col s12 m6 equal-flex personal-info-item">
                        <input type="text" name="phone" placeholder="Phone" />
                    </div>
                    <div class="col s12 m6 equal-flex personal-info-item">
                        <input type="text" name="address" placeholder="Address" />
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
                    <c:forEach items="${sessionScope.cart}" var="entry" varStatus="loop">
                        <tr>
                            <td>
                                ${loop.index + 1}
                                <input type="hidden" name="id" value="${entry.value.id}" />
                            </td>
                            <td>
                                <c:out value="${entry.value.title}" />
                            <td>
                                <div target="food${entry.value.id}-price" value="${entry.value.price}">
                                    <fmt:formatNumber value="${entry.value.price}" type="number" minFractionDigits="2"
                                                      maxFractionDigits="2"/> UAH
                                </div>
                            </td>
                            <td class="quantity-table-item">
                                <input type="hidden" name="quantity" target="food${entry.value.id}-quantity-value"
                                       value="${entry.value.id}"/>
                                <div class="s-hflex">
                                    <div class="decrease quantity-control clickable"
                                         data-target="food${entry.value.id}">
                                        -
                                    </div>
                                    <div class="value s-vflex-center" target="food${entry.value.id}">
                                            ${entry.value.quantity}
                                    </div>
                                    <div class="increase quantity-control clickable"
                                         data-target="food${entry.value.id}">
                                        +
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div target="food${entry.value.id}-total" class="food-total">
                            <span class="value">
                                <fmt:formatNumber value="${entry.value.price * entry.value.quantity}" type="number"
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
                    <button type="submit" class="btn waves-effect waves-light">Confirm Order</button>
                </div>
            </div>
        </form>
    </div>
</div>
