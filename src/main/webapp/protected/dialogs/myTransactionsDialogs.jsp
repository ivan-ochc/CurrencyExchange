<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="declineTransactionModal"
     class="modal hide fade in centering"
     role="dialog"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="declineTransactionModalLabel" class="displayInLine">
            <spring:message code="transaction.decline"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="declineTransactionForm" novalidate>
            <p><spring:message code="transaction.declineConfirm"/></p>

            <input type="submit"
                   class="btn btn-inverse"
                   ng-click="declineTransaction();"
                   value='Decline'/>
            <button class="btn btn-inverse"
                    data-dismiss="modal"
                    ng-click="exit('#declineTransactionModal');"
                    aria-hidden="true">
                <spring:message code="cancel"/>
            </button>
        </form>
    </div>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit">
        <spring:message code="request.error"/>
    </span>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorIllegalAccess">
        <spring:message code="request.illegal.access"/>
    </span>
</div>


<div id="acceptTransactionModal"
     class="modal hide fade in centering"
     role="dialog"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="acceptTransactionModalLabel" class="displayInLine">
            <spring:message code="transaction.accept"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="acceptTransactionForm" novalidate>
            <p><spring:message code="transaction.confirm"/></p>

            <input type="submit"
                   class="btn btn-inverse"
                   ng-click="acceptTransaction();"
                   value='Accept'/>
            <button class="btn btn-inverse"
                    data-dismiss="modal"
                    ng-click="exit('#acceptTransactionModal');"
                    aria-hidden="true">
                <spring:message code="cancel"/>
            </button>
        </form>
    </div>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit">
        <spring:message code="request.error"/>
    </span>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorIllegalAccess">
        <spring:message code="request.illegal.access"/>
    </span>
</div>