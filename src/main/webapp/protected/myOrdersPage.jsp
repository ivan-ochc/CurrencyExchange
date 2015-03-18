<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="row-fluid" ng-controller="myOrdersController">
    <div ng-cloak id="gridContainer">
        <div class="alert alert-info" ng-class="{'': displaySuccessOrderMessage == true, 'none': displaySuccessOrderMessage != true}" >
            <spring:message code="order.newOrder" />
        </div>
        <div class="alert alert-info" ng-class="{'': displaySuccessDeletingMessage == true, 'none': displaySuccessDeletingMessage != true}">
            <spring:message code="order.deletingOrder" />
        </div>
        <div id="loadingModal" class="modal hide fade in centering"
             role="dialog"
             aria-hidden="true">
            <div id="divLoadingIcon" class="text-center">
                <div class="icon-align-center loading"></div>
            </div>
        </div>
        <table class="table table-bordered data">
            <thead>
            <tr>
                <th scope="col"><spring:message code="order.type"/></th>
                <th scope="col"><spring:message code="order.currency"/></th>
                <th scope="col"><spring:message code="order.amount"/></th>
                <th scope="col"><spring:message code="order.exchangeRate"/></th>
                <th scope="col"><spring:message code="order.date"/></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <%--<c:forEach items="${recordList}" var="order">--%>
            <tr ng-repeat="order in page.source">
                <td class="tdOrdersCentered">{{order.orderType}}</td>
                <td class="tdOrdersCentered">{{order.currency}}</td>
                <td class="tdOrdersCentered">{{order.amount}}</td>
                <td class="tdOrdersCentered">{{order.exchangeRate}}</td>
                <td class="tdOrdersCentered">{{order.orderDate  | date : 'dd-MM-yyyy HH-mm-ss'}}</td>
                <td class="width15">
                    <div class="text-center">
                        <input type="hidden" value="{{order.id}}"/>
                        <a href="#deleteOrdersModal"
                           ng-click="selectedOrder(order);"
                           role="button"
                           title="<spring:message code="order.delete"/>"
                           class="btn btn-inverse" data-toggle="modal">
                            <i class="icon-minus"></i>
                        </a>
                        <%--<a href="${pageContext.request.contextPath}/protected/purse/delete/{{order.id}}" role="button" class="btn btn-inverse" data-toggle="modal"><i class="icon-minus"></i></a>--%>
                        <a href="#updateOrdersModal"
                           ng-click="selectedOrder(order);"
                           role="button"
                           title="<spring:message code="order.update"/>"
                           class="btn btn-inverse" data-toggle="modal">
                            <i class="icon-pencil"></i>
                        </a>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>

        <div>
            <br/>
            <a href="#addOrdersModal"
               role="button"
               title="<spring:message code='order.create'/>"
               class="btn btn-inverse"
               data-toggle="modal">
                <i class="icon-plus"></i>
                &nbsp;&nbsp;<spring:message code="order.create"/>
            </a>
        </div>

        <jsp:include page="dialogs/myOrderDialogs.jsp"/>

    </div>
</div>
<script src="<c:url value='/resources/js/pages/myOrders.js' />"></script>