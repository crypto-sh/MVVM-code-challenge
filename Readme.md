# Mobiquity Test Project

This project created base on [Mobiquity](https://www.mobiquity.com) assignment for hiring process.


It is base on Kotlin with most the new Android architecture components and another useful library on android such as
  - ViewModel
  - LiveData
  - Lifecycle
  - Data Binding
  - Mvvm
  - Dagger
  - androidTest with espresso


# Description
This project implemented by two Modules(core, App). In the core module, I developed all generic class and other classes would be useful for another module If we want to extend the project like Wear, Tv, etc.
The core module is responsible for providing the RestApi library and other classes by Dagger. for using generic classes we can just extend the class from them in the target module(App).
App module used for developing everything we want to show in our application. It contains two activities (MainActivity, DetailActivity).  


Prerequisites
=============
    - Android Studio 3.5.1
    - Gradle version 5.4.1+
    - Kotlin 1.3.50

## Author

[Ali Shatergholi](https://github.com/alishatergholi)
