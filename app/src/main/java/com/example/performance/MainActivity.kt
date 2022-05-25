package com.example.performance

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.acesso.acessobio_android.AcessoBioListener
import com.acesso.acessobio_android.iAcessoBioSelfie
import com.acesso.acessobio_android.onboarding.AcessoBio
import com.acesso.acessobio_android.onboarding.camera.UnicoCheckCameraOpener
import com.acesso.acessobio_android.onboarding.camera.selfie.SelfieCameraListener
import com.acesso.acessobio_android.services.dto.ErrorBio
import com.acesso.acessobio_android.services.dto.ResultCamera
import com.example.performance.ui.theme.PerformanceTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    val test = MutableStateFlow("Teste")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TEST_MEMORY", "onCreate: ${this@MainActivity}")
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
                    onClick = {
                        lifecycleScope.launch {
                            test.emit("Teste")
                        }
                        initCamera(this@MainActivity)
                    }
                ) {
                    Text(text = test.collectAsState().value)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("TEST_MEMORY", "onResume: ${this@MainActivity}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TEST_MEMORY", "onDestroy: ${this@MainActivity}")
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
                lifecycleScope.launch {
                    test.emit(getString(R.string.test_string))
                }
                Log.d("TEST_MEMORY", "onUserClosedCameraManually: ${this@MainActivity}")
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
                            lifecycleScope.launch {
                                test.emit(getString(R.string.test_string_2))
                            }
                            Log.d("TEST_MEMORY", "onSuccessSelfie: ${this@MainActivity}")
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