# MISW-DesarrolloMovil-202215

# VinilosAPP_G18
Vinilos es una aplicación para dispositivos Android para explorar tus gustos musicales , en este 1er reléase, encontra funcionalidades como listar álbumes y consultar el detalle del álbum seleccionado.
La aplicación utiliza conectividad a internet así que asegúrese de tener una conexión  activa

## IDE de desarrollo
Android Studio Dolphin | 2021.3.1

## Lenguaje de desarrollo
Kotlin

## En el build.gradle (app)
Para asegurar que la aplicación pueda ejecutarse de manera correcta, la versiones utilizadas en las dependencias fueron:

dependencies {

    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.5.1'
    implementation 'com.google.android.material:material:1.7.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'com.android.volley:volley:1.2.1'
    implementation 'androidx.navigation:navigation-fragment:2.5.3'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.4-rc01'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.0-rc01'
    androidTestImplementation 'androidx.test:runner:1.1.0'
    androidTestImplementation 'androidx.test:rules:1.5.0-rc01'
    androidTestImplementation 'androidx.test.espresso:espresso-contrib:3.5.0-rc01'
    implementation 'de.hdodenhof:circleimageview:3.1.0'
}

Asegurarse que en Android/buildFeatures , este configurado a :
buildFeatures {
    viewBinding true
    dataBinding true
}
