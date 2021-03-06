<div class="row">
    <div class="col-sm-6 col-md-6 col-md-offset-3">
            <div class="text-success h4">${flashMessage}</div>
            <div class="text-danger h4">${flashError}</div>
        <h1>Create Account</h1>
        <form action="/create_account" method="POST" onsubmit="return window.confirmPassword();">
            <div class="row">
                <div class="col-md-4">Username<span class="required">*</span></div>
                <div class="col-md-8">
                    <input type="text" name="username" class="form-control" placeholder="Username" required>
                </div>
            </div><br>
            <div class="row">
                <div class="col-md-4">Password<span class="required">*</span></div>
                <div class="col-md-8">
                    <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
                </div>
            </div><br>
            <div class="row">
                <div class="col-md-4">Confirm Password<span class="required">*</span></div>
                <div class="col-md-8">
                    <input type="password" id="confirmPassword" class="form-control" placeholder="Confirm Password" required>
                </div>
            </div><br>
            <div class="row">
                <div class="col-md-4">Name<span class="required">*</span></div>
                <div class="col-md-8">
                    <input type="text" name="name" class="form-control" placeholder="Name" required>
                </div>
            </div>
            <button class="btn btn-link pull-right" type="submit">Submit</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</div>