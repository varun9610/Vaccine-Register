package com.android.vaccineregister

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro2
import com.github.paolorotolo.appintro.AppIntro2Fragment
import com.github.paolorotolo.appintro.model.SliderPagerBuilder

class WelcomeActivity : AppIntro2() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showIntroSlides()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showIntroSlides() {

        val pageOne = SliderPagerBuilder()
                .title(getString(R.string.slide_one_top_text))
                .description(getString(R.string.slide_one_down_text))
                .imageDrawable(R.drawable.slider1)
                .bgColor(getColor(R.color.teal_200))
                .build()

        val pageTwo = SliderPagerBuilder()
                .title(getString(R.string.slide_two_top_text))
                .description(getString(R.string.slide_two_down_text))
                .imageDrawable(R.drawable.slider2)
                .bgColor(R.color.purple_200)
                .build()

        val pageThree = SliderPagerBuilder()
                .title(getString(R.string.slide_three_top_text))
                .description(getString(R.string.slide_three_down_text))
                .imageDrawable(R.drawable.slider3)
                .bgColor(R.color.teal_700)
                .build()

        addSlide(AppIntro2Fragment.newInstance(pageOne))
        addSlide(AppIntro2Fragment.newInstance(pageTwo))
        addSlide(AppIntro2Fragment.newInstance(pageThree))
        showStatusBar(false)
        setFadeAnimation()
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        goToMain()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        goToMain()
    }
}