package com.kaer.menuw.presentation.home.refrigerator.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaer.menuw.domain.entity.RecommendMenu

class MenuListViewModel: ViewModel() {

    private val _mockMenuList: MutableLiveData<List<RecommendMenu>> = MutableLiveData(
        mutableListOf(
            RecommendMenu(
                1,
                "http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00587_1.png",
                "두부 카프리제",
                "소고기(100g), 돼지고기(100g), 표고버섯(2개), 마(100g),\n연근(20g), 감자(60g), 깻잎(3장), 애호박(1/3개), 마늘(20g),\n두유(100g), 설탕(20g), 소금(0.2g), 저염간장(20g)"
            ),
            RecommendMenu(
                2,
                "http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00432_1.png",
                "불고기 미니볼",
                "다진소고기 40g, 다진양파 15g 빵가루 30g, 계란 60g, 밀가루 15g\n식용유 200g, 단호박 25g, 감자 25g, 로즈마리 1g\n양념장 : 참기름 5g, 맛간장 15g, 생강청 15g, 흰후추 1g, 다진마늘 1g, 통깨 1g\n소스 : 딸기잼 30g, 오미자청 15g, 물 30g"
            ),
            RecommendMenu(
                3,
                "http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00596_1.png",
                "방울토마토를 곁들인 너비아니구이와 쌈밥",
                "소고기(120g), 흑미(100g), 아스파라거스(3개), 감자(50g),\\n당근(20g), 사과(1/2개), 양송이버섯(5개), 방울토마토(5개),\\n깻잎(3장), 배(1/4개), 브로콜리(30g), 마늘(20g), 설탕(10g),\\n저염간장(10g), 참기름(2g)"
            ),
            RecommendMenu(
                4,
                "http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00599_1.png",
                "버섯과 두부 비프웰링턴",
                "소고기(안심, 120g), 두부(50g), 양송이버섯(3개), 양파(30g),\\n당근(20g), 단호박(1/2개), 가지(1/2개), 감자(50g), 깻잎(3장),\\n마늘(20g), 버터(20g), 밀가루(100g), 달걀(1개), 소금(0.3g)"
            ),
            RecommendMenu(
                5,
                "http://www.foodsafetykorea.go.kr/uploadimg/20190408/20190408092401_1554683041417.jpg",
                "함박 스테이크 & 토마토 카레소스",
                "소고기(다짐육) 50g, 양송이버섯 5g, 양파 40g, 빵가루 25g, 다진 마늘 5g, 카레가루 15g, 토마토 25g, 당근 15g, 감자 15g, 계란노른자 5g"
            ),
        )
    )

    val mockMenuList: LiveData<List<RecommendMenu>>
        get() = _mockMenuList
}