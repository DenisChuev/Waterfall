package dc.waterfall

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig.EmailBuilder
import com.firebase.ui.auth.AuthUI.IdpConfig.GoogleBuilder
import com.google.firebase.auth.FirebaseAuth

val providers = listOf(
    EmailBuilder().build(),
    GoogleBuilder().build()
)

class LoginActivity : ComponentActivity() {

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth: FirebaseAuth = FirebaseAuth.getInstance();

        auth.addAuthStateListener {
            if (it.currentUser != null) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                showAuthUI(this)
            }

        }

    }

    private fun showAuthUI(context: Context) {
        val authUi = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        resultLauncher.launch(Intent(authUi))
    }
}