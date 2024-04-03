<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>

<header>
    <div class="navbar-fixed">
        <nav id="navbar">
            <div class="nav-wrapper teal lighten-2">
                <div class="container">
                    <div class="row">
                        <div class="col s12">
                            <div class="s-hflex-start" style="justify-content: space-between;">
                                <div>
                                    <a href="/" class="brand-logo full-height">
                                        <div class="full-height p10">
                                            <img src="/resources/images/logo.png" alt="Logo" class="full-height">
                                        </div>
                                    </a>
                                </div>
                                <div class="s-hflex-start">
                                    <ul id="nav-mobile">
                                        <li>
                                            <a href="/menu" class="tooltipped" data-position="bottom"
                                               data-tooltip="Menu">
                                                Menu
                                            </a>
                                        </li>
                                        <li>
                                            <a href="/cart/view" class="tooltipped" data-position="bottom"
                                               data-tooltip="Cart">
                                                <c:choose>
                                                    <c:when test="${totalFoodQuantity > 0}">
                                                        Cart (${totalFoodQuantity})
                                                    </c:when>
                                                    <c:otherwise>
                                                        Cart
                                                    </c:otherwise>
                                                </c:choose>
                                            </a>
                                        </li>
                                        <tf:forAdmin>
                                            <li>
                                                <a href="/admin/panel" class="tooltipped" data-position="bottom"
                                                   data-tooltip="Control Panel">
                                                    Control Panel
                                                </a>
                                            </li>
                                            <li>
                                                <a href="/logout" class="tooltipped" data-position="bottom"
                                                   data-tooltip="Logout">
                                                    <i class="material-icons">exit_to_app</i>
                                                </a>
                                            </li>
                                        </tf:forAdmin>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</header>
