#include <jni.h>
#include <string>


extern "C"
JNIEXPORT void JNICALL
Java_example_orbianta_androidmemoryleak_MainActivity_leak_1memory(JNIEnv *env, jobject thiz) {
    while(true){
        malloc(8*3*500);
    }

}