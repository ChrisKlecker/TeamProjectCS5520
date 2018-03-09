/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamExerciseCSCI5520;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author David Klecker
 */
@Named(value = "javaExercises")
@SessionScoped
public class JavaExercises implements Serializable {

    /**
     * Creates a new instance of JavaExercises
     */
    public JavaExercises() {
    }
    
}
