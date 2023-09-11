This is a demo project currently WIP for Compose Multi-Platform

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
Currently, this app uses MVVM Architecture, which is achieved with a third-party library called Moko.

## Libraries
- Moko for Viewmodels
- PreCompose for Navigation
- Ktor (not used but included for serialization if we pull the data from any API)
- Kamel (also not used but if we need to load images from the internet in future version of the app, we included it)

## Previews
Previews are only working on the Android side, currently, we include compose preview and compose uiTooling on the build.gradle (:shared) folder only for androidMain.
Then inside *androidApp* we create a preview folder and use the Preview right there. 

Since this is a compose multiplatform project, we can preview only on the Android side, but this is not a blocker since the same view will be shown on iOS.

We can create custom themes for iOS look and feel.

## Explanation Video
[![Video](https://img.youtube.com/vi/VgQPsuYD_V4/0.jpg)](https://www.youtube.com/watch?v=VgQPsuYD_V4)

Feel free to add any PR that improves the app.
