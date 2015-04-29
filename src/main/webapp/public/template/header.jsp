<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="masthead">
    <h3 class="muted">
        <spring:message code='header.message'/>
    </h3>

    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">
                <ul class="nav" ng-controller="LocationController">
                    <li ng-class="{'active': activeURL == 'home', '': activeURL != 'home'}" >
                        <a href="<c:url value="/"/>"
                           title='<spring:message code="header.home"/>'
                                >
                            <p><spring:message code="header.home"/></p>
                        </a>
                    </li>
                    <li ng-class="{'gray': activeURL == 'order', '': activeURL != 'order'}" class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle"><p><spring:message code="header.orderMenu"/></p><b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a title='<spring:message code="header.myOrders"/>' href="<c:url value='/protected/orders/myOrders'/>">
                                <p><spring:message code="header.myOrders"/></p>
                            </a>
                            </li>
                            <li><a title='<spring:message code="header.allOrders"/>' href="<c:url value='/protected/orders/allOrders'/>">
                                <p><spring:message code="header.allOrders"/></p>
                            </a>
                            </li>
                            <li><a title='transactions' href="<c:url value='/protected/orders/myTransactions'/>">
                                <p><spring:message code="header.transactions"/></p>
                            </a>
                            </li>
                        </ul>

                    </li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li ng-class="{'gray': activeURL == 'adminPage', '': activeURL != 'adminPage'}">
                        <a title='<spring:message code="header.admin"/>' href="<c:url value='/protected/adminPage'/>">
                            <p><spring:message code="header.admin"/></p>
                        </a>
                    </li>
                     </sec:authorize>
                </ul>
                <ul class="nav pull-right">
                    <li><a href="<c:url value='/logout' />" title='<spring:message code="header.logout"/>'><p class="displayInLine"><spring:message code="header.logout"/>&nbsp;(${user.name})</p></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
