package com.project.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        // 모든 테스트 메서드에 자동으로 Retry 클래스를 설정함
        annotation.setRetryAnalyzer(Retry.class);
    }
}