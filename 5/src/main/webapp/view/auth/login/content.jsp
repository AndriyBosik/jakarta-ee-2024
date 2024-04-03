<div class="window-height window-width yellow lighten-5 s-vflex-center">
    <div class="container">
        <div class="row">
            <div class="col s12 m4 push-m4">
                <div class="p10 radius-4 z-depth-1">
                    <form action="" method="post" class="auto-height">
                        <div class="full-width input-field col s12">
                            <i class="material-icons prefix">account_circle</i>
                            <input id="login" type="text" name="login"/>
                            <label for="login">Login</label>
                        </div>
                        <div class="full-width input-field col s12">
                            <i class="material-icons prefix">lock</i>
                            <input id="password" type="password" name="password"/>
                            <label for="password">Password</label>
                        </div>
                        <c:if test="${not empty errorMessage}">
                            <div class="full-width col s12">
                                <p class="center-align pink-text text-lighten-1 weight-normal login-errorKey-message">
                                    <c:out value="${errorMessage}"/>
                                </p>
                            </div>
                        </c:if>
                        <div class="col s12">
                            <button class="full-width btn waves-effect waves-light" type="submit">
                                Login
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>