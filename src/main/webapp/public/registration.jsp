<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row-fluid">
    <div class="jumbotron">
        <h1><spring:message code='project.name'/></h1>
    </div>
</div>
<div class="row-fluid">
    <div class="span4 offset4 well" ng-controller="registrationController">
        <legend><spring:message code="registration.header" /></legend>

            <form name="registrationForm" novalidate>

            <div>
                <div>
                    <p id ="errorMessage">
                </div>
                <div class="alert alert-error" ng-class="{'': displayUserNameError == true, 'none': displayUserNameError == false}">
                    <spring:message code="registration.userNameError" />
                </div>
                <div class="alert alert-error" ng-class="{'': displayPasswordError == true, 'none': displayPasswordError == false}">
                    <spring:message code="registration.passwordError" />
                </div>
                <div class="alert alert-info" ng-class="{'': displaySuccessUserCreationMessage == true, 'none': displaySuccessUserCreationMessage == false}">
                    <spring:message code="registration.successMessage" />
                </div>
                <%--${pwdMessage}--%>
                <%--${userMessage}--%>
                <%--${errorMessage}--%>
                <div>
                    <label>
                <span class="alert-error"
                      ng-show="displayValidationError && registrationForm.name.$error.required">
                                    <spring:message code="registration.userName" />
                </span>
                 <span class="alert-error"
                              ng-show="displayValidationError && registrationForm.name.$error.minlength">
                                    <spring:message code="registration.userNameSizeMore" />
                 </span>
                 <span class="alert-error"
                              ng-show="displayValidationError && registrationForm.name.$error.maxlength">
                                    <spring:message code="registration.userNameSizeLess" />
                 </span>
                    </label>
                </div>
                <input name="name"
                       type="text"
                       class="span12"
                       ng-model="user.name"
                       ng-maxlength="30"
                       ng-minlength="3"
                       required
                       placeholder="<spring:message code='sample.login' /> ">

                <label>
                <span class="alert-error"
                      ng-show="displayValidationError && registrationForm.email.$error.required">
                                    <spring:message code="registration.email" />
                                </span>
                <span class="alert-error"
                          ng-show="displayValidationError && registrationForm.email.$error.email">
                                    <spring:message code="registration.emailFormat" />
                </span>
                </label>
                <input name="email"
                       ng-model="user.email"
                       type="email"
                       class="span12"
                       required
                       placeholder="Email">

                <label>
                <span class="alert-error"
                      ng-show="displayValidationError && registrationForm.password.$error.required">
                                    <spring:message code="registration.password" />
                                </span>
                </label>
                <input name="password"
                       type="password"
                       ng-model="user.password"
                       required
                       class="span12"
                       placeholder="Password">

                <label>
                <span class="alert-error"
                      ng-show="displayValidationError && registrationForm.confirmPassword.$error.required">
                                    <spring:message code="registration.password" />
                                </span>
                <span class="alert-error"
                      ng-class="{'': displayIncorrectPasswordMessage == true, 'none': displayIncorrectPasswordMessage == false}">
                                    Incorrect password
                                </span>

                </label>
                <input name="confirmPassword"
                       type="password"
                       class="span12"
                       ng-model="confirmPassword"
                       required
                       placeholder="Retype Password">

                <button type="submit"
                        name="submit"
                        id="btnSubmit"
                        class="btn btn-inverse btn-block"
                        ng-click="createUser(registrationForm);">
                    <spring:message code="registration.create" />
                </button>
                <p id="demo"></p>
            </div>
           </form>
           <form  method="post" action="login">
            <%--<a title='<spring:message code="login.registration"/>' href="<c:url value='/registration'/>">--%>
            <button type="submit" class="btn btn-inverse btn-block"><spring:message code="login.refer" /></button>
            <%--</a>--%>
        </form>

    </div>
</div>

<script src="<c:url value='/resources/js/pages/registration.js' />"></script>