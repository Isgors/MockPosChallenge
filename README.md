# Mock POS Challenge

This project is a mock Point of Sale (POS) application developed as a challenge. It demonstrates various Android development concepts including UI with Jetpack Compose, state management, and build variant configuration.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Key Libraries](#key-libraries)
- [Project Structure Highlights](#project-structure-highlights)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Building and Running](#building-and-running)
- [Build Variants](#build-variants)
    - [Available Flavors](#available-flavors)
    - [Running Debug Variants](#running-debug-variants)
    - [Important Note on Release Variants](#important-note-on-release-variants)
- [Code Style and Conventions](#code-style-and-conventions)
- [Further Development](#further-development)

## Technologies Used

- **Kotlin:** The primary programming language used for development.
- **Android SDK:** For building native Android applications.
- **Gradle:** Build automation tool.
- **Jetpack Compose:** Android's modern toolkit for building native UI declaratively.
- **Material Design 3:** Theming and UI components based on the latest Material Design guidelines.

## Key Libraries

This project leverages several Android Jetpack and third-party libraries:

- **Jetpack Compose:**
    - `androidx.compose.ui`: Core UI tooling for Compose.
    - `androidx.compose.material3`: Material Design 3 components.
    - `androidx.compose.material:material-icons-core`: For Material icons.
    - `androidx.compose.foundation`: Foundation layout and interaction building blocks.
    - `androidx.compose.runtime`: Core runtime for Compose state management.
    - `androidx.compose.ui:ui-tooling-preview`: For Composable previews in Android Studio.
    - `androidx.activity:activity-compose`: Integration for Compose in Activities.
- **Kotlin Standard Library & Coroutines:**
    - `org.jetbrains.kotlin:kotlin-stdlib`
    - `org.jetbrains.kotlinx:kotlinx-coroutines-core` & `kotlinx-coroutines-android`: For asynchronous programming.
- **Dependency Injection:**
    - `io.insert-koin:koin-core`: Core Koin library.
    - `io.insert-koin:koin-android`: Koin for Android specific features.
    - `io.insert-koin:koin-androidx-compose`: Koin integration for Jetpack Compose.
- **AndroidX Core & Lifecycle:**
    - `androidx.core:core-ktx`: Kotlin extensions for core Android libraries.
    - `androidx.lifecycle:lifecycle-runtime-ktx`: Lifecycle-aware components.
    - `androidx.lifecycle:lifecycle-viewmodel-compose`: ViewModel integration with Compose.
- **Testing:**
    - `junit:junit`: For unit tests.
    - `androidx.test.ext:junit`: AndroidX Test library for JUnit.
    - `org.mockito:mockito-core`: For UI tests.
    - `androidx.compose.ui:ui-test-junit4`: For Compose UI tests.

## Project Structure Highlights

- **`app/src/main/java/com/igordesouza/mockposchallenge/`**: Main application code.
    - **`di/`**: Dependency injection modules (Koin).
    - **`domain/`**: Business logic, use cases, and domain models.
    - **`data/`**: Data sources, repositories.
    - **`ui/`**: UI-related classes.
        - **`MainActivity.kt`**: The main entry point Activity.
        - **`theme/`**: Jetpack Compose theming (colors, typography, shapes).
        - **`components/`**: Reusable UI components (e.g., `CurrencyInput`, `NumericKeyboard`).
        - **`screens/`**: Composable functions representing different application screens.
        - **`model/`**: UI-specific data models or states.
- **`app/src/main/res/`**: Main application resources (drawables, values, etc.).
- **Flavor-specific directories (e.g., `app/src/standard/res/`, `app/src/gold/res/`)**: Contain resources specific to product flavors.
- **`app/build.gradle.kts`**: Module-level Gradle build script, including dependencies and build variant configuration.

## Getting Started

### Prerequisites

- Android Studio Iguana | 2023.2.1 or newer.
- Android SDK Platform matching `compileSdk` (currently 36).
- An Android Emulator or a physical Android device with `minSdk` (currently 24) or higher.

### Building and Running

1.  **Clone the repository:**
2.  **Open in Android Studio:**
    - Launch Android Studio.
    - Select "Open" and navigate to the cloned project directory.
    - Allow Gradle to sync and build the project.
3.  **Select a Build Variant:**
    - Open the "Build Variants" panel in Android Studio (usually bottom-left, or View > Tool Windows > Build Variants).
    - For running the app to reach `MainActivity` for general development and testing, **select a `debug` variant**. For example:
        - `standardPositivoDebug`
        - `goldSumniDebug`
        - Any combination ending in `Debug`.
4.  **Run the App:**
    - Select an emulator or connected device from the target device dropdown.
    - Click the "Run 'app'" button (green play icon) or use the menu: Run > Run 'app'.
    - This will build the selected debug variant and install it on the target device/emulator, launching `MainActivity`.

## Build Variants

This project is configured with multiple build variants based on product flavors and build types.

### Available Flavors

- **`version` dimension:**
    - `standard`
    - `gold`
- **`manufacturer` dimension:**
    - `positivo`
    - `sumni`

These combine with build types (`debug`, `release`) to create variants like `standardPositivoDebug`, `goldSumniRelease`, etc.

### Running Debug Variants

All `debug` variants (e.g., `standardSumniDebug`, `goldPositivoDebug`) are configured to use the standard Android debug signing key. These are the variants you should use for development and testing. They will build and run without any additional signing configuration required from your side.

### Important Note on Release Variants

The `release` build variants (e.g., `standardProductionRelease`, `goldStagingRelease`) in this project are configured with **example `signingConfigs`**.

**These `release` variants WILL NOT RUN "out-of-the-box" because:**

1.  The `release_keystore.jks` file is a placeholder and does not exist in the repository.
2.  The passwords and alias are placeholders.
3.  In real-world POS applications, release signing configurations are often highly specific, potentially varying by device manufacturer, Android OS version, or specific client requirements. They involve secure keystores and credentials that are not part of the generic codebase.

This setup is purely illustrative of how one *might* configure signing for different release scenarios. **To build a runnable `release` variant, you would need to provide a valid keystore and update the `signingConfigs.release` block with your actual credentials.** For development and testing the application's functionality, please use the `debug` variants.

## Code Style and Conventions

- Follow standard Kotlin coding conventions.
- Adhere to Android Jetpack Compose best practices.
- Use Material Design 3 guidelines for UI/UX.

## Further Development

- Investigate some weird behaviors when typing specific values on the keyboard
- Add calculator to HomeScreen
- Add payment methods icons