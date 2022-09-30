package js.pekah.basictodolist.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DialogViewModel : ViewModel(){

    private val _name = MutableLiveData("긴머리 소녀")
    private val _data = MutableLiveData("인스타처럼 자기소개 메세지 표시")
    private val _place = MutableLiveData("24세 여성 정자동 어쩌구 저쩌구")

    val name: LiveData<String> = _name
    val data: LiveData<String> = _data
    val place: LiveData<String> = _place
}