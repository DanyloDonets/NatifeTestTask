package donets.danylo.natife.testtask.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.appsflyer.AppsFlyerLib
import com.bumptech.glide.Glide
import donets.danylo.natife.testtask.R

class FullScreenView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_view)

        val imageView = findViewById<ImageView>(R.id.ImageView)

        val url = intent.getStringExtra("url")

        Glide.with(this).load(url).into(imageView)
        AppsFlyerLib.getInstance().logEvent(applicationContext, "FullScreenMode", null)
    }


}