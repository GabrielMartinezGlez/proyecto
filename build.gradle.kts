// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
<<<<<<< HEAD
    //id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
=======
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
>>>>>>> 4a2c94e902e05916090ba4cc160161fc3b2dd4ae
}