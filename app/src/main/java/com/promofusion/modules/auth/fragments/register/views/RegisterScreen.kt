package com.promofusion.modules.auth.fragments.welcome.views

import android.util.Patterns
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.promofusion.R
import com.promofusion.common.components.HeaderTitle
import com.promofusion.common.theme.PromoFusionTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.promofusion.modules.auth.fragments.login.viewmodels.LoginViewModel
import com.promofusion.modules.auth.fragments.login.viewmodels.RegisterViewModel
import com.promofusion.modules.auth.navigations.models.AuthNavigation


@Composable
fun RegisterScreen(navController: NavController) {
    val logoPainter: Painter = painterResource(id = R.drawable.ic_welcome_iamge)
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val viewModel = viewModel<RegisterViewModel>()
    viewModel.setAppContext(LocalContext.current)
    viewModel.setNavController(navController)

    fun validateInputs(): Boolean {
        val emailPattern = Patterns.EMAIL_ADDRESS

        // Validate email
        if (email.isEmpty() || !emailPattern.matcher(email).matches()) {
            // Handle invalid email
            // You can show an error message or perform any other action
            return false
        }

        // Validate password (you can add more conditions based on your requirements)
        if (password.isEmpty() || password.length < 6) {
            // Handle invalid password
            // You can show an error message or perform any other action
            return false
        }

        return true
    }


    LazyColumn(modifier = Modifier.fillMaxWidth()) {

        item {
            HeaderTitle(title = "Register",
                description = "Get started with PromoFusion",
                action = {},modifier = Modifier.padding(24.dp, 12.dp)
            )


            Image(
                painter = logoPainter, "logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 34.dp)
            )
        }
        item {
            TextField(
                value = email,
                onValueChange = {
                    email = it },
                label = { Text("Email") },
                placeholder = { Text("Email") },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "Search Icon"
                    )
                },
                modifier = Modifier
                    .padding(24.dp, 12.dp)
                    .fillMaxWidth()
                    .clip(shape = MaterialTheme.shapes.extraLarge)
                    .border(2.dp, Color.Gray, MaterialTheme.shapes.extraLarge)
            )


            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_keyhole_line),
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                },visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .padding(24.dp, 12.dp)
                    .fillMaxWidth()
                    .clip(shape = MaterialTheme.shapes.extraLarge)
                    .border(2.dp, Color.Gray,  MaterialTheme.shapes.extraLarge)
            )

        }

        item {
            Button(modifier = Modifier
                .padding(24.dp, 12.dp)
                .fillMaxWidth()
                .height(54.dp),
                enabled = validateInputs() && !viewModel.pending.value,
                onClick = {
                    viewModel.handleOnSubmit(email, password)
                }
            ) {
                Text(text = if (viewModel.pending.value) "Register ..." else "Register" )
            }
        }
        item {
            Row(modifier = Modifier.fillMaxWidth()
                .padding(24.dp, 6.dp),
            ) {
                Text(text = "Already has an account.")
                Text(text = "Sign In Now",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 4.dp).clickable {
                        navController.navigate(AuthNavigation.SignIn.route)
                    },
                    fontWeight = FontWeight.Bold,
                )
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    PromoFusionTheme {
        RegisterScreen(navController = NavController(LocalContext.current))
    }
}
