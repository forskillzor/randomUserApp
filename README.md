# Random User App (Android)

A clean Android app demonstrating modern architecture with Jetpack components, fetching and displaying random user data from [RandomUser.me API](https://randomuser.me/).

## Features

- **User List Screen**: Displays fetched users with pagination support
- **User Details Screen**: Shows comprehensive user information
- **Offline Support**: Caches data using Room
- **Modern Architecture**: MVVM with Clean Architecture principles
- **Jetpack Components**: ViewModel, LiveData, Navigation, Room

## Tech Stack

### Core Libraries
| Library | Purpose | Why Chosen |
|---------|---------|------------|
| `androidx.core` | Android essentials | Required for basic app functionality |
| `androidx.navigation` | Fragment navigation | Simplifies navigation between screens |
| `Room` | Local database | Best SQLite abstraction for Android |
| `Retrofit + OkHttp` | Networking | Industry standard for REST APIs |
| `Kotlin Serialization` | JSON parsing | Lightweight with native Kotlin support |
| `Hilt` | Dependency injection | Easier than Dagger for beginners |
| `Glide` | Image loading | Fast with good caching support |

### UI Components
- RecyclerView with DiffUtil
- SwipeRefreshLayout
- Material Design components

### Testing
- JUnit + MockK for unit tests
- Turbine for Flow testing
- Espresso for UI tests

## Screenshots

<div style="display: flex; gap: 20px;">
  <img src="https://github.com/forskillzor/randomUserApp/blob/main/screenshots/userlist.jpeg" width="200" />
  <img src="https://github.com/forskillzor/randomUserApp/blob/main/screenshots/userdetails.jpeg" width="200" />
</div>

## Key Features Implementation

### Data Flow
1. Fetch from API (Retrofit)
2. Cache in Room
3. Map to Domain objects
4. Present in UI (ViewModel + StateFlow)

### Error Handling
- Network errors
- Database exceptions
- UI state management

## Dependencies

See `build.gradle` for full list. Only essential libraries were used.

## Why This Project?

Demonstrates:
- Clean Architecture implementation
- Modern Android development practices
- Proper separation of concerns
- Production-ready error handling

### Library Selection Justification

Android Core (androidx.core.ktx, appcompat, material, activity, constraintlayout)
Foundational libraries for Android development. Provide compatibility, Material Design components, and essential UI tools. Indispensable for any Android project.

Navigation (androidx.navigation)
Simplifies fragment navigation, replacing manual transaction management. Chosen for its convenience and Jetpack integration.

Room (androidx.room)
Local data storage solution. Seamlessly integrates with Kotlin Coroutines and Flow, plus offers intuitive annotations. Alternatives like Realm are more complex and excessive for this use case.

Retrofit + OkHttp + Logging Interceptor
Retrofit is the industry standard for API communication. OkHttp provides flexibility, while Logging Interceptor aids request debugging. Alternatives (Ktor, Volley) are less optimal for JSON APIs.

Kotlin Serialization
Lightweight and efficient data serialization. Preferred over Gson/Moshi due to native Kotlin support and reduced overhead.

Hilt
Streamlines dependency injection. While Dagger is more powerful, Hilt offers better accessibility for beginners - making it the ideal choice for junior developers.

Glide
Image loading and caching library. Though Picasso is simpler, Glide delivers better performance and functionality. Coil is a modern alternative but less stable.

Swipe-to-Refresh (androidx.swiperefreshlayout)
Ready-made "pull-to-refresh" implementation. Avoids unnecessary custom solutions.

Testing Suite (JUnit, MockK, Coroutines Test, Turbine, MockWebServer)
Standard toolkit for unit and integration tests. MockK surpasses Mockito for Kotlin projects. Turbine simplifies Flow testing. MockWebServer enables API testing.

Espresso + AndroidX Test
The de facto standard for UI testing on Android.

### Conclusion

Selected proven, widely-adopted libraries that accelerate development while maintaining modern standards. The stack contains only essential components - nothing superfluous, just what's actually required for the application to function optimally.
