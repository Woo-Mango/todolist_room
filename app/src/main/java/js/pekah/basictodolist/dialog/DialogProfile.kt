package js.pekah.basictodolist.dialog

import android.app.Dialog
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import js.pekah.basictodolist.R
import js.pekah.basictodolist.databinding.UserProfileBinding

class DialogProfile(private val context: AppCompatActivity) {

    private lateinit var binding : UserProfileBinding
    private val dialog = Dialog(context)

    private lateinit var followButton : Button
    private lateinit var messageButton : Button
    private lateinit var cancelButton : Button

    private val viewModel = DialogViewModel()

    fun start(){
        binding = UserProfileBinding.inflate(context.layoutInflater)
        binding.lifecycleOwner = context
        binding.profile = viewModel
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)


        followButton = dialog.findViewById(R.id.btnFollow)
        messageButton = dialog.findViewById(R.id.btnMessage)
        cancelButton = dialog.findViewById(R.id.btnCancel)

        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
    /*
    fun setOnOKClickedListener(listener: (String) -> Unit) {
        this.listener = object: UserProfileOKClickedListener {
            override fun onOKClicked(content: String) {
                listener(content)
            }
        }
    }
    */

/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)


        followButton = findViewById(R.id.btnFollow)
        messageButton = findViewById(R.id.btnMessage)
        cancelButton = findViewById(R.id.btnCancel)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 취소 버튼 클릭 시 onCancelButtonClicked 호출 후 종료
        cancelButton.setOnClickListener {
            customDialogInterface.onCancelButtonClicked()
            dismiss()
        }

    }
*/
}