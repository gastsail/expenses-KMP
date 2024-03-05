## App

# 🍎iOS
### 🍎 Screenshots (DarkMode)
<div style="display: flex; justify-content: space-between;">
    <img src="https://github.com/gastsail/expenses-KMP/assets/57275367/2d346be2-38ac-4c23-b4fc-959155de85bc" height=400>
    <img src="https://github.com/gastsail/expenses-KMP/assets/57275367/f1557929-d4e2-4b30-8c65-93ca82ad9c5a" height=400>
    <img src="https://github.com/gastsail/expenses-KMP/assets/57275367/ea82d847-84c5-4f38-819a-6a77cfe638cf" height=400>
</div>

# 🤖Android
### 🤖Screenshots (DarkMode)
<div style="display: flex; justify-content: space-between;">
    <img src="https://github.com/gastsail/expenses-KMP/assets/57275367/f9c26ed3-14e6-4653-9a8f-0fd682e843e5" height=400>
    <img src="https://github.com/gastsail/expenses-KMP/assets/57275367/07c4c5dc-99e4-487a-8279-ed60f8b4042b" height=400>
    <img src="https://github.com/gastsail/expenses-KMP/assets/57275367/ab90f5be-499b-4a61-bd4e-2bbab023e4eb" height=400>
</div>

## App Architecture
Currently, this app uses MVVM Architecture, which is achieved with a third-party library called PreCompose.

## Libraries
- PreCompose for Viewmodels
- PreCompose for Navigation
- Koin for DI with PreCompose
- Kamel (Not used but if we need to load images from the internet in future versions of the app, we include it)
- SQLDelight 2.0.1 for local storage
- Ktor for API support (please follow https://github.com/gastsail/ktorExpensesApi/tree/master to start the server) also see ExpensesRepoImpl to setup the BASE_URL

## Previews
Previews are only working on the Android side, currently, we include compose preview and compose uiTooling on the build.gradle (:shared) folder only for androidMain.
Then inside *androidApp* we create a preview folder and use the Preview right there. 

Since this is a compose multiplatform project, we can preview only on the Android side, but this is not a blocker since the same view will be shown on iOS.

We can create custom themes for iOS look and feel.

## Explanation Video
[![Video](https://img.youtube.com/vi/VgQPsuYD_V4/0.jpg)](https://www.youtube.com/watch?v=VgQPsuYD_V4)

Feel free to add any PR that improves the app.

## Full Udemy Course [Spanish]
https://www.udemy.com/course/kotlin-multiplataforma-curso-intensivo-para-android-y-ios/

## Support the project
If you like this project you can leave your cup of coffee here 👇

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/cz3H0ZfiV)

