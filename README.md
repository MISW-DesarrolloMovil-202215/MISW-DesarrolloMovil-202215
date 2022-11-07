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
    implementation 'com.github.bumptech.glide:glide:4.14.2'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.14.2'
}

Asegurarse que en Android/buildFeatures , este configurado a :

buildFeatures {

    viewBinding true
    dataBinding true
}

## Ejecución de pruebas E2E con Espresso 
Para la ejecución de pruebas seleccione la opción de 'Edit configurations...'

![image](https://user-images.githubusercontent.com/66291589/200235320-9635b0bd-9743-472d-b323-5881465575ac.png)

Seguido, seleccione el modulo de la aplicación VinilosApp y de click en Ok.
![image](https://user-images.githubusercontent.com/66291589/200235475-8ac1b0ab-8e4a-4864-9638-db34a2640051.png)

Finalmente, podrá ejecutar todas las pruebas del mismo modo en que corre la aplicación, seleccionando el botón verde de ejecución 'Run'.


## APK
Descomprimir e instalar.
[VinilosApp.zip](https://github.com/MISW-DesarrolloMovil-202215/MISW-DesarrolloMovil-202215/files/9948271/VinilosApp.zip)

## Releases
[V1.0.0](https://github.com/MISW-DesarrolloMovil-202215/MISW-DesarrolloMovil-202215/releases/tag/v1.0.0)
