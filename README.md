# TwitterCloneApp

Twitter clone app with Firebase Firestore using MVVM architechture. It has functionality like add, update and delete tweets (**Multi person realtime twitter app**).

## Features

- Login and Signup using Firebase Authentication.
- Type in a tweet with 280 characters limit.
- Save this along with the current time into Firestore.
- View a list of all tweets, sorted by most recent date.
- The app update in realtime when the data changes (Add, Edit, Delete) on Firestore.

## Project Flow

- User can login using credentials otherwise create a account.
- After Authentication user navigate to the main page where home and profile option is giiven on bottom tab.
- At home tab page all tweets are listed on sort by recent time.
- User can click on the bottom right button to add Tweet where there's limit to the tweet (280 characters). Afteer that tweet text gets red.
- On home tab page user can edit and delete their own tweets.
- At Profile tab page user details and logout is there. 

## Demo

- Login Page <br>
 ![WhatsApp Image 2021-05-13 at 16 11 20 (3)](https://user-images.githubusercontent.com/19749442/118118458-98227600-b40a-11eb-8e70-1a0e9aad255f.jpeg)

- Signup Page <br>
 ![WhatsApp Image 2021-05-13 at 16 11 20 (4)](https://user-images.githubusercontent.com/19749442/118118532-ac667300-b40a-11eb-9518-4de35c3b47ec.jpeg)

- Home Tab Page <br>
 ![WhatsApp Image 2021-05-13 at 16 11 20 (2)](https://user-images.githubusercontent.com/19749442/118117714-9ad09b80-b409-11eb-9f45-21ac7bd3acab.jpeg)

- Home Page Tab without tweets <br>
 ![WhatsApp Image 2021-05-13 at 16 11 20](https://user-images.githubusercontent.com/19749442/118119064-6a89fc80-b40b-11eb-8b35-992e26cf6f7c.jpeg)

- Add Tweet Page <br>
 ![WhatsApp Image 2021-05-13 at 16 04 44 (1)](https://user-images.githubusercontent.com/19749442/118118939-3adaf480-b40b-11eb-9422-5705ffd13309.jpeg)

- Profile Page <br>
  ![WhatsApp Image 2021-05-13 at 16 11 20 (1)](https://user-images.githubusercontent.com/19749442/118119159-9311f680-b40b-11eb-8bb8-773f93935305.jpeg)
  
  
## Gradle Dependencies

TwitterCloneApp is currently using the following dependency.

| Dependency | Use |
| ------ | ------ |
| [Dagger Hilt][dagger] | For dependency injection  |
| [Firebase/Firestore][firestore] | To store and fetch data at realtime |
| [Firebase/Auth][fireAuth] | To authenticate and create user |
| [ViewModel][viewModel] |  for preparing and managing the data for an Activity or a Fragment |


   [dagger]: <https://developer.android.com/training/dependency-injection/hilt-android>
   [firestore]: <https://firebase.google.com/docs/firestore>
   [fireAuth]: <https://firebase.google.com/docs/auth>
   [viewModel]: <https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel>
