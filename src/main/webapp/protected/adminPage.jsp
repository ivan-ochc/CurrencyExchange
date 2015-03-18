<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row-fluid" ng-controller="adminController">
    <div ng-cloak id="gridContainer">
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
                <th scope="col"><spring:message code="adminPage.userName"/></th>
                <th scope="col"><spring:message code="adminPage.userEmail"/></th>
                <th scope="col"><spring:message code="adminPage.userPassword"/></th>
                <th scope="col"><spring:message code="adminPage.userStatus"/></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="user in page.source">
                <td class="tdOrdersCentered">{{user.name}}</td>
                <td class="tdOrdersCentered">{{user.email}}</td>
                <td class="tdOrdersCentered">{{user.password}}</td>
                <td class="tdOrdersCentered">{{user.enabled}}</td>
                <td class="width15">
                    <div class="text-center">
                        <input type="hidden" value="{{user.userId}}"/>
                        <a href="#deleteUsersModal"
                           ng-click="selectedUser(user);"
                           role="button"
                           title="<spring:message code="adminPage.deleteUser"/>"
                           class="btn btn-inverse" data-toggle="modal">
                            <i class="icon-minus"></i>
                        </a>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <jsp:include page="dialogs/adminDialogs.jsp"/>
    </div>
</div>
<script src="<c:url value='/resources/js/pages/adminPage.js' />"></script>