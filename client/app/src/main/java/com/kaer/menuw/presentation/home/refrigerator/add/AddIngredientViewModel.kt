package com.kaer.menuw.presentation.home.refrigerator.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaer.menuw.domain.entity.Ingredient
import com.kaer.menuw.domain.entity.IngredientTotal
import com.kaer.menuw.domain.usecase.GetIngredientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddIngredientViewModel @Inject constructor(
    private val getIngredientUseCase: GetIngredientUseCase
) : ViewModel() {

//    private val _mockIngredientList: MutableLiveData<List<IngredientTotal>> = MutableLiveData(
//        mutableListOf(
//            IngredientTotal(
//                "채소",
//                arrayListOf(
//                    IngredientTotal.IngredientItem(1, "가지", "https://d25vrbl2gtcuw7.cloudfront.net/채소/가지.svg"),
//                    IngredientTotal.IngredientItem(2, "감자", "https://d25vrbl2gtcuw7.cloudfront.net/채소/감자.svg"),
//                    IngredientTotal.IngredientItem(3, "고구마", "https://d25vrbl2gtcuw7.cloudfront.net/채소/고구마.svg"),
//                    IngredientTotal.IngredientItem(4, "깻잎", "https://d25vrbl2gtcuw7.cloudfront.net/채소/깻잎.svg"),
//                    IngredientTotal.IngredientItem(5, "당근", "https://d25vrbl2gtcuw7.cloudfront.net/채소/당근.svg"),
//                    IngredientTotal.IngredientItem(6, "대파", "https://d25vrbl2gtcuw7.cloudfront.net/채소/대파.svg"),
//                    IngredientTotal.IngredientItem(7, "마늘", "https://d25vrbl2gtcuw7.cloudfront.net/채소/마늘.svg"),
//                    IngredientTotal.IngredientItem(8, "무", "https://d25vrbl2gtcuw7.cloudfront.net/채소/무.svg"),
//                    IngredientTotal.IngredientItem(9, "방울토마토", "https://d25vrbl2gtcuw7.cloudfront.net/채소/방울토마토.svg"),
//                    IngredientTotal.IngredientItem(10, "배추", "https://d25vrbl2gtcuw7.cloudfront.net/채소/배추.svg"),
//                    IngredientTotal.IngredientItem(11, "부추", "https://d25vrbl2gtcuw7.cloudfront.net/채소/부추.svg"),
//                    IngredientTotal.IngredientItem(12, "브로콜리", "https://d25vrbl2gtcuw7.cloudfront.net/채소/브로콜리.svg"),
//                    IngredientTotal.IngredientItem(13, "새송이버섯", "https://d25vrbl2gtcuw7.cloudfront.net/채소/새송이버섯.svg"),
//                    IngredientTotal.IngredientItem(14, "생강", "https://d25vrbl2gtcuw7.cloudfront.net/채소/생강.svg"),
//                    IngredientTotal.IngredientItem(15, "시금치", "https://d25vrbl2gtcuw7.cloudfront.net/채소/시금치.svg"),
//                    IngredientTotal.IngredientItem(16, "아스파라거스", "https://d25vrbl2gtcuw7.cloudfront.net/채소/아스파라거스.svg"),
//                    IngredientTotal.IngredientItem(17, "애호박", "https://d25vrbl2gtcuw7.cloudfront.net/채소/애호박.svg"),
//                    IngredientTotal.IngredientItem(18, "양배추", "https://d25vrbl2gtcuw7.cloudfront.net/채소/양배추.svg"),
//                    IngredientTotal.IngredientItem(19, "양상추", "https://d25vrbl2gtcuw7.cloudfront.net/채소/양상추.svg"),
//                    IngredientTotal.IngredientItem(20, "양송이버섯", "https://d25vrbl2gtcuw7.cloudfront.net/채소/양송이버섯.svg"),
//                    IngredientTotal.IngredientItem(21, "양파", "https://d25vrbl2gtcuw7.cloudfront.net/채소/양파.svg"),
//                    IngredientTotal.IngredientItem(22, "청경채", "https://d25vrbl2gtcuw7.cloudfront.net/채소/청경채.svg"),
//                    IngredientTotal.IngredientItem(23, "콩나물", "https://d25vrbl2gtcuw7.cloudfront.net/채소/콩나물.svg"),
//                    IngredientTotal.IngredientItem(24, "토마토", "https://d25vrbl2gtcuw7.cloudfront.net/채소/토마토.svg"),
//                    IngredientTotal.IngredientItem(25, "파프리카", "https://d25vrbl2gtcuw7.cloudfront.net/채소/파프리카.svg"),
//                    IngredientTotal.IngredientItem(26, "팽이버섯", "https://d25vrbl2gtcuw7.cloudfront.net/채소/팽이버섯.svg"),
//                    IngredientTotal.IngredientItem(27, "표고버섯", "https://d25vrbl2gtcuw7.cloudfront.net/채소/표고버섯.svg"),
//                    IngredientTotal.IngredientItem(28, "피망", "https://d25vrbl2gtcuw7.cloudfront.net/채소/피망.svg")
//                )
//            ),
//            IngredientTotal(
//                "유제품",
//                arrayListOf(
//                    IngredientTotal.IngredientItem(29, "버터", "https://d25vrbl2gtcuw7.cloudfront.net/유제품/버터.svg"),
//                    IngredientTotal.IngredientItem(30, "생크림", "https://d25vrbl2gtcuw7.cloudfront.net/유제품/생크림.svg"),
//                    IngredientTotal.IngredientItem(31, "아이스크림", "https://d25vrbl2gtcuw7.cloudfront.net/유제품/아이스크림.svg"),
//                    IngredientTotal.IngredientItem(32, "요거트", "https://d25vrbl2gtcuw7.cloudfront.net/유제품/요거트.svg"),
//                    IngredientTotal.IngredientItem(33, "우유", "https://d25vrbl2gtcuw7.cloudfront.net/유제품/우유.svg"),
//                    IngredientTotal.IngredientItem(34, "치즈", "https://d25vrbl2gtcuw7.cloudfront.net/유제품/치즈.svg"),
//                )
//            ),
//            IngredientTotal(
//                "곡류",
//                arrayListOf(
//                    IngredientTotal.IngredientItem(35, "강낭콩", "https://d25vrbl2gtcuw7.cloudfront.net/곡물/강낭콩.svg"),
//                    IngredientTotal.IngredientItem(36, "기장", "https://d25vrbl2gtcuw7.cloudfront.net/곡물/기장.svg"),
//                    IngredientTotal.IngredientItem(37, "귀리", "https://d25vrbl2gtcuw7.cloudfront.net/곡물/귀리.svg"),
//                    IngredientTotal.IngredientItem(38, "메밀", "https://d25vrbl2gtcuw7.cloudfront.net/곡물/메밀.svg"),
//                    IngredientTotal.IngredientItem(39, "병아리콩", "https://d25vrbl2gtcuw7.cloudfront.net/곡물/병아리콩.svg"),
//                    IngredientTotal.IngredientItem(40, "보리", "https://d25vrbl2gtcuw7.cloudfront.net/곡물/보리.svg"),
//                    IngredientTotal.IngredientItem(41, "쌀", "https://d25vrbl2gtcuw7.cloudfront.net/곡물/쌀.svg"),
//                    IngredientTotal.IngredientItem(42, "옥수수", "https://d25vrbl2gtcuw7.cloudfront.net/곡물/옥수수.svg"),
//                    IngredientTotal.IngredientItem(43, "완두콩", "https://d25vrbl2gtcuw7.cloudfront.net/곡물/완두콩.svg"),
//                    IngredientTotal.IngredientItem(44, "팥", "https://d25vrbl2gtcuw7.cloudfront.net/곡물/팥.svg"),
//                    IngredientTotal.IngredientItem(45, "호밀", "https://d25vrbl2gtcuw7.cloudfront.net/곡물/호밀.svg")
//                )
//            ),
//            IngredientTotal(
//                "육류",
//                arrayListOf(
//                    IngredientTotal.IngredientItem(46, "달걀", "https://d25vrbl2gtcuw7.cloudfront.net/육류/달걀.svg"),
//                    IngredientTotal.IngredientItem(47, "닭고기", "https://d25vrbl2gtcuw7.cloudfront.net/육류/닭고기.svg"),
//                    IngredientTotal.IngredientItem(48, "돼지고기", "https://d25vrbl2gtcuw7.cloudfront.net/육류/돼지고기.svg"),
//                    IngredientTotal.IngredientItem(49, "베이컨", "https://d25vrbl2gtcuw7.cloudfront.net/육류/베이컨.svg"),
//                    IngredientTotal.IngredientItem(50, "소고기", "https://d25vrbl2gtcuw7.cloudfront.net/육류/소고기.svg"),
//                    IngredientTotal.IngredientItem(51, "소세지", "https://d25vrbl2gtcuw7.cloudfront.net/육류/소세지.svg"),
//                    IngredientTotal.IngredientItem(52, "양고기", "https://d25vrbl2gtcuw7.cloudfront.net/육류/양고기.svg"),
//                    IngredientTotal.IngredientItem(53, "오리고기", "https://d25vrbl2gtcuw7.cloudfront.net/육류/오리고기.svg"),
//                    IngredientTotal.IngredientItem(54, "칠면조", "https://d25vrbl2gtcuw7.cloudfront.net/육류/칠면조.svg"),
//                    IngredientTotal.IngredientItem(55, "페퍼로니", "https://d25vrbl2gtcuw7.cloudfront.net/육류/페퍼로니.svg"),
//                    IngredientTotal.IngredientItem(56, "햄", "https://d25vrbl2gtcuw7.cloudfront.net/육류/햄.svg")
//                )
//            ),
//            IngredientTotal(
//                "생선",
//                arrayListOf(
//                    IngredientTotal.IngredientItem(57, "고등어", "https://d25vrbl2gtcuw7.cloudfront.net/생선/고등어.svg"),
//                    IngredientTotal.IngredientItem(58, "꽁치", "https://d25vrbl2gtcuw7.cloudfront.net/생선/꽁치.svg"),
//                    IngredientTotal.IngredientItem(59, "대구", "https://d25vrbl2gtcuw7.cloudfront.net/생선/대구.svg"),
//                    IngredientTotal.IngredientItem(60, "멸치", "https://d25vrbl2gtcuw7.cloudfront.net/생선/멸치.svg"),
//                    IngredientTotal.IngredientItem(61, "연어", "https://d25vrbl2gtcuw7.cloudfront.net/생선/연어.svg"),
//                    IngredientTotal.IngredientItem(62, "아귀", "https://d25vrbl2gtcuw7.cloudfront.net/생선/아귀.svg"),
//                    IngredientTotal.IngredientItem(63, "장어", "https://d25vrbl2gtcuw7.cloudfront.net/생선/장어.svg"),
//                    IngredientTotal.IngredientItem(64, "참치", "https://d25vrbl2gtcuw7.cloudfront.net/생선/참치.svg")
//                )
//            ),
//            IngredientTotal(
//                "조미료",
//                arrayListOf(
//                    IngredientTotal.IngredientItem(65, "간장", "https://d25vrbl2gtcuw7.cloudfront.net/조미료/간장.svg"),
//                    IngredientTotal.IngredientItem(66, "고추장", "https://d25vrbl2gtcuw7.cloudfront.net/조미료/고추장.svg"),
//                    IngredientTotal.IngredientItem(67, "고춧가루", "https://d25vrbl2gtcuw7.cloudfront.net/조미료/고춧가루.svg"),
//                    IngredientTotal.IngredientItem(68, "꿀", "https://d25vrbl2gtcuw7.cloudfront.net/조미료/꿀.svg"),
//                    IngredientTotal.IngredientItem(69, "조미료", "https://d25vrbl2gtcuw7.cloudfront.net/조미료/된장.svg"),
//                    IngredientTotal.IngredientItem(70, "레몬즙", "https://d25vrbl2gtcuw7.cloudfront.net/조미료/레몬즙.svg"),
//                    IngredientTotal.IngredientItem(71, "설탕", "https://d25vrbl2gtcuw7.cloudfront.net/조미료/설탕.svg",),
//                    IngredientTotal.IngredientItem(72, "소금", "https://d25vrbl2gtcuw7.cloudfront.net/조미료/소금.svg"),
//                    IngredientTotal.IngredientItem(73, "식용유", "https://d25vrbl2gtcuw7.cloudfront.net/조미료/식용유.svg"),
//                    IngredientTotal.IngredientItem(74, "식초", "https://d25vrbl2gtcuw7.cloudfront.net/조미료/식초.svg"),
//                    IngredientTotal.IngredientItem(75, "올리브유", "https://d25vrbl2gtcuw7.cloudfront.net/조미료/올리브유.svg"),
//                    IngredientTotal.IngredientItem(76, "후추", "https://d25vrbl2gtcuw7.cloudfront.net/조미료/후추.svg")
//                )
//            ),
//        )
//    )
//
//    val mockIngredientList: LiveData<List<IngredientTotal>>
//        get() = _mockIngredientList

    private val _ingredientList = MutableLiveData<List<Ingredient>>()
    val ingredientList: LiveData<List<Ingredient>>
        get() = _ingredientList

    private val _backgroundTextVisible: MutableLiveData<Boolean> = MutableLiveData()
    val backgroundTextVisible: LiveData<Boolean>
        get() = _backgroundTextVisible

    private val _selectedTypeId: MutableLiveData<Int> = MutableLiveData(0)
    val selectedTypeId: LiveData<Int>
        get() = _selectedTypeId

    private val _selectedIngredientArray: MutableLiveData<ArrayList<IngredientTotal.IngredientItem>> = MutableLiveData()
    val selectedIngredientArray: LiveData<ArrayList<IngredientTotal.IngredientItem>>
        get() = _selectedIngredientArray

    private val _updateStoredIngredientArray: MutableLiveData<ArrayList<IngredientTotal.IngredientItem>> = MutableLiveData()
    val updateStoredIngredientArray: LiveData<ArrayList<IngredientTotal.IngredientItem>>
        get() = _updateStoredIngredientArray

    private val _addBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val addBtnEnabled: LiveData<Boolean>
        get() = _addBtnEnabled

    private val _deleteBtnVisible: MutableLiveData<Boolean> = MutableLiveData(false)
    val deleteBtnVisible: LiveData<Boolean>
        get() = _deleteBtnVisible

    private val _deleteEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val deleteEnabled: LiveData<Boolean>
        get() = _deleteEnabled

    fun getIngredientList() {
        Timber.d("아이템 리스트 viewmodel -> 테스트")
        viewModelScope.launch {
            getIngredientUseCase().onSuccess {  ingredients ->
                Timber.d("아이템 리스트 viewmodel 성공 -> $ingredients")
                _ingredientList.value = ingredients
            }.onFailure {
                Timber.d("아이템 리스트 viewmodel 실패 -> $it")
            }
        }
    }

    fun setBackgroundTextVisible(visible: Boolean) {
        Timber.d("비어있나? -> $visible")
        _backgroundTextVisible.value = visible
    }

    fun setDeleteBtnVisible(visible: Boolean) {
        _deleteBtnVisible.value = visible
    }

    fun setDeleteEnabled(enabled: Boolean) {
        _deleteEnabled.value = enabled
    }

    fun clickTypeId(typeId: Int) {
        _selectedTypeId.value = typeId
    }

    fun selectedIngredientList(selectedArray: ArrayList<IngredientTotal.IngredientItem>) {
        _selectedIngredientArray.value = selectedArray
    }

    fun updateStoredList(storedList: ArrayList<IngredientTotal.IngredientItem>) {
        _updateStoredIngredientArray.value = storedList
    }

    fun setAddBtnEnabled(enabled: Boolean) {
        _addBtnEnabled.value = enabled
    }

    companion object {
        const val VEGETABLE = "채소"
        const val DAIRY_FOOD = "유제품"
        const val GRAIN = "곡류"
        const val MEAT = "육류"
        const val FISH = "생선"
        const val OTHERS = "기타"
    }

}