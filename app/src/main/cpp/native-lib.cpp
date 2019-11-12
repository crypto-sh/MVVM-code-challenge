#include <jni.h>
#include <string>

extern "C"
jstring
Java_com_mobiquity_utils_Apps_secretKey(
        JNIEnv *env,
        jobject /* this */) {
    std::string publicKey= "we can hold important string key here";
    return env->NewStringUTF(publicKey.c_str());
}


