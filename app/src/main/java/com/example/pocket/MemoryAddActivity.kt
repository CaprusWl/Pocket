package com.example.pocket

import android.Manifest
import android.Manifest.permission.*
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pocket.util.MediaPlayerHelper
import com.example.pocket.util.MediaRecorderHelper
import kotlinx.android.synthetic.main.activity_memory_add.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import com.zhihu.matisse.engine.impl.GlideEngine
import android.content.pm.ActivityInfo
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.filter.Filter
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.bumptech.glide.Glide
import com.example.pocket.memo.MemoFragment
import kotlinx.android.synthetic.main.item_memory.*


class MemoryAddActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private val REQUEST_CODE_CHOOSE : Int = 23

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "您拒绝授权,并勾选了不再提醒", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this).setTitle("打开应用程序设置修改应用程序权限").build().show()
        } else {
            Toast.makeText(this, "您拒绝授权", Toast.LENGTH_SHORT).show()
            checkAudioPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(this, "您同意了授权", Toast.LENGTH_SHORT).show()
        checkAudioPermission()
    }

    private lateinit var baseUrl: String
    private lateinit var audioUrl: String
    private val recorderHelper: MediaRecorderHelper = MediaRecorderHelper()
    private val playerHelper: MediaPlayerHelper = MediaPlayerHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_add)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = Color.TRANSPARENT
        }

        baseUrl = applicationContext.filesDir.absolutePath
        audioUrl = "$baseUrl/mAudio"

        checkAudioPermission()

        btn_add_audio.setOnClickListener {
            if (recorderHelper.status == MediaRecorderHelper.STATUS.START) {
                btn_add_audio.text = "播放录音"
                recorderHelper.stop()
                recorderHelper.release()
                Toast.makeText(this, "finished recording", Toast.LENGTH_SHORT).show()
            } else if (recorderHelper.status == MediaRecorderHelper.STATUS.INITIALIZED) {
                btn_add_audio.text = "停止录音"
                recorderHelper.start()
                Toast.makeText(this, "begin recording", Toast.LENGTH_SHORT).show()
            } else if (recorderHelper.status == MediaRecorderHelper.STATUS.RELEASED) {
                if (playerHelper.status == MediaPlayerHelper.STATUS.NOT_INIT) {
                    playerHelper.initialize(audioUrl)
                }
                if (playerHelper.status == MediaPlayerHelper.STATUS.START) {
                    playerHelper.stop()
                }
                btn_add_audio.text = "播放录音"
                playerHelper.start()
                Toast.makeText(this, "begin playing", Toast.LENGTH_SHORT).show()
            } else {
                recorderHelper.initialize(audioUrl)
                recorderHelper.start()
                Toast.makeText(this, "begin recording", Toast.LENGTH_SHORT).show()
            }
        }

        selected_photo.setOnClickListener {
            Matisse.from(this@MemoryAddActivity)
                .choose(MimeType.ofAll())
                .countable(true)
                .maxSelectable(1)
                .addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(resources.getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(Glide4Engine())
                .forResult(REQUEST_CODE_CHOOSE)
        }

        remind_back_button.setOnClickListener {
            finish()
        }

        btn_commit.setOnClickListener {
            val intent = Intent(this, MemoFragment::class.java)
            intent.putExtra("title", send_title_edit_text.text.toString())
            intent.putExtra("content", txt_content.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun checkAudioPermission() {
        if (EasyPermissions.hasPermissions(this, RECORD_AUDIO, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)) {
            recorderHelper.initialize(audioUrl)
        } else {
            EasyPermissions.requestPermissions(
                this,
                "应用程序需要访问您的录音机,您需要在下个弹窗中允许我们使用录音机",
                1010,
                RECORD_AUDIO, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private lateinit var mSelected: List<Uri>

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == Activity.RESULT_OK) {
            mSelected = Matisse.obtainResult(data!!)
            selected_photo.background = null
            Glide.with(this).load(mSelected[0]).into(selected_photo)
        } else {
            Toast.makeText(this, "select failed", Toast.LENGTH_SHORT).show()
        }
    }
}
