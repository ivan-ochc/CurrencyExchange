<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="deleteUsersModal"
     class="modal hide fade in centering"
     role="dialog"
     aria-labelledby="searchUsersModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="deleteUsersModalLabel" class="displayInLine">
            <spring:message code="adminPage.deleteUser"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="deleteUserForm" novalidate>
            <p><spring:message code="adminPage.confirm"/>&nbsp;{{user.name}}?</p>

            <input type="submit"
                   class="btn btn-inverse"
                   ng-click="deleteUser();"
                   value='<spring:message code="delete"/>'/>
            <button class="btn btn-inverse"
                    data-dismiss="modal"
                    ng-click="exit('#deleteUsersModal');"
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
