<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="row-fluid" ng-controller="allOrdersController">
    <div ng-cloak id="gridContainer">

        <div class="alert alert-info" ng-class="{'': displayTransactionSuccessMessage == true, 'none': displayTransactionSuccessMessage != true}">
            <spring:message code="transaction.transactionSuccess" />
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
                <th scope="col"><a href="" ng-click="predicate = '-authorName'; reverse=!reverse"> <spring:message code="order.author"/></a></th>
                <th scope="col"><a href="" ng-click="predicate = '-orderType'; reverse=!reverse"><spring:message code="order.type"/></a></th>
                <th scope="col"><a href="" ng-click="predicate = '-currency'; reverse=!reverse"><spring:message code="order.currency"/></a></th>
                <th scope="col"><a href="" ng-click="predicate = '-amount'; reverse=!reverse"><spring:message code="order.amount"/></a></th>
                <th scope="col"><a href="" ng-click="predicate = '-exchangeRate'; reverse=!reverse"><spring:message code="order.exchangeRate"/></a></th>
                <th scope="col"><a href="" ng-click="predicate = '-location'; reverse=!reverse"><spring:message code="order.location"/></a></th>
                <th scope="col"><spring:message code="order.contactInfo"/></th>
                <th scope="col"><a href="" ng-click="predicate = '-orderDate'; reverse=!reverse"><spring:message code="order.date"/></a></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="order in page.source | orderBy:predicate:reverse" ng-class="{'strikeout': order.amount == 0, '': order.amount != 0}" >
                <td class="tdOrdersCentered">{{order.authorName}}</td>
                <td class="tdOrdersCentered">{{order.orderType}}</td>
                <td class="tdOrdersCentered">{{order.currency}}</td>
                <td class="tdOrdersCentered">{{order.amount}}</td>
                <td class="tdOrdersCentered">{{order.exchangeRate}}</td>
                <td class="tdOrdersCentered">{{order.location}}</td>
                <td class="tdOrdersCentered">{{order.contactInfo}}</td>
                <td class="tdOrdersCentered">{{order.orderDate  | date : 'dd-MM-yyyy HH-mm-ss'}}</td>
                <td class="width15">
                    <div class="text-center">
                        <input type="hidden"/>
                        <a href="#addTransactionModal"
                           ng-click="selectedOrder(order);"
                           role="button"
                           title="<spring:message code="transaction.add"/>"
                           class="btn btn-inverse" data-toggle="modal">
                            <i class="icon-plus"></i>
                        </a>
                </td>
            </tr>
            </tbody>
        </table>

        <jsp:include page="dialogs/allOrderDialogs.jsp"/>

    </div>
</div>
<script src="<c:url value='/resources/js/pages/allOrders.js' />"></script>