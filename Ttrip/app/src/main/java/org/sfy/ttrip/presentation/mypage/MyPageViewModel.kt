package org.sfy.ttrip.presentation.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sfy.ttrip.data.remote.Resource
import org.sfy.ttrip.domain.entity.mypage.UserProfile
import org.sfy.ttrip.domain.entity.user.UserTest
import org.sfy.ttrip.domain.usecase.mypage.GetUserProfileUseCase
import org.sfy.ttrip.domain.usecase.mypage.UpdatePreferencesUseCase
import org.sfy.ttrip.domain.usecase.mypage.UpdateUserInfoUseCase
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val updateUserInfoUseCase: UpdateUserInfoUseCase,
    private val updatePreferencesUseCase: UpdatePreferencesUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _userTest: MutableLiveData<UserTest> =
        MutableLiveData(UserTest(0, 0, 0, 0, 0, 0, 0, 0, 0))
    val userTest: LiveData<UserTest> = _userTest

    private val _userProfile = MutableLiveData<UserProfile?>()
    val userProfile: LiveData<UserProfile?> = _userProfile

    var genderState = ""

    fun updateUserInfo(age: Int, gender: String, intro: String, nickname: String) {
        viewModelScope.launch {
            updateUserInfoUseCase(age, gender, intro, nickname)
        }
    }

    fun checkSurvey(position: Int, record: Int) {
        val userTest = userTest.value ?: return
        when (position) {
            0 -> userTest.preferNatureThanCity = record
            1 -> userTest.preferPlan = record
            2 -> userTest.preferDirectFlight = record
            3 -> userTest.preferCheapHotelThanComfort = record
            4 -> userTest.preferGoodFood = record
            5 -> userTest.preferCheapTraffic = record
            6 -> userTest.preferPersonalBudget = record
            7 -> userTest.preferTightSchedule = record
            8 -> userTest.preferShoppingThanTour = record
        }
        _userTest.value = userTest
    }

    fun updatePreferences(
        preferCheapHotelThanComfort: Int,
        preferCheapTraffic: Int,
        preferDirectFlight: Int,
        preferGoodFood: Int,
        preferNatureThanCity: Int,
        preferPersonalBudget: Int,
        preferPlan: Int,
        preferShoppingThanTour: Int,
        preferTightSchedule: Int
    ) {
        viewModelScope.launch {
            updatePreferencesUseCase(
                preferCheapHotelThanComfort,
                preferCheapTraffic,
                preferDirectFlight,
                preferGoodFood,
                preferNatureThanCity,
                preferPersonalBudget,
                preferPlan,
                preferShoppingThanTour,
                preferTightSchedule
            )
        }
    }

    fun getUserProfile() = viewModelScope.launch {
        when (val value = getUserProfileUseCase()) {
            is Resource.Success -> {
                _userProfile.value = value.data
            }
            is Resource.Error -> {
                Log.d("getUserProfile", "getUserProfile: ${value.errorMessage}")
            }
        }
    }
}