<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="row-fluid" ng-controller="myTransactionsController">
    <div ng-cloak id="gridContainer">
        <div class="alert alert-info" ng-class="{'': displaySuccessDeclineMessage == true, 'none': displaySuccessDeclineMessage != true}">
            <spring:message code="transaction.transactionDecline" />
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
                <th scope="col"><a href="" ng-click="predicate = '-user.name'; reverse=!reverse"><spring:message code="transaction.author"/></a></th>
                <th scope="col"><a href="" ng-click="predicate = '-transactionAmount'; reverse=!reverse"><spring:message code="transaction.amount"/></a></th>
                <th scope="col"><a href="" ng-click="predicate = '-order.currency'; reverse=!reverse"><spring:message code="transaction.currency"/></a></th>
                <th scope="col"><a href="" ng-click="predicate = '-order.exchangeRate'; reverse=!reverse"><spring:message code="transaction.exchangeRate"/></a></th>
                <th scope="col"><a href="" ng-click="predicate = '-order.orderType'; reverse=!reverse"><spring:message code="transaction.orderType"/></a></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="transaction in page.source | orderBy:predicate:reverse">
                <td class="tdOrdersCentered">{{transaction.user.name}}</td>
                <td class="tdOrdersCentered">{{transaction.transactionAmount}}</td>
                <td class="tdOrdersCentered">{{transaction.order.currency}}</td>
                <td class="tdOrdersCentered">{{transaction.order.exchangeRate}}</td>
                <td class="tdOrdersCentered">{{transaction.order.orderType}}</td>
                <td class="width15">
                    <div class="text-center">
                        <input type="hidden" value=""/>
                        <a href="#acceptTransactionModal"
                           ng-click="selectedTransaction(transaction);"
                           role="button"
                           title="<spring:message code="transaction.accept"/>"
                           class="btn btn-inverse" data-toggle="modal">
                            <i class="icon-plus"></i>
                        </a>
                        <a href="#declineTransactionModal"
                           ng-click="selectedTransaction(transaction);"
                           role="button"
                           title="<spring:message code="transaction.decline"/>"
                           class="btn btn-inverse" data-toggle="modal">
                            <i class="icon-minus"></i>
                        </a>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <jsp:include page="dialogs/myTransactionsDialogs.jsp"/>
    </div>
</div>
<script src="<c:url value='/resources/js/pages/myTransactions.js' />"></script>