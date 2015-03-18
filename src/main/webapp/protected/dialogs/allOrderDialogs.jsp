<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div id="addTransactionModal"
     class="modal hide fade in centering insertAndUpdateDialogs"
     role="dialog"
     aria-labelledby="addTransactionModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="addTransactionModalLabel" class="displayInLine">
            <spring:message code="transaction.add"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="addTransactionForm" novalidate>
            <div class="pull-left">
                <div>
                    <div class="input-append">
                        <input name="transactionAmount"
                               ng-model="exchangeTransaction.transactionAmount"
                               type="number"
                               ng-maxlength="6"
                               min="1"
                               required
                               autofocus
                                />
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && addTransactionForm.transactionAmount.$error.required">
                                        <spring:message code="required"/>
                                </span>
                            <span class="alert alert-error"
                                  ng-show="displayValidationError && addTransactionForm.transactionAmount.$error.maxlength">
                                        <spring:message code="order.tooMuch"/>
                                </span>
                            <span class="alert alert-error"
                                  ng-show="displayValidationError && addTransactionForm.transactionAmount.$error.min">
                                        <spring:message code="order.tooLess"/>
                                </span>
                        </label>
                    </div>
                </div>
                <button type="submit"
                       class="btn btn-inverse"
                       ng-click="createTransaction(addTransactionForm);">
                       <spring:message code="transaction.addNew"/>
                </button>
                <button class="btn btn-inverse"
                        data-dismiss="modal"
                        ng-click="exit('#addTransactionModal');"
                        aria-hidden="true">
                    <spring:message code="cancel"/>
                </button>
                <p>
                <div class="alert alert-error" ng-class="{'': displayTransactionErrorMessage == true, 'none': displayTransactionErrorMessage != true}">
                    <spring:message code="transaction.transactionError" />
                </div>
                </p>
            </div>
        </form>
    </div>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit">
        <spring:message code="request.error"/>
    </span>
</div>
