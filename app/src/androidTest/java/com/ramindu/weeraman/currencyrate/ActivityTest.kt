package com.ramindu.weeraman.currencyrate

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.text.TextWatcher
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.ramindu.weeraman.currencyrate.viewmodel.ExchangeRateViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


class ActivityTest {

    @Rule
    @JvmField
    val activityRule = IntentsTestRule(MainActivity::class.java)

    @Rule
    @JvmField
    val taskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var viewModel: ExchangeRateViewModel

    @Mock
    private lateinit var emailTextChangedListener: TextWatcher

    @Mock
    private lateinit var passwordTextChangedListener: TextWatcher

    private val emailField = MutableLiveData<Boolean?>()
    //private val passwordField = ObservableField<String>()



   // private lateinit var loginFragment: LoginFragment
//
    @Before
    fun setUp() {
       activityRule.activity.viewModelFactory  = createViewModelFactory(viewModel)
       `when`(viewModel.isLoading).thenReturn(emailField)
    }

    @Test
    fun isProgressVisible() {
        emailField.value = true
        onView(withId(R.id.loadingProgressBar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun <T : ViewModel> createViewModelFactory(viewModel: T): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
                if (viewModelClass.isAssignableFrom(viewModel.javaClass)) {
                    @Suppress("UNCHECKED_CAST")
                    return viewModel as T
                }
                throw IllegalArgumentException("Unknown view model class " + viewModelClass)
            }
        }
    }

   /* def mockito_version = '2.28.2' // For local unit tests on your development machine
    testImplementation "org.mockito:mockito-core:$mockito_version" // For instrumentation tests on Android devices and emulators
    androidTestImplementation "org.mockito:mockito-android:$mockito_version"

    androidTestImplementation 'androidx.test.espresso:espresso-intents:3.1.0'
    androidTestImplementation 'androidx.test:runner:1.1.0'
    androidTestImplementation 'androidx.test:rules:1.1.0'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.0'
    androidTestImplementation "android.arch.core:core-testing:1.0.0"
    androidTestImplementation 'androidx.test.ext:junit:1.1.0'
    testImplementation 'org.mockito:mockito-inline:2.13.0'*/
}