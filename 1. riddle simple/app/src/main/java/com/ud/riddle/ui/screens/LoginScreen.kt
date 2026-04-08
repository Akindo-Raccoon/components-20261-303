package com.ud.riddle.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun LoginScreen(loginClick: (String, String) -> Unit){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

     var auth: FirebaseAuth = Firebase.auth

    Column() {

        Text("Input a email")
        TextField(
            label = { Text("email")},
            value = email,
            onValueChange = {
                email = it
            }
        )

        Spacer(modifier = Modifier.padding(2.dp))

        Text("Input a password")

        TextField(
            label = { Text("password")},
            value = password,
            onValueChange = {
                password = it
            }
        )

        Button(onClick = { loginClick(email, password)}) {
            Text("Log in")
        }
    }
}

@Preview
@Composable
fun ShowLoginScreen(){
    LoginScreen(loginClick = { email, passwoard -> {}})
}