    package com.example.laboratorio6_pm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio6_pm.ui.theme.Laboratorio6_PMTheme

    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                Laboratorio6_PMTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Login()
                    }
                }
            }
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    //passwordValue
    //usernameValue
    fun Login() {
        var usernameValue by remember {
            mutableStateOf("")
        }
        var passwordValue by remember {
            mutableStateOf("")
        }
        val Correo = "123@gmail.com"
        val Contrasena = "123"
        var text by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        val context = LocalContext.current


        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.fondoplaya),
                contentDescription = "login",
                modifier = Modifier
                    .fillMaxSize()
                    .blur(6.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(28.dp)
                    .alpha(0.6f)
                    .clip(
                        CutCornerShape(
                            topStart = 8.dp,
                            topEnd = 16.dp,
                            bottomStart = 16.dp,
                            bottomEnd = 8.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.background)
            )
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                LoginHeader()

                LoginFields(
                    usernameValue,
                    passwordValue,
                    onUsernameChange = {
                        usernameValue = it
                    },
                    onPasswordChange = {
                        passwordValue = it
                    },
                    onForgotPasswordClick = {

                    }
                )

                LoginFooter(
                    onSignInClick = {
                        //passwordValue
                        //usernameValue
                        if (usernameValue == Correo && passwordValue == Contrasena) {
                            val prueba = Intent(context, Galeria::class.java)
                            prueba.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            context.startActivity(prueba)
                        }else{
                            Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                        }

                    },
                    onSignUpClick = {}
                )
            }
        }
    }

    @Composable
    fun LoginHeader() {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome Back", fontSize = 36.sp, fontWeight = FontWeight.ExtraBold)
            Text(
                text = "Sign in to continue",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }

    @Composable
    fun ColumnScope.LoginFields(
        username: String,
        password: String,
        onUsernameChange: (String) -> Unit,
        onPasswordChange: (String) -> Unit,
        onForgotPasswordClick: () -> Unit
    ) {
        DemoField(
            value = username,
            label = "Username",
            placeholder = "Enter your email address",
            onValueChange = onUsernameChange,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        DemoField(
            value = password,
            label = "Password",
            placeholder = "Enter your Password",
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password")
            }
        )

        TextButton(onClick = onForgotPasswordClick, modifier = Modifier.align(Alignment.End)) {
            Text(text = "Forgot Password?")
        }
    }

    @Composable
    fun LoginFooter(
        onSignInClick: () -> Unit,
        onSignUpClick: () -> Unit
    ) {
        Button(onClick = onSignInClick, modifier = Modifier.fillMaxWidth(), ) {

            Text(text = "Ingresar")


        }
        TextButton(onClick = onSignUpClick) {
            Text(text = "Don't have an account? Click here")
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DemoField(
        value: String,
        label: String,
        placeholder: String,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        onValueChange: (String) -> Unit
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(text = label)
            },
            placeholder = {
                Text(text = placeholder)
            },
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Laboratorio6_PMTheme {
            Login()
        }
    }
