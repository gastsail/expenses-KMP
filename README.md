This is a demo project currently WIP for Compose Multi-Platform

## App

![Screenshot 2023-08-22 at 11 16 48](https://github.com/gastsail/expenses-KMP/assets/24615408/d10d447c-5cce-433f-ba3c-2d9d59b44d97)

![Screenshot 2023-08-22 at 11 18 02](https://github.com/gastsail/expenses-KMP/assets/24615408/5e58de01-eab4-4409-8616-c438893ba0b6)

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

## Explanation Video
[![Video](https://img.youtube.com/vi/VgQPsuYD_V4/0.jpg)](https://www.youtube.com/watch?v=VgQPsuYD_V4)

Since this is a compose multiplatform project, we can preview only on the Android side, but this is not a blocker since the same view will be shown on iOS.

We can create custom themes for iOS look and feel.

Feel free to add any PR that improves the app.
