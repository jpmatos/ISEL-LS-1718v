package pt.isel.ls.utils;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;

public class ClassFinder {

    public static Object findClass(String packageToSearch, Class<? extends Annotation> annotation, String annotationField, String equal) {
        Reflections reflections = new Reflections(packageToSearch);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(annotation);
        String annotationValue = null;
        Annotation ann;
        for (Class <?> klass: classes) {
            ann = klass.getAnnotations()[0];
            try {
                annotationValue = (String)ann
                        .annotationType()
                        .getDeclaredMethod(annotationField, (Class<?>[]) null)
                        .invoke(ann, (Object[]) null);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (annotationValue.equals(equal)){
                try {
                    return klass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static <T> Iterable<T> findClasses (String packageToSearch, Class<? extends Annotation> annotation){
        Reflections reflections = new Reflections(packageToSearch);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(annotation);
        ArrayList<T> res = new ArrayList();
        for (Class <?> klass:classes) {
            try {
                res.add((T)klass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

}
