package ru.geekbrains.nasa

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import ru.geekbrains.nasa.mvp.model.api.nasa.entity.mars.Camera
import ru.geekbrains.nasa.mvp.model.api.nasa.entity.mars.Photo
import ru.geekbrains.nasa.mvp.model.api.nasa.entity.mars.Rover
import ru.geekbrains.nasa.ui.fragments.PhotoFragment
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestPhotoFragment {
    private lateinit var scenario: FragmentScenario<PhotoFragment>


    @Before
    fun setup() {

        var photo = Photo()
        var camera = Camera()
        camera.fullName = "camera"
        var rover = Rover()
        rover.name = "rover"

        photo.id = 123
        photo.imgSrc = "https://i.artfile.ru/s/1158678_240417_94_ArtFile_ru.jpg"
        photo.camera = camera
        photo.rover = rover
        val fragmentArgs = bundleOf("photo" to photo)

        scenario = launchFragmentInContainer(fragmentArgs, R.style.Theme_OuterSpace) {
            PhotoFragment()
        }
        Thread.sleep(2000)
    }

    @Test
    fun actionBarTitle_HasText() {
        onView(
            allOf(withId(R.id.tv_photo_id_val), isDisplayed())
        ).check(matches(withText("123")))
        onView(
            allOf(withId(R.id.tv_photo_camera_val), isDisplayed())
        ).check(matches(withText("camera")))
        onView(
            allOf(withId(R.id.tv_photo_rover_name_val), isDisplayed())
        ).check(matches(withText("rover")))

    }

    @Test
    fun fragmentPhoto_IsDisplayed() {
        onView(withId(R.id.iv_photo_imgSrc))
            .check(matches(isDisplayed()))
    }

    @Test
    fun fragment_EffectiveVisible() {
        onView(allOf(withId(R.id.iv_photo_imgSrc), isDisplayed()))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        onView(allOf(withId(R.id.tv_photo_id_val), isDisplayed())).check(
            matches(withEffectiveVisibility(Visibility.VISIBLE))
        )
    }
}