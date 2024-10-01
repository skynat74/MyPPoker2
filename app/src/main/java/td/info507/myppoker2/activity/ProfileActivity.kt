package td.info507.myppoker2.activity

import CardStorage
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import td.info507.myppoker2.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        updateProfilePicture()
        updateMessage()
        updateStorage()
    }

    private fun updateProfilePicture() {

        var picture_button = findViewById<ImageButton>(R.id.profile_picture_button)
        var profile_view = findViewById<ImageView>(R.id.profile_view)

        var take_photo =
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
                if (bitmap != null)
                    profile_view.setImageBitmap(bitmap)
            }

        picture_button.setOnClickListener {
            take_photo.launch(null)
        }
    }

    private fun updateMessage() {
        var message_button = findViewById<ImageButton>(R.id.profile_message_button)

        message_button.setOnClickListener {

            if (checkPermission(Manifest.permission.CALL_PHONE)) {
                var intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:0782453122"))
                startActivity(intent)
            }
        }
    }

    private fun checkPermission (permission: String):Boolean {
        var res = true
        if (ContextCompat.checkSelfPermission(applicationContext, permission) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                ActivityCompat.requestPermissions(this, arrayOf(permission), 0)
            }
            res = false
        }
        return res
    }

    private fun updateStorage() {
        val radioGroup = findViewById<RadioGroup>(R.id.profile_storage)
        var id = R.id.storage_none
        when (CardStorage.getStorage(applicationContext)) {
            CardStorage.DATA_BASE -> id = R.id.storage_db
            CardStorage.FILE_JSON -> id = R.id.storage_json
        }
        radioGroup.check(id)
        radioGroup.setOnCheckedChangeListener{group, checkedId ->
            var prefStorage = CardStorage.NONE
            when (checkedId) {
            R.id.storage_db -> prefStorage = CardStorage.DATA_BASE
            R.id.storage_json -> prefStorage = CardStorage.FILE_JSON
        }
            CardStorage.setStorage(applicationContext, prefStorage)
        }
    }
}