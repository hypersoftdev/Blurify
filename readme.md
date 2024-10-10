[![](https://jitpack.io/v/hypersoftdev/Blurify.svg)](https://jitpack.io/#hypersoftdev/Blurify)

# Blurify

**Blurify** is a custom `ConstraintLayout` that enables you to add a blurred background effect to your views in Android. It allows for the application of a blur to the background of any view while also offering customizable gradient overlays, rounded corners, and more.

## Features

- Apply a blur effect to any view's background.
- Add a gradient overlay with customizable start and end colors.
- Set the corner radius for rounded corners.
- Customizable overlay color and stroke width for gradient lines.

## Step-by-Step Usage:

### 1. Dependency Addition

To use the **Blurify**, follow these steps to update your Gradle files.

#### Gradle Integration

##### Step A: Add Maven Repository
In your **project-level** `build.gradle` or `settings.gradle` file, add the following repository:

```
repositories {
    google()
    mavenCentral()
    maven { url "https://jitpack.io" }
}
```

### Step B: Add Dependencies

Include the **Blurify** library in your **app-level** `build.gradle` file. Replace `x.x.x` with the latest version: [![](https://jitpack.io/v/hypersoftdev/Blurify.svg)](https://jitpack.io/#hypersoftdev/Blurify)

```
implementation 'com.github.hypersoftdev:Blurify:x.x.x'
```

## Usage

### XML

Here’s an example of how to use `BlurryBackgroundView` in your XML layout:

```
<ImageView
    android:id="@+id/image"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scaleType="centerCrop"
    android:src="@drawable/background_image"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />

<com.hypersoft.blurify.BlurryBackgroundView
    android:id="@+id/blurryBackgroundView"
    android:layout_width="0dp"
    android:layout_height="300dp"
    app:backgroundColorOverlay="@color/gray_white"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:startColorGradient="@color/gray" />

```

### Programmatically

You can modify the `BlurryBackgroundView` programmatically in your Kotlin code as shown below:

```
override fun onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)

    val blurryBackgroundView = findViewById<BlurryBackgroundView>(R.id.blurryBackgroundView)
    blurryBackgroundView.updateBackgroundFromView()
}
```

### Custom Attributes

| **Attribute**            | **Description**                                          | **Default Value**       |
|--------------------------|----------------------------------------------------------|-------------------------|
| `cornerRadiusView`        | Sets the corner radius for the blurred background.       | `0dp`                   |
| `backgroundColorOverlay`  | The color of the overlay applied on top of the blur.     | `@color/gray_white`      |
| `startColorGradient`      | Start color for the gradient applied on the background.  | `@color/secondary_text`  |
| `strokeWidthGradient`     | The width of the stroke used for the gradient line.      | `5dp`                   |

### How It Works

- The `BlurryBackgroundView` captures a screenshot of its root view, then crops the relevant portion to create a blurred background effect.
- The blur effect is applied using either **RSBlur** or **FastBlur**, depending on the device's capabilities.
- A customizable overlay color and a gradient line can be drawn over the blurred background for additional styling.

### Customization Options

- **Corner Radius**: Control the rounding of the blurred background using the `cornerRadiusView` attribute.
- **Gradient**: Add a gradient overlay with customizable `startColorGradient` and `endColorGradient`.
- **Blur Radius**: Control the intensity of the blur effect, with a default blur radius of 25.

## Screen Demo

![Demo](https://github.com/hypersoftdev/Blurify/blob/master/screens/screen1.jpg?raw=true)

# Acknowledgements

This work would not have been possible without the invaluable contributions of **Shahrukh Gill**. His expertise, dedication, and unwavering support have been instrumental in bringing this project to fruition.

![Profile](https://github.com/hypersoftdev/Blurify/blob/master/screens/image_profile.jpg?raw=true)

We are deeply grateful for **Shahrukh Gill** involvement and his belief in the importance of this work. His contributions have made a significant impact, and we are honored to have had the opportunity to collaborate with him.

# LICENSE

Copyright 2020 Hypersoft Inc

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

