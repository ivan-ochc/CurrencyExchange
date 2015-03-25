<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div id="addOrdersModal"
     class="modal hide fade in centering insertAndUpdateDialogs"
     role="dialog"
     aria-labelledby="addOrdersModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="addOrdersModalLabel" class="displayInLine">
            <spring:message code="order.create"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="newOrderForm" novalidate>
            <div class="pull-left">
                <div>
                    <div class="input-append">
                        <select name="orderType" ng-model="order.orderType" required>
                            <option selected value>Choose type:</option>
                            <option >BUY</option>
                            <option >SELL</option>
                        </select>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && newOrderForm.orderType.$error.required">
                                    <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <select name="currency" ng-model="order.currency" required>
                            <option selected value>Choose currency:</option>
                            <option >USD</option>
                            <option >EUR</option>
                        </select>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && newOrderForm.currency.$error.required">
                                    <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <input  name="amount"
                                ng-model="order.amount"
                                ng-maxlength="6"
                                min="1"
                                type="number"
                                required
                                placeholder='<spring:message code="order.amount" /> '/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && newOrderForm.amount.$error.required">
                                    <spring:message code="required"/>
                                </span>
                            <span class="alert alert-error"
                                  ng-show="displayValidationError && newOrderForm.amount.$error.maxlength">
                                    <spring:message code="order.tooMuch"/>
                                </span>
                            <span class="alert alert-error"
                                  ng-show="displayValidationError && newOrderForm.amount.$error.min">
                                    <spring:message code="order.tooLess"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <input  name="exchangeRate"
                                ng-model="order.exchangeRate"
                                type="number"
                                ng-maxlength="5"
                                min="8"
                                required
                                placeholder='<spring:message code="order.exchangeRate" /> '/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && newOrderForm.exchangeRate.$error.required">
                                    <spring:message code="required"/>
                                </span>
                            <span class="alert alert-error"
                                  ng-show="displayValidationError && newOrderForm.exchangeRate.$error.maxlength">
                                    <spring:message code="order.tooMuch"/>
                                </span>
                            <span class="alert alert-error"
                                  ng-show="displayValidationError && newOrderForm.exchangeRate.$error.min">
                                    <spring:message code="order.tooLess"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <select name="location" ng-model="order.location" required>
                            <option selected value>Choose location:</option>
                            <option >Mykhaila Hryshka St</option>
                            <option >Petra Hryhorenka Ave</option>
                            <option >Kharkivs'ke Hwy</option>
                        </select>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && newOrderForm.location.$error.required">
                                    <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <input  name="contactInfo"
                                ng-model="order.contactInfo"
                                type="text"
                                required
                                placeholder='<spring:message code="order.contactInfo" /> '/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && newOrderForm.contactInfo.$error.required">
                                    <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <input type="submit"
                       class="btn btn-inverse"
                       ng-click="createOrder(newOrderForm);"
                       value='<spring:message code="create"/>'/>
                <button class="btn btn-inverse"
                        data-dismiss="modal"
                        ng-click="exit('#addOrdersModal');"
                        aria-hidden="true">
                    <spring:message code="cancel"/>
                </button>
            </div>
        </form>
    </div>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit">
        <spring:message code="request.error"/>
    </span>
</div>

<div id="updateOrdersModal"
     class="modal hide fade in centering insertAndUpdateDialogs"
     role="dialog"
     aria-labelledby="updateOrdersModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="updateRecordsModalLabel" class="displayInLine">
            <spring:message code="order.update"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="updateOrderForm" novalidate>
            <input type="hidden"
                   required
                   ng-model="order.id"
                   name="id"
                   value="{{order.id}}"/>
            <div class="pull-left">
                <div>
                    <div class="input-append">
                        <input name="amount"
                               type="number"
                               autofocus
                               required
                               ng-model="order.amount"
                               ng-maxlength="6"
                               min="1"
                               name="name"
                               placeholder="<spring:message code='order.amount'/> "/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && updateOrderForm.amount.$error.required">
                                    <spring:message code="required"/>
                                </span>
                            <span class="alert alert-error"
                                  ng-show="displayValidationError && updateOrderForm.amount.$error.maxlength">
                                    <spring:message code="order.tooMuch"/>
                                </span>
                            <span class="alert alert-error"
                                  ng-show="displayValidationError && updateOrderForm.amount.$error.min">
                                    <spring:message code="order.tooLess"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <input name="exchangeRate"
                               type="number"
                               required
                               ng-model="order.exchangeRate"
                               ng-maxlength="5"
                               min="8"
                               name="email"
                               placeholder="<spring:message code='order.exchangeRate'/> "/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && updateOrderForm.exchangeRate.$error.required">
                                    <spring:message code="required"/>
                                </span>
                            <span class="alert alert-error"
                                  ng-show="displayValidationError && updateOrderForm.exchangeRate.$error.maxlength">
                                    <spring:message code="order.tooMuch"/>
                                </span>
                            <span class="alert alert-error"
                                  ng-show="displayValidationError && updateOrderForm.exchangeRate.$error.min">
                                    <spring:message code="order.tooLess"/>
                                </span>
                        </label>
                    </div>
                </div>
                <input type="submit"
                       class="btn btn-inverse"
                       ng-click="updateOrder(updateOrderForm);"
                       value='<spring:message code="update"/>'/>
                <button class="btn btn-inverse"
                        data-dismiss="modal"
                        ng-click="exit('#updateOrdersModal');"
                        aria-hidden="true">
                    <spring:message code="cancel"/></button>
            </div>
        </form>
    </div>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit">
        <spring:message code="request.error"/>
    </span>
</div>

<div id="deleteOrdersModal"
     class="modal hide fade in centering"
     role="dialog"
     aria-labelledby="searchOrdersModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="deleteOrdersModalLabel" class="displayInLine">
           <spring:message code="order.delete"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="deleteOrderForm" novalidate>
            <p><spring:message code="delete.confirm"/></p>

            <input type="submit"
                   class="btn btn-inverse"
                   ng-click="deleteOrder();"
                   value='<spring:message code="delete"/>'/>
            <button class="btn btn-inverse"
                    data-dismiss="modal"
                    ng-click="exit('#deleteOrdersModal');"
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


