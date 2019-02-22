package com.siliconvalleyoffice.git4jira.controller

import com.siliconvalleyoffice.git4jira.contract.Login
import com.siliconvalleyoffice.git4jira.service.Service
import com.siliconvalleyoffice.git4jira.view.HomeView
import com.siliconvalleyoffice.git4jira.view.LoginView
import javafx.application.Platform
import tornadofx.*
import tornadofx.FX.Companion.primaryStage

class LoginController(val loginView: LoginView, val loginService: Service.Login): Login.Controller {

    override fun login(userName: String, password: String) {
        val (user, error) = loginService.login(userName, password)
        if(user != null) {
            Platform.runLater { loginView.replaceWith(HomeView::class, sizeToScene = true, centerOnScreen = true) }
        } else if(error != null) {
            loginView.updateStatus(error.message ?: "Login Failed!")
        }
    }

    override fun logout() {
        primaryStage.uiComponent<UIComponent>()?.replaceWith(LoginView::class, sizeToScene = true, centerOnScreen = true)
    }
}