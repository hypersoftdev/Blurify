**BlurryBackgroundView**

BlurryBackgroundView is a custom ConstraintLayout that allows you to add a blurred background effect to your views in Android. This view applies a blur to the background of any view and adds customizable gradient overlays and rounded corners.

**Features**

- Apply a blur effect to any view's background.
- Add a gradient overlay with customizable start and end colors.
- Set the corner radius for rounded corners.
- Customizable overlay color and stroke width for gradient lines.

**Installation**

Add the following dependency to your project:

## Usage

### XML

Here is an example of how to use BlurryBackgroundView in your XML layout:

xml

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

### Programmatically

You can also modify the view programmatically

override fun onWindowFocusChanged(hasFocus: Boolean) {

super.onWindowFocusChanged(hasFocus)

val blurryBackgroundView = findViewById&lt;BlurryBackgroundView&gt;(R.id.blurryBackgroundView)

blurryBackgroundView.updateBackgroundFromView()

}

**Custom Attributes**

| **Attribute** | **Description** | **Default Value** |
| --- | --- | --- |
| cornerRadiusView | Set the corner radius for the blurred background. | 0dp |
| backgroundColorOverlay | The color of the overlay applied on top of the blurred background. | @color/gray_white |
| startColorGradient | Start color for the gradient applied on the background. | @color/secondary_text |
| strokeWidthGradient | The width of the stroke used for the gradient line. | 5dp |

**How It Works**

- The BlurryBackgroundView captures a screenshot of its root view and then crops the relevant portion to create a blurred background.
- The blur is applied using either RSBlur or FastBlur depending on the device's capabilities.
- An overlay color and a gradient line can be drawn over the blurred background for additional customization.

## Customization Options

- **Corner Radius**: Control the rounding of the background.
- **Gradient**: Add a gradient overlay with customizable start and end colors.
- **Blur Radius**: Control the intensity of the blur with a default radius of 25.

## License