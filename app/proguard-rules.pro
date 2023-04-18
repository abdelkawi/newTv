# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keepattributes EnclosingMethod

-dontoptimize
-dontshrink

-keepattributes Signature

-keep class com.google.** { *; }
-keep class com.gun0912.** { *; }
-keep class androidx.** { *; }
-keep class me.zhanghai.android.materialratingbar** { *; }
-keep class com.onesignal.** { *; }
-keep class com.android.** { *; }
-keep class android.** { *; }
-keep class com.rilixtech.** { *; }
-keep class io.michaelrocks.libphonenumber.android.** { *; }
-keep class org.junit.** { *; }
-keep class cz.msebera.** { *; }
-keep class com.nostra13.** { *; }
-keep class org.apache.commons.** { *; }
-keep class com.chaos.view.** { *; }
-keep class com.theoplayer.** { *; }
-keep class com.crashlytics.** { *; }
-keep class com.esafirm.** { *; }
-keep class org.jsoup.** { *; }
-keep class com.madx.updatechecker.lib.** { *; }
-keep class com.red5pro.streaming.** { *; }
-keep class de.adorsys.** { *; }
-keep class com.yospace.** { *; }

-dontwarn com.google.**
-dontwarn com.gun0912.**
-dontwarn androidx.**
-dontwarn me.zhanghai.android.materialratingbar**
-dontwarn com.onesignal.**
-dontwarn com.android.**
-dontwarn android.**
-dontwarn com.rilixtech.**
-dontwarn io.michaelrocks.libphonenumber.android.**
-dontwarn org.junit.**
-dontwarn cz.msebera.**
-dontwarn com.nostra13.**
-dontwarn org.apache.commons.**
-dontwarn com.chaos.view.**
-dontwarn com.theoplayer.**
-dontwarn com.crashlytics.**
-dontwarn com.esafirm.**
-dontwarn org.jsoup.**
-dontwarn com.madx.updatechecker.lib.**
-dontwarn com.red5pro.streaming.**
-dontwarn de.adorsys.**
-dontwarn com.yospace.**


-keep class com.scottyab.rootbeer.** { *; }
-dontwarn com.scottyab.rootbeer.**
