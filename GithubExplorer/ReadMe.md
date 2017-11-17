GitHub Explorer

This app allows you to search for repositories of a particular language on GitHub.

Functional requirements:

1. User should be able to enter a programming language - Done

2. App should request a list of popular GitHub repositories for this language via the GitHub API and present this list to the user - Done

3. User should be able to tap on a repository to see repository data like name, description, 3 newest issues, and 3 top contributors - Done (No webviews are used)

Non functional requirements:

1. The app should be displayed equally well for phones of two different sizes (Tested on Samsung Galaxy S6, Nexus 5 and Note 4) - Looked fine

2. Data should be fetched from the GitHub API - Done (no repo data is stored locally)


Application design:

MVP - Model - View - Presenter

This is a very popular design pattern used in Android as it allows us to unit test the presenter without a device.
The View is an interface that will be implemented by a framework class (such as an Activity or Fragment) and passed to the Presenter.

The Presenter does all the decision making and thus we will test this thoroughly.

The View implementation is a passive view (it does no decision making) and thus need not be unit tested, there should be UI tests for it though.

The Model is the data store, but since I'm not storing any data for this app, I've chosen to skip this.

I've also used a Repository which acts as a bridge between the front end and the back end.
The advantage of having this is, we are free to change our back end implementation without touching the front end (hence providing seperation of concerns).
For e.g., I'm currently using Retrofit to make my requests (RetrofitGitHubRepository), but this can easily be replaced with a Volley or any other HTTP client.

I'm using Android Studio 3.0 with the latest SDK version (compileSdkVersion 27).
In this version we don't need to cast our findViewById calls.

    TextView textView = findViewById(R.id.textview); //This is valid, no need to explicitly cast

This allows me to use lambdas (for single abstract method interfaces like View.onClickListener).

    textView.setOnClickListener(v -> doSomethingOnClick());

Interfaces are allowed to have static methods.

Unit tests:

All the presenters are fully tested.
I have a Parser class to parse a few things which weren't auto parsed correctly, this is also completely tested.

Readability of code:

I've seperated my packaged by feature (all the classes related to one feature are in the same package). This allows easier extraction and modularization of code.
The visibility of fields and methods are kept to the lowest possible one.
Many fields and methods are package-private as they are exposed to in the package, but need not be exposed to the whole app (by using public)

I've used descriptive names for fields and methods and kept the methods small as much as possible.