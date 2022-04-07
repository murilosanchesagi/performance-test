package com.example.performance

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.acesso.acessobio_android.AcessoBioListener
import com.acesso.acessobio_android.iAcessoBioSelfie
import com.acesso.acessobio_android.onboarding.AcessoBio
import com.acesso.acessobio_android.onboarding.camera.UnicoCheckCameraOpener
import com.acesso.acessobio_android.onboarding.camera.selfie.SelfieCameraListener
import com.acesso.acessobio_android.services.dto.ErrorBio
import com.acesso.acessobio_android.services.dto.ResultCamera
import com.example.performance.ui.theme.PerformanceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    enabled = true,
                    onClick = { initCamera(this@MainActivity) }
                ) {
                    Text(text = "Button")
                }
            }
//            PerformanceTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    DefaultPreview()
//                }
//            }
        }
    }

    private fun initCamera(mainActivity: MainActivity) {
        if (hasPermission(Manifest.permission.CAMERA)) {
            initUnicoCamera(mainActivity)
        } else {
            requestPermission(Manifest.permission.CAMERA, 100)
        }
    }

    private fun initUnicoCamera(mainActivity: MainActivity) {
        AcessoBio(mainActivity, object : AcessoBioListener {
            override fun onErrorAcessoBio(p0: ErrorBio?) {
                Log.d("CAMERA", "onErrorAcessoBio")
            }

            override fun onUserClosedCameraManually() {
                Log.d("CAMERA", "onUserClosedCameraManually")
            }

            override fun onSystemClosedCameraTimeoutSession() {
                Log.d("CAMERA", "onSystemClosedCameraTimeoutSession")
            }

            override fun onSystemChangedTypeCameraTimeoutFaceInference() {
                Log.d("CAMERA", "onSystemChangedTypeCameraTimeoutFaceInference")
            }
        })
            .setTimeoutSession(40.0)
            .build()
            .prepareSelfieCamera("json-service.json", object : SelfieCameraListener {
                override fun onCameraReady(p0: UnicoCheckCameraOpener.Selfie) {
                    p0.open(object : iAcessoBioSelfie {
                        override fun onSuccessSelfie(p0: ResultCamera?) {
                            Log.d("CAMERA", "onSuccessSelfie")
                        }

                        override fun onErrorSelfie(p0: ErrorBio?) {
                            Log.d("CAMERA", "onErrorSelfie")
                        }
                    })
                }

                override fun onCameraFailed(p0: String?) {
                    Log.d("CAMERA", "onCameraFailed")
                }
            })
    }
}


@Composable
fun Sample() {
//    ComposeList()
    CollapsingToolbarSample()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PerformanceTheme {
        Sample()
    }
}