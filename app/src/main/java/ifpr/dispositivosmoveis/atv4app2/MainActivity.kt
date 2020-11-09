package ifpr.dispositivosmoveis.atv4app2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when {
            intent?.action == Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    handleSendText(intent)
                } else if (intent.type?.startsWith("image/") == true) {
                    handleSendImage(intent)
                }
            }
        }
    }

    private fun handleSendText(intent: Intent) {
        val editTextReceivedText = findViewById<EditText>(R.id.editTextReceivedText)

        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            editTextReceivedText.setText(it);
        }
    }

    private fun handleSendImage(intent: Intent) {
        val imageViewReceivedImage = findViewById<ImageView>(R.id.imageViewReceivedImage)

        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
            imageViewReceivedImage.setImageURI(it);
        }
    }
}