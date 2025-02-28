package es.uc3m.android.fintasy

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.uc3m.android.fintasy.ui.theme.FintaSyTheme

import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.annotation.StringRes


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FintaSyTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .background(Black)
                ) { innerPadding ->
                    MyLayout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: Painter? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier,
    @StringRes hint: Int
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = stringResource(id = hint), color = MaterialTheme.colorScheme.primary) },
        leadingIcon = leadingIcon?.let {
            { Icon(painter = it, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(8.dp)) }
        },
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.primary,
            disabledTextColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary, // Color del cursor
            focusedIndicatorColor = MaterialTheme.colorScheme.primary, // Sin borde cuando está en foco
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary, // Sin borde cuando no está en foco
            disabledIndicatorColor = MaterialTheme.colorScheme.primary, // Sin borde cuando está deshabilitado
        )
    )
}


@Composable
fun MyLayout(modifier: Modifier = Modifier) {
    // State variables for text fields
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Row(modifier = modifier.fillMaxSize()) {
        // Left spacer (15% width)
        Spacer(
            modifier = Modifier
                .weight(0.15f)
                .fillMaxHeight()
        )
        // Middle content (70% width)
        Box(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxHeight()
        ) {
            Column(modifier = modifier.padding(top = 5.dp)) {
                Spacer(modifier = modifier.fillMaxHeight(0.01f))
                Text(
                    text = stringResource(R.string.welcome_label),
                    //modifier = Modifier.padding(top = 5.dp),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
                Text(
                    text = stringResource(R.string.caption_missed_you),
                    // modifier = Modifier.padding(bottom = 16.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
                Spacer(modifier = modifier.fillMaxHeight(0.05f))
                Row(modifier = Modifier.align(alignment = Alignment.Start)){
                    Text(
                    text = stringResource(R.string.not_a_member),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.weight(0.7f))
                    Text(
                    text = stringResource(R.string.create_account),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clickable {
                        println("TODO sign up")
                    }
                    )
                }
                /*Text(
                    text = stringResource(R.string.email_edit_text),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(alignment = Alignment.Start)
                )*/
                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    hint = R.string.email_edit_text,
                    leadingIcon = painterResource(id = R.drawable.baseline_mail_outline_24), // Si tienes un ícono
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.align(alignment = Alignment.Start)

                )
                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    hint = R.string.password_edit_text,
                    leadingIcon = painterResource(id = R.drawable.baseline_vpn_key_24), // Si tienes un ícono
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier.align(alignment = Alignment.Start),
                    // visualTransformation = PasswordVisualTransformation()

                )
                Button(
                    onClick = {
                        println("TODO login")
                    },
                    modifier = Modifier
                        .align(alignment = Alignment.End)
                        .padding(top = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(stringResource(R.string.login_button_text))
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                            contentDescription = stringResource(R.string.login_button_text),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .fillMaxHeight(0.2f)
            ) {
                Text(
                    text = stringResource(R.string.don_t_have_an_account_sign_up),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .clickable {
                            println("TODO sign up")
                        },
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        // Right spacer (15% width)
        Spacer(
            modifier = Modifier
                .weight(0.15f)
                .fillMaxHeight()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyLayoutPreview() {
    FintaSyTheme {
        MyLayout()
    }
}

